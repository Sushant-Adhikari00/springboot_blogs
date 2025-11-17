package com.project.blogs.mapper;

import com.project.blogs.dto.user_dto.request.RequestUserDto;
import com.project.blogs.dto.user_dto.request.UpdateUserDto;
import com.project.blogs.dto.user_dto.response.ResponseViewUserDto;
import com.project.blogs.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class UserMapper {
    public User saveUser(RequestUserDto requestUserDto) {

        String uniqueId = String.valueOf(UUID.randomUUID());

        User user = new User();
        user.setUsername(requestUserDto.getUsername());
        user.setEmail(requestUserDto.getEmail());
        user.setFullName(requestUserDto.getFirstName());
        user.setPasswordHash(requestUserDto.getPassword());
        user.setUniqueId(uniqueId);
       return user;
    }

    public abstract ResponseViewUserDto viewUser(User user);


    public User updateUser(User user,UpdateUserDto updateUserDto) {
        user.setUsername(updateUserDto.getUsername());
        user.setEmail(updateUserDto.getEmail());
        user.setFullName(updateUserDto.getFirstName());
        user.setPasswordHash(updateUserDto.getPassword());
        return user;
    }

    public User deleteUser(User user) {
        user.setDeleted(true);
        return user;
    }

}
