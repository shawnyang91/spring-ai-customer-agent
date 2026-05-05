package com.shwan.customer.agent.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LogisticsTool {

    @Tool(description = "根据物流单号查询当前运输状态、轨迹节点和派送员联系方式")
    public Map<String, Object> trackLogistics(@ToolParam(description = "trackingNo") String trackingNo) {
        // TODO: 替换为实际物流 API
        return Map.of(
                "trackingNo", trackingNo,
                "status", "运输中",
                "currentLocation", "杭州市分拨中心",
                "nextStep", "预计明日送达",
                "courierPhone", "138****5678"
        );
    }
}