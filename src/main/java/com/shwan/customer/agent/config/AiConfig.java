package com.shwan.customer.agent.config;

import com.shwan.customer.agent.advisor.SecurityFilterAdvisor;
import com.shwan.customer.agent.tool.LogisticsTool;
import com.shwan.customer.agent.tool.OrderTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbacks;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.advisor.QuestionAnswerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    public ChatClient customerServiceClient(ChatModel chatModel,
                                            ChatMemory chatMemory,
                                            VectorStore vectorStore,
                                            OrderTool orderTool,
                                            LogisticsTool logisticsTool) {
        ToolCallback[] tools = ToolCallbacks.from(orderTool, logisticsTool);

        return ChatClient.builder(chatModel)
                .defaultSystem("""
                    你是专业电商智能客服。语气友好、简洁准确。
                    优先使用提供的知识库和业务工具解答。
                    不确定时引导用户转人工，禁止编造信息。
                    当前时间：{current_date}
                    """)
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory))
                .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore))
                .defaultAdvisors(new SecurityFilterAdvisor())
                .defaultTools(tools)
                .build();
    }
}