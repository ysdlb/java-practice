package ysdlb.property.clzobj.clazz;

public class Man {
    // 加了别的非空构造函数，这个空的默认构造函数就必须手动写一个
    public Man() {}

    public Man(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    private int id;
    private int age;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", age='" + age + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
