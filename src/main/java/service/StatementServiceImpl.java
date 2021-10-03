package service;

import datastore.MemoryDataStore;
import enums.TransactionType;
import lombok.NonNull;
import model.Transaction;
import model.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class StatementServiceImpl implements StatementService {

    private final MemoryDataStore dataStore;

    public StatementServiceImpl(@NonNull MemoryDataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void logTransaction(User user, String otherEntity, TransactionType type, double amount, boolean isSystemTransaction) {
        user.getWallet().getTransactions().add(new Transaction(user.getWallet().getWalletId(), Timestamp.valueOf(LocalDateTime.now()),
                type, otherEntity,amount, isSystemTransaction));
    }

    @Override
    public void printStatement(String name) {
        System.out.println("Statement "+name);
        User user = dataStore.nameToUser.get(name);
        if(user == null) {
            System.out.println("User: %s doesn't exist".formatted(name));
        }
        else{
            user.getWallet().getTransactions().forEach(transaction -> {
                String type = transaction.getType() == TransactionType.CREDITED ? " credit " : " debit ";
                System.out.println(transaction.getOtherEntity()+ type + transaction.getAmount() );
            });
        }
    }
}
