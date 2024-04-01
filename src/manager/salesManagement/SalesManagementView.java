package manager.salesManagement;

import dto.ProductDto;
import dto.SalesDto;
import dto.UserDto;
import manager.PrintInfo;
import manager.userManagement.UserManagementService;
import manager.vmManagement.VmManagementService;

import java.util.Scanner;

public class SalesManagementView {
    Scanner scanner;
    SalesManagementService salesManagementService;
    VmManagementService vmManagementService;
    UserManagementService userManagementService;
    int productId;
    String userId;

    public SalesManagementView() {
        this.scanner = new Scanner(System.in);
        this.salesManagementService = new SalesManagementService();
        this.vmManagementService = new VmManagementService();
        this.userManagementService = new UserManagementService();
    }

    public void showSalesManagementView() {
        while (true) {
            showSalesManagementMenu();

            if (!selectSalesManagementMenu()) {
                break;
            }
        }
    }

    private void showSalesManagementMenu() {
        System.out.println(
                "1.제품별 판매현황 2.회원별 판매현황 3.총판매현황 4.종료");
    }

    private boolean selectSalesManagementMenu() {
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                System.out.println("판매현황을 조회할 제품번호를 입력하세요.");
                productId = scanner.nextInt();
                ProductDto productDto = vmManagementService.showOneVMData(productId);

                if (productDto == null) {
                    System.out.println("해당 제품이 존재하지 않습니다.");
                } else {
                    SalesDto salesDto = salesManagementService.getProductSalesReport(productId);
                    if (salesDto != null) {
                        PrintInfo.line();
                        PrintInfo.salesProductTitle();
                        PrintInfo.line();
                        System.out.println(salesDto);
                        PrintInfo.line();
                    } else {
                        System.out.println("해당 제품의 판매현황이 없습니다.");
                    }
                }
                break;

            case 2:
                System.out.println("판매현황을 조회할 회원아이디를 입력하세요.");
                userId = scanner.next();
                UserDto userDto = userManagementService.showOneUserData(userId);

                if (userDto == null) {
                    System.out.println("해당 회원이 존재하지 않습니다.");
                } else {
                    SalesDto salesDto = salesManagementService.getUserSalesReport(userId);
                    if (salesDto != null) {
                        PrintInfo.line();
                        PrintInfo.salesUserTitle();
                        PrintInfo.line();
                        System.out.println(salesDto);
                        PrintInfo.line();
                    } else {
                        System.out.println("해당 회원의 구매현황이 없습니다.");
                    }
                }
                break;
            case 3:
                SalesDto totalSalesDto = salesManagementService.getTotalSalesReport();
                if (totalSalesDto != null) {
                    PrintInfo.line();
                    PrintInfo.salesTotalTitle();
                    PrintInfo.line();
                    System.out.println(totalSalesDto);
                    PrintInfo.line();
                } else {
                    System.out.println("전체 판매현황이 없습니다.");
                }
                break;
            case 4:
                System.out.println("판매관리를 종료하시겠습니까?(Y/N)");
                String answer = scanner.next();
                if (answer.equalsIgnoreCase("Y")) {
                    return false;
                    // false를 가지고 showSalesManagementView() 안의 호출한 곳으로 돌아가기
                    // showSalesManagementView()의 반복문을 탈출해 쭉 거슬러 올라가 showManagerView()부터 다시 실행
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
        return true; // switch문 종료하고 다시 판매관리 메뉴 보여주기
    }
}
