package com.docker.restcrud.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.docker.restcrud.models.Course;
import com.docker.restcrud.models.Student;
import com.docker.restcrud.services.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/getAllStudents")
	public List<Student> getAllStudents() {
	    return studentService.getAllStudents();
	}
	
	@PostMapping("/createStudent")
	public Student createStudent(@Valid @RequestBody Student student) {
		return studentService.saveStudent(student);
	}
	
	@GetMapping("/getStudentById/{studentid}")
	public Student getCourseById(@PathVariable("studentid") Long studentid) {
		return studentService.findStudentById(studentid).orElseThrow(() -> new IllegalStateException(
		          "No Student found with ID : " + studentid));
	}
	
	@GetMapping("/student/{studentid}/getCourses")
	public List<Course> retrieveCoursesForStudent(@PathVariable("studentid") Long studentid) {
		Student student = studentService.findStudentById(studentid).orElseThrow(() -> new IllegalStateException(
		          "No Student found with ID : " + studentid));
		return student.getCourses();
	}
	
	@GetMapping("/student/{studentid}/getCourse/{courseid}")
	public Course retrieveDetailsForCourse(@PathVariable("studentid") Long studentid,
			@PathVariable("courseid") Long courseid) {
		Student student = studentService.findStudentById(studentid).orElseThrow(() -> new IllegalStateException(
		          "No Student found with ID : " + studentid));
		for (Course course : student.getCourses()) {
			if (course.getId().equals(courseid)) {
				return course;
			}
		}
		return null;
	}
	
	@PostMapping("/student/{studentid}/addCourse")
	public ResponseEntity<Void> registerStudentForCourse(
			@PathVariable("studentid") Long studentid, @RequestBody Course newcourse) {

		if (newcourse == null)
			return ResponseEntity.noContent().build();
		Student student = studentService.findStudentById(studentid).orElseThrow(() -> new IllegalStateException(
		          "No Student found with ID : " + studentid));
		student.getCourses().add(newcourse);
		studentService.saveStudent(student);

		

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(newcourse.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/student/{studentid}/addCourses")
	public Student registerStudentForCourses(
			@PathVariable("studentid") Long studentid, @RequestBody List<Course> newcourses) {

		Student student = studentService.findStudentById(studentid).orElseThrow(() -> new IllegalStateException(
		          "No Student found with ID : " + studentid));
		student.getCourses().addAll(newcourses);
		return studentService.saveStudent(student);
	}
	
	@PutMapping("/updateStudentById/{studentid}")
	public Student updateCourseById(@PathVariable("studentid") Long studentid, @Valid @RequestBody Student updatedstudent) {
		Student student = studentService.findStudentById(studentid).orElseThrow(() -> new IllegalStateException(
		          "No Student found with ID : " + studentid));
		student.setName(updatedstudent.getName());
		student.setDescription(updatedstudent.getDescription());
		student.setCourses(updatedstudent.getCourses());
		
		return studentService.saveStudent(student);
	}
	
	@DeleteMapping("/deleteStudentById/{studentid}")
	public ResponseEntity<?> deleteNote(@PathVariable("studentid") Long studentid) {
		Student student = studentService.findStudentById(studentid).orElseThrow(() -> new IllegalStateException(
		          "No Student found with ID : " + studentid));

	    studentService.deleteStudent(student);

	    return ResponseEntity.ok().build();
	}

}
