package com.pseudowasabi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/first-service")
@Slf4j
public class FirstServiceController {

    Environment env;

    @Autowired
    public FirstServiceController(Environment env) {
        this.env = env;
    }

    @GetMapping("/welcome")
    public String welcome(@RequestHeader("first-req-header") String firstRequestHeader,
                          HttpServletRequest httpServletRequest) {
        log.info("Server port: {}, Header value: {}", httpServletRequest.getServerPort(), firstRequestHeader);
        return String.format("Welcome to first-service on port %s", env.getProperty("local.server.port"));
    }
}
