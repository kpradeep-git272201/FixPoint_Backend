package com.fixpoint.module.user.service;

import com.fixpoint.module.user.dto.UserDto;
import com.fixpoint.module.user.entity.User;
import com.fixpoint.module.user.exceptions.ResouceNotFoundException;
import com.fixpoint.module.user.repository.UserRepository;
import com.fixpoint.module.user.utils.CustomObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private CustomObjectMapper objectMapper;
    @Autowired
    private UserRepository repository;
    @Override
    public UserDto addUser(UserDto userDto) {
        User user = objectMapper.convert(userDto, User.class);
        User saveUser = this.repository.save(user);
        return objectMapper.convert(saveUser, UserDto.class);
    }

    @Override
    public List<UserDto> addUsers(List<UserDto> userDtos) {
        List<User> convert = Arrays.asList(objectMapper.convert(userDtos, User[].class));
        List<User> users = this.repository.saveAll(convert);
        return Arrays.asList(objectMapper.convert(users, UserDto[].class));
    }

    @Override
    public UserDto getUser(Long userId) {
        User user = this.repository.findById(userId).orElseThrow(() -> new ResouceNotFoundException("User not found for given Id: " + userId));
        return objectMapper.convert(user, UserDto.class);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> allUsers = this.repository.findAll();
        return Arrays.asList(objectMapper.convert(allUsers, UserDto[].class));
    }

    @Override
    public boolean deleteUser(Long Id) {
        boolean isUserDeleted =false;
        try{
            this.repository.deleteById(Id);
            isUserDeleted=true;
        }catch (Exception e){
            isUserDeleted=false;
        }
        return isUserDeleted;
    }

    @Override
    public UserDto updateUser(UserDto user, Long userId) {
        Optional<User> byId = this.repository.findById(userId);
        User user1 = byId.get();
        if(user.getUserName()!=null){
            user1.setUserName(user.getUserName());
        }
        if(user.getEmail()!=null){
            user1.setEmail(user.getEmail());
        }
        if(user.getDesignation()!=null){
            user1.setDesignation(user.getDesignation());
        }

        User save = this.repository.save(user1);
        return objectMapper.convert(save, UserDto.class);
    }

    @Override
    public List<User> deleteMulUser(List<Long> userIds) {
        return null;
    }

    @Override
    public List<User> updateMulUser(List<User> users) {
        return null;
    }
}
