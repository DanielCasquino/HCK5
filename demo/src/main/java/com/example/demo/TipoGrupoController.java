package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/types")
public class TipoGrupoController {
  @Autowired
  private TipoGrupoRepository tipoGrupoRepository;

  // tipos
  @GetMapping
  public ResponseEntity<List<TipoGrupo>> tipos() {
    List<TipoGrupo> tipos = tipoGrupoRepository.findAll();
    return new ResponseEntity<>(tipos, HttpStatus.OK);
  }

  // tipos/x
  @GetMapping("/{id}")
  public ResponseEntity<TipoGrupo> getTipo(@PathVariable Long id) {
    Optional<TipoGrupo> query = tipoGrupoRepository.findById(id);
    return new ResponseEntity<>(query.get(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<String> createTipo(@RequestBody TipoGrupo person) {
    tipoGrupoRepository.save(person);
    return ResponseEntity.status(201).body("Type created");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteTipo(@PathVariable Long id) {

    return ResponseEntity.status(201).body("Type created");
  }
}
