package br.com.alefe.experiementos.controller;

import br.com.alefe.experiementos.model.Person;
import br.com.alefe.experiementos.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {
    private final PersonService service;

    @PostMapping
    ResponseEntity<Person> save(@RequestBody Person person){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(person));
    }

    @GetMapping
    ResponseEntity<List<Person>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/getByName")
    ResponseEntity<List<Person>> getByName(@RequestParam String name){
        return ResponseEntity.ok(service.findByName(name));
    }
}
