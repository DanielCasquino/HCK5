package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    // persons
    @GetMapping
    public ResponseEntity<List<Person>> persons() {
        List<Person> persons = personRepository.findAll();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    // persons/x
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        Optional<Person> query = personRepository.findById(id);
        return new ResponseEntity<>(query.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createPersons(@RequestBody Person person) {
        personRepository.save(person);
        return ResponseEntity.status(201).body("Person created");
    }

    // persons/groups/x
    // grupos a los que pertenece la persona
    @GetMapping("/groups/{id}")
    public ResponseEntity<Set<Test>> getPersonsGroups(@PathVariable Long id) {
        Optional<Person> query = personRepository.findById(id);
        // Si se encuentra a la persona
        if (query.isPresent()) {
            Set<Test> memberIn = query.get().getGrupos();
            return new ResponseEntity<>(memberIn, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
