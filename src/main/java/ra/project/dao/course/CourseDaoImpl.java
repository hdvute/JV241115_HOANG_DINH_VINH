package ra.project.dao.course;

import org.springframework.stereotype.Repository;
import ra.project.entity.Course;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;
@Repository
public class CourseDaoImpl implements ICourseDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Course> findAll() {
        return entityManager.createQuery("FROM Course", Course.class).getResultList();
    }

    @Override
    public boolean existByName(String name) {
        return !entityManager.createQuery("FROM Course  C where  C.courseName like :name")
                .setParameter("name", name)
                .getResultList().isEmpty();
    }

    @Override
    public Course findById(Integer id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public void save(Course course) {
        if(course.getCourseId() == null){
            // add new
            entityManager.persist(course);
        }else{
            // update
            entityManager.merge(course);
        }
    }

    @Override
    public void delete(Integer id) {
        Course course = findById(id);
        if (course == null) {
            throw new IllegalArgumentException("Danh mục không tồn tại!");
        }
        try {
            entityManager.remove(course);
            entityManager.flush(); // Để Hibernate thực hiện xóa ngay, ném lỗi nếu có ràng buộc FK
        } catch (PersistenceException e) {
            throw new IllegalStateException("Không thể xóa.");
        }
    }
}
