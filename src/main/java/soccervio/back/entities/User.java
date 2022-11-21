package soccervio.back.entities;

import lombok.*;
import soccervio.back.entities.enums.Gender;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 50, nullable = false)
    private String fullName;

    @Column(length = 50, nullable = false, unique = true)
    private String userName;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 25, unique = true)
    private String phoneNumber;

    @OneToOne
    private Photo photo;

    @Column(length = 6)
    private Gender gender;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Date createdAt;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;
}
