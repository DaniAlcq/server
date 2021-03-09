package it.bit.academy.corsopiu.controllers;

import it.bit.academy.corsopiu.dtos.CourseDto;
import it.bit.academy.corsopiu.entities.Course;
import it.bit.academy.corsopiu.services.abstractions.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/course")
@CrossOrigin
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Get a collection of courses
     *
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<Collection<CourseDto>> allCourses() {
        // recuperiamo l'elenco dei corsi dal service
        Collection<Course> listaCorsi = courseService.getCourses();
        // conversione da entity a dto
//        return listaCorsi.stream().map(e -> new CourseDto(e)).collect(Collectors.toList());
        List<CourseDto> result = listaCorsi.stream().map(CourseDto::new).collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Get a collection of categories
     *
     * @return
     */
    @GetMapping("/allcategories/")
    public Collection<String> allCategories() {
        return courseService.getAllCategories();
    }

    /**
     * Get only one course
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> findCourseById(@PathVariable long id) {
        Optional<Course> opt = courseService.getCourseById(id);

        if (opt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new CourseDto(opt.get()), HttpStatus.OK);
    }

    /**
     * Delete a course
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<CourseDto> deleteCourseById(@PathVariable long id) {

        Optional<Course> course = courseService.getCourseById(id);
        if (course.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            courseService.deleteCourse(id);

            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Create a new course
     *
     * @param dto
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto dto) {
        Course course = dto.toCourse();

        Course saved = courseService.saveCourse(course);
        CourseDto saveDto = new CourseDto(saved);
        return new ResponseEntity<>(saveDto, HttpStatus.CREATED);
    }


    //update
    //delete
    //save solo course

    //get all edizioni, per corsi id
    //get module

    //

}
