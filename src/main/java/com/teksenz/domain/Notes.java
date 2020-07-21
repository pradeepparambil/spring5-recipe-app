package com.teksenz.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String RecipeNotes;
    @OneToOne(mappedBy = "notes")
    private Recipe recipe;
}
