package user;

import java.util.Scanner;

public class UserView {
    // 멤버변수
    private Scanner scanner;
    private UserService userService;
    private UserDto userDto;

    private String userId = null;
    private String userPw = null;
    private String userName = null;
    private String userTel = null;
    private int userMoney;

    // 생성자
    public UserView() {
        this.scanner = new Scanner(System.in);
        this.userService = new UserService();
    }

    // 메서드
    public void signUpView() {
        getInfoToSignUp();

        if (canUserSignUp()) {
            doSignUpProcess();
        } else {
            System.out.println("기존 계정으로 로그인 해주세요.");
        }
    }

    private void getInfoToSignUp() {
        System.out.println("회원가입을 위한 정보를 입력하세요.");
        System.out.println("아이디: ");
        userId = scanner.next();

        System.out.println("패스워드: ");
        userPw = scanner.next();

        System.out.println("이름: ");
        userName = scanner.next();

        System.out.println("연락처: ");
        userTel = scanner.next();
    }

    private boolean canUserSignUp() {
        // 중복된 아이디가 아닐 것
        //return (userId != userId ? true : false);
        return true;
    }

    private void doSignUpProcess() {
        userDto = UserDto.allData(userId, userPw,userName,userTel, userMoney);
        int result = userService.signUpService(userDto);
        if (result != 0) {
            System.out.println("회원가입 성공");
        } else {
            System.out.println("회원가입 실패");
        }
    }

    public void signInView() {

    }



}
