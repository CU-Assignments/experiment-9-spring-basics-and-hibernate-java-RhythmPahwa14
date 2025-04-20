import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(AppConfig.class);

        BankingService bankingService = context.getBean(BankingService.class);

        Account fromAccount = new Account(1, 5000);
        Account toAccount = new Account(2, 1000);
        
        try {
            bankingService.transfer(fromAccount, toAccount, 1500);  // Will cause rollback
        } catch (Exception e) {
            System.out.println("Transaction failed: " + e.getMessage());
        }
        
        context.close();
    }
}
