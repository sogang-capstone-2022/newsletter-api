package com.capstone.newsletterapi.controller;

import com.capstone.newsletterapi.dto.SubscribeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscribe")
public class SubscribeController {

    @PostMapping
    public String subscribeNewsLetter(@RequestBody SubscribeRequestDto requestDto){
        return "hello";
    }
}
