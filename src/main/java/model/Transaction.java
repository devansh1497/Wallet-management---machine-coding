package model;

import enums.TransactionType;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
public class Transaction {
    private UUID uuid;
    private final UUID wallet;
    private final Timestamp madeOn;
    private final TransactionType type;
    private final String otherEntity;
    private final double amount;
    private final boolean isSystemTransaction;

    public Transaction(UUID wallet, Timestamp madeOn, TransactionType type, String otherEntity, double amount, boolean isSystemTransaction) {
        this.wallet = wallet;
        this.madeOn = madeOn;
        this.type = type;
        this.otherEntity = otherEntity;
        this.amount = amount;
        this.isSystemTransaction = isSystemTransaction;

    }
}
