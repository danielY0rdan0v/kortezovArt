package com.project.web.kortezovart.service;

import com.project.web.kortezovart.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("👉 👉 👉 СТЪПКА 1: Спринг търси потребител: '" + username + "'");

        com.project.web.kortezovart.models.User myUser = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    System.out.println("❌ ГРЕШКА: Потребителят не е намерен в базата!");
                    return new UsernameNotFoundException("User not found with username: " + username);
                });

        System.out.println("✅ СТЪПКА 2: Потребителят е намерен в базата!");
        System.out.println("🔑 СТЪПКА 3: Паролата (хешът), прочетена от базата е: '" + myUser.getPassword() + "'");

        return org.springframework.security.core.userdetails.User
                .withUsername(myUser.getUsername())
                .password(myUser.getPassword())
                .roles("ADMIN")
                .build();
    }
}
