package proxy.dynamic;

import proxy.dynamic.target.Car;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * jdk 动态代理类
 */
public class CarHandler implements InvocationHandler {

    // 真实类的对象
    private Object car;

    public CarHandler(Object obj) {
        this.car = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result = method.invoke(car, args);
        System.out.println("after");
        return  result;
    }

}
