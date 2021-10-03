package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
public class Wallet {
    private UUID walletId;
    private User user;
    private double balance;
    private List<Transaction> transactions;
    private LocalDateTime createdOn;

    public Wallet(User user, double balance) {
        this.user = user;
        this.balance = balance;
        this.transactions = new ArrayList<>();
        this.createdOn = LocalDateTime.now();
    }

    public void reduceBalance(double amount) {
        if(this.balance <= amount) {
            System.out.println("Insufficient balance");
        }
        else{
            this.balance -= amount;
        }
    }

    public void addBalance(double amount) {
        this.balance += amount;
    }

    public int getNumOfTransactions() {
        return (int)this.transactions.stream().filter(transaction -> !transaction.isSystemTransaction()).count();
    }
}
