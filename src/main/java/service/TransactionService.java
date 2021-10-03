package service;

public interface TransactionService {

    /**
     * Method to make the payment
     * @param from
     *        User name who is making the payment
     * @param to
     *        User name who will receive the payment
     * @param amount
     *        Transaction amount
     */
    void pay(String from, String to, double amount);
}
