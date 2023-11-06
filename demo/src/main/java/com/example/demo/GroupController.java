package com.example.demo;

import java.util.List;
import java.util.Set;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public ResponseEntity<List<Test>> groups() {
        List<Test> query = groupRepository.findAll();
        return new ResponseEntity<>(query, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createGroup(@RequestBody Test group) {
        groupRepository.save(group);
        return ResponseEntity.status(201).body("Group created");
    }

    @PutMapping("/{groupId}/{personId}")
    public ResponseEntity<String> subscribePerson(@PathVariable Long groupId, @PathVariable Long personId) {
        Optional<Test> groupQuery = groupRepository.findById(groupId);
        if (groupQuery.isPresent()) {
            Optional<Person> personQuery = personRepository.findById(personId);
            if (personQuery.isPresent()) {
                Set<Test> personGroups = personQuery.get().getGrupos();
                personGroups.add(groupQuery.get()); // Añade el el grupo a la lista de grupos de la persona
                personQuery.get().setGrupos(personGroups); // Establece el contenido de sus grupos
                personRepository.save(personQuery.get());

                Set<Person> personsInGroup = groupQuery.get().getPersons();
                personsInGroup.add(personQuery.get());
                groupQuery.get().setPersons(personsInGroup); // Establece el contenido de sus grupos
                groupRepository.save(groupQuery.get());
                return ResponseEntity.status(201).body("Person " + personId + " enrolled in group " + groupId);
            }
            return ResponseEntity.status(404).body("Person not found");
        }
        return ResponseEntity.status(404).body("Group not found");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Test> getGroup(@PathVariable Long id) {
        Optional<Test> query = groupRepository.findById(id);
        return new ResponseEntity<>(query.get(), HttpStatus.OK);
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Set<Person>> getGroupsPersons(@PathVariable Long id) {
        Optional<Test> query = groupRepository.findById(id);
        if (query.isPresent()) {
            return new ResponseEntity<Set<Person>>(query.get().getPersons(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Set<Person>>(HttpStatus.NOT_FOUND);
        }
    }
}
