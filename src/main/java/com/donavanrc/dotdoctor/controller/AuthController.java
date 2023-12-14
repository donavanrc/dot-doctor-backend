package com.donavanrc.dotdoctor.controller;

import com.donavanrc.dotdoctor.domain.user.AuthDTO;
import com.donavanrc.dotdoctor.domain.user.AuthTokenDTO;
import com.donavanrc.dotdoctor.domain.user.User;
import com.donavanrc.dotdoctor.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity<AuthTokenDTO> authenticate(@RequestBody @Valid AuthDTO body) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(body.username(), body.password());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var token = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new AuthTokenDTO(token));
    }
}
