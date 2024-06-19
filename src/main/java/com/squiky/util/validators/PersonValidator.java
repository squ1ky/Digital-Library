package com.squiky.util.validators;

import com.squiky.models.Person;
import com.squiky.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        Optional<Person> personInStorage = peopleService.findByFullNameAndYearOfBirth(
                person.getFullName(),
                person.getYearOfBirth()
        );

        if (personInStorage.isPresent()) {
            errors.rejectValue("fullName", "", "This person is already contained in the library");
        }
    }
}
