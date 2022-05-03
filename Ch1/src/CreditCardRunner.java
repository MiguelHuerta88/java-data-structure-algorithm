public class CreditCardRunner {
    public static void main(String[] args) {
        CreditCard[] wallet = new CreditCard[3];
        wallet[0] = new CreditCard("Miguel Huerta", "California Savings", "1234 5648 9898 8989", 5000);
        wallet[1] = new CreditCard("Susan Espino", "Los Angeles Savings", "9897 5648 9898 6632", 3500);
        wallet[2] = new CreditCard("Noemi Huerta", "Carson Savings", "1234 9998 9898 8989", 300);

        for (int val = 1; val <= 16; val++) {
            wallet[0].charge(3*val);
            wallet[1].charge(2*val);
            wallet[2].charge(val);
        }

        for (CreditCard card: wallet) {
            CreditCard.printSummary(card);
            while (card.getBalance() > 200.0) {
                card.makePayment(200);
                System.out.println("New Balance = " + card.getBalance());
            }
        }
    }
}
