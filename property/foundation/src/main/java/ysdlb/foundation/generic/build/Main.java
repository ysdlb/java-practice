package ysdlb.foundation.generic.build;

import ysdlb.foundation.generic.build.proxy.ProxyA;
import ysdlb.foundation.generic.build.service.ServiceA1;
import ysdlb.foundation.initialize.cycle.A;

public class Main {
    public static void main(String[] args) {
        noBuild();
        System.out.println("===================================================");
        hasBuild();
    }

    public static void noBuild() {
        Assemble assemble1 = new Assemble();

        ProxyA proxyA = new ProxyA();
        ServiceA1 serviceA1 = new ServiceA1();
        proxyA.setServiceA1(serviceA1);
        assemble1.setA(proxyA);

        assemble1.a().doSomething();
    }

    public static void hasBuild() {
        Assemble assemble = new Assemble();
        assemble.build(new ProxyA(), new ServiceA1(), ProxyA::setServiceA1, assemble::setA);
        assemble.a().doSomething();
    }
}
