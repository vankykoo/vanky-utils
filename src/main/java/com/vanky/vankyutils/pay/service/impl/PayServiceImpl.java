package com.vanky.vankyutils.pay.service.impl;

import com.vanky.vankyutils.pay.enums.EPayChannel;
import com.vanky.vankyutils.pay.factory.PayStrategyFactory;
import com.vanky.vankyutils.pay.pojo.PayOrder;
import com.vanky.vankyutils.pay.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vanky
 * @create 2025/2/13 23:42
 * @description
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    @Autowired
    private PayStrategyFactory payStrategyFactory;

    @Override
    public PayOrder createOrder(String itemId, String userId, int payChannel) {
        EPayChannel channel = EPayChannel.convert(payChannel);
        if (channel == null){
            log.error("支付方式不支持");
            return null;
        }
        return payStrategyFactory.getStrategy(channel.name()).createOrder(itemId, userId);
    }

    @Override
    public void callback(String payload, int payChannel) {
        EPayChannel channel = EPayChannel.convert(payChannel);
        if (channel == null){
            log.error("支付方式不支持");
            return;
        }
        payStrategyFactory.getStrategy(channel.name()).callback(payload);
    }
}
