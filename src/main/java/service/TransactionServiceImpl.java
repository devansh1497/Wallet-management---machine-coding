package service;

import common.Constants;
import common.Utilities;
import datastore.MemoryDataStore;
import enums.TransactionType;
import lombok.NonNull;
import model.User;

public class TransactionServiceImpl implements TransactionService {

    private final MemoryDataStore dataStore;
    private final OfferService offerService;
    private final StatementService statementService;

    public TransactionServiceImpl(@NonNull MemoryDataStore dataStore, @NonNull  OfferService offerService, @NonNull
            StatementService statementService) {
        this.dataStore = dataStore;
        this.offerService = offerService;
        this.statementService = statementService;
    }

    @Override
    public void pay(String from, String to, double amount) {
        amount = Utilities.round(amount, Constants.DECIMAL_VALUES);
        if(amount < Constants.MINIMUM_TRANSACTION_AMOUNT) {
            System.out.println("Minimum amount required is: "+Constants.MINIMUM_TRANSACTION_AMOUNT);
        }
        else {
            User fromUser = dataStore.nameToUser.get(from);
            User toUser = dataStore.nameToUser.get(to);
            if(fromUser == null || toUser == null ){
                System.out.println("Invalid user");
                return;
            }
            boolean canMakeTransaction = validateMinimumAmount(fromUser, amount);
            if(!canMakeTransaction) {
                System.out.println("Insufficient balance");
            }
            else{
                fromUser.getWallet().reduceBalance(amount);
                toUser.getWallet().addBalance(amount);
                offerService.checkAndApplyOffer1(fromUser, toUser);
                statementService.logTransaction(fromUser, toUser.getName(), TransactionType.DEBITED, amount, false);
                statementService.logTransaction(toUser, fromUser.getName(), TransactionType.CREDITED, amount, false);
            }
        }
    }

    private boolean validateMinimumAmount(User fromUser, double amount) {
        return fromUser.getWallet().getBalance() - amount > 0;
    }
}
