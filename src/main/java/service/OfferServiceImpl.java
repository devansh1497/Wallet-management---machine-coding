package service;

import datastore.MemoryDataStore;
import enums.TransactionType;
import lombok.NonNull;
import model.User;
import java.util.PriorityQueue;

public class OfferServiceImpl implements OfferService {

    private final MemoryDataStore dataStore;
    private final StatementService statementService;


    public OfferServiceImpl(@NonNull MemoryDataStore dataStore, @NonNull StatementService statementService) {
        this.dataStore = dataStore;
        this.statementService = statementService;
    }

    @Override
    public void checkAndApplyOffer1(User fromUser, User toUser) {
        if(fromUser.getWallet().getBalance() == toUser.getWallet().getBalance()) {
            fromUser.getWallet().addBalance(10);
            toUser.getWallet().addBalance(10);
            String offer2Name = "Offer1";
            double AMOUNT_OFFER_1 = 10;
            statementService.logTransaction(fromUser, offer2Name, TransactionType.CREDITED, AMOUNT_OFFER_1, true);
            statementService.logTransaction(toUser, offer2Name, TransactionType.CREDITED, AMOUNT_OFFER_1, true);
        }
    }

    @Override
    public void applyOffer2() {
        PriorityQueue<User> usersForOffer2 = new PriorityQueue<>((a,b) -> {
           if(a.getWallet().getTransactions() != b.getWallet().getTransactions()) {
               return b.getWallet().getNumOfTransactions() - a.getWallet().getNumOfTransactions();
           }
           if(a.getWallet().getBalance() != b.getWallet().getBalance()) {
               return b.getWallet().getBalance() > a.getWallet().getBalance() ? -1 : 1;
           }
           if(a.getWallet().getCreatedOn() != b.getWallet().getCreatedOn()) {
               return a.getWallet().getCreatedOn().isBefore(b.getWallet().getCreatedOn()) ? -1 : 1;
           }
           return 0;
        });
        usersForOffer2.addAll(dataStore.nameToUser.values());
        User user1 = !usersForOffer2.isEmpty() ? usersForOffer2.poll() : null;
        User user2 = !usersForOffer2.isEmpty() ? usersForOffer2.poll() : null;
        User user3 = !usersForOffer2.isEmpty() ? usersForOffer2.poll() : null;

        addBalanceForSelectedUsers(user1, user2, user3);
        addToUserForOffer2(user1, usersForOffer2);
        addToUserForOffer2(user2, usersForOffer2);
        addToUserForOffer2(user3, usersForOffer2);
    }

    private void addToUserForOffer2(User user, PriorityQueue<User> pq) {
        if(user != null) {
            pq.offer(user);
        }
    }

    private void addBalanceForSelectedUsers(User firstUser, User secondUser, User thirdUser) {
        double maxOffer2Amt = 10;
        makeTransaction(firstUser, maxOffer2Amt);
        double secondMaxOffer2Amt = 5;
        makeTransaction(secondUser, secondMaxOffer2Amt);
        double thirdMaxOffer2Amt = 2;
        makeTransaction(thirdUser, thirdMaxOffer2Amt);
    }

    private void makeTransaction(User user, double amount) {
        if(user  == null){
            return;
        }
        user.getWallet().addBalance(amount);
        String offer2Name = "Offer2";
        statementService.logTransaction(user, offer2Name, TransactionType.CREDITED, amount, true);
    }
}
