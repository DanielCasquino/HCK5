package com.example.demo;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "persona")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "grupos_personas", joinColumns = @JoinColumn(name = "persona_id"), inverseJoinColumns = @JoinColumn(name = "grupos_id"))
    @JsonIgnore
    private Set<Grupo> grupos;

    public Person() {
    }

    public Person(Long id, String name, Set<Grupo> grupos) {
        this.id = id;
        this.name = name;
        this.grupos = grupos;
    }

    public String getName() {
        return name;
    }

    public Set<Grupo> getGrupos() {
        return grupos;
    }

    public Long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGrupos(Set<Grupo> grupos) {
        this.grupos = grupos;
    }
}