package com.Almaamouny.UserService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomValue implements Serializable {

    private Integer code;
    private  UserRequest newUser;
}
