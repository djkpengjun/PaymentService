package com.alipay.payment.service.remote.stub;

public enum PaymentType {

    BALANCE_PAY,    // pay with balance
    COUPON_PAY,     // pay with coupon discount
    VOUCHER_PAY,    // pay with something like gift card
    LAISEE_PAY;     // pay with red pocket, so called 红包 in Chinese 

    private PaymentRemoteSerivceStub remotePaymentSerivce;

    public void setPaymentRemoteSerivceStub(PaymentRemoteSerivceStub remotePaymentSerivce) {
        this.remotePaymentSerivce = remotePaymentSerivce;
    }

    public PaymentRemoteSerivceStub getPaymentRemoteSerivceStub() {
        return remotePaymentSerivce;
    }
}
