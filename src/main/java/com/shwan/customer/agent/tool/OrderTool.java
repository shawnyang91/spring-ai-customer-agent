package com.shwan.customer.agent.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class OrderTool {

    @Tool(description = "根据订单号查询订单状态、金额、商品列表及预计发货时间")
    public Map<String, Object> queryOrder(@ToolParam(description = "orderId") String orderId) {
        // TODO: 替换为实际 Feign/HTTP 调用订单微服务
        return Map.of(
                "orderId", orderId,
                "status", "已发货",
                "amount", 299.00,
                "items", java.util.List.of("智能音箱", "充电线"),
                "eta", "2026-05-08"
        );
    }
}