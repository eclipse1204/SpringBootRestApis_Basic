package com.example.RESTAPIS.restApis.repository;

import com.example.RESTAPIS.restApis.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
