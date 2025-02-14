package com.vanky.vankyutils.pay.strategy;

import java.math.BigDecimal;
import java.util.Map;

abstract class AbstractPayStrategy implements PayStrategy {
    // 通用参数校验
    protected boolean validateAmount(BigDecimal amount) {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }

    // 通用日志记录
    protected void logPayment(String orderId, String channel) {
        System.out.println("[" + channel + "] Payment logged: " + orderId);
    }

    // 通用签名验证模板方法
    @Override
    public boolean verifySign(Map<String, String> params) {
        // 这里实现通用验证逻辑，子类可以覆盖
        System.out.println("Performing generic signature verification");
        return true;
    }
}