package manager.userManagement;

import dto.UserDto;
import manager.PrintInfo;

import java.util.List;
import java.util.Scanner;

public class UserManagementView {
    Scanner scanner;
    UserManagementService userManagementService;

    public UserManagementView() {
        this.scanner = new Scanner(System.in);
        this.userManagementService = new UserManagementService();
    }

    public void showUserManagementView() {
        while (true) {
            showUserManagementMenu();

            if (!selectUserManagementMenu()) {
                break;
            }
        }
    }

    private void showUserManagementMenu() {
        System.out.println(
                "1.회원등록 2.회원수정 3.회원삭제 4.전체조회 5.회원검색 6.종료");
    }

    private boolean selectUserManagementMenu() {
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                insertUserDataView();
                break;
            case 2:
                updateUserDataView();
                break;
            case 3:
                deleteUserDataView();
                break;
            case 4:
                showAllUserDataView();
                break;
            case 5:
                showOneUserDataView();
                break;
            case 6:
                System.out.println("회원관리를 종료하시겠습니까?(Y/N)");
                String answer = scanner.next();
                if (answer.equalsIgnoreCase("Y")) {
                    return false;
                    // false를 가지고 showUserManagementView() 안의 호출한 곳으로 돌아가기
                    // showUserManagementView()의 반복문을 탈출해 쭉 거슬러 올라가 showManagerView()부터 다시 실행
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
        return true; // switch문 종료하고 다시 회원관리 메뉴 보여주기
    }

    private void insertUserDataView() {
        System.out.println("새로운 회원을 등록합니다.");

        System.out.println("아이디: ");
        String userId = scanner.next();

        System.out.println("비밀번호: ");
        String userPw = scanner.next();

        System.out.println("이름: ");
        String userName = scanner.next();

        System.out.println("전화번호: ");
        String userTel = scanner.next();

        UserDto dto = UserDto.allOfExceptBalance(userId,userPw,userName,userTel);

        int result = userManagementService.insertUserData(dto);
        if (result != 0) {
            System.out.println("회원등록 성공");
        } else {
            System.out.println("회원등록 실패");
        }
    }
    private void updateUserDataView() {
        System.out.println("수정할 회원의 아이디를 입력하세요.");
        String oldId = scanner.next();

        UserDto userDto = userManagementService.showOneUserData(oldId);
        if (userDto != null) {
            // 기존 회원 정보 출력 + 새로운 회원 정보 받기
            System.out.println("변경 전 아이디: " + userDto.userId());
            String userId = scanner.next(); // 새로운 아이디를 입력받음

            System.out.println("변경 전 비밀번호: " + userDto.userPw());
            String userPw = scanner.next(); // 새로운 비밀번호를 입력받음

            System.out.println("변경 전 이름: " + userDto.userName());
            String userName = scanner.next(); // 새로운 이름을 입력받음

            System.out.println("변경 전 전화번호: " + userDto.userTel());
            String userTel = scanner.next(); // 새로운 전화번호를 입력받음

            // 새로운 데이터를 dto에 담기
            UserDto newUserDto = new UserDto(userId,userPw,userName,userTel, userDto.balance());

            // 데이터를 수정하는 메서드 호출
            int result = userManagementService.updateUserData(newUserDto);
            if (result != 0) {
                System.out.println("회원수정 성공");
            } else {
                System.out.println("회원수정 실패");
            }
        } else {
            System.out.println("해당 회원이 존재하지 않습니다.");
        }

    }
    private void deleteUserDataView() {
        int result;
        String deleteYesOrNo;

        // 삭제할 아이디 입력 받기
        System.out.println("삭제할 회원의 아이디를 입력하세요.");
        String userId = scanner.next();

        UserDto userDto = userManagementService.showOneUserData(userId);
        if (userDto != null) {
            // 삭제 의사 확인하기
            do {
                System.out.println("정말로 삭제하겠습니까?(Y/N)");
                deleteYesOrNo = scanner.next();
            } while (!(deleteYesOrNo.equalsIgnoreCase("Y")
                    || deleteYesOrNo.equalsIgnoreCase("N")));

            if (deleteYesOrNo.equalsIgnoreCase("Y")) {
                // 삭제 처리하기
                result = userManagementService.deleteUserData(userId);
                if (result != 0) {
                    System.out.println("회원삭제 성공");
                } else {
                    System.out.println("회원삭제 실패");
                }
            }
        } else {
            System.out.println("해당 회원이 존재하지 않습니다.");
        }
    }
    private void showAllUserDataView() {
        List<UserDto> dtoList;
        dtoList = userManagementService.showAllUserData();

        if (dtoList.isEmpty()) {
            System.out.println("회원 데이터가 존재하지 않습니다.");
        } else {
            PrintInfo.line();
            PrintInfo.userTitle();
            PrintInfo.line();
            for (UserDto userDto : dtoList) {
                System.out.println(userDto);
            }
            PrintInfo.line();
        }
    }
    private void showOneUserDataView() {
        String userId;

        System.out.println("검색할 회원아이디를 입력하세요.");
        userId = scanner.next();
        UserDto userDto = userManagementService.showOneUserData(userId);;

        if (userDto == null) {
            System.out.println("해당하는 회원이 존재하지 않습니다.");
        } else {
            PrintInfo.line();
            PrintInfo.userTitle();
            PrintInfo.line();
            System.out.println(userDto);
            PrintInfo.line();
        }
    }
}
