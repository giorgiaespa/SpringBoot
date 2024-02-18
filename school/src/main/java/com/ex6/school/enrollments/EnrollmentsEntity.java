package com.ex6.school.enrollments;

import com.ex6.school.classes.ClassesEntity;
import com.ex6.school.student.StudentEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity

public class EnrollmentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    private List <ClassesEntity> classes;

    @OneToMany
    private List <StudentEntity> students;

}