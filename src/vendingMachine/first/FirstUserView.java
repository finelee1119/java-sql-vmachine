package vendingMachine.first;

import dto.UserDto;
import manager.ManagerView;
import user.UserView;
import user.money.ChargeService;

import java.util.Scanner;

public class FirstUserView {
    Scanner scanner;
    FirstUserService firstUserService;
    UserView userView;
    ManagerView managerView;
    ChargeService chargeService;
    String userId = null, userPw = null, userName = null, userTel = null;
    int balance = 0;
    boolean signedIn = false; // 사용자 로그인 상태를 추적하는 변수

    public FirstUserView() {
        this.scanner = new Scanner(System.in);
        this.firstUserService = new FirstUserService();
        this.userView = new UserView();
        this.managerView = new ManagerView();
        this.chargeService = new ChargeService();
    }

    public void signUpView() {
        getUserInfo();
        doSignUpProcess();
    }


    public void signInView() {
        getSignedUserInfo();
        doSignInProcess();
    }



    private void getUserInfo() {
        System.out.println("회원가입을 위한 정보를 입력하세요.");

        System.out.println("아이디: ");
        userId = scanner.next();

        System.out.println("패스워드: ");
        userPw = scanner.next();

        System.out.println("이름: ");
        userName = scanner.next();

        System.out.println("연락처: ");
        userTel = scanner.next();

        signedIn = true; // 회원가입 시에도 로그인 상태로 설정
    }
    private void doSignUpProcess() {
        UserDto userDto = UserDto.allOf(userId,userPw,userName,userTel,balance);

        int result = firstUserService.signUpService(userDto);

        if (result != 0) {
            System.out.println("회원가입 성공");
        } else {
            System.out.println("회원가입 실패");
        }
    }


    private void getSignedUserInfo() {
        System.out.println("로그인을 위한 정보를 입력하세요.");

        System.out.println("아이디: ");
        userId = scanner.next();

        System.out.println("비밀번호: ");
        userPw = scanner.next();

        signedIn = firstUserService.signInService(userId, userPw); // 로그인 결과에 따라 로그인 상태 설정
    }
    private void doSignInProcess() {
        boolean signedInUser = firstUserService.signInService(userId, userPw);

        if (signedInUser) {
            if (userId.equals("root") && userPw.equals("1111")) {
                System.out.println("관리자 모드 입니다.");
                managerView.showManagerView();
            } else {
                System.out.println(userId + "님, 환영합니다.");
                System.out.println("현재 잔액: " + chargeService.getBalance(userId) + "원 입니다.");
                userView.showUserView(userId);
            }

        } else {
            System.out.println("로그인에 실패했습니다.");
        }

    }
}
