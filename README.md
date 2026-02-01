# Lab 2: Enabling Short-term Memory with Chat Memory

## 🎯 Learning Objectives

By the end of this lab, you will:
- Set up the Redis Agent Memory Server using Docker
- Implement short-term memory using LangChain4J's ChatMemory
- Enable conversation continuity within a single chat session
- Understand how chat memory stores maintain conversation context
- Test memory retention across multiple message exchanges

#### 🕗 Estimated Time: 10 minutes

## 🏗️ What You're Building

In this lab, you'll enhance the basic chat application with short-term memory capabilities, allowing the AI to remember previous messages within a conversation session. This includes:

- **ChatMemoryStore**: LangChain4J implementation for the Agent Memory Server
- **Context Preservation**: Maintaining conversation flow across messages
- **Memory Configuration**: Setting up memory boundaries and constraints

### Architecture Overview

![architecture-overview.png](images/architecture-overview.png)

## 📋 Prerequisites Check

Before starting, ensure you have:

- [ ] Completed Lab 1 successfully
- [ ] Backend application running without errors
- [ ] Frontend application accessible at http://localhost:3000
- [ ] OpenAI API key configured and working

## 🚀 Setup Instructions

### Step 1: Switch to the Lab 2 Branch

```bash
git checkout lab-2-starter
```

### Step 2: Define the Redis Agent Memory Server URL

```bash
AGENT_MEMORY_SERVER_URL=http://localhost:8000
```

### Step1 3: Start the Redis Agent Memory Server

```bash
docker compose up -d
```

### Step 4: Verify if the Containers are Running

```bash
docker ps --format "table {{.ID}}\t{{.Image}}\t{{.Status}}\t{{.Ports}}\t{{.Names}}"
```

You should see something like this:

```bash
CONTAINER ID   IMAGE                                  STATUS                   PORTS                                         NAMES
48df24537047   redislabs/agent-memory-server:0.12.7   Up 2 minutes (healthy)   0.0.0.0:8000->8000/tcp, [::]:8000->8000/tcp   redis-agent-memory-server
33aade92ddbd   redis/redisinsight:3.0.2               Up 2 minutes (healthy)   0.0.0.0:5540->5540/tcp, [::]:5540->5540/tcp   redis-insight
3c449915bb35   redis:8.4.0                            Up 2 minutes (healthy)   0.0.0.0:6379->6379/tcp, [::]:6379->6379/tcp   redis-database
```

### Step 5: Review the ChatMemoryStore Implementation

Open `backend-layer/src/main/java/io/redis/devrel/workshop/extensions/WorkingMemoryStore.java` and review the code.

This is a wrapper around the Redis Agent Memory Server REST APIs implemented using the support for chat memory stored from LangChain4J.

### Step 6: Implement the ChatMemoryStore Bean

Open `backend-layer/src/main/java/io/redis/devrel/workshop/memory/ShortTermMemory.java` and implement the method `chatMemoryStore()`.

Change from this:

```java
@Bean
public ChatMemoryStore chatMemoryStore() {
  // TODO: Implement a WorkingMemoryStore that connects to the agentMemoryServerUrl
  return null;
}
```

To this:

```java
@Bean
public ChatMemoryStore chatMemoryStore() {
    return WorkingMemoryStore.builder()
            .agentMemoryServerUrl(agentMemoryServerUrl)
            .storeAiMessages(true)
            .build();
}
```

This bean will provide the persistence layer for the chat memory, taking care of storing and retrieving messages from the Redis Agent Memory Server.

### Step 7: Implement the ChatMemory Bean

Open `backend-layer/src/main/java/io/redis/devrel/workshop/memory/ShortTermMemory.java` and implement the method `chatMemory()`.

Change from this:

```java
@Bean
public ChatMemory chatMemory(ChatMemoryStore chatMemoryStore) {
  // TODO: Implement a WorkingMemoryChat that uses the WorkingMemoryStore
  return null;
}
```

To this:

```java
@Bean
public ChatMemory chatMemory(ChatMemoryStore chatMemoryStore) {
  return WorkingMemoryChat.builder()
          .id(userId)
          .chatMemoryStore(chatMemoryStore)
          .build();
}
```

