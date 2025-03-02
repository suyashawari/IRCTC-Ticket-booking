package com.booking.irctc.services;

import com.booking.irctc.entity.UserData;
import com.booking.irctc.entity.UserPrinciples;
import com.booking.irctc.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private final UserDataRepository userDataRepository;
    private final PasswordEncoder passwordEncoder;

    private UserData loggedInUser;

    public UserService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserData getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
           throw new RuntimeException("not authenticated yet  .....");
        }
        String username = authentication.getName();
        return userDataRepository.findByEmail(username);
    }

    //    Signup the user
    public Boolean signup(UserData userData) {
//        check user is presnt in DB
        UserData isPresent = userDataRepository.findByEmail(userData.getEmail());
//        if yes then return false that user already present
        if (isPresent != null) return false;
//        if not then save the user in DB wiht password encoding
        else {

            userData.setPassword(passwordEncoder.encode(userData.getPassword()));
            userDataRepository.save(userData);
            return true;
        }
    }

    //    login the user
    public Boolean logIn(UserData user) {
//        fetch the user by using mail
        UserData dbUser = userDataRepository.findByEmail(user.getEmail());
// check the mail and password if they are matched with the DB email and pass then give true else false
//
        if (dbUser != null && passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            loggedInUser = dbUser;
            return true;
        } else return false;

    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserData user = userDataRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);

        }
        return new UserPrinciples(user);
    }
}
