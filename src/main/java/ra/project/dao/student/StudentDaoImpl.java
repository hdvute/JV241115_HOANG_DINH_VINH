package ra.project.dao.student;

import org.springframework.stereotype.Repository;
import ra.project.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class StudentDaoImpl implements IStudentDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> findAll() {
        return entityManager.createQuery("FROM  Student ", Student.class).getResultList();
    }

    @Override
    public List<Student> paginationList(String keyword, int limit, int offset) {

        String jpql = "FROM Student S WHERE S.studentName LIKE :key";
        return entityManager.createQuery(jpql, Student.class)
                .setParameter("key", "%"+keyword+"%")
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public Student findById(String id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void save(Student student) {
        if(student.getStudentId() == null){
            // add new
            entityManager.persist(student);
        }else{
            // update
            entityManager.merge(student);
        }
    }

    @Override
    public void delete(String id) {
        entityManager.remove(findById(id));
    }
}
