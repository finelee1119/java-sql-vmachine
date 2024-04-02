package user;

import manager.product.ProductView;
import user.buy.BuyView;
import user.money.ChargeView;

import java.util.Scanner;

public class UserView {
    Scanner scanner;
    ChargeView chargeView;
    BuyView buyView;
    ProductView productView;

    public UserView() {
        this.scanner = new Scanner(System.in);
        this.chargeView = new ChargeView();
        this.buyView = new BuyView();
        this.productView = new ProductView();
    }

    public void showUserView(String userId) {
        while (true) {
            showUserMenu();

            if (!selectUserMenu(userId)) {
                break;
            }
        }
    }

    private void showUserMenu() {
        System.out.println("1.금액충전 2.제품구입 3.잔액반환 4.제품정보 5.종료");
    }

    private boolean selectUserMenu(String userId) {
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                chargeView.chargeMoneyView(userId);
                break;
            case 2:
                buyView.buyProductView(userId);
                break;
            case 3:
                chargeView.returnMoneyView(userId);
                break;
            case 4:
                productView.showAllProductView();
                break;
            case 5:
                System.out.println("사용을 종료하시겠습니까?(Y/N)");
                String answer = scanner.next();
                if (answer.equalsIgnoreCase("Y")) {
                    return false;
                    // false를 가지고 showUserView() 안의 호출한 곳으로 돌아가기
                    // showUserView()의 반복문을 탈출해 쭉 거슬러 올라가 run()부터 다시 실행
                } else if (answer.equalsIgnoreCase("N")) {
                    break;
                } else {
                    System.out.println("잘못된 문자를 입력했습니다.");
                    break;
                }
            default:
                System.out.println("잘못된 번호를 선택했습니다.");
                break;
        }
        return true; // switch문 종료하고 다시 사용자 메뉴 보여주기
    }
}
