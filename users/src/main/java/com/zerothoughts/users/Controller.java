package com.zerothoughts.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class Controller {
    @Autowired
    private Service service;
    @PostMapping("new")
    public ResponseEntity<Object> createUser(@RequestBody User user){
        try {
            return new ResponseEntity<>(service.createUser(user), HttpStatus.CREATED);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("get/{id}")
    public ResponseEntity<Object> getUser(@PathVariable String id){
        try{
            return new ResponseEntity<>(service.getUser(id), HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping(value = "update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable String id, @RequestBody User user){
        try{
            return new ResponseEntity<>(service.updateUser(id, user), HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
