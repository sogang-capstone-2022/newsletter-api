package com.capstone.newsletterapi.controller;

import com.capstone.newsletterapi.dto.MailingListDto;
import com.capstone.newsletterapi.dto.SubscribeRequestDto;
import com.capstone.newsletterapi.dto.SubscribeResponseDto;
import com.capstone.newsletterapi.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscribe")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @GetMapping("/users/{token}")
    public MailingListDto getMailingList(@PathVariable String token){
        return subscribeService.getMailingListUser(token);
    }
}
