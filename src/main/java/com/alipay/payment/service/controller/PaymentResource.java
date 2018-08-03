package com.alipay.payment.service.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.payment.service.remote.stub.PaymentType;

@RequestMapping("/payment")
public interface PaymentResource {

    @GetMapping("/types")
    public CompletableFuture<List<PaymentType>> getEnabledPaymentTypes();

    @PutMapping("types/{type}/action/{action}")
    public default void disable(@NotNull @PathVariable("paymentType") PaymentType paymentType) {
        paymentType.getPaymentRemoteSerivceStub().disable();
    }

    @PutMapping("types/{type}/action/enable")
    public default void enable(@NotNull @PathVariable("paymentType") PaymentType paymentType) {
        paymentType.getPaymentRemoteSerivceStub().enable();
    }
}