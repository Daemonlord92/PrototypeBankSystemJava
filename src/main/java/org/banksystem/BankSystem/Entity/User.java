package org.banksystem.BankSystem.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.banksystem.BankSystem.Enum.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private float balance;
    private boolean hired;

    @OneToMany(mappedBy = "user")
    private Set<Transaction> transactions;

    @OneToMany(mappedBy = "user")
    private Set<Application> applications;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name().toString()));
    }

    @Override
    public String getUsername() {
        return email;
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
