public class CreditCard {
    private String customer;
    private String bank;
    private String account;
    private int limit;
    protected double balance;

    /**
     * Constructor
     * @param cust
     * @param bk
     * @param acc
     * @param lmt
     * @param bal
     */
    public CreditCard(String cust, String bk, String acc, int lmt, double bal) {
        customer = cust;
        bank = bk;
        account = acc;
        limit = lmt;
        balance = bal;
    }

    /**
     * Constructor
     *
     * @param customer
     * @param bank
     * @param account
     * @param limit
     */
    public CreditCard(String customer, String bank, String account, int limit) {
        this(customer, bank, account, limit, 0.0); // call ourselves passing 0 for final param
    }

    /**
     * Get Customer
     * @return
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * Get Bank
     * @return
     */
    public String getBank() {
        return bank;
    }

    /**
     * get Account
     * @return
     */
    public String getAccount() {
        return account;
    }

    /**
     * Get limit
     * @return
     */
    public int getLimit() {
        return limit;
    }

    /**
     * get Balance
     * @return
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Charge method
     * @param price
     * @return
     */
    public boolean charge(double price) {
        if (price + balance > this.limit) {
            return false;
        }

        // otherwise we charge
        balance += price;
        return true;
    }

    /**
     * Make payment
     * @param amount
     */
    public void makePayment(double amount) {
        this.balance -= amount;
    }

    /**
     * Print Summary method
     * @param card
     */
    public static void printSummary(CreditCard card) {
        System.out.println("Customer= " + card.customer);
        System.out.println("Bank= " + card.bank);
        System.out.println("Account= " + card.account);
        System.out.println("Balance= " + card.balance);
        System.out.println("Limit= " + card.limit);
    }
}
