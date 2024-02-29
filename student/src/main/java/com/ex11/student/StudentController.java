package com.ex11.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @PostMapping("/newStudent")
    private @ResponseBody Student createStudent(@RequestBody Student student) {
        return studentRepository.saveAndFlush(student);
    }

    @GetMapping("/getAll")
    private @ResponseBody List<Student>readAll (){
        return studentRepository.findAll();
    }

    @GetMapping("/getById/{id}")
    private @ResponseBody Student readStudent (@PathVariable Long id){
        Optional <Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }

    @PatchMapping("/updatePK/{id}")
    private @ResponseBody Student updateId (
            @PathVariable long id,
            @RequestBody Student student
    ) {
        if (student != null) {
            student.setId(id);
             return studentRepository.saveAndFlush(student);
        }
        return null;
    }

    @PatchMapping("/{id}/updateStatus")
    private Student isWorking (
            @PathVariable long id,
            @RequestParam boolean working
    ){
        return studentService.updateStatus(id, working);
    }

    @DeleteMapping("/delete/{id}")
    private void deleteById (@PathVariable Long id) {
        studentRepository.deleteById(id);
    }




}
