package day6.은행계좌이체시스템;

public class BankDemo {
    public static void main(String[] args) {
        System.out.println("===== 은행계좌이체시스템 예외 처리 데모 =====\n");

        // 계좌 생성
        BankAccount account1 = new BankAccount("홍길동", 10000);
        BankAccount account2 = new BankAccount("김철수", 5000);
        BankAccount account3 = new BankAccount("이영희", 20000);

        System.out.println("초기 계좌 상태:");
        System.out.println(account1);
        System.out.println(account2);
        System.out.println(account3);
        System.out.println();

        // 시나리오 1: 정상 입금 및 출금
        System.out.println("시나리오 1: 정상 입금 및 출금");
        try {
            System.out.println("계좌1에 5000원 입금 시도...");
            account1.deposit(5000);
            System.out.println("계좌1에서 2000원 출금 시도...");
            account1.withdraw(2000);
            System.out.println("성공: " + account1);
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
        System.out.println();

        // 시나리오 2: 잔액 부족 예외
        System.out.println("시나리오 2: 잔액 부족 예외");
        try {
            System.out.println("계좌2에서 10000원 출금 시도... (잔액 부족)");
            account2.withdraw(10000);
        } catch (BankAccount.InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());
            System.out.println("요청 금액: " + e.getRequested() + ", 사용 가능 금액: " + e.getAvailable());
        } catch (Exception e) {
            System.out.println("기타 예외 발생: " + e.getMessage());
        }
        System.out.println();
    }
}
