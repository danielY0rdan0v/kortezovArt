package com.project.web.kortezovart.config;

import com.project.web.kortezovart.models.Role;
import com.project.web.kortezovart.models.User;
import com.project.web.kortezovart.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository repository;

    public AdminSeeder(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (repository.findByUsername("Dekata").isEmpty()) {

            User user = new User("Dekata", "123", Role.ADMIN );
            repository.save(user);
            System.out.println("✅ Служебният Админ " + user.getUsername() + " беше създаден успешно!");
        }else {
            System.out.println("ℹ️ Админът вече съществува в базата данни.");
        }
    }
}
