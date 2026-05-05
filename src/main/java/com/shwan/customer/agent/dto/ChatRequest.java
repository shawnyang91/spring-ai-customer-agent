package com.shwan.customer.agent.dto;

import lombok.Data;

/**
 * @author yangshumin
 * @version v1.0
 * @date 2026/5/5
 */
@Data
public class ChatRequest {
    private String sessionId;
    private String message;
}