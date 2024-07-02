package idblab.todaysmenu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {


    @GetMapping("/login")
    public String Login() {
        return "login";
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "배포테스트입니다";
    }

    @GetMapping("/test")
    public String test() {return "테스트테스트";}
}