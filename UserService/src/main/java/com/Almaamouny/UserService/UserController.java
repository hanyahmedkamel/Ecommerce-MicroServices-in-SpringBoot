package com.Almaamouny.UserService;
import com.Almaamouny.UserService.JWT.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@RestController
@AllArgsConstructor
public class UserController {

    private JwtService jwtService;
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private RedisService redisService;
    private KafkaTemplate<String,Integer>kafkaTemplate;


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

        Random random = new Random();
        int sixDigitNumber = 100000 + random.nextInt(900000);
        CustomValue customValue=new CustomValue(sixDigitNumber,request);
        redisService.saveData(request.email(),customValue);
        kafkaTemplate.send("ActivateAccount",sixDigitNumber);

        return ResponseEntity.status(202).build();

    }

    @PutMapping("/user")
    public ResponseEntity<Void> updateCustomer(@RequestBody UserRequest request) {
        this.userService.updateUser(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/address_id")
    public ResponseEntity<Integer> userAddressId() {

        return ResponseEntity.ok(this.userService.findById(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName())).address().getId());
    }


    @GetMapping("/user")
    public ResponseEntity<String> getUser() {


        return ResponseEntity.ok(this.userService.findById(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName())).email());
    }


    @PostMapping("/activate")
    public ResponseEntity<Integer> activateUser(@RequestBody ActivateRequest request) {
        System.out.println("11111");

        CustomValue customValue = redisService.getData(request.email());

        if (Objects.equals(request.code(), customValue.getCode()))
            return ResponseEntity.status(202).body(userService.createUser(customValue.getNewUser()));
        else return ResponseEntity.status(404).build();


    }

}
