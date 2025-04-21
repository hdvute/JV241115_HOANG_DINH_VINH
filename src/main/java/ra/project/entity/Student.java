package ra.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Student {
    @Id
    @Column(name = "id", length = 5)
    private String studentId;
    @NotBlank(message = "Không được đc trống!")
    @Column(name = "name", nullable = false, length = 200)
    private String studentName;

    @NotBlank(message = "Không được đc trống!")
    @Email(message = "Email không hợp lệ!")
    @Column(name = "email", nullable = false, unique = true, columnDefinition = "TEXT")
    private String studentEmail;

    @NotBlank(message = "Không được đc trống!")
    @Pattern(
            regexp = "^0[0-9]{9}$",
            message = "Số điện thoại không hợp lệ! Phải bắt đầu bằng 0 và có 10 chữ số."
    )
    @Column(name = "phone", nullable = false, unique = true, length = 15)
    private String phone;

    @NotBlank(message = "Không được đc trống!")
    @Column(name = "sex", nullable = false)
    private Boolean sex;

    @NotBlank(message = "Không được đc trống!")
    @Column(name = "bid", nullable = false)
    private LocalDate bid;

    @Column(name = "avatar", columnDefinition = "TEXT")
    private String avatar;

    @NotBlank(message = "Không được đc trống!")
    @Column(name = "status", nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}
