package com.mybasket.app.service.impl;

import com.mybasket.app.dto.UserDto;
import com.mybasket.app.entity.User;
import com.mybasket.app.exception.ResourceNotFoundException;
import com.mybasket.app.repository.UserRepository;
import com.mybasket.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    @RequiredArgsConstructor
    public class UserServiceImpl implements UserService {

        private final UserRepository userRepository;
        private final ModelMapper modelMapper;

        @Override
        public UserDto createUser(UserDto userDto){
            User user= modelMapper.map(userDto, User.class);
            User savedUser= userRepository.save(user);
            return modelMapper.map(savedUser, UserDto.class);
        }

    @Override
    public UserDto updateUser(Integer userId, UserDto userDto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
//        user.setUserImageUrl(userDto.getUserImageUrl());

        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }



    @Override
    public void deleteUser(Integer userId) {

    }

    @Override
    public List<UserDto> getAllUsers() {
        return List.of();
    }

    @Override
    public UserDto getUser(Integer userId) {
        return null;
    }

    @Override
    public void saveUser(User user) {

    }

}
