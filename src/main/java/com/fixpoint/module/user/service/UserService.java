package com.fixpoint.module.user.service;

import com.fixpoint.module.user.dto.UserDto;
import com.fixpoint.module.user.entity.User;

import java.util.List;

public interface UserService {
    UserDto addUser(UserDto userDto);
    List<UserDto> addUsers(List<UserDto> userDtos);
    UserDto getUser(Long userId);
    List<UserDto> getUsers();
    boolean deleteUser(Long Id);
    UserDto updateUser(UserDto user, Long userId);
    List<User> deleteMulUser(List<Long> userIds);
    List<User> updateMulUser(List<User> users);
}
