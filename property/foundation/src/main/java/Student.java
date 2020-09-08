public class Student {
    private Integer id;
    private String name;

    public Student(Integer _id, String _name) {
        id = _id;
        name = _name;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
