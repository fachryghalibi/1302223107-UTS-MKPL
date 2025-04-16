package lib;

public class Child {
    private final String name;
    private final String idNumber;

    public Child(String name, String idNumber) {
        this.name = name;
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public String getIdNumber() {
        return idNumber;
    }
}
