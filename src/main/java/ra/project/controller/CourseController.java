package ra.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.project.dao.course.ICourseDao;
import ra.project.entity.Course;
import ra.project.service.course.CourseServiceImpl;
import ra.project.service.course.ICourseService;

import javax.validation.Valid;


@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private ICourseService courseService;

    @GetMapping({"","/list"})
    public String list(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "course/list";
    }
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        return "course/add";
    }
//    @PostMapping("/add")
//    public String doAdd(@ModelAttribute("course") Course request){
//        courseService.save(request);
//        return "redirect:/course";
//    }
    @PostMapping("/add")
    public String doAdd(@Valid @ModelAttribute("course") Course request,
                        BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("course", request);
            return "course/add";
        }
        try {
            courseService.save(request);
        } catch (Exception e) {
            result.rejectValue("courseName", "error.course", "Tên đã tồn tại..... ");
            model.addAttribute("course", request);
            return "course/add";
        }
        //categoryService.create(request);
        return "redirect:/course";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Course entity = courseService.findById(id);
        model.addAttribute("course", entity);
        return "course/edit";
    }

    @PostMapping("/update")
    public String doUpdate(@Valid @ModelAttribute("course") Course request,
                           BindingResult result,
                           Model model
    ) {
        courseService.save(request);
        return "redirect:/course";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        try {
            courseService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Xóa thành công");
        } catch (IllegalStateException | IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/course";
    }
}
