package com.example.blogsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User implements UserDetails {
    // id - username - password (Add All validation required)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;


    @Column(unique = true)
    @NotNull
    private String username;



    @Column(columnDefinition = "varchar(10) not null check(role='ADMIN'' or role='USER'")
    private String role;

    @Column(nullable = false)
    @NotNull
    private String password;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Blog> blogList;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
