package com.ex11.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public Student updateStatus (Long id,boolean isWorking) {
        Optional<Student>workingStudent = studentRepository.findById(id);
        if (workingStudent.isPresent()) {
            workingStudent.get().setIsWorking(isWorking);
            return studentRepository.save(workingStudent.get());
        }
        return null;
    }
}
