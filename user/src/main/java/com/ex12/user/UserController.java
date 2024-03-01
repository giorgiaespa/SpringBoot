package com.ex12.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/new")
    public User createCar (@RequestBody User newUser) {
        return userRepository.saveAndFlush(newUser);
    }

    @GetMapping("/mock")
    public User getUser (){
        User user = new User();
        user.setName("Amelia");
        user.setSurname("Grey");
        user.setEmail("a.grey@gmail.com");
        user.setPassword("MyPsw123");

        return user;
    }

    @GetMapping("/getAll")
    public List<User> readAll (){
        return userRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Optional<User> readUserById (@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            return userRepository.findById(id);
        }
        return Optional.empty();
    }

    @PatchMapping("/updatePassword/{id}")
    public Optional<User> updatedUser (@PathVariable long id, @RequestParam User updateData) {
        Optional<User> userCheck = userRepository.findById(id);
        if (userCheck.isPresent()){
            updateData.setPassword(updateData.getPassword());
            userRepository.saveAndFlush(userCheck.get());
            return userCheck;
        }
        return Optional.empty();
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteById (@PathVariable long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return HttpStatus.GONE;
        }
        return HttpStatus.CONFLICT;
    }

    @DeleteMapping("deleteAll")
    public void deleteAll (){
        userRepository.deleteAll();
    }
}
