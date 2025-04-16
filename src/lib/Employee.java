package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

// Enum untuk representasi gender
enum Gender {
    MALE,
    FEMALE
}

public class Employee {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;
    
    private LocalDate joinDate;
    private int monthWorkingInYear;
    
    private boolean isForeigner;
    private Gender gender; // Menggunakan enum Gender sebagai pengganti boolean
    
    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;
    
    private String spouseName;
    private String spouseIdNumber;

    private List<Child> children = new LinkedList<>(); // Menyimpan data anak dalam bentuk list

	public class Child {
		private String name;
		private String idNumber;
	
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
    
    public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, 
                   int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean isMale) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
        this.joinDate = LocalDate.of(yearJoined, monthJoined, dayJoined);
        this.isForeigner = isForeigner;
        this.gender = isMale ? Gender.MALE : Gender.FEMALE; // Konversi boolean ke enum
    }
    
    /**
     * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
     * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
     */
    
    private static final int GRADE_1_SALARY = 3000000;
    private static final int GRADE_2_SALARY = 5000000;
    private static final int GRADE_3_SALARY = 7000000;
     
    public void setMonthlySalary(int grade) {
        int baseSalary = 0;
    
        switch (grade) {
            case 1:
                baseSalary = GRADE_1_SALARY;
                break;
            case 2:
                baseSalary = GRADE_2_SALARY;
                break;
            case 3:
                baseSalary = GRADE_3_SALARY;
                break;
            default:
                throw new IllegalArgumentException("Grade tidak valid");
        }
    
        if (isForeigner) {
            baseSalary *= 1.5;
        }
        this.monthlySalary = baseSalary;
    }
     
    public void setAnnualDeductible(int deductible) {    
        this.annualDeductible = deductible;
    }
    
    public void setAdditionalIncome(int income) {    
        this.otherMonthlyIncome = income;
    }
    
    public void setSpouse(String spouseName, String spouseIdNumber) {
        this.spouseName = spouseName;
        this.spouseIdNumber = spouseIdNumber;
    }
    
	public void addChild(String childName, String childIdNumber) {
		children.add(new Child(childName, childIdNumber));
	}
	
    public int getAnnualIncomeTax() {
        //Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
        LocalDate currentDate = LocalDate.now();
        
        if (currentDate.getYear() == joinDate.getYear()) {
            monthWorkingInYear = currentDate.getMonthValue() - joinDate.getMonthValue();
        } else {
            monthWorkingInYear = 12;
        }
        
        return TaxFunction.calculateTax(
            monthlySalary, 
            otherMonthlyIncome, 
            monthWorkingInYear, 
            annualDeductible, 
            spouseIdNumber == null || spouseIdNumber.equals(""), 
            children.size()
        );
    }
}
