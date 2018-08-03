package com.alipay.payment.service.remote.stub.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.alipay.payment.service.remote.stub.PaymentRemoteSerivceStub;
import com.alipay.payment.service.remote.stub.PaymentType;
import com.alipay.payment.service.value.ConsultResult;

@Component
public class CouponPay implements PaymentRemoteSerivceStub {

    private Boolean isEnabled = false;

    @PostConstruct
    public void initialize() {
        PaymentType.COUPON_PAY.setPaymentRemoteSerivceStub(this);
    }

    @Override
    public ConsultResult isEnabled() {
        return new ConsultResult(isEnabled, isEnabled ? "" : "Not enough coupon");
    }

    @Override
    public void enable() {
        isEnabled = true;
    }

    @Override
    public void disable() {
        isEnabled = false;
    }
}
