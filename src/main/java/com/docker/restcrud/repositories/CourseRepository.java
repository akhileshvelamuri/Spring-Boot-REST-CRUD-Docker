package com.docker.restcrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.docker.restcrud.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
