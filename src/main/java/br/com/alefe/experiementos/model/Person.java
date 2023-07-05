package br.com.alefe.experiementos.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {
    private String nome;
    private int idade;
}
