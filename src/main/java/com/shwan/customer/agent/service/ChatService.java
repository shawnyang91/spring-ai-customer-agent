package com.shwan.customer.agent.service;

import com.shwan.customer.agent.config.Resilience4jConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatClient customerServiceClient;
    private final Resilience4jConfig.FallbackService fallbackService;

    public Flux<String> streamChat(String sessionId, String message) {
        try {
            return customerServiceClient.prompt()
                    .user(message)
                    .system("sessionId=" + sessionId)
                    .stream()
                    .content();
        } catch (Exception e) {
            // 实际生产建议使用 Resilience4j fallback 或全局异常处理
            return Flux.just(fallbackService.getFallbackResponse());
        }
    }
}