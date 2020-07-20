package com.docker.restcrud.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docker.restcrud.models.Course;
import com.docker.restcrud.repositories.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;

	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	public Course saveCourse(@Valid Course course) {
		return courseRepository.save(course);
	}

	public Optional<Course> findCourseById(Long courseid) {
		return courseRepository.findById(courseid);
	}

	public void deleteCourse(Course course) {
		courseRepository.delete(course);
	}

}
