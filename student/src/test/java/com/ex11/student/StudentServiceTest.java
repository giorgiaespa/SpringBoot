package com.ex11.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void checkStudentService () throws Exception {
        Student mockStudent = new Student();
        mockStudent.setName("Zachary");
        mockStudent.setSurname("Johnson");
        mockStudent.setIsWorking(true);

        studentRepository.saveAndFlush(mockStudent);
        assertThat(mockStudent.getId()).isNotNull();

        Student student = studentService.updateStatus(mockStudent.getId(), true);
        assertThat(student.getId()).isNotNull();
        assertThat(student.getIsWorking()).isTrue();
    }
}
