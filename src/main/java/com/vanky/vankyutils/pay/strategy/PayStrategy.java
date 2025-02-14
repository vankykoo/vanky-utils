package com.vanky.vankyutils.pay.strategy;

import com.vanky.vankyutils.pay.pojo.PayOrder;

import java.util.Map;

/**
 * @author vanky
 * @create 2025/2/13 22:46
 * @description
 */
public interface PayStrategy {

    /**
     * 创建订单
     * @param itemId 商品id
     * @param userId 用户id
     * @return
     */
    PayOrder createOrder(String itemId, String userId);

    /**
     * 第三方支付结果回调
     * @param payload 加密后支付信息
     */
    void callback(String payload);

    /**
     * 验证签名
     * @param params
     * @return
     */
    boolean verifySign(Map<String, String> params);
}
