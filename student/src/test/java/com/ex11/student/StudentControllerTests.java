package com.ex11.student;


import static org.assertj.core.api.Assertions.assertThat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class StudentCrudTests {

	@Autowired
	private StudentController studentController;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private Student createStudent() throws Exception {
		Student mockStudent = new Student();
		mockStudent.setName("Zachary");
		mockStudent.setSurname("Johnson");
		mockStudent.setIsWorking(true);

		return createStudentb(mockStudent);
	}

	private Student createStudentb(Student mockStudent) throws Exception {
		MvcResult mvcResult = createStudentRequest(mockStudent);
		Student idk = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Student.class);
		assertThat(idk.getId()).isNotNull();
		assertThat(idk).isNotNull();
		return idk;
	}

	private MvcResult createStudentRequest(Student mockStudent) throws Exception {
		if (mockStudent == null) return null;
		String studentJSON = objectMapper.writeValueAsString(mockStudent);

		return this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/newStudent")
						.contentType(MediaType.APPLICATION_JSON)
						.content(studentJSON)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
	}

	private Student readById(Long id) throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/v1/getById/" + id))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();


		if (mvcResult.getResponse().getContentLength() == 0) {
			return null;
		}
		String studentJSON = mvcResult.getResponse().getContentAsString();
		return objectMapper.readValue(studentJSON, Student.class);
	}

	@Test
	void checkStudentController() {
		assertThat(studentController).isNotNull();
	}

	@Test
	void createStudentTest() throws Exception {
		Student studentFromResponse = createStudent();
		assertThat(studentFromResponse.getId()).isNotNull();
	}

	@Test
	void readAllTest() throws Exception {
		createStudentRequest(new Student());

		MvcResult mvcResult = this.mockMvc.perform(get("/v1/getAll"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		List<Student> students = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), List.class);
		assertThat(students.size()).isNotZero();

		System.out.println("Students in db are " + students.size());
	}

	@Test
	void readOneTest() throws Exception {
		Student student = createStudent();
		assertThat(student.getId()).isNotNull();

		MvcResult mvcResult = this.mockMvc.perform(get("/v1/getById/" + student.getId()))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		Student student1 = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Student.class);
		;
		assertThat(student1.getId()).isEqualTo(student.getId());
	}

	@Test
	void updateStudentStatusTest() throws Exception {
		Student student = createStudent();
		assertThat(student.getId()).isNotNull();

		Boolean updatedStatus = false;
		student.setIsWorking(updatedStatus);

		String studentJSON = objectMapper.writeValueAsString(student);

		MvcResult mvcResult = this.mockMvc.perform(patch("/v1/" + student.getId() + "/updateStatus?working=false")
						.contentType(MediaType.APPLICATION_JSON)
						.content(studentJSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		Student studentFromResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Student.class);
		assertThat(studentFromResponse.getId()).isEqualTo(student.getId());
		assertThat(studentFromResponse.getIsWorking()).isEqualTo(student.getIsWorking());
	}

	@Test
	void deleteStudentTest() throws Exception {
		Student student = createStudent();
		assertThat(student.getId()).isNotNull();

		this.mockMvc.perform(delete("/v1/delete/" + student.getId()))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		Student studentFR = readById(student.getId());
		assertThat(studentFR).isNull();
	}
}
