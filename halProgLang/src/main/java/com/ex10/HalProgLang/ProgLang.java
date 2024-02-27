package com.ex10.HalProgLang;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table (name = "ProgrammingLanguage")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProgLang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String name;

    private int firstAppearance;

    @NonNull
    private String inventor;
}
