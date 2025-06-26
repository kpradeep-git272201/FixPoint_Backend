package com.fixpoint.module.user.controller;

import com.fixpoint.module.user.dto.UserDto;
import com.fixpoint.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Object> addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);

    }

    @PostMapping("/addUsers")
    public ResponseEntity<Object> addUsers(@RequestBody List<UserDto> userDtos){
        List<UserDto> userDto1 = userService.addUsers(userDtos);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }
    //get user
    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUseryById( @PathVariable String userId){
        UserDto userDto = userService.getUser(Long.parseLong(userId));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    //get all user
    @GetMapping
    public ResponseEntity<Object> getAllUsers(){
        List<UserDto> users = userService.getUsers();
        if(users.isEmpty()){
            return  new ResponseEntity<>("User not found", HttpStatus.OK);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable String userId){
        boolean isUserDeleted = this.userService.deleteUser(Long.parseLong(userId));
        if(isUserDeleted){
            return new ResponseEntity<>("User deleted successfully!", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("User deletion failed!", HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Object> updatedUserById(@PathVariable String userId, @RequestBody UserDto userDto){
        UserDto updatedUser = this.userService.updateUser(userDto, Long.parseLong(userId));
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    @GetMapping("/test")
    public String getTest(){
        return "I am running";
    }

}
