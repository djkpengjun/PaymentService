package com.alipay.payment.service.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.payment.config.AsyncConfig;
import com.alipay.payment.service.remote.stub.PaymentRemoteSerivce;
import com.alipay.payment.service.remote.stub.PaymentType;
import com.alipay.payment.service.value.ConsultResult;

@RestController
public class PaymentResourceController implements PaymentResource, ApplicationRunner {

    private Logger log = Logger.getLogger(PaymentResourceController.class);

    @Autowired
    private PaymentRemoteSerivce paymentRemoteSerivce;

    @Autowired
    private PaymentTypesCacheService cacheService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        cacheService.init();
    }

    @Override
    @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
    public CompletableFuture<List<PaymentType>> getEnabledPaymentTypes() {

        log.debug("Call api to get enabled payment types");
        CompletableFuture<List<PaymentType>> paymentTypeConsultPromise = new CompletableFuture<>();
        CompletableFuture.runAsync(() -> {
            try {
                paymentTypeConsultPromise.complete(getEnabledPayemntTypes());
            } catch (Exception e) {
                paymentTypeConsultPromise.completeExceptionally(e);
            }
        });
        return paymentTypeConsultPromise;
    }

    private List<PaymentType> getEnabledPayemntTypes() {
        return Stream.of(PaymentType.values()).parallel().filter(this::isPaymentTypeEnabled)
                .collect(Collectors.toList());
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
