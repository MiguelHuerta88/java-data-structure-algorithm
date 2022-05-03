public class PredatoryCreditCard extends CreditCard{
    private double apr;

    /**
     * Constructor
     * @param cust
     * @param bk
     * @param acc
     * @param lim
     * @param bal
     * @param rate
     */
    public PredatoryCreditCard(String cust, String bk, String acc, int lim, double bal, double rate) {
        super(cust, bk, acc, lim, bal);
        apr = rate;
    }

    /**
     * Process monthly interest charges
     */
    public void processMonth() {
        if (balance > 0) {
            double monthlyFactor = Math.pow(1 + apr, 1.0/12);
            balance += monthlyFactor;
        }
    }

    /**
     * Overriding the charge method defined in parent
     * @param price
     * @return
     */
    public boolean charge(double price) {
        boolean isSuccess = super.charge(price);
        if (!isSuccess) {
            balance += 5; // $5 dollar penalty
        }

        return isSuccess;
    }
}
