# PaymentService

The launch banner is designed for AliPay,  do you like it ?
![Colored Banner](https://raw.githubusercontent.com/djkpengjun/RPNCalculatorService/master/images/banner_airwallex.png)

## OKR
   
   Objects

    用户有多种支付方式（余额、红包、优惠券，代金券等），假如每种支付方式需要通过实时调用远程服务获取可用性。
    在外部资源环境不变情况下，请设计程序以最短响应时间获得尽可能多的可用支付方式列表。

    假定支付方式可用性咨询服务接口定义：PaymentRemoteSerivce
    接口方法：ConsultResult isEnabled(String paymentType); 
  
    尽快的返回可用的payment type
    
    
   Key results
   
   1) Payment service interface defined and implemented based on java 8
   2) Payment cache and update with 
   3) Swagger API doc / Dockerfile
   4) Make API request asynchronous which introduced by Sprint 4.2 & servlet 3
