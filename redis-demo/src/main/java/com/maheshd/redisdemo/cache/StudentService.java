package com.maheshd.redisdemo.cache;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "Student")
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	private static final String COMMON_PART_OF_KEY = "STUDENT";

	public List<Student> getAllStudent() {
		return studentRepository.findAll();
	}

	@Cacheable(value = COMMON_PART_OF_KEY, key = "#studentId")
	public Student getStudentById(Integer studentId) {
		Optional<Student> studentOp = studentRepository.findById(studentId);
		if(studentOp.isPresent()) {
			return studentOp.get();
		}
		return null;
	}

	@CachePut(value = COMMON_PART_OF_KEY, key = "#student.id")
	public void addStudent(Student student) {
		studentRepository.save(student);
	}

	@CacheEvict(value = COMMON_PART_OF_KEY, key = "#studentId")
	public void deleteStudentById(Integer studentId) {
		studentRepository.deleteById(studentId);
	}

}
