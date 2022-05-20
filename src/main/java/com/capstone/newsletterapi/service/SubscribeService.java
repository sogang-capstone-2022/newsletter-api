package com.capstone.newsletterapi.service;

import com.capstone.newsletterapi.dto.SubscribeRequestDto;
import com.capstone.newsletterapi.dto.SubscribeResponseDto;
import com.capstone.newsletterapi.model.User;
import com.capstone.newsletterapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SubscribeService {
    private final UserRepository userRepository;

    @Transactional
    public SubscribeResponseDto subscribe(SubscribeRequestDto subscribeRequestDto) {
        String email = subscribeRequestDto.getEmail();
        Optional<User> byEmail = userRepository.findByEmail(email);

        if (byEmail.isEmpty()) {
            User user = new User();
            user.setEmail(email);
            user.setIsAuthenticated(true);
            User save = userRepository.save(user);
            UUID userId = save.getUserId();

            return new SubscribeResponseDto(userId, true);
        }

        return new SubscribeResponseDto(null, false);
    }

    @Transactional
    public SubscribeResponseDto cancel(UUID uuid) {
        Optional<User> byId = userRepository.findById(uuid);

        if (byId.isPresent()) {
            User user = byId.get();
            if (user.getIsAuthenticated()) {
                user.setIsAuthenticated(false);
                return new SubscribeResponseDto(uuid, true);
            }
            return new SubscribeResponseDto(uuid, false);
        }
        return new SubscribeResponseDto(null, false);
    }
}
