package idblab.todaysmenu.user.presentation;

import idblab.todaysmenu.user.dto.command.SignupRequestDto;
import idblab.todaysmenu.user.domain.User;
import idblab.todaysmenu.user.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable(value = "userId") String userAccount) {
        userService.deleteUser(userAccount);
    }
}