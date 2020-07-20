package com.docker.restcrud.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docker.restcrud.models.Student;
import com.docker.restcrud.repositories.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public Student saveStudent(@Valid Student student) {
		return studentRepository.save(student);
	}

	public Optional<Student> findStudentById(Long studentid) {
		return studentRepository.findById(studentid);
	}

	public void deleteStudent(Student student) {
		studentRepository.delete(student);
	}

}
