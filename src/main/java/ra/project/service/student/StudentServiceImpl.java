package ra.project.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ra.project.dao.course.ICourseDao;
import ra.project.dao.student.IStudentDao;
import ra.project.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private IStudentDao studentDao;
    @Autowired
    private ICourseDao courseDao;
    @Override
    public List<Student> paginationList(String keyword, int page, int size) {
        return studentDao.paginationList(keyword,size, page*size);
    }

    @Override
    public int countTotalPages(int size) {
        int totalElements = studentDao.findAll().size();
        int mod = totalElements % size;
        int total = totalElements/size;
        return mod==0?total:total+1;
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public Student findById(String id) {
        return studentDao.findById(id);
    }
    @Transactional
    @Override
    public void save(Student student) {
        if (student.getStudentId() == null) {
            student.setStudentId(Integer.valueOf(new Random().nextInt(12345)).toString()); ;
        }
        studentDao.save(student);
    }
    @Transactional
    @Override
    public void delete(String id) {
        studentDao.delete(id);
    }

}
