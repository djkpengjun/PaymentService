package com.alipay.payment.service.controller;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.alipay.payment.service.remote.stub.PaymentRemoteSerivce;
import com.alipay.payment.service.remote.stub.PaymentType;
import com.alipay.payment.service.value.ConsultResult;

@Component
public class PaymentTypesCacheService {

    private Logger log = Logger.getLogger(PaymentTypesCacheService.class);

    @Autowired
    private PaymentRemoteSerivce paymentRemoteSerivce;

    private List<PaymentType> enabledPaymentTypes = Collections.emptyList();

    @CachePut(value="paymentTypes")
    public void init() {
        enabledPaymentTypes = Stream.of(PaymentType.values()).parallel().filter(this::isPaymentTypeEnabled)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "paymentTypes")
    public List<PaymentType> getEnabledPayemntTypes() {
        if(!enabledPaymentTypes.isEmpty())
            return enabledPaymentTypes;
        return Stream.of(PaymentType.values()).parallel().filter(this::isPaymentTypeEnabled)
                .collect(Collectors.toList());
    }

    @CacheEvict(value = "paymentTypes")
    public void evict(){
        enabledPaymentTypes = Collections.emptyList();
    }

    private boolean isPaymentTypeEnabled(PaymentType paymentType) {
        ConsultResult result = paymentRemoteSerivce.isEnabled(paymentType.toString());
        if (!result.getIsEnable()) {
            CompletableFuture.runAsync(() -> {
                log.debug("Payment type " + paymentType + " is disabled with error code " + result.getErrorCode());
            });
        }
        return result.getIsEnable();
    }
}
