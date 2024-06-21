package com.Almaamouny.UserService;
import com.Almaamouny.UserService.JWT.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class UserController {

    private JwtService jwtService;
    private UserService userService;
    private AuthenticationManager authenticationManager;


    @PostMapping("/authenticate")
    public ResponseEntity<String> createAuthenticationToken(@RequestBody UserRequest authenticationRequest) throws Exception {

        UserDetails userDetails=null;
        try {
            userDetails=(UserDetails)  authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.email(), authenticationRequest.passWord())
            ).getPrincipal();

        } catch (Exception e) {
            throw new Exception("Incorrect username or password", e);
        }


        Map<String,Object>mp=new HashMap<>();
        final String jwt = jwtService.generateToken(userDetails.getUsername(), (String) userDetails.getAuthorities().iterator().next().getAuthority(),mp);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt);

        return ResponseEntity.ok().headers(headers).build();

    }

    @PostMapping("/user")
    public ResponseEntity<Integer>createUser(@RequestBody UserRequest request){
        return ResponseEntity.ok(userService.createUser(request));

    }

    @PutMapping("/user")
    public ResponseEntity<Void> updateCustomer(@RequestBody UserRequest request) {
        this.userService.updateUser(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/address_id")
    public ResponseEntity<Integer> userAddressId() {

        System.out.println("1111111");

        return ResponseEntity.ok(this.userService.findById(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName())).address().getId());
    }


    @GetMapping("/user")
    public ResponseEntity<String> getUser() {

        System.out.println("1111111");

        return ResponseEntity.ok(this.userService.findById(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName())).email());
    }




}
