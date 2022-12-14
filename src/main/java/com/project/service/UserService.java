package com.project.service;

import com.project.dto.UserRequest;
import com.project.dto.UserResponse;
import com.project.entity.User;
import com.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserResponse createUser(UserRequest request){
        User user = mapToEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return mapToResponse(user);
    }


    public UserResponse update(Long id, UserRequest request){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UsernameNotFoundException(format("User with  email not found", id));
        }
        mapToUpdate(request, user.get());
        return  mapToResponse(userRepository.save(user.get()));
    }


    public UserResponse findById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UsernameNotFoundException(format("User with  email not found", id));
        }
        return  mapToResponse(user.get());
    }


    public UserResponse deleteById(Long id){
    Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
        throw new UsernameNotFoundException(format("User with  email not found", id));
    }
        userRepository.deleteById(id);
        return mapToResponse(user.get());
}


    public User mapToEntity(UserRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setSurname(request.getSurname());
        user.setCreated(LocalDate.now());
        return user;
    }

    public User mapToUpdate(UserRequest request, User user){
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return user;
    }

    public UserResponse mapToResponse(User user){
        return  UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .created(user.getCreated())
                .build();

    }


}
