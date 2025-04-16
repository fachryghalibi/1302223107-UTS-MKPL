package lib;

public class EmployeeInfo {
    public String employeeId;
    public String firstName;
    public String lastName;
    public String idNumber;
    public String address;
    public int yearJoined;
    public int monthJoined;
    public int dayJoined;
    public boolean isForeigner;
    public boolean isMale;

    public EmployeeInfo(String employeeId, String firstName, String lastName, String idNumber, String address,
                        int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean isMale) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
        this.yearJoined = yearJoined;
        this.monthJoined = monthJoined;
        this.dayJoined = dayJoined;
        this.isForeigner = isForeigner;
        this.isMale = isMale;
    }
}
