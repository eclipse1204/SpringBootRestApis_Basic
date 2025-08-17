package com.example.RESTAPIS.restApis.controller;

import com.example.RESTAPIS.restApis.dto.NewStudentDto;
import com.example.RESTAPIS.restApis.dto.StudentDto;
import com.example.RESTAPIS.restApis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentById(id));
    }

    @PostMapping("/students")
    public ResponseEntity<StudentDto> createNewStudent(@RequestBody NewStudentDto newStudentDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(newStudentDto));
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<StudentDto> updateStudentById(@RequestBody NewStudentDto newStudentDto, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudentById(newStudentDto,id));
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<StudentDto> updateStudentPartiallyById(@PathVariable Long id,@RequestBody Map<String,Object> updates){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudentPartiallyById(id,updates));
    }
}
