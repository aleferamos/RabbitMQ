package br.com.alefe.experiementos;

import br.com.alefe.experiementos.model.Person;
import br.com.alefe.experiementos.repository.PersonRepository;
import br.com.alefe.experiementos.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MockitoSettings
@RequiredArgsConstructor
@ExtendWith(OutputCaptureExtension.class)
class TestServicePerson {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonService service;

    private final CapturedOutput output;

    @Test
    void shouldReturnAPersonListAndLogWhenCallGetAllMethod() {
        when(repository.findAll()).thenReturn(List.of(
                Person.builder()
                        .nome("Teste1")
                        .idade(10)
                        .build(),

                Person.builder()
                        .nome("Teste2")
                        .idade(25)
                        .build()
        ));

        List<Person> person = service.getAll();

        assertEquals(2, person.size());
        assertTrue(output.getOut().contains("Finding person in database."));
        assertTrue(output.getOut().contains("2 people found."));
    }

    @Test
    void shouldThrowExceptionWhenCallGetAllMethodAndRepositoryReturnsNullList() {
        when(repository.findAll()).thenReturn(null);

        Exception response = assertThrowsExactly(RuntimeException.class, () -> service.getAll());

        assertEquals("There is not people in database.", response.getMessage());
        assertTrue(output.getOut().contains("Finding person in database."));
    }

    @Test
    void shouldThrowExceptionWhenCallGetAllMethodAndRepositoryReturnsEmptyList() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        Exception response = assertThrowsExactly(RuntimeException.class, () -> service.getAll());

        assertEquals("There is not people in database.", response.getMessage());
        assertTrue(output.getOut().contains("Finding person in database."));
    }


    @Test
    void shouldSavePersonWhenCallMethodSave(){
        Person personToSave = Person.builder()
                .nome("Alefe patrick")
                .idade(25)
                .build();

        when(repository.save(personToSave)).thenReturn(personToSave);

        Person response = service.save(personToSave);

        assertTrue(output.getOut().contains("Saving person: Alefe patrick"));
        assertThat(response).isEqualTo(personToSave);
        verify(repository).save(personToSave);
    }

    @Test
    void shouldThrowExceptionWhenPersonToSaveIsNull(){
        Exception exception = assertThrowsExactly(RuntimeException.class, () -> service.save(null));

        assertEquals("Person who will save can't be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAgeIsLessThan18(){
        Person personToSave = Person.builder()
                .nome("Teste patrick")
                .idade(17)
                .build();

        Exception response = assertThrowsExactly(RuntimeException.class, () -> service.save(personToSave));

        assertEquals("Person age is less then 18.", response.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameNotContainsSurname(){
        Person personToSave = Person.builder()
                .nome("Teste")
                .idade(18)
                .build();

        Exception response = assertThrowsExactly(RuntimeException.class, () -> service.save(personToSave));

        assertEquals("Person name is invalid.", response.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameContainsInvalidCaracteres(){
        Person personToSave = Person.builder()
                .nome("Teste")
                .idade(18)
                .build();

        Exception response = assertThrowsExactly(RuntimeException.class, () -> service.save(personToSave));

        assertEquals("Person name is invalid.", response.getMessage());
    }

    @Test
    void shouldFindTwoPersonByNameWhenNameStartsWithPe(){
        List<Person> personListFound = List.of(
                Person.builder()
                        .nome("Alefe")
                        .build(),

                Person.builder()
                        .nome("Pedro")
                        .build(),

                Person.builder()
                        .nome("Peixoto")
                        .build()
        );

        when(repository.findAll()).thenReturn(personListFound);

        List<Person> response = service.findByName("Pe");

        assertEquals(2, response.size());
    }

    @Test
    void shouldThrowExceptionWhenFoundListIsEmpty(){
        List<Person> personListFound = List.of(
                Person.builder()
                        .nome("Alefe")
                        .build(),

                Person.builder()
                        .nome("Pedro")
                        .build(),

                Person.builder()
                        .nome("Peixoto")
                        .build()
        );

        when(repository.findAll()).thenReturn(personListFound);

        Exception response = assertThrowsExactly(RuntimeException.class, () -> service.findByName("Be"));

        assertEquals("Person not found with initials: Be", response.getMessage());
    }

    @Test
    void shouldDeleteWhenContainsPersonName(){
        List<Person> personListFound = List.of(
                Person.builder()
                        .nome("Alefe")
                        .build(),

                Person.builder()
                        .nome("Pedro")
                        .build(),

                Person.builder()
                        .nome("Peixoto")
                        .build()
        );

        when(repository.findAll()).thenReturn(personListFound);

        List<Person> personList = service.deleteByName("Alefe");

        assertEquals(2,personList.size());
    }
}