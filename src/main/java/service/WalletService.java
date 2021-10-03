package service;

public interface WalletService {

    /**
     * Method to create the wallet
     * @param name
     *        Name of the user
     * @param amount
     *        Initial balance
     */
    void createWallet(String name, double amount);

    /**
     * Method to print the overview
     */
    void overview();

}
