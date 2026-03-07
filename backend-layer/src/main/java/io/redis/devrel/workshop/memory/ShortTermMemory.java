package io.redis.devrel.workshop.memory;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import io.redis.devrel.workshop.extensions.WorkingMemoryChat;
import io.redis.devrel.workshop.extensions.WorkingMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShortTermMemory {

    @Value("${agent.memory.server.url}")
    private String agentMemoryServerUrl;

    @Autowired
    private String userId;

    @Bean
    public ChatMemoryStore chatMemoryStore() {
        // TODO: Implement a WorkingMemoryStore that connects to the agentMemoryServerUrl
        return null;
    }

    @Bean
    public ChatMemory chatMemory(ChatMemoryStore chatMemoryStore) {
        // TODO: Implement a WorkingMemoryChat that uses the WorkingMemoryStore
        return null;
    }

}
