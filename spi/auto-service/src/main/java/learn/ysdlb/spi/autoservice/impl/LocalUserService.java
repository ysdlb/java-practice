package learn.ysdlb.spi.autoservice.impl;

import com.google.auto.service.AutoService;
import learn.ysdlb.spi.autoservice.UserService;

@AutoService(UserService.class)
public class LocalUserService implements UserService {
    @Override
    public String userName() {
        return "local user";
    }
}
