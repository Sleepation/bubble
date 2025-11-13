package com.bubblewrap.bubble;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


    @RestController
    @RequestMapping("/api")
    public class EncryptionController {
        private DirectEncrypter de;

        @PostMapping("/encrypt")
        public String encrypt(@RequestBody String text) {
            de = new DirectEncrypter(text, de);
            return new de.
        }

        @PostMapping("/decrypt")
        public String decrypt(@RequestBody String text) {

            return new DirectEncrypter.decrypt(); // fake decryption (reverse again)
        }


}
