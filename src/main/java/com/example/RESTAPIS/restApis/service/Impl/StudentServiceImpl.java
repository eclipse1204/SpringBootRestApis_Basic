package com.example.RESTAPIS.restApis.service.Impl;

import com.example.RESTAPIS.restApis.dto.NewStudentDto;
import com.example.RESTAPIS.restApis.dto.StudentDto;
import com.example.RESTAPIS.restApis.entity.Student;
import com.example.RESTAPIS.restApis.repository.StudentRepository;
import com.example.RESTAPIS.restApis.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students
                .stream()
                .map(student -> modelMapper.map(student,StudentDto.class))
                .toList();
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student=studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Student not found with given ID "+id));
        return modelMapper.map(student,StudentDto.class);
    }

    @Override
    public StudentDto createStudent(NewStudentDto newStudentDto) {
        Student student=modelMapper.map(newStudentDto,Student.class);
        Student createdStudent=studentRepository.save(student);
        return modelMapper.map(createdStudent,StudentDto.class);
    }

    @Override
    public StudentDto updateStudentById(NewStudentDto newStudentDto, Long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("Student not found with id : "+id);
        }
        Student stud=studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Student not found with id : "+id));
        modelMapper.map(newStudentDto,stud);
        Student createdStudent=studentRepository.save(stud);
        return modelMapper.map(createdStudent,StudentDto.class);
    }

    @Override
    public void deleteStudentById(Long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("Student not found with id : "+id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudentPartiallyById(Long id, Map<String, Object> updates) {
        Student student=studentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Student not found with id : "+id));
        for(String key:updates.keySet()){
            switch (key){
                case "name":
                    student.setName((String) updates.get(key));
                    break;
                case "email":
                    student.setEmail((String) updates.get(key));
                    break;
                default:
                    throw new IllegalArgumentException("Field not found : "+key);
            }
        }
        Student stud=studentRepository.save(student);
        return modelMapper.map(stud,StudentDto.class);
    }

}
