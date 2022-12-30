package soccervio.back.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }

    public Role() {
    }

    public Role(int id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority ;
    }
}
