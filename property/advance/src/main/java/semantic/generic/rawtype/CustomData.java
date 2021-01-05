package semantic.generic.rawtype;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class CustomData<T> {

    private T data;

    private List<Integer> list = new ArrayList<>();

    private Map<Integer, String> map = new HashMap<>();

    public CustomData() {}

    public CustomData(T data) {
        this.data = data;
    }
}
