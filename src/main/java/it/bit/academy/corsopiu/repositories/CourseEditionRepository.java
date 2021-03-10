package it.bit.academy.corsopiu.repositories;

import it.bit.academy.corsopiu.entities.CourseEdition;
import it.bit.academy.corsopiu.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface CourseEditionRepository extends JpaRepository<CourseEdition, Long> {
    @Query("select ce from CourseEdition as ce where ce.course.id = :courseId")
    Collection<CourseEdition> getAllCoursesEditionsByIdCourseQuery(@Param("courseId") Long courseId);


}
