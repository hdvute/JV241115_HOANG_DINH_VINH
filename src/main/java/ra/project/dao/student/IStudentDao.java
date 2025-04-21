package ra.project.dao.student;

import ra.project.dao.IGenericDao;
import ra.project.entity.Student;

import java.util.List;

public interface IStudentDao extends IGenericDao<Student, String> {
    List<Student> paginationList(String keyword, int limit, int offset);
}
