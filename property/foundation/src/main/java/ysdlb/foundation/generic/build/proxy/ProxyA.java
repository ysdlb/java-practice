package ysdlb.foundation.generic.build.proxy;

import ysdlb.foundation.generic.build.service.ServiceA1;

public class ProxyA {
    ServiceA1 serviceA1;

    public void setServiceA1(ServiceA1 serviceA1) {
        this.serviceA1 = serviceA1;
    }

    public void doSomething() {
        System.out.println(serviceA1.sign);
    }
}
