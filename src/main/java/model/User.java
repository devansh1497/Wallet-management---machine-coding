package model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class User {
    private final UUID userId;
    private final String name;
    private final String email;
    private final Integer phone;
    private Wallet wallet;

    public User(String name) {
        this.userId = UUID.randomUUID();
        this.name = name;
        this.email = null;
        this.phone =  null;
    }

    public void createWallet(double amount) {
        this.wallet = new Wallet(this, amount);
    }
}
