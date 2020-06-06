package com.example.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/home")
@RequiredArgsConstructor
public class HomeController {

    @GetMapping
    public String index() {
        return "Home Controller";
    }
}