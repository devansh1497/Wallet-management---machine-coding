package service;

import model.User;

public interface OfferService {

    /**
     * Method to check and apply offer 1 if it can be applied
     * @param fromUser
     *          User who is making the payment
     * @param toUser
     *          User who will receive the payment
     */
    void checkAndApplyOffer1(User fromUser, User toUser);

    /**
     * Method to apply offer 2
     */
    void applyOffer2();
}
