package manager;

import manager.sales.SalesView;
import manager.member.MemberView;
import manager.product.ProductView;

import java.util.Scanner;

public class ManagerView {
    Scanner scanner;
    ProductView productView;
    MemberView memberView;
    SalesView salesView;

    public ManagerView() {
        this.scanner = new Scanner(System.in);
        this.productView = new ProductView();
        this.memberView = new MemberView();
        this.salesView = new SalesView();
    }

    public void showManagerView() {
        while (true) {
            showManagerMenu();

            if (!selectManagerMenu()) {
                break;
            }
        }
    }

    private void showManagerMenu() {
        System.out.println("1.제품관리 2.회원관리 3.판매관리 4.관리자종료");
    }

    private boolean selectManagerMenu() {
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                productView.showProductView();
                break;
            case 2:
                memberView.showMemberView();
                break;
            case 3:
                salesView.showSalesView();
                break;
            case 4:
                System.out.println("관리자를 종료하고 첫화면으로 돌아가겠습니까?(Y/N)");
                String answer = scanner.next();
                if (answer.equalsIgnoreCase("Y")) {
                    return false;
                    // false를 가지고 showManagerView() 안의 호출한 곳으로 돌아가기
                    // showManagerView()의 반복문을 탈출해 쭉 거슬러 올라가 run()부터 다시 실행
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
        return true; // switch문 종료하고 다시 관리자 메뉴 보여주기
    }
}
