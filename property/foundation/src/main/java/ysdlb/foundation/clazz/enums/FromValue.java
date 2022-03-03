package ysdlb.foundation.clazz.enums;

public enum FromValue {
    DOG("dog", 10),
    CAT("cat", 10)
    ;

    private final String name;
    private final Integer weight;

    FromValue(String name, Integer weight) {
        this.name = name;
        this.weight = weight;
    }

    public static void main(String[] args) {
        FromValue fromValue = FromValue.valueOf("DOG");
    }
}
