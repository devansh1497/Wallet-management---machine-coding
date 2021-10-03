package service;

import common.Constants;
import datastore.MemoryDataStore;
import lombok.NonNull;
import model.User;

public class WalletServiceImpl implements WalletService {

    private final MemoryDataStore dataStore;

    public WalletServiceImpl(@NonNull MemoryDataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void createWallet(String name, double amount) {
        if(amount < Constants.MINIMUM_AMOUNT_TO_CREATE_WALLET) {
            System.out.println("Minimum amount required is: "+Constants.MINIMUM_AMOUNT_TO_CREATE_WALLET);
        }
        else if(dataStore.nameToUser.containsKey(name)) {
            System.out.println("User: %s already exists".formatted(name));
        }
        else{
            User user = new User(name);
            user.createWallet(amount);
            dataStore.nameToUser.put(name, user);
        }
    }

    @Override
    public void overview() {
        for(User user : dataStore.nameToUser.values()) {
            String name = user.getName();
            double accountBalance = user.getWallet().getBalance();
            System.out.println(name+": "+accountBalance);
        }
    }

}
