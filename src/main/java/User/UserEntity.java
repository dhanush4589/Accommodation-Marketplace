package User;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "accommodation_db")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "First_name", nullable = false)
    private String firstName;

    @Column(name = "Last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "createdAt", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "createBy")
    @CreatedDate
    private String createdBy;

    @Column(name = "lastUpdateAt")
    @LastModifiedDate
    private Instant lastUpdatedAt;

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdatedAt=" + lastUpdatedAt +
                '}';
    }
}
