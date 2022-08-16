package learn.ysdlb.spi.autoservice;

import java.util.ServiceLoader;

public class AutoServiceApplication {
    public static void main(String[] args) {
        ServiceLoader<UserService> loader = ServiceLoader.load(UserService.class);

        for (UserService userService: loader) {
            System.out.println(userService.userName());
        }
    }
}
