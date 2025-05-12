package day6.은행계좌이체시스템;

import lombok.Data;

import java.util.UUID;

@Data
public class BankAccountTry {
    private String accountNumber;
    private String accountHolder;
    private String bankName;
    private String password;
    private double balance;
    private boolean locked;
    private TransactionLogger logger;

    public BankAccountTry() {
        this.accountNumber = generateAccountNumber();
        this.balance = 0.0;
        this.locked = false;
        this.logger = new ConsoleTransactionLogger();
        this.bankName = "토스뱅크";
        this.password = "zkzk0209";
    }

    public BankAccountTry(String accountHolder, double initialBalance) {
        this();
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    // 예외 클래스 정의
    public static class BankAccountException extends Exception {
        public BankAccountException(String message) { super(message); }
        public BankAccountException(String message, Throwable cause) { super(message, cause); }
    }

    public static class InsufficientBalanceException extends BankAccountException {
        private final double requested;
        private final double available;

        public InsufficientBalanceException(double requested, double available) {
            super(String.format("Insufficient balance. Requested: %.2f, Available: %.2f", requested, available));
            this.requested = requested;
            this.available = available;
        }
    }

    public static class InvalidTransferAmountException extends BankAccountException {
        public InvalidTransferAmountException(String message) { super(message); }
    }

    public static class AccountLockedException extends BankAccountException {
        public AccountLockedException(String message) { super(message); }
    }

    public static class AccountValidationException extends BankAccountException {
        public AccountValidationException(String message) { super(message); }
    }

    public static class BankSystemException extends RuntimeException {
        public BankSystemException(String message) { super(message); }
        public BankSystemException(String message, Throwable cause) { super(message, cause); }
    }

    // 트랜잭션 로거 인터페이스
    public interface TransactionLogger {
        void logTransaction(String message);
        void logError(String message, Throwable error);
    }

    // 콘솔 로깅 구현
    private class ConsoleTransactionLogger implements TransactionLogger {
        @Override
        public void logTransaction(String message) {
            System.out.println("[Transaction] " + message);
        }

        @Override
        public void logError(String message, Throwable error) {
            System.out.println("[Error] " + message);
            if (error != null) {
                error.printStackTrace();
            }
        }
    }
    //UUID는 총 10자리의 식별자 번호 생성
    private String generateAccountNumber() {
        return "ACCT-" + UUID.randomUUID().toString().substring(0, 8);
    }

    private boolean isLocked() {
        return this.locked;
    }

    // 입금
    public void deposit(double amount, String bankName, String password) {
        try {
            if (!this.bankName.equals(bankName) || !this.password.equals(password)) {
                throw new AccountValidationException("Invalid bank name or password");
            }
            if (amount <= 0) {
                throw new InvalidTransferAmountException("Deposit amount must be positive");
            }
            if (locked) {
                throw new AccountLockedException(accountNumber);
            }

            balance += amount;
            logger.logTransaction(String.format("Deposited %.2f to %s", amount, accountNumber));

        } catch (BankAccountException e) {
            logger.logError("Deposit failed: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.logError("Unexpected error during deposit: " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    // 출금
    public void withdraw(double amount, String password) {
        try {
            if (!this.password.equals(password)) {
                throw new AccountValidationException("Invalid password");
            }
            if (amount <= 0) {
                throw new InvalidTransferAmountException("Withdrawal amount must be positive");
            }
            if (locked) {
                throw new AccountLockedException(accountNumber);
            }
            if (amount > balance) {
                throw new InsufficientBalanceException(amount, balance);
            }

            balance -= amount;
            logger.logTransaction(String.format("Withdrew %.2f from %s", amount, accountNumber));

        } catch (BankAccountException e) {
            logger.logError("Withdrawal failed: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.logError("Unexpected error during withdrawal: " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    // 송금
    public void transfer(BankAccountTry toAccount, double amount)
            throws AccountValidationException, InvalidTransferAmountException,
            InsufficientBalanceException {
        String transactionId = "TXN-" + UUID.randomUUID().toString().substring(0, 8);
        try {
            validateTransfer(toAccount, amount);

            executeTransfer(toAccount, amount, transactionId);

            logger.logTransaction(String.format("Transferred %.2f from %s to %s (Transaction ID: %s)",
                    amount, accountNumber, toAccount.getAccountNumber(), transactionId));

        } catch (BankAccountException e) {
            logger.logError("Transfer failed: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.logError("Unexpected error during transfer: " + e.getMessage(), e);
            throw new BankSystemException("System error occurred during transfer operation", e);
        }
    }

    private void validateTransfer(BankAccountTry toAccount, double amount)
            throws AccountValidationException, InvalidTransferAmountException,
            InsufficientBalanceException, AccountLockedException {
        if (toAccount == null) {
            throw new AccountValidationException("Recipient account cannot be null");
        }
        if (this.accountNumber.equals(toAccount.getAccountNumber())) {
            throw new AccountValidationException("Cannot transfer to the same account");
        }
        if (amount <= 0) {
            throw new InvalidTransferAmountException("Transfer amount must be positive");
        }
        if (this.isLocked()) {
            throw new AccountLockedException(this.accountNumber);
        }
        if (toAccount.isLocked()) {
            throw new AccountLockedException(toAccount.getAccountNumber());
        }
        if (amount > this.balance) {
            throw new InsufficientBalanceException(amount, this.balance);
        }
    }

    private void executeTransfer(BankAccountTry toAccount, double amount, String transactionId) {
        this.balance -= amount;
        toAccount.balance += amount;

        logger.logTransaction(String.format("Transfer executed: %.2f from %s to %s (Transaction ID: %s)",
                amount, accountNumber, toAccount.getAccountNumber(), transactionId));
    }
}
