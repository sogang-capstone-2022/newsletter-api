package com.capstone.newsletterapi.controller;

import com.capstone.newsletterapi.dto.SubscribeRequestDto;
import com.capstone.newsletterapi.dto.SubscribeResponseDto;
import com.capstone.newsletterapi.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscribe")
public class SubscribeController {
    private final SubscribeService subscribeService;

    @PostMapping
    public SubscribeResponseDto subscribeNewsLetter(@RequestBody SubscribeRequestDto requestDto){
        return subscribeService.subscribe(requestDto);
    }

    @GetMapping("/cancel/{uuid}")
    public SubscribeResponseDto cancelNewsLetter(@PathVariable UUID uuid){
        return subscribeService.cancel(uuid);
    }
}
