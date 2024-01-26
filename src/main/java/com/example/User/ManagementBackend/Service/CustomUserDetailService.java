package com.example.User.ManagementBackend.Service;

import com.example.User.ManagementBackend.Config.CustomUserDetails;
import com.example.User.ManagementBackend.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userRepo;

    

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if(!userRepo.isEmailExist(email))
            throw new UsernameNotFoundException("Username not found");
        User user = userRepo.findByEmail(email);

        return new CustomUserDetails(user);
    }
}
