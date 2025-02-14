package com.vanky.vankyutils.pay.factory;

import com.vanky.vankyutils.pay.strategy.PayStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vanky
 * @create 2025/2/13 23:31
 * @description
 */
@Component
public class PayStrategyFactory {

    private static Map<String, PayStrategy> strategies = new HashMap<>();

    @Autowired
    public PayStrategyFactory(Map<String, PayStrategy> payStrategies) {
        strategies = payStrategies;
    }

    public PayStrategy getStrategy(String name) {
        return strategies.get(name);
    }
}
