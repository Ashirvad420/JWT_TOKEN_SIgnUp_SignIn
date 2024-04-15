package com.airbnb.airbnb.controller;

import com.airbnb.airbnb.dto.LoginDto;
import com.airbnb.airbnb.dto.PropertyUserDto;
import com.airbnb.airbnb.entity.PropertyUser;
import com.airbnb.airbnb.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // http://localhost:8080/api/v1/users/addUser
    @PostMapping("/addUser")  // this is for SignUp....  part 1
    public ResponseEntity<String> addUser(@RequestBody PropertyUserDto dto) {
        PropertyUser user = userService.addUser(dto);
        if (user != null) { // which means data saved into database
            return new ResponseEntity<>("sign up successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")  // this is for SignIn....  part 2
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {

        boolean status = userService.verifyLogin(loginDto);

        if (status) {
            return new ResponseEntity<>("sign up successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);

    }
}
