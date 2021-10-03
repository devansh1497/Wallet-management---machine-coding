import datastore.MemoryDataStore;
import service.OfferService;
import service.OfferServiceImpl;
import service.StatementService;
import service.StatementServiceImpl;
import service.TransactionService;
import service.TransactionServiceImpl;
import service.WalletService;
import service.WalletServiceImpl;

public class WalletManagementApp {
    public static void main(String[] args) throws Exception {
        MemoryDataStore dataStore = new MemoryDataStore();
        WalletService walletService = new WalletServiceImpl(dataStore);
        StatementService statementService = new StatementServiceImpl(dataStore);
        OfferService offerService = new OfferServiceImpl(dataStore, statementService);
        TransactionService transactionService =  new TransactionServiceImpl(dataStore, offerService, statementService);
        /*
         * CreateWallet Harry 100
         * CreateWallet Ron 95.7
         * CreateWallet Hermione 104
         * CreateWallet Albus 200
         * CreateWallet Draco 500
         */
        walletService.createWallet("Harry", 100);
        walletService.createWallet("Ron", 95.7);
        walletService.createWallet("Hermoine", 104);
        walletService.createWallet("Albus", 200);
        walletService.createWallet("Draco", 500);

        walletService.overview();

        /*
         * TransferMoney Albus Draco 30
         * TransferMoney Hermione Harry 2
         * TransferMoney Albus Ron 5
         */
        transactionService.pay("Albus", "Draco", 30);
        transactionService.pay("Hermoine", "Harry", 2);
        transactionService.pay("Albus", "Ron", 5);

        walletService.overview();

        statementService.printStatement("Harry");
        statementService.printStatement("Albus");

        offerService.applyOffer2();

        walletService.overview();
    }
}
