package com.ex11.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Student newStudent(Student student) {
        return studentRepository.saveAndFlush(student);
    }

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public Student findOne (Long id){
        Optional <Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }

//    public static Student newId(Long id, Student student) {
//        if (student != null) {
//            student.setId(id);
//            return studentRepository.saveAndFlush(student);
//        }
//        return null;
//    }
    public Student updateStatus (Long id,boolean isWorking) {
        Optional<Student>workingStudent = studentRepository.findById(id);
        if (workingStudent.isPresent()) {
            workingStudent.get().setIsWorking(isWorking);
            return studentRepository.save(workingStudent.get());
        }
        return null;
    }

    public void deletedById (Long id) {
        studentRepository.deleteById(id);
    }
}
