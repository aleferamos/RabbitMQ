package br.com.alefe.experiementos.repository;

import br.com.alefe.experiementos.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {
    List<Person> personList = new ArrayList<>();

    public List<Person> findAll(){
        return personList;
    }

    public Person save(Person person){
        personList.add(person);
        return person;
    }
}
