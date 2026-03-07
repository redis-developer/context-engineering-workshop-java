# Context Engineering Workshop for Java Developers

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java 21+](https://img.shields.io/badge/Java-21%2B-blue.svg)](https://www.oracle.com/java/technologies/downloads)
[![LangChain4J](https://img.shields.io/badge/LangChain4J-Framework-green.svg)](https://github.com/langchain4j/langchain4j)
[![Redis Agent Memory](https://img.shields.io/badge/Redis-Agent%20Memory-DC382D.svg)](https://redis.github.io/agent-memory-server/)
[![Redis LangCache](https://img.shields.io/badge/Redis-LangCache-DC382D.svg)](https://redis.io/langcache/)

## Overview
Welcome to this hands-on workshop, where you'll learn to implement sophisticated context-engineering patterns. Context Engineering is the practice of strategically designing, structuring, and optimizing the information provided to AI models (particularly LLMs) to achieve desired outputs. It goes beyond simple prompt engineering by considering the entire context window and how data is organized, presented, and sequenced to maximize model performance. In this workshop, you will learn how to implement this using [Java](https://www.java.com/en/), [LangChain4J](https://github.com/langchain4j/langchain4j), and [Redis](https://redis.io/).

### 🤔 Why Context Engineering?

LLMs have limited context windows and no inherent memory between conversations. Without proper context engineering:
- Models lose track of conversation history
- Responses lack relevant domain knowledge
- Token limits are exceeded, causing failures
- Repeated API calls increase costs

This workshop teaches you patterns to solve these challenges systematically.

### 🎯 What you'll build

By the end of this workshop, you'll have built a complete AI application featuring:
* LLM integration using OpenAI, Spring Boot, and LangChain4J
* Vector embeddings for semantic search with a chunking strategy
* RAG (Retrieval-Augmented Generation) with knowledge bases
* Dual-layer memory architecture (short-term and long-term memory)
* Query compression techniques for efficient context retrieval
* Content Reranking to optimize the relevance of retrieved information
* Few-shot learning pattern for improved generated responses
* Dynamic context window management based on token limits
* Semantic caching to optimize performance and reduce LLM costs

## 📋 Prerequisites

### Required knowledge
* Basic understanding of Java programming
* Basic knowledge of LLMs and AI concepts
* Familiarity with command-line interfaces
* Basic understanding of Docker and Git
* Familiarity with RESTful APIs

### Required software
The required software to complete this workshop depends on the option you select below. You can choose from the following setup options:

#### Option 1: GitHub Codespaces
* GitHub account
* Access to GitHub Codespaces (quota/billing enabled)
* Browser or VS Code with the GitHub Codespaces extension

#### Option 2: Dev Containers locally
* [Docker](https://docs.docker.com/get-docker/)
* Java IDE compatible with [Dev Containers](https://containers.dev/)
  * [VS Code](https://code.visualstudio.com/)
  * [IntelliJ IDEA](https://www.jetbrains.com/idea/)

#### Option 3: Local development
* [Java 21+](https://www.oracle.com/java/technologies/downloads)
* [Maven 3.9+](https://maven.apache.org/install.html)
* [Node.js](https://nodejs.org/en/download/)
* [Docker](https://docs.docker.com/get-docker/)
* [Git](https://git-scm.com/install/)
* Java IDE

### Required accounts
| Account                                                  | Description                                              | Cost                  |
|:---------------------------------------------------------|:---------------------------------------------------------|:----------------------|
| [OpenAI](https://auth.openai.com/create-account)         | LLM that will power the responses for the AI application | Free trial sufficient |
| [Redis Cloud](https://redis.io/try-free)                 | Semantic caching service powered by Redis LangCache      | Free tier sufficient  |

> 💡 **Note on OpenAI costs:** While OpenAI occasionally offers free trial credits to new accounts, this workshop assumes pay-as-you-go pricing. The estimated cost of $1-3 covers all 9 labs. You can monitor your usage in the [OpenAI dashboard](https://platform.openai.com/usage) to track costs in real-time.

## 🗺️ Workshop Structure
This workshop has an estimated duration of 2 hours and is organized into 9 progressive labs, each building on the previous one. Each lab introduces a specific context engineering challenge, which is then addressed in the subsequent lab.

| Lab | Topic                                                                                                                                                       | Duration | Branch                            |
|:----|:------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------|:----------------------------------|
| 1   | [Set up and deploy the AI application](../../tree/lab-1-starter/README.md)                   | 25 mins  | `lab-1-starter` |
| 2   | [Enabling short-term memory with chat memory](../../tree/lab-2-starter/README.md)            | 10 mins  | `lab-2-starter` |
| 3   | [Knowledge base with embeddings, parsers, and splitters](../../tree/lab-3-starter/README.md) | 10 mins  | `lab-3-starter` |
| 4   | [Implementing basic RAG with knowledge base data](../../tree/lab-4-starter/README.md)        | 15 mins  | `lab-4-starter` |
| 5   | [Enabling on-demand context management for memories](../../tree/lab-5-starter/README.md)     | 10 mins  | `lab-5-starter` |
| 6   | [Implementing query compression and context reranking](../../tree/lab-6-starter/README.md)   | 15 mins  | `lab-6-starter` |
| 7   | [Implementing a few-shot into the system prompt design](../../tree/lab-7-starter/README.md)  | 05 mins  | `lab-7-starter` |
| 8   | [Enabling token management to handle token limits](../../tree/lab-8-starter/README.md)       | 05 mins  | `lab-8-starter` |
| 9   | [Implementing semantic caching for conversations](../../tree/lab-9-starter/README.md)        | 25 mins  | `lab-9-starter` |

Each lab also contains a corresponding `lab-X-solution` branch with the completed code for reference. You can use this branch to compare your current implementation using `git diff {lab-X-solution}`. Alternatively, you can switch to the solution branch at any time during the lab if you are falling behind or to get unstuck.

## 🚀 Getting Started

### Step 1: Choose your Setup Option
Pick one of the setup options from the Prerequisites section:
- GitHub Codespaces
- Dev Containers locally
- Local development

### Step 2: Start your Workspace

If you are using **GitHub Codespaces**:
- Create a new codespace for this repository. You can find instructions on how to create a new codespace [here](https://docs.github.com/en/codespaces/getting-started/quickstart).

If you are using **Dev Containers locally**:
- Clone the repository:
  ```bash
  git clone https://github.com/redis-developer/context-engineering-workshop-java.git
  ```
- Open the project in a Dev Container. The instructions for doing this vary by IDE. Follow the guides for either [VS Code](https://code.visualstudio.com/docs/devcontainers/containers#_quick-start-open-an-existing-folder-in-a-container) or [IntelliJ IDEA](https://www.jetbrains.com/help/idea/start-dev-container-from-welcome-screen.html).

If you are using **Local development**:
- Clone the repository:
  ```bash
  git clone https://github.com/redis-developer/context-engineering-workshop-java.git
  ```
- Verify the installed tools:
  ```bash
  java -version
  mvn -version
  npm --version
  docker --version
  git --version
  ```

> 💡 For Dev Containers and GitHub Codespaces, use the forwarded URL from the Ports panel for browser access to app ports (for example, 3000, 8080, and 5540), and use sidecar service DNS names for terminal commands inside the container workspace (for example, `http://redis-agent-memory-server:8000`).

### Step 3: Begin your First Lab

- Switch to the `lab-1-starter` branch
```bash
git checkout lab-1-starter
```
- Then follow the README instructions

## 📚 Resources
- [Alexa Skill using Context Engineering](https://github.com/redis-developer/agent-memory-server-with-alexa-demo)
- [LangChain4J Documentation](https://docs.langchain4j.dev/)
- [Redis Agent Memory Server](https://redis.github.io/agent-memory-server/)
- [Redis LangCache Documentation](https://redis.io/docs/latest/develop/ai/langcache/)
- [OpenAI API Reference](https://platform.openai.com/docs/api-reference)

## 🤝 Contributing
Contributions are welcome! Please feel free to submit a Pull Request. For major changes, please open an issue first to discuss what you would like to change.

## 👥 Maintainers
- Ricardo Ferreira — [@riferrei](https://github.com/riferrei)

## 📄 License
This project is licensed under the [MIT License](./LICENSE).
