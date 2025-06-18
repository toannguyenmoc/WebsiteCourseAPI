package com.course.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.course.model.Account;

public class AccountDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public AccountDetails(Account account) {
        this.username = account.getEmail();
        this.password = account.getPassword();

        // một account có nhiều role , tách từng role ra bằng dấu ,
        // this.authorities = List.of(account.getRole().split(","))
        // .stream()
        // .map(SimpleGrantedAuthority::new) // mỗi role sẽ thành một
        // SimpleGrantedAuthority
        // .collect(Collectors.toList()); //gom lại thành một list

        String roleName = switch (account.getRole()) {
            case 1 -> "ADMIN";
            case 2 -> "TEACHER";
            default -> "NORMAL_USER";
        };

        // trong trường hợp mỗi account chỉ có một role duy nhất
        this.authorities = List.of(new SimpleGrantedAuthority(roleName));

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() { // kiểm tra tài khoản có hết hạn không
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // kiểm tra tài khoản có bị khóa không
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // kiểm tra đăng nhập có còn hợp lệ không
        return true;
    }

    @Override
    public boolean isEnabled() { // kiểm tra tài khoản có được kích hoạt/ đăng nhập không
        return true;
    }

}
