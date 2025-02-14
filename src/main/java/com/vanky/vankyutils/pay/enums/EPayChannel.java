package com.vanky.vankyutils.pay.enums;

// 支付类型枚举
public enum EPayChannel {
    WECHAT(0),
    ALIPAY(1);

    private int value;

    EPayChannel(int value){
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    public static EPayChannel convert(int value) {
        for(EPayChannel v : values()) {
            if(v.value() == value) {
                return v;
            }
        }
        return null;
    }
}