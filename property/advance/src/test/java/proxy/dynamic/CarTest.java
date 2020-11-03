package proxy.dynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import proxy.dynamic.target.Car;
import proxy.dynamic.target.CarImpl;

import java.lang.reflect.Proxy;

@DisplayName("for learn proxy")
public class CarTest {

    @Test
    @DisplayName("a simple dynamic proxy")
    void simpleProxyTest() {
        Car car = new CarImpl();
        CarHandler carHandler = new CarHandler(car);

        Car proxy = (Car) Proxy.newProxyInstance(
                car.getClass().getClassLoader(),
                car.getClass().getInterfaces(),
                carHandler
        );

        proxy.run();
    }

}
