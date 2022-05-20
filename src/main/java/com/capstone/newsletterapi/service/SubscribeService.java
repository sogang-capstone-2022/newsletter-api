package com.capstone.newsletterapi.service;

import com.capstone.newsletterapi.dto.MailingListDto;
import com.capstone.newsletterapi.dto.SubscribeRequestDto;
import com.capstone.newsletterapi.dto.SubscribeResponseDto;
import com.capstone.newsletterapi.model.User;
import com.capstone.newsletterapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SubscribeService {
    @Value("${subscribe.token}")
    private String subscribeToken;
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

    @Transactional(readOnly = true)
    public MailingListDto getMailingListUser(String token) {
        if (this.subscribeToken.equals(token)) {
            List<User> subscribeUsers = userRepository.findByIsAuthenticatedTrue();

            List<String> emailList = subscribeUsers.stream()
                    .map(User::getEmail)
                    .collect(Collectors.toList());

            return new MailingListDto(emailList, true);
        }

        return new MailingListDto(null, false);
    }
}
