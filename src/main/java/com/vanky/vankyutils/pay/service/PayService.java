package com.vanky.vankyutils.pay.service;

import com.vanky.vankyutils.pay.pojo.PayOrder;

/**
 * @author vanky
 * @create 2025/2/13 23:41
 * @description
 */
public interface PayService {

    /**
     * 创建订单
     * @param itemId
     * @param userId
     * @param payChannel
     * @return
     */
    PayOrder createOrder(String itemId, String userId, int payChannel);

    /**
     * 支付结果回调
     * @param payload
     * @param payChannel
     */
    void callback(String payload, int payChannel);
}
