package com.vanky.vankyutils.pay.pojo;

import lombok.Data;

/**
 * @author vanky
 * @create 2025/2/13 23:14
 * @description
 */
@Data
public class PayOrder {
    // 订单号
    private String orderId;
    // 订单状态
    private int orderStatus;
    // 商品id
    private String itemId;
}
