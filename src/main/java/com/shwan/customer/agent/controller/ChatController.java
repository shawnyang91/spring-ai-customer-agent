package com.shwan.customer.agent.controller;

import com.shwan.customer.agent.dto.ChatRequest;
import com.shwan.customer.agent.dto.ChatResponse;
import com.shwan.customer.agent.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatResponse> chatStream(@RequestBody ChatRequest request) {
        return chatService.streamChat(request.getSessionId(), request.getMessage())
                .map(content -> {
                    ChatResponse resp = new ChatResponse();
                    resp.setSessionId(request.getSessionId()); // Changed from setSessionId to setSessionId
                    resp.setMessage(content);
                    resp.setDone(false);
                    return resp;
                })
                .concatWith(Flux.just(new ChatResponse() {{
                    setSessionId(request.getSessionId());
                    setMessage("");
                    setDone(true);
                }}));
    }
}