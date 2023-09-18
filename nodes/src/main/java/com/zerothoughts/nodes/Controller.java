package com.zerothoughts.nodes;

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
    public ResponseEntity<Object> createNode(@RequestBody Node node){
        try{
            return new ResponseEntity<>(service.createNode(node), HttpStatus.CREATED);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateNode(@PathVariable String id, @RequestBody Map<String, Object> update){
        try{
            return new ResponseEntity<>(service.updateNode(id, update), HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getNode(@PathVariable String id) {
        try{
            return new ResponseEntity<>(service.getNode(id), HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get")
    public ResponseEntity<Object> getNodes(@RequestBody String [] ids){
        try{
            return new ResponseEntity<>(service.getNodes(ids), HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
