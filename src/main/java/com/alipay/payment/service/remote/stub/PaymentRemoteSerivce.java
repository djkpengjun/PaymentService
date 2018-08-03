package com.alipay.payment.service.remote.stub;

import org.springframework.stereotype.Service;

import com.alipay.payment.service.value.ConsultResult;

@Service
public class PaymentRemoteSerivce {

    public ConsultResult isEnabled(String paymentType) {
        return PaymentType.valueOf(paymentType).getPaymentRemoteSerivceStub().isEnabled();
    }
}
