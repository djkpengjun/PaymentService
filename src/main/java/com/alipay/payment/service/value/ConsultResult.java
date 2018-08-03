package com.alipay.payment.service.value;

import lombok.Builder;

@Builder
public class ConsultResult {

    public ConsultResult (boolean isEnable, String errorCode){
        this.isEnable = isEnable;
        this.errorCode = errorCode;
    }

    /** 咨询结果是否可用*/
    private boolean isEnable;

    /** 错误码 */
    private String errorCode;

    public boolean getIsEnable(){
        return isEnable;
    }

    public String getErrorCode(){
        return errorCode;
    }

    
}
