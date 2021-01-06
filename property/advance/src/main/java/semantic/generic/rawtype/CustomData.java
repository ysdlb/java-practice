package semantic.generic.rawtype;

import lombok.Data;

import java.util.*;

@Data
public class CustomData<T> {

    private T data;

    private List<Integer> list = new ArrayList<>();

    private Map<Integer, String> map = new HashMap<>();

    private Set<? extends Number> set = new HashSet<>();

    public CustomData() {}

    public CustomData(T data) {
        this.data = data;
    }
}
