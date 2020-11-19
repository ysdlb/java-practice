package bytecode.junior;

import java.util.ArrayList;
import java.util.List;

public class LocalDemo {
    private List<String> data = new ArrayList<>();

    public void doSomething0(String param) {
        if (data != null && !data.isEmpty() && data.contains(param))
            System.out.println(data.indexOf(param));
    }

    public void doSomething1(String param) {
        List<String> data = this.data;
        if (data != null && !data.isEmpty() && data.contains(param))
            System.out.println(data.indexOf(param));
    }
}
