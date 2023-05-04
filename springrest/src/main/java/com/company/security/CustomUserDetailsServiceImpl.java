package com.company.security;


import com.company.entities.Admin;
import com.company.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminService.getAdmin(email);

        if (admin != null) {
            UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(email);

            builder.disabled(false);
            builder.password(admin.getPassword());

            String[] authoritiesArr = {admin.getRole()};
            builder.authorities(authoritiesArr);

            return builder.build();
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

    }
}