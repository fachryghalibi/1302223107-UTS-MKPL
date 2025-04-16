package lib;

public class TaxFunction {

    private static final int BASIC_NON_TAXABLE_INCOME = 54000000;
    private static final int MARRIED_ADDITION = 4500000;
    private static final int CHILD_ADDITION = 1500000;
    private static final int MAX_CHILDREN_COUNT = 3;
    private static final double TAX_RATE = 0.05;

    /**
     * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
     * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan dikurangi penghasilan tidak kena pajak.
     */
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking,
                                   int deductible, boolean isMarried, int numberOfChildren) {

        if (numberOfMonthWorking > 12) {
            System.err.println("More than 12 months working per year");
        }

        int cappedChildren = Math.min(numberOfChildren, MAX_CHILDREN_COUNT);

        int annualIncome = calculateAnnualIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking);
        int nonTaxableIncome = calculateNonTaxableIncome(isMarried, cappedChildren);
        int taxableIncome = annualIncome - deductible - nonTaxableIncome;

        return calculateAnnualTax(taxableIncome);
    }

    private static int calculateAnnualIncome(int monthlySalary, int otherMonthlyIncome, int monthsWorked) {
        return (monthlySalary + otherMonthlyIncome) * monthsWorked;
    }

    private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
        int nonTaxable = BASIC_NON_TAXABLE_INCOME;
        if (isMarried) {
            nonTaxable += MARRIED_ADDITION;
        }
        nonTaxable += numberOfChildren * CHILD_ADDITION;
        return nonTaxable;
    }

    private static int calculateAnnualTax(int taxableIncome) {
        if (taxableIncome <= 0) {
            return 0;
        }
        return (int) Math.round(taxableIncome * TAX_RATE);
    }
}

