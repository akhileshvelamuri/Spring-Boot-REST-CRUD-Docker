package com.docker.restcrud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import com.docker.restcrud.models.Course;
import com.docker.restcrud.services.CourseService;

@RestController
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/getAllCourses")
	public List<Course> getAllCourses() {
	    return courseService.getAllCourses();
	}
	
	@PostMapping("/createCourse")
	public Course createCourse(@Valid @RequestBody Course course) {
		return courseService.saveCourse(course);
	}
	
	@GetMapping("/getCourseById/{courseid}")
	public Course getCourseById(@PathVariable("courseid") Long courseid) {
		return courseService.findCourseById(courseid).orElseThrow(() -> new IllegalStateException(
		          "No Course found with ID : " + courseid));
	}
	
	@PutMapping("/updateCourseById/{courseid}")
	public Course updateCourseById(@PathVariable("courseid") Long courseid, @Valid @RequestBody Course updatedcourse) {
		Course course = courseService.findCourseById(courseid).orElseThrow(() -> new IllegalStateException(
		          "No Course found with ID : " + courseid));
		course.setName(updatedcourse.getName());
		course.setDescription(updatedcourse.getDescription());
		course.setSteps(updatedcourse.getSteps());
		
		return courseService.saveCourse(course);
	}
	
	@DeleteMapping("/deleteCourseById/{courseid}")
	public ResponseEntity<?> deleteNote(@PathVariable("courseid") Long courseid) {
		Course course = courseService.findCourseById(courseid).orElseThrow(() -> new IllegalStateException(
		          "No Course found with ID : " + courseid));

	    courseService.deleteCourse(course);

	    return ResponseEntity.ok().build();
	}

}
