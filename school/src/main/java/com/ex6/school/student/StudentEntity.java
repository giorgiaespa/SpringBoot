package com.ex6.school.student;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@Table
@Entity
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String lastName;

    @NonNull
    private String firstName;

    @NonNull
    @Column(unique = true)
    public String email;
}
