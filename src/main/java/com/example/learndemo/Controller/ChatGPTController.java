package com.example.learndemo.Controller;
import com.example.learndemo.Model.Chat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plexpt.chatgpt.ChatGPT;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.CompletableFuture;

@RestController
public class ChatGPTController {
    private ChatGPT chatGPT;

    public ChatGPTController() {
        // Initialize the ChatGPT object with your API key
        this.chatGPT = ChatGPT.builder()
                .apiKey("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")
                .build()
                .init();
    }

    @GetMapping("/chat")
    @ResponseBody
    @Async
    public CompletableFuture<Chat> getResponse(@RequestParam(value = "userid", defaultValue = "0") int userid,
                                               @RequestParam(value = "question", defaultValue = "hello") String question){
        return CompletableFuture.supplyAsync(() -> {
            // Use the context parameter to maintain conversation state
            String res = chatGPT.chat(question);
            ObjectMapper objectMapper = new ObjectMapper();
            Chat message = new Chat(userid, res, 0);
            return message;
        });
    }
}
