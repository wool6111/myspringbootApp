package com.basic.myspringboot.controller;

import com.basic.myspringboot.entity.User;
import com.basic.myspringboot.entity.Users;
import com.basic.myspringboot.exception.ResourceNotFoundException;
import com.basic.myspringboot.repository.UserRepository;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserRepository userRepository;

    // constructor injection
//    public UserRestController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);// Optional<User
        User user = userOptional.orElseThrow(() -> new ResourceNotFoundException("User","Id",id));
        return user;
    }
    // ResponseEntity 사용
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        // if(!optionalUser.isPresent()) {
        if(optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(email + " User Not Found");
        }
        User user = optionalUser.get();
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PatchMapping("/{email}")
    public ResponseEntity<?> modifyUser(@PathVariable String email, @RequestBody User userDetail) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(email + " User Not Found");
        }
        User existUser = optionalUser.get();
        existUser.setName(userDetail.getName());
        User updatedUser = userRepository.save(existUser);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","id",id));
        userRepository.delete(user);

        return ResponseEntity.ok().body(id + " 삭제처리 되었습니다.");
    }

    @GetMapping(value = "/xml", produces = {"application/xml"})
    public Users getUsersXml() {
        Users users = new Users();
        users.setUsers(userRepository.findAll());
        return users;
    }

}
