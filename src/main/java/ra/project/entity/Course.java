package ra.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer courseId;

    @NotBlank(message = "Không được đc trống!")
    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String courseName;

    @NotBlank(message = "Không được đc trống!")
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Student> students = new ArrayList<>();
}
