package ra.project.dao.course;

import ra.project.dao.IGenericDao;
import ra.project.entity.Course;

import java.util.List;

public interface ICourseDao extends IGenericDao<Course, Integer> {
    boolean existByName(String name);
}
