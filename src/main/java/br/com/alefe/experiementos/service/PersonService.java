package br.com.alefe.experiementos.service;

import br.com.alefe.experiementos.model.Person;
import br.com.alefe.experiementos.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;

    public List<Person> getAll() {
        log.info("Finding person in database.");
        List<Person> personListFound = repository.findAll();

        if (CollectionUtils.isEmpty(personListFound)) {
            throw new RuntimeException("There is not people in database.");
        }

        log.info("{} people found.", personListFound.size());
        return personListFound;
    }

    public Person save(Person person) {
        personValidation(person);

        log.info("Saving person: {}", person.getNome());
        return repository.save(person);
    }

    public List<Person> findByName(String name) {
        List<Person> personFoundList = repository.findAll()
                .stream()
                .filter(person -> person.getNome().contains(name))
                .toList();

        if (CollectionUtils.isEmpty(personFoundList)) {
            throw new RuntimeException(String.format("Person not found with initials: %s", name));
        }

        return personFoundList;
    }

    public List<Person> deleteByName(String name) {
        return repository.findAll().stream().filter(person -> !person.getNome().equals(name)).toList();
    }

    private void personValidation(Person person) {
        if (Objects.isNull(person)) {
            throw new RuntimeException("Person who will save can't be null");
        }

        if (person.getIdade() < 18) {
            throw new RuntimeException("Person age is less then 18.");
        }

        var pattern = Pattern.compile("^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$");
        var matcher = pattern.matcher(person.getNome());


        if (matcher.matches()) {
            throw new RuntimeException("Person name is invalid.");
        }
    }
}
