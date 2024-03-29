package vendingMachine;

import user.UserView;

import java.util.Scanner;

public class VendingMachine {
    // 멤버변수
    private Scanner scanner;
    private UserView userView;

    // 생성자
    public VendingMachine() {
        this.scanner = new Scanner(System.in);
        this.userView = new UserView();
    }

    // 메서드
    public void run() {
        while (true) {
            showMainMenu();

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    signUp();
                    break;
                case 2:
                    singIn();
                    break;
                case 3:
                    exitMessage();
                    break;
                default:
                    hasInvalidChoiceMessage();
                    break;
            }
        }
    }

    private void showMainMenu() {
        System.out.println("===========================");
        System.out.println("회원용 자판기 입니다.");
        System.out.println("===========================");
        System.out.println("1: 회원가입");
        System.out.println("2: 로그인");
        System.out.println("3: 종료");
        System.out.println("===========================");
        System.out.print("메뉴 선택: ");
    }
    private void signUp() {
        userView.signUpView();
    }
    private void singIn() {
        userView.signInView();
    }
    private void exitMessage() {
        System.out.println("프로그램을 종료합니다.");
    }
    private void hasInvalidChoiceMessage() {
        System.out.println("잘못된 선택입니다.");
    }
}
