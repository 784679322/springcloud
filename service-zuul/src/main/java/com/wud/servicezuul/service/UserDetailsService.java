package com.wud.servicezuul.service;

import org.springframework.security.core.userdetails.UserDetails;



public interface UserDetailsService {

    public UserDetails loadUserByUsername(String username) throws Exception;


}
