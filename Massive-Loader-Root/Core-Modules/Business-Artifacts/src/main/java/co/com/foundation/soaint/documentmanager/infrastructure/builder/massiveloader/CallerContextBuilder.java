package co.com.foundation.soaint.documentmanager.infrastructure.builder.massiveloader;

import co.com.foundation.soaint.infrastructure.builder.Builder;
import co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain.CallerContext;

public class CallerContextBuilder implements Builder<CallerContext>{

    private String beanName;
    private String methodName;
    private Class serviceInterface;
    
    private CallerContextBuilder() {
    }
    
    public static CallerContextBuilder newBuilder(){
        return new CallerContextBuilder();
    }

    public CallerContextBuilder withBeanName(String beanName) {
        this.beanName = beanName;
        return this;
    }

    public CallerContextBuilder withMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public CallerContextBuilder withServiceInterface(Class serviceInterface) {
        this.serviceInterface = serviceInterface;
        return this;
    }

    @Override
    public CallerContext build() {
        return new CallerContext(beanName,methodName,serviceInterface);
    }
}
