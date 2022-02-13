package fi.crosskey.mortagePlan;

public class Prospect {
    private final String name;
    private final double loan;
    private final double interest;
    private final int years;
    private final double monthlyPayment;

    /**
     *Constructor for the Customer class
     * @param name The name of the Customer
     * @param loan  The amount loaned by Customer
     * @param interest The yearly interest in *100 . E.g. 5 means 0.05 yearly interest rate
     * @param years The amount of years the loan will be paid back
     */
    public Prospect(String name, double loan, double interest, int years) {
        this.name = name;
        this.loan = loan;
        this.interest = interest / 100;
        this.years = years;
        monthlyPayment = this.calculateMonthlyPayment();
    }

    /**
     *Function that returns the amount a customer should pay every month bases on the information Customer has.
     * @return
     */
    public double calculateMonthlyPayment() {
        int p = this.years * 12;//Number of payments
        double monthlyInterest= this.interest/12;
        double e;// Fixed monthly payment
        e = this.loan * ((monthlyInterest * pow((1 + monthlyInterest), p)) / (pow((1 + monthlyInterest), p) - 1));
        return e;
    }

    /**
     *Getter for name
     * @return The name of Customer
     */
    public String getName() {
        return name;
    }

    /**
     *Getter for the amount loaned
     * @return The loaned amount
     */
    public double getLoan() {
        return loan;
    }


    /**
     *Getter for years
     * @return The years
     */
    public int getYears() {
        return years;
    }

    public double getInterest() {
        return interest;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    /**
     *A function that calculate the power
     * @param number The base number(x)
     * @param exponent The exponent (e)
     * @return The result of (x^e)
     */
    public double pow(double number, int exponent) {
        double result = 1;
        for (int i = 0; i < exponent; i++) {
            result = result * number;
        }
        return result;
    }


}
