package co.com.foundation.soaint.documentmanager.infrastructure.massiveloader.domain;

public class CallerContext<T> {

    private final String beanName;
    private final String methodName;
    private final Class<T> serviceInterface;

    public CallerContext(String beanName, String methodName,Class<T> serviceInterface) {
        this.beanName = beanName;
        this.methodName = methodName;
        this.serviceInterface = serviceInterface;
    }

    public static CallerContext getDefaultContext(){
        return new CallerContext("","",null);
    }

    public String getBeanName() {
        return beanName;
    }

    public String getMethodName() {
        return methodName;
    }

    public Class<T> getServiceInterface() {
        return serviceInterface;
    }
}
