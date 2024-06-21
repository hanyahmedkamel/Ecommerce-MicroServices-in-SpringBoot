package com.Almaamouny.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private userRepository repository;
    @Autowired
    private UserMapper userMapper;


    public Integer createUser(UserRequest userRequest){

        var user=repository.save(userMapper.toUser(userRequest));

        return user.getId();
    }


    public void updateUser(UserRequest request) {


        var customer = this.repository.findById(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName()))
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Cannot update customer:: No customer found with the provided ID: %d", request.id())
                ));

        userMapper.toUser(customer,request);
        this.repository.save(customer);
    }

    public UserResponse findById(Integer id) {
        return this.repository.findById(id)
                .map(userMapper::fromUser)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found with the provided ID: %d", id)));
    }

    public boolean existsById(Integer id) {
        return this.repository.findById(id)
                .isPresent();
    }

    public void deleteUser(Integer id) {
        this.repository.deleteById(id);
    }
}
