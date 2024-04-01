package vendingMachine;

import db.DBConn;
import vendingMachine.first.FirstUserView;

import java.util.Scanner;

public class VendingMachine {
    Scanner scanner;
    FirstUserView firstUserView;

    public VendingMachine() {
        this.scanner = new Scanner(System.in);
        this.firstUserView = new FirstUserView();
    }

    public void run() {
        while (true) {
            introduceMachine();
            giveMachineOption();
        }
    }

    private void introduceMachine() {
        System.out.println("안녕하세요,회원전용 자판기 입니다.");
        System.out.println("이용을 원하시면 회원가입 후 로그인이 필요합니다.");
        System.out.println("1.회원가입 2.로그인 3.종료");
    }
    private void giveMachineOption() {
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                firstUserView.signUpView();
                System.out.println();
                break;
            case 2:
                firstUserView.signInView();
                System.out.println();
                break;
            case 3:
                System.out.println();
                DBConn.close();
                break;
            default:
                System.out.println("잘못된 번호를 선택했습니다.");
                System.out.println();
                break;
        }
    }
}
