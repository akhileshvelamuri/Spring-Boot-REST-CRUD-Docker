package com.docker.restcrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.docker.restcrud.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
