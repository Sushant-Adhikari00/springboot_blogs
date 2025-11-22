package com.project.blogs.service.impl;

import com.project.blogs.core.dto.ApiResponse;
import com.project.blogs.core.service.EmailTemplateService;
import com.project.blogs.core.service.FileService;
import com.project.blogs.dto.user_dto.request.DeleteUserDto;
import com.project.blogs.dto.user_dto.request.RequestUserDto;
import com.project.blogs.dto.user_dto.request.UpdateUserDto;
import com.project.blogs.dto.user_dto.request.ViewUserRequest;
import com.project.blogs.dto.user_dto.response.ResponseViewUserDto;
import com.project.blogs.entity.User;
import com.project.blogs.exception.DuplicateException;
import com.project.blogs.exception.FileSizeExceededException;
import com.project.blogs.exception.NotFoundException;
import com.project.blogs.mapper.UserMapper;
import com.project.blogs.repo.UserRepo;
import com.project.blogs.service.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EmailTemplateService emailTemplateService;
    @Autowired
    private FileService fileService;

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<?>> saveUser(RequestUserDto requestUserDto, MultipartFile profilePicture) {
        boolean existByEmail = userRepo.existsByEmail(requestUserDto.getEmail());
        if (existByEmail) {
            logger.error("Failed to save user due to Email {} already exists", requestUserDto.getEmail());
            throw new DuplicateException("Email already exist");
        }
        boolean existByPhone = userRepo.existsByUsername(requestUserDto.getUsername());
        if (existByPhone) {
            logger.error("Failed to save user due to Username {} already exists", requestUserDto.getUsername());
            throw new DuplicateException("Username already exists");
        }

        User user = userMapper.saveUser(requestUserDto);

        if (profilePicture != null && !profilePicture.isEmpty()) {
            long maxSize =10*1024*1024; //ie 10 MB

            if(profilePicture.getSize()>maxSize){
                throw new FileSizeExceededException("File size must be less than or equal to "+maxSize);
            }

            String fileName = fileService.uploadFile(profilePicture);
            user.setProfilePicture(fileName);
        }
        userRepo.save(user);
        emailTemplateService.sendWelcomeMail(user);
        logger.info("User saved");
        ApiResponse<String> apiResponse = new ApiResponse<>(true, 200, LocalDateTime.now(), "Created Successfully");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @Override
    @Transactional
    public ResponseEntity<ApiResponse<?>> viewUser(ViewUserRequest viewUserRequest) {
        Optional<User> user = userRepo.findByUsername(viewUserRequest.getUsername());
        if (user.isEmpty()) {
            logger.error("User with username {} not found", viewUserRequest.getUsername());
            throw new NotFoundException("User with username " + viewUserRequest.getUsername() + " not found");
        }

        ResponseViewUserDto responseViewUserDto = userMapper.viewUser(user.get());

        ApiResponse<?> response = new ApiResponse<>(true, "User is fetch successfully", 200, LocalDateTime.now(), responseViewUserDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @Override
    @Transactional
    public ResponseEntity<ApiResponse<?>> updateUser(UpdateUserDto updateUserDto) {
        Optional<User> userOptional = userRepo.findByUsername(updateUserDto.getUsername());

        boolean
                existById = userRepo.existsById(updateUserDto.getId());
        if (!existById) {
            logger.error("Failed to save user due to User {} does not exists", updateUserDto.getId());
            throw new NotFoundException("User does not exist");
        }

        User userExists = userOptional.get();

        boolean existByEmail = userRepo.existsByEmail(updateUserDto.getEmail());
        if (existByEmail) {
            logger.error("Failed to save user due to Email {} already exists", updateUserDto.getEmail());
            throw new DuplicateException("Email already exist");
        }

        boolean existByPhone = userRepo.existsByUsername(updateUserDto.getUsername());
        if (existByPhone) {
            logger.error("Failed to save user due to Username {} already exists", updateUserDto.getUsername());
            throw new DuplicateException("Username already exists");
        }

        User userToUpdate = userMapper.updateUser(userExists, updateUserDto);
        userRepo.save(userToUpdate);
        logger.info("User updated");
        ApiResponse<String> apiResponse = new ApiResponse<>(true, 200, LocalDateTime.now(), "Created Successfully");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<?>> deleteUser(DeleteUserDto deleteUserDto) {
        Optional<User> user = userRepo.findByUsername(deleteUserDto.getUsername());
        boolean existByUsername = userRepo.existsByUsername(deleteUserDto.getUsername());
        if (!existByUsername) {
            logger.error("Failed to save user due to User {} does not exists", deleteUserDto.getUsername());
            throw new NotFoundException("User does not exist");
        }
        User userToDelete = userMapper.deleteUser(user.get());
        userRepo.save(userToDelete);
        logger.info("User with id {} deleted successfully", deleteUserDto.getUsername());

        ApiResponse<?> apiResponse = new ApiResponse(true, 200, LocalDateTime.now(), "User deleted successfully");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

}
