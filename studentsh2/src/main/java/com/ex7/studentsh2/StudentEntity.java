package com.ex7.studentsh2;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
    private String firstname;

    @NonNull
    private String lastname;

    @Column(unique = true)
    private String email;
}
