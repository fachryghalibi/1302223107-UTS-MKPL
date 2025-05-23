package lib;

import java.time.LocalDate;
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

	private Spouse spouse;


    private List<Child> children = new LinkedList<>(); // Menyimpan data anak dalam bentuk list

    // Constructor dengan parameter object EmployeeInfo
    public Employee(EmployeeInfo info) {
        this.employeeId = info.employeeId;
        this.firstName = info.firstName;
        this.lastName = info.lastName;
        this.idNumber = info.idNumber;
        this.address = info.address;
        this.joinDate = LocalDate.of(info.yearJoined, info.monthJoined, info.dayJoined);
        this.isForeigner = info.isForeigner;
        this.gender = info.isMale ? Gender.MALE : Gender.FEMALE; // Konversi boolean ke enum
    }

    /**
     * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
     * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
     */
	public enum Grade {
		GRADE_1(3000000),
		GRADE_2(5000000),
		GRADE_3(7000000);
	
		private final int baseSalary;
	
		Grade(int baseSalary) {
			this.baseSalary = baseSalary;
		}
	
		public int getBaseSalary(boolean isForeigner) {
			return isForeigner ? (int) (baseSalary * 1.5) : baseSalary;
		}
	}
	

	public void setMonthlySalary(Grade grade) {
		this.monthlySalary = grade.getBaseSalary(isForeigner);
	}
	

    public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    public void setAdditionalIncome(int income) {
        this.otherMonthlyIncome = income;
    }

	public void setSpouse(Spouse spouse) {
		this.spouse = spouse;
	}

	public void addChild(Child child) {
		children.add(child);
	}
	

    public int getAnnualIncomeTax() {
		int monthsWorked = getMonthsWorkedInYear();
		boolean isSingle = isSingle();
	
		return TaxFunction.calculateTax(
			monthlySalary,
			otherMonthlyIncome,
			monthsWorked,
			annualDeductible,
			isSingle,
			children.size()
		);
	}
	
	private int getMonthsWorkedInYear() {
		LocalDate currentDate = LocalDate.now();
		if (currentDate.getYear() == joinDate.getYear()) {
			return currentDate.getMonthValue() - joinDate.getMonthValue();
		} else {
			return 12;
		}
	}
	
	private boolean isSingle() {
		return spouse == null || spouse.getIdNumber().equals("");
	}
	
}
