package com.dakshina.SpringSecurity.service;

import com.dakshina.SpringSecurity.entity.UserEntity;
import com.dakshina.SpringSecurity.entity.UserPrincipal;
import com.dakshina.SpringSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUserName(username);

        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }

        return  new UserPrincipal(user);
    }
}
