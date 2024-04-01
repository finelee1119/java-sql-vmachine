package user.money;

import java.util.Scanner;

public class ChargeView {
    Scanner scanner;
    ChargeService chargeService;


    public ChargeView() {
        this.scanner = new Scanner(System.in);
        this.chargeService = new ChargeService();
    }

    public void chargeMoneyView(String userId) {
        showChargeMoneyCondition();
        chargeBalanceView(userId);
    }

    public void returnMoneyView(String userId) {
        int returnedMoney = chargeService.returnBalance(userId);
        System.out.println("반환 완료: " + returnedMoney + "원");
    }


    private void showChargeMoneyCondition() {
        System.out.println(
                "금액충전은 천원 단위로 카드 결제만 가능합니다.");
    }
    private void chargeBalanceView(String userId) {
        System.out.println("금액 입력: ");
        int amount = scanner.nextInt();

        if (amount > 0 && amount % 1000 == 0) {
            int newBalance = chargeService.updateBalance(userId, amount);
            System.out.println("충전이 완료되었습니다.");
            System.out.println("현재 잔액: " + newBalance + "원");
        } else {
            System.out.println("잘못된 시도입니다.");
        }
    }
}
