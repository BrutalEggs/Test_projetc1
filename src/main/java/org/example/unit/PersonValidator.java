package org.example.unit;

import org.example.DAO.PersonDAO;
import org.example.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * created by dmitry.fateev
 * 07.06.2022
 */
@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if (personDAO.showPerson(person.getName()) != null) {
            errors.rejectValue("name", "", "This name is already taken");
        }

    }

}
