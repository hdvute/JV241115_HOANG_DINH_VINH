package ra.project.service.student;

import ra.project.dao.IGenericDao;
import ra.project.entity.Student;

import java.util.List;

public interface IStudentService extends IGenericDao<Student, String> {
    List<Student> paginationList(String keyword, int page, int size);
    int countTotalPages(int size);
}
