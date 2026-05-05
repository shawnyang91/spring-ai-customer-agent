package com.shwan.customer.agent.advisor;

import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.client.advisor.api.AdvisedRequest;
import org.springframework.ai.chat.client.advisor.api.AdvisedResponse;

import java.util.regex.Pattern;

public class SecurityFilterAdvisor implements Advisor {
    private static final Pattern SENSITIVE_PATTERN = Pattern.compile("(?=.*(?:delete|update|drop|exec|<script|password|身份证|银行卡))", Pattern.CASE_INSENSITIVE);

    @Override
    public AdvisedResponse advise(AdvisedRequest request, Chain chain) {
        String userInput = request.userText();
        if (userInput != null && SENSITIVE_PATTERN.matcher(userInput).find()) {
            throw new SecurityException("输入包含潜在风险指令，已拦截");
        }
        return chain.call(request);
    }

    @Override
    public String getName() {
        return "SecurityFilter";
    }
}