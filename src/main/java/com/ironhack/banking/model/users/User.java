package com.ironhack.banking.model.users;

import com.ironhack.banking.model.enums.Role;
import com.ironhack.banking.repository.AccountRepository;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role rol;
    @OneToOne(fetch = FetchType.EAGER)
    private AccountHolder accountHolder;

    public User() {
    }

    public User(@NotNull String username, @NotNull String password, Role rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public User(@NotNull String username, @NotNull String password, Role rol, AccountHolder accountHolder) {
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.accountHolder = accountHolder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                rol == user.rol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, rol);
    }
}