This bean will manage the chat memory for each user session, using the `WorkingMemoryStore` to persist messages.

### Step 8: Rebuild and Run the Backend

```bash
cd backend-layer
mvn clean package
mvn spring-boot:run
```

### Step 9: Keep the Frontend Running

The frontend should still be running from Lab 1. If not:

```bash
cd frontend-layer
npm start
```

## 🧪 Testing Your Memory Implementation

### Memory Retention Test

1. Open http://localhost:3000 in your browser
2. Clear any previous conversations (refresh the page)
3. Type "Hi, my name is {{your-name}}" in the chat
4. Verify you receive a response acknowledging your name
5. Type "What is my name?" in the chat
6. **Verify the AI now remembers your name** (unlike in Lab 1)

![lab-2-memory-testing.png](images/lab-2-memory-testing.png)

As you can see, the AI now remembers your name within the same session, demonstrating that short-term memory is functioning correctly. This is possible because now the code is leveraging the Redis Agent Memory Server to store and retrieve conversation history.

If you want to verify the stored messages, you can use RedisInsight to connect to the Redis database and inspect the stored keys and values. Open your browser and type the following:

http://localhost:5540

Click on the database `redis-database:6379` and expand the `Keys` section to see the stored messages. You should see the following key:

![redis-insight-working-memory.png](images/redis-insight-working-memory.png)

If you click on the key, you can see the stored messages in JSON format.

![redis-insight-working-memory-details.png](images/redis-insight-working-memory-details.png)

With this implementation of short-term memory, your chat application can now maintain context within a session, providing a more natural and engaging user experience. This will happen even if the backend layer is restarted.

However, keep in mind that these messages are still short-term, meaning that they are shorted-lived. By default, the implementation of the ChatMemoryStore updates the working memory to be active for 5 minutes. This is controlled by the TTL property of the key.

## 🎨 Understanding the Code

### 1. `ChatMemoryStore`
- Storage implementation for the Agent Memory Server
- Session-based memory isolation with dedicated namespace
- Temporary storage for conversations (up to 5 minutes)

### 2. `ChatMemory`
- Pass-through implementation of a chat memory
- Always keeps all the messages in-memory
- Implemented for testing purposes (not production)

### 2. Memory Integration
- Automatically injected into the AI service
- Maintains conversation context transparently
- No changes needed to `BasicChatAssistant`

## 🔍 What's Still Missing? (Context Engineering Perspective)

Your application now has short-term memory, but still lacks:
- ❌ **No Long-term Memory**: Memory is lost between sessions
- ❌ **No Knowledge Base**: No knowledge besides the conversation
- ❌ **No Semantic Search**: Cannot retrieve relevant information

**Next labs will address these limitations!**

## 🐛 Troubleshooting

### Common Issues and Solutions

<details>
<summary>Error: "ChatMemory bean not found"</summary>

Solution:
- Ensure `@Configuration` annotation is present on ShortTermMemory class
- Verify all `@Bean` methods are properly annotated
- Check that component scanning includes the memory package
</details>

<details>
<summary>AI still doesn't remember previous messages</summary>

Solution:
- Verify ChatMemoryStore bean is properly configured
- Check that the same userId is being used across messages
- Check if the Agent Memory Server is running and accessible
</details>

<details>
<summary>Memory seems to cut off too early</summary>

Solution:
- Check if you are not having network issues (unlikely with local setup)
- Review token limit configuration in your OpenAI account
- Consider the model's token limit (gpt-3.5-turbo has 4096 tokens)
</details>

## 🎉 Lab Completion

Congratulations! You've successfully:
- ✅ Implemented short-term chat memory
- ✅ Enabled conversation continuity within sessions
- ✅ Tested memory retention across messages

## 📚 Additional Resources

- [LangChain4J Chat Memory](https://docs.langchain4j.dev/tutorials/chat-memory)
- [Token Counting in LLMs](https://platform.openai.com/tokenizer)
- [Context Window Management](https://docs.langchain4j.dev/integrations/language-models/open-ai)

## ➡️ Next Steps

You're ready for [Lab 3: Knowledge Base with Embeddings, Parsers, and Splitters](../lab-3-starter/README.md) where you'll add document processing and knowledge base capabilities.

```bash
git checkout lab-3-starter
```