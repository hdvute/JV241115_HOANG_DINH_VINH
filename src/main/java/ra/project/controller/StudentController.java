package ra.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.project.dao.student.IStudentDao;
import ra.project.entity.Student;
import ra.project.service.course.ICourseService;
import ra.project.service.student.IStudentService;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    IStudentService studentService;
    @Autowired
    ICourseService courseService;

//    @GetMapping({"","/list"})
//    public String list(Model model) {
//        model.addAttribute("student", studentService.findAll());
//        return "course/list";
//    }

    @GetMapping({"","/list"})
    public String list(@RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "5") int size,
                       @RequestParam(value = "keyword", defaultValue = "") String keyword,
                       @RequestParam(value = "error", defaultValue = "") String message,
                       Model model){
        model.addAttribute("students", studentService.paginationList(keyword,page,size));
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages",studentService.countTotalPages(size));
        if (!Objects.equals(message, "")){
            model.addAttribute("error", message);
        }
        return "student/list";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id){
        studentService.delete(id);
        return "redirect:/student";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("courses", courseService.findAll());
        return "student/add";
    }

    // xữ lý thêm mới
    @PostMapping("/add")
    public String doAdd(@Valid @ModelAttribute("student") Student request,
                        BindingResult result,
                        @RequestParam("avatarFile") MultipartFile avatarFile,
                        Model model){
        if (result.hasErrors()){
            model.addAttribute("student", request);
            return "student/add";
        }
        studentService.save(request);
        return "redirect:/student";
    }

}
