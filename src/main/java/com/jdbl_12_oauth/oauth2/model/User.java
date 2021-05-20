package com.jdbl_12_oauth.oauth2.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User  implements UserDetails {
      @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       Long id;
     String userName;
     String password;
     boolean isAccountNonExpired=true;
     boolean  isAccountNonLocked=true;
     boolean isCredentialsNonExpired=true;
     boolean isEnabled=true;
     @ManyToMany(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
     List<Role> roles;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
