package com.example.spring_ai_ollama.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ChatController {
    ChatClient chatClient;

    ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "q") String query) {
        String response = this.chatClient.prompt(query).call().content();
        return ResponseEntity.ok(response);
    }
}
