package proxy.dynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import proxy.dynamic.target.Car;
import proxy.dynamic.target.CarImpl;

import java.lang.reflect.Proxy;

@DisplayName("for learn proxy")
public class CarTest {

    @Test
    @DisplayName("a simple dynamic proxy")
    void simpleProxyTest() {
        CarImpl car = new CarImpl();
        CarHandler carHandler = new CarHandler(car);


        Car proxy = (Car) Proxy.newProxyInstance(
                car.getClass().getClassLoader(),
                car.getClass().getInterfaces(),
                carHandler
        );

        proxy.run();
        System.out.println("===========hashCode()===========");
        System.out.println("this: " + proxy.hashCode());

        System.out.println("carHandler: " + carHandler.hashCode());
        System.out.println("car: " + car.hashCode());
        System.out.println("================");
        System.out.println(proxy.get());

        System.out.println("\n++++++++++++++++++++++++++++++\n");

        Car proxy2 = (Car) Proxy.newProxyInstance(
                car.getClass().getClassLoader(),
                car.getClass().getInterfaces(),
                (proxy_, method_, args_) -> {
                    System.out.println("before proxy2");
                    Object result = method_.invoke(car, args_);
                    System.out.println("after proxy2");
                    return  result;
                }
        );
        proxy2.run();
        System.out.println("================");
        System.out.println(proxy2.get());
    }
}
