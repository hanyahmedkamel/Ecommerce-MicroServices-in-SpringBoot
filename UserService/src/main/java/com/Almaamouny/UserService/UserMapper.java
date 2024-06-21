package com.Almaamouny.UserService;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class UserMapper {
    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    public User toUser(UserRequest request){
        if (request == null) {
            return null;
        }


        return User.builder()

                .id(request.id())
                .firstName(request.firstname())
                .lastName(request.lastname())
                .email(request.email())
                .passWord(bCryptPasswordEncoder.encode(request.passWord()))
                .address(request.address())
                .role(request.role())
                .build();


    }

    public void toUser(User user,UserRequest request){
        if (request == null) {
            return ;
        }
        if (request.email()!=null)
            user.setEmail(request.email());
        if(request.passWord()!=null)
            user.setPassWord(bCryptPasswordEncoder.encode(request.passWord()));

        Address address=user.getAddress();
        if (request.address()!=null){

        if (request.address().getStreet()!=null)
            address.setStreet(request.address().getStreet());
        if (request.address().getHouseNumber()!=null)
            address.setHouseNumber(request.address().getHouseNumber());
        if (request.address().getZipCode()!=null)
            address.setZipCode(request.address().getZipCode());
        }

       // user.setAddress(address);




    }

    public UserResponse fromUser(User user){

        if (user == null) {
            return null;
        }

        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getAddress()
        );

    }



}
