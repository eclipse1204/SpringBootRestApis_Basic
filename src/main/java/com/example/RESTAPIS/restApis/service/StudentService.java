package com.example.RESTAPIS.restApis.service;

import com.example.RESTAPIS.restApis.dto.NewStudentDto;
import com.example.RESTAPIS.restApis.dto.StudentDto;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<StudentDto> getAllStudents();
    StudentDto getStudentById(Long id);
    StudentDto createStudent(NewStudentDto newStudentDto);

    StudentDto updateStudentById(NewStudentDto newStudentDto, Long id);

    void deleteStudentById(Long id);

    StudentDto updateStudentPartiallyById(Long id, Map<String, Object> updates);
}
