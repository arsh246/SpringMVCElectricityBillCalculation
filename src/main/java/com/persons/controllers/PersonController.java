package com.persons.controllers;


import com.persons.beans.Person;
import com.persons.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.Objects;

@Controller
@SessionAttributes("person")
public class PersonController {
    PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @ModelAttribute("person")
    public Person populatePerson() {
        return new Person();
    }

    @GetMapping("/personsearchform")
    public String searchForm() {
        return "personsearchform";
    }

    @PostMapping("/calculateAmount")
    public ModelAndView calculateAmount(@ModelAttribute("person") Person person) {
        ModelAndView mav= null;
        Person person1= null;
        try {
            if(person.getName()!=null&& !Objects.equals(person.getName(), "")) {
                person1 = personDAO.getPersonByName(person.getName());
            }
            if(person.getServiceNumber()!=null&& !Objects.equals(person.getServiceNumber(), "")) {
                person1 = personDAO.getPersonByServiceNumber(person.getServiceNumber());
            }
            mav = new ModelAndView("welcome");
            if(null!=person1) {
                System.out.println(person1.getId()+"..."+person1.getName()+"..."+person1.getServiceNumber()
                +"..consumed units.."+person1.getConsumedUnits());
                boolean isAvailable = false;
                int consumedUnits = person1.getConsumedUnits();
                int bill = getBill(consumedUnits);

                System.out.println("Bill:  "+bill);
                mav.addObject("ID", person1.getId());
                mav.addObject("name", person1.getName());
                mav.addObject("serviceNumber", person1.getServiceNumber());
                mav.addObject("consumedUnits", consumedUnits);
                mav.addObject("bill", bill);


            }
            else  {
                mav.addObject("bill", "Not present in the database");
            }
        }catch (SQLException e) {
            System.out.println("SQLException: "+e);
        }
        return mav;

    }

    private static int getBill(int consumedUnits) {
        int bill = 0;
        if(consumedUnits <=100){
            bill = consumedUnits *10;
        }
        if(consumedUnits <=200){
            bill = (100*10)+(consumedUnits -100)*15;
        }
        if(consumedUnits <=300){
            bill = (100*10)+(100*15)+(consumedUnits -200)*20;
        }
        if(consumedUnits >300){
            bill = (100*10)+(100*15)+(100*20)+(consumedUnits -300)*25;
        }
        return bill;
    }
}
