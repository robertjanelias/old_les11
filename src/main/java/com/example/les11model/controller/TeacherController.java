package com.example.les11model.controller;

import com.example.les11model.model.Teacher;
import com.example.les11model.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository repos;

    @GetMapping
    public ResponseEntity<List<Teacher>> getTeachers() {
        return ResponseEntity.ok(repos.findAll());
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        repos.save(teacher);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + teacher.getId()).toUriString());
        return ResponseEntity.created(uri).body(teacher);
    }

    @GetMapping("/after")
    public ResponseEntity<List<Teacher>> getTeachersAfter(@RequestParam LocalDate date) {
        return ResponseEntity.ok(repos.findByDobAfter(date));
    }
}
