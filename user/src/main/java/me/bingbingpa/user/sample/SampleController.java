package me.bingbingpa.user.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/user")
    public String user() {
        return "user";
    }
}
