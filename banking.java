import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankingService {

    @Autowired
    private AccountDao accountDao;

    @Transactional
    public void transfer(Account fromAccount, Account toAccount, double amount) {
        // Debit from one account
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        
        // Credit to another account
        toAccount.setBalance(toAccount.getBalance() + amount);

        // Simulate failure for rollback
        if (amount > 1000) {
            throw new RuntimeException("Amount exceeds limit for transaction");
        }

        // Save updated accounts to DB
        accountDao.update(fromAccount);
        accountDao.update(toAccount);
    }
}
