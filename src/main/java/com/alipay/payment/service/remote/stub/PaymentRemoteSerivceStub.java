package com.alipay.payment.service.remote.stub;

import com.alipay.payment.service.value.ConsultResult;

public interface PaymentRemoteSerivceStub {

    public ConsultResult isEnabled();

    public void enable();

    public void disable();
}
