package com.example.demo.common;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class LoginUser implements UserDetails {
    private Integer userId;   // 你自己的业务主键
    private String username;
    private String password;
    private String realName;
    private Collection<? extends GrantedAuthority> authorities;

    /* 下面几个 UserDetails 方法按规则返回即可 */
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}