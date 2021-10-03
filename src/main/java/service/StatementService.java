package service;

import enums.TransactionType;
import model.User;

public interface StatementService {

    /**
     * Method to make log the transaction
     * @param user
     *        The transaction sill be made in the statement of this {@link User}
     * @param otherEntity
     *        The name of the other entity involved in the transaction
     * @param type
     *        Type of transaction: Credit or Debit {@link TransactionType}
     * @param amount
     *        Amount for this transaction
     * @param isSystemTransaction
     *        Flag to indicate if this transaction was made by the system(like for offers)
     */
    void logTransaction(User user, String otherEntity, TransactionType type, double amount, boolean isSystemTransaction);

    /**
     * Method to print the statement for the given user name
     * @param name
     *        Name of the user
     */
    void printStatement(String name);

}
