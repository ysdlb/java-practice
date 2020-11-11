package ysdlb.foundation.generic.build;

import ysdlb.foundation.generic.build.proxy.ProxyA;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Assemble {
    private ProxyA a;

    public void setA(ProxyA a) {
        this.a = a;
    }

    public ProxyA a() {
        return a;
    }

    public <P, S> Assemble build(P proxy, S service, BiConsumer<P, S> setter, Consumer<P> add) {
        setter.accept(proxy, service);
        add.accept(proxy);
        return this;
    }
}
