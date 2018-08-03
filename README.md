# PaymentService

The launch banner is designed for AliPay,  do you like it ?
![Colored Banner](https://raw.githubusercontent.com/djkpengjun/PaymentService/master/alipay.png)

## OKR
   
   Objects

    用户有多种支付方式（余额、红包、优惠券，代金券等），假如每种支付方式需要通过实时调用远程服务获取可用性。
    在外部资源环境不变情况下，请设计程序以最短响应时间获得尽可能多的可用支付方式列表。

    假定支付方式可用性咨询服务接口定义：PaymentRemoteSerivce
    接口方法：ConsultResult isEnabled(String paymentType); 
  
    尽快的返回可用的payment type
    
    GET   http://localhost:8080/payment/types#
    
    
   Key results
   
   1) Payment service interface defined and implemented based on java 8 parallel stream
   2) Payment cache and update integrated but need more time to polish 
   3) Swagger API doc / Dockerfile
   4) Make API request asynchronous which introduced by Sprint 4.2 & servlet 3
   5) Unit test is added for key business logic
   6) Logging for disabled payment types with detailed error code
   
   
## How to build and run
   
   mvn clean package
   
   ./target/service-payment-server-0.0.1-SNAPSHOT.jar
   
   
   PUT   http://localhost:8080/payment/types/BALANCE_PAY/action/disable
   
   PUT   http://localhost:8080/payment/types/BALANCE_PAY/action/enable
   
   GET   http://localhost:8080/payment/types#
   
## Roadmap to enhance the performance

   1) To achieve better response speed, cache should be utilized
      Either through redis or memcache,  the disable & enable actions should be broadcase to update the cache 
      
   2) deployment file need to be defined for kubernetes integration
   
   3) Spring WebFlux should be used since it uses neety under the hood to support larger volume of requests

   4) Spring Cache annotaion needs to be integrated with sprint redis
   
      https://www.journaldev.com/18141/spring-boot-redis-cache
