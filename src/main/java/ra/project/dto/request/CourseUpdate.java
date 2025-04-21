package ra.project.dto.request;

import org.hibernate.validator.constraints.NotBlank;
import ra.project.validator.CourseUnique;

public class CourseUpdate {
    @NotBlank(message = "Không được đc trống!")
    @CourseUnique
    private String courseName;

    @NotBlank(message = "Không được đc trống!")
    private String description;
}
