package server.user.controller;


import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import server.exception.ChhalaangException;
import server.user.model.FleetUser;
import server.user.model.Role;
import server.user.request.CreateAdminReq;
import server.user.request.SignInRequest;
import server.user.response.SignInResponse;
import server.user.response.UserResponse;
import server.user.service.UserService;

import java.security.Principal;
import java.util.Optional;

import static server.util.ProjectUtil.getUser;

@RestController
@RequestMapping("/user")
@Timed
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public SignInResponse login(@RequestBody SignInRequest signinRequest) {
        if (signinRequest.getToken() != null && !signinRequest.getToken().isEmpty()) {
            return userService.processGoogleLogin(signinRequest.getToken());
        }
        throw  new RuntimeException(" Can't login without token");
    }

    @GetMapping("/")
    public UserResponse getProfile(Principal principal) {
        FleetUser user = userService.getUserFromPrincipal(principal);
        user = userService.getUser(user.getId());
        return new UserResponse(user);
    }

    @PostAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @PostMapping("/create")
    public UserResponse create(@RequestBody CreateAdminReq req, Principal principal) {
        FleetUser user = getUser(principal);
        if(user.getRole().equals(Role.MANAGER) && req.getRole().equals(Role.ADMIN)){
            throw new ChhalaangException("MANAGER can not create a ADMIN User");
        }
        FleetUser newUser;
        Optional<FleetUser> userOpt = userService.getUserByEmail(req.getEmail());
        if(userOpt.isPresent()){
            return new UserResponse(userOpt.get());
        }
        newUser = userService.createUser(req.getEmail(), req.getName(), req.getRole());
        return new UserResponse(newUser);
    }
}
