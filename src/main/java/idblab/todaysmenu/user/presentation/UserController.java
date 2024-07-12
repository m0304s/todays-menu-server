package idblab.todaysmenu.user.presentation;

import idblab.todaysmenu.user.dto.command.SignupRequestDto;
import idblab.todaysmenu.user.domain.User;
import idblab.todaysmenu.user.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody SignupRequestDto signupRequest) {
        User registeredUser = userService.registerUser(signupRequest);
        return ResponseEntity.ok(registeredUser);
    }
}