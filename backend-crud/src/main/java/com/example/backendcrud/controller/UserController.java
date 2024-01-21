//UserController
package com.example.backendcrud.controller;
import com.example.backendcrud.exception.UserNotFoundException;
import com.example.backendcrud.model.User;
import com.example.backendcrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        newUser.generateIdAndSet();
        return userRepository.save(newUser);
    }

    @GetMapping("/getUsers")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
        User getUserById(@PathVariable String id){
        return userRepository.findById(id)
                .orElseThrow(() ->new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable String id){
        return userRepository.findById(id)
                .map(user ->{
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable String id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id"+ id +"has been deleted successfully";
    }

}


