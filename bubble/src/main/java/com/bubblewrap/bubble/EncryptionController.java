package com.bubblewrap.bubble;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


    @RestController
    @RequestMapping("/api")
    public class EncryptionController {

        @PostMapping("/encrypt")
        public String encrypt(@RequestBody String text) {
            return new StringBuilder(text).reverse().toString(); // fake encryption
        }

        @PostMapping("/decrypt")
        public String decrypt(@RequestBody String text) {
            return new StringBuilder(text).reverse().toString(); // fake decryption (reverse again)
        }
}
