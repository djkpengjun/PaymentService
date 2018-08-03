package com.alipay.payment.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alipay.payment.config.AppConfig;
import com.alipay.payment.service.remote.stub.PaymentRemoteSerivce;
import com.alipay.payment.service.remote.stub.PaymentType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class PaymentRemoteSerivceTests {

    @Test
    public void testEnableBalancePay() {
        PaymentRemoteSerivce service = new PaymentRemoteSerivce();
        PaymentType.BALANCE_PAY.getPaymentRemoteSerivceStub().disable();
        assertThat(service.isEnabled(PaymentType.BALANCE_PAY.toString()).getIsEnable()).isFalse();
        PaymentType.BALANCE_PAY.getPaymentRemoteSerivceStub().enable();;
        assertThat(service.isEnabled(PaymentType.BALANCE_PAY.toString()).getIsEnable()).isTrue();
    }

  
}
