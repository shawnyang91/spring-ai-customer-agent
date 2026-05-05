package com.shwan.customer.agent.config;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Component;

@Component
public class Resilience4jConfig {

    @Component
    public static class FallbackService {
        @CircuitBreaker(name = "ai-chat", fallbackMethod = "fallback")
        public String getFallbackResponse() {
            return "客服系统暂时繁忙，已为您记录问题，人工将尽快联系您。";
        }

        public String fallback(Throwable t) {
            return "服务降级：模型调用异常，请稍后重试或联系人工客服。";
        }
    }
}