package com.vanky.vankyutils.pay.strategy;

import com.vanky.vankyutils.pay.pojo.PayOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
class WeChatPayStrategy extends AbstractPayStrategy {
    @Override
    public PayOrder createOrder(String itemId, String userId) {
        // 创建订单
        // 向第三方发起支付请求
        log.info("WeChatPayStrategy createOrder itemId:{} userId:{}", itemId, userId);
        return null;
    }

    @Override
    public void callback(String payload) {
        // 验证身份
        // 订单状态修改
        // 发货处理
        log.info("WeChatPayStrategy callback payload:{}", payload);
    }

    @Override
    public boolean verifySign(Map<String, String> params) {
        // 微信特有的签名验证逻辑
        log.info("Performing WeChat signature verification {}", params);
        return super.verifySign(params);
    }
}