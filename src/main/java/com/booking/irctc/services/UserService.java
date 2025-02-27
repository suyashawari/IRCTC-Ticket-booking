package com.booking.irctc.services;

import com.booking.irctc.entity.UserData;
import com.booking.irctc.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private final UserDataRepository userDataRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    //    Signup the user
    public Boolean signup(UserData userData) {
//        check user is presnt in DB
        UserData isPresent = userDataRepository.findByEmail(userData.getEmail());
//        if yes then return false that user already present
        if (isPresent!=null) return false;
//        if not then save the user in DB wiht password encoding
        else {
            userData.setPassword(passwordEncoder.encode(userData.getPassword()));

            userDataRepository.save(userData);
            return true;
        }
    }
//    login the user
    public Boolean logIn(UserData user){
//        fetch the user by using mail
          UserData dbUser = userDataRepository.findByEmail(user.getEmail());
// check the mail and password if they are matched with the DB email and pass then give true else false
//
        return dbUser != null && passwordEncoder.matches(user.getPassword(), dbUser.getPassword());

    }


}
