package ra.project.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import ra.project.validator.CourseUnique;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseDto {
    @NotBlank(message = "Không được đc trống!")
    @CourseUnique
    private String courseName;

    @NotBlank(message = "Không được đc trống!")
    private String description;
}
