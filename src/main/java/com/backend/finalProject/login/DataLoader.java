package com.backend.finalProject.login;

import com.backend.finalProject.login.auth.IUserRepository;
import com.backend.finalProject.login.auth.User;
import com.backend.finalProject.login.auth.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private IUserRepository repository;

    @Autowired
    public DataLoader(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String admin_password = passwordEncoder.encode("admin");
        String user_password = passwordEncoder.encode("user");

        repository.save(new User("admin", "admin", "admin@admin.com", admin_password, UserRole.ROLE_ADMIN));
        repository.save(new User("user", "user", "user@mail.com", user_password, UserRole.ROLE_USER));

    }
}
