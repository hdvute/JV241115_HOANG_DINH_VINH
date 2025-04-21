package ra.project.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ra.project.dao.course.ICourseDao;
import ra.project.entity.Course;


import javax.transaction.Transactional;
import java.util.List;
@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    private ICourseDao courseDao;

    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public Course findById(Integer id) {
        return courseDao.findById(id);
    }
    @Transactional
    @Override
    public void save(Course course) {
        courseDao.save(course);
    }
    @Transactional
    @Override
    public void delete(Integer id) {
        try {
            courseDao.delete(id);
        }catch (DataIntegrityViolationException e){
            throw new IllegalStateException("Không thể xóa .");
        }
    }
}
