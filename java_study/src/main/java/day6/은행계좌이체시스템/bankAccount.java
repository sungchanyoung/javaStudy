package day6.은행계좌이체시스템;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.security.auth.login.AccountLockedException;
import java.util.UUID;

@Data
@AllArgsConstructor
public class bankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private boolean locked;
    private TransactionLoger loger;

    public  static class BankAccountException extends Exception{
        public BankAccountException(String message) {
            super(message);
        }

        public BankAccountException(String message, Throwable cause) {
            super(message, cause);
        }

    }

    //잔액 부족
    public static class InsufficientFundsException extends BankAccountException{
       private final double requested;
       private final double available;

       public InsufficientFundsException( double requested, double available) {
           super(String.format("Insufficient balance. Requested : %.2f , Available: %.2f",
                   requested, available));
           this.requested = requested;
           this.available = available;
       }
    }

    //유효하지 않은 송금액 예외
    public static class InvalidTransferAmountException extends BankAccountException{
        public InvalidTransferAmountException(String message) {
            super("Account is locked : " + message);
        }
    }

    public static class  AccountValidationException extends  BankAccountException{
        public AccountValidationException(String message) {
            super(message);
        }
    }

    //시스템 에외  -> 시스템 내부 오류 발생시  -> 인체크 예외 (RuntimeException)
    public static class BankSystemException extends BankAccountException{
        public BankSystemException(String message) {
            super(message);
        }

        public BankSystemException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    //트랜잭션 로거 인터페이스 -> 트랜잭샨 로깅을 위해 만든것
    public interface TransactionLoger{
        void logTransaction(String message);
        void logError(String message, Throwable error);
    }

    public void BankAccount(String accountHolder,double balance){
        this.accountNumber = generateAccountNumber();
        this.balance = 0.0;
        this.locked = false;
        this.loger = new ConsoleTransactionLoger();

    }

    private class ConsoleTransactionLoger implements TransactionLoger{
        @Override
        public void logTransaction(String message) {
            System.out.println("[Transaction]" + message);
        }
        @Override
        public void logError(String message, Throwable error) {
            System.out.println("[Error]" + message);
            if (error != null){
                error.printStackTrace();
            }
        }
    }

    //계좌번호 생성
    private String generateAccountNumber() {
        return "ACCT"+ UUID.randomUUID().toString().substring(0,8);
    }

    //입금
    public void deposit(double amount) throws InvalidTransferAmountException, AccountLockedException, BankSystemException {
        try {
            //입금액
            if (amount <= 0) {
                throw new InvalidTransferAmountException("Amount must be greater than 0");
            }

            //게좌 장금 확인
            if (locked) {
                throw new AccountLockedException(accountNumber);
            }

            // 입금 실행
            this.balance += amount;
            loger.logTransaction(String.format("Deposited %.2f to %s. New balance: %.2f :",
                    amount, accountNumber,balance));
        } catch (InvalidTransferAmountException | AccountLockedException e) {
            loger.logError("Deposit failed" + e.getMessage(),e);
            throw e;
        }catch (Exception e){
            loger.logError("unexpected error during deposit" + e.getMessage(),e);
            throw new BankSystemException("System error occurred during deposit operation",e);
        }
    }

    //출금
    public void withdraw(double amount) throws InvalidTransferAmountException, AccountLockedException,
            BankSystemException, InsufficientFundsException{
        try {
            //출금액
            if (amount <= 0) {
                throw new InvalidTransferAmountException("withdraw must be greater than 0");
            }

            //게좌 장금 확인
            if (locked) {
                throw new AccountLockedException(accountNumber);
            }
            if (amount > balance) {
                throw new InsufficientFundsException(amount,balance);
            }

            // 출금 실행
            this.balance -= amount;
            loger.logTransaction(String.format("Withdrew %.2f from %s. New balance: %.2f :",
                    amount, accountNumber,balance));
        } catch (InvalidTransferAmountException | AccountLockedException | InsufficientFundsException e) {
            loger.logError("Withdrew failed" + e.getMessage(),e);
            throw e;
        }catch (Exception e){
            loger.logError("unexpected error during withdrew" + e.getMessage(),e);
            throw new BankSystemException("System error occurred during withdrew operation",e);
        }
    }
}
