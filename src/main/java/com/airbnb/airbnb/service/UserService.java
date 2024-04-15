package com.airbnb.airbnb.service;

import com.airbnb.airbnb.dto.LoginDto;
import com.airbnb.airbnb.dto.PropertyUserDto;
import com.airbnb.airbnb.entity.PropertyUser;
import com.airbnb.airbnb.repository.PropertyUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private PropertyUserRepository propertyUserRepository;

    public UserService(PropertyUserRepository propertyUserRepository) {
        this.propertyUserRepository = propertyUserRepository;
    }

    // build the method which can add user database with encryption..... part 1

    public PropertyUser addUser(PropertyUserDto dto)
    {
        PropertyUser user = new PropertyUser();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setUserRole(dto.getUserRole());
        user.setPassword(new BCrypt().hashpw(dto.getPassword(), BCrypt.gensalt(10)));
        propertyUserRepository.save(user);
        return user;
    }


    // this is for SignIn...... part 2
    public boolean verifyLogin(LoginDto loginDto) {

        Optional<PropertyUser> opUser = propertyUserRepository.findByUsername(loginDto.getUsername());
        if (opUser.isPresent())
        {
            PropertyUser user = opUser.get();
            // wrapping this code for jwt token
             return BCrypt.checkpw(loginDto.getPassword(), user.getPassword());
        }
        return false;
    }

}
