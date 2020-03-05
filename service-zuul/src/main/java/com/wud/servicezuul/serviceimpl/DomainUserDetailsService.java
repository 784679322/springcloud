package com.wud.servicezuul.serviceimpl;

import com.wud.servicezuul.service.UserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DomainUserDetailsService implements UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = User.findByUsernameOrMobileNumber(username,username)
        if(!user){
            def third = ThirdOauth.findAllByUnionid(username)
            if(third.size()==0){
                throw new UsernameNotFoundException("user not found",username)
            }else{
                user = third.user
            }
        }
        System.out.println("User : "+user);
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.username, user.password,
                user.enabled, !user.accountExpired,!user.passwordExpired,!user.accountLocked, getGrantedAuthorities(user));

    }

    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(Role aut in user.authorities){
            authorities.add(new SimpleGrantedAuthority(aut.authority))
        }
        System.out.print("authorities :"+authorities);
        return authorities;
    }


}
