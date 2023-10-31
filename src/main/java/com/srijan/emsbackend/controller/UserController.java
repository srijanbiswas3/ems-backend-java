package com.srijan.emsbackend.controller;

import com.srijan.emsbackend.exceptionn.UserNotFoundException;
import com.srijan.emsbackend.model.User;
import com.srijan.emsbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    private User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    private List<User> getAllUser() {

        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    private User getUserById(@PathVariable Long id) {

        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    private User updateUser(@RequestBody User newUser,@PathVariable Long id){
        return userRepository.findById(id).map(user->{
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            user.setUsername(newUser.getUsername());
            return userRepository.save(user);
        })
                .orElseThrow(()->new UserNotFoundException(id));
    }
    @DeleteMapping("/users/{id}")
    private String deleteUser(@PathVariable Long id)
    {
        if(!userRepository.existsById(id))
        {
            throw  new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id "+id+" has been deleted Successfully";
    }

}
