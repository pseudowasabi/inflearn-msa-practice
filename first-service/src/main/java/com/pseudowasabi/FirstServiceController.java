package com.pseudowasabi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first-service")
@Slf4j
public class FirstServiceController {

    @GetMapping("/welcome")
    public String welcome(@RequestHeader("first-req-header") String firstRequestHeader) {
        log.info("Header value: {}", firstRequestHeader);
        return "Welcome to first-service.";
    }
}
