package com.bubblewrap.bubble;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/ping")
    public String ping() {
        return "pong 💥 Server is up!";
    }

    @PostMapping("/echo")
    public String echo(@RequestBody String text) {
        return "You said: " + text;
    }
}
