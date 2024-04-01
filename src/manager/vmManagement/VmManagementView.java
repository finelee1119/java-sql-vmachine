package manager.vmManagement;

import dto.ProductDto;
import manager.PrintInfo;

import java.util.List;
import java.util.Scanner;

public class VmManagementView {
    Scanner scanner;
    VmManagementService vmManagementService;

    public VmManagementView() {
        this.scanner = new Scanner(System.in);
        this.vmManagementService = new VmManagementService();
    }

    public void showVMManagementView() {
        while (true) {
            showVMManagementMenu();

            if (!selectVMManagementMenu()) {
                break;
            }
        }
    }

    private void showVMManagementMenu() {
        System.out.println(
                "1.제품등록 2.제품수정 3.제품삭제 4.전체조회 5.제품검색 6.종료");
    }

    private boolean selectVMManagementMenu() {
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                insertVMDataView();
                break;
            case 2:
                updateVMDataView();
                break;
            case 3:
                deleteVMDataView();
                break;
            case 4:
                showAllVMDataView();
                break;
            case 5:
                showOneVMDataView();
                break;
            case 6:
                System.out.println("자판기관리를 종료하시겠습니까?(Y/N)");
                String answer = scanner.next();
                if (answer.equalsIgnoreCase("Y")) {
                    return false;
                    // false를 가지고 showVMachineManagementView() 안의 호출한 곳으로 돌아가기
                    // showVMachineManagementView()의 반복문을 탈출해 쭉 거슬러 올라가 showManagerView()부터 다시 실행
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
        return true; // switch문 종료하고 다시 자판기관리 메뉴 보여주기
    }

    private void insertVMDataView() {
        System.out.println("새로운 제품을 등록합니다.");

        System.out.println("제품명: ");
        String productName = scanner.next();

        System.out.println("가격: ");
        int productPrice = scanner.nextInt();

        System.out.println("재고: ");
        int productStock = scanner.nextInt();

        ProductDto dto = ProductDto.allOfExceptId(productName, productPrice, productStock);

        int result = vmManagementService.insertVMData(dto);
        if (result != 0) {
            System.out.println("제품등록 성공");
        } else {
            System.out.println("제품등록 실패");
        }
    }
    private void updateVMDataView() {
        System.out.println("수정할 제품의 번호를 입력하세요.");
        int productId = scanner.nextInt();

        ProductDto productDto = vmManagementService.showOneVMData(productId);
        if (productDto != null) {
            // 기존 제품 정보 출력 + 새로운 제품 정보 받기
            System.out.println("변경 전 제품명: " + productDto.productName());
            String productName = scanner.next(); // 새로운 제품명을 입력받음

            System.out.println("변경 전 가격: " + productDto.productPrice());
            int productPrice = scanner.nextInt(); // 새로운 가격을 입력받음

            System.out.println("변경 전 재고: " + productDto.productStock());
            int productStock = scanner.nextInt(); // 새로운 재고를 입력받음

            // 새로운 데이터를 dto에 담기
            ProductDto newProductDto = new ProductDto(productId, productName, productPrice, productStock);

            // 데이터를 수정하는 메서드 호출
            int result = vmManagementService.updateVMData(newProductDto);
            if (result != 0) {
                System.out.println("제품수정 성공");
            } else {
                System.out.println("제품수정 실패");
            }
        } else {
            System.out.println("해당 제품이 존재하지 않습니다.");
        }
    }
    private void deleteVMDataView() {
        int result;
        String deleteYesOrNo;

        // 삭제할 아이디 입력 받기
        System.out.println("삭제할 제품의 번호를 입력하세요.");
        int productId = scanner.nextInt();

        ProductDto productDto = vmManagementService.showOneVMData(productId);
        if (productDto != null) {
            // 삭제 의사 확인하기
            do {
                System.out.println("정말로 삭제하겠습니까?(Y/N)");
                deleteYesOrNo = scanner.next();
            } while (!(deleteYesOrNo.equalsIgnoreCase("Y")
                    || deleteYesOrNo.equalsIgnoreCase("N")));

            if (deleteYesOrNo.equalsIgnoreCase("Y")) {
                // 삭제 처리하기
                result = vmManagementService.deleteVMData(productId);
                if (result != 0) {
                    System.out.println("제품삭제 성공");
                } else {
                    System.out.println("제품삭제 실패");
                }
            }
        } else {
            System.out.println("해당 제품이 존재하지 않습니다.");
        }
    }
    public void showAllVMDataView() {
        List<ProductDto> dtoList;
        dtoList = vmManagementService.showAllVMData();

        if (dtoList.isEmpty()) {
            System.out.println("제품 데이터가 존재하지 않습니다.");
        } else {
            PrintInfo.line();
            PrintInfo.productTitle();
            PrintInfo.line();
            for (ProductDto productDto : dtoList) {
                System.out.println(productDto);
            }
            PrintInfo.line();
        }
    }
    private void showOneVMDataView() {
        int productId;

        System.out.println("검색할 제품번호를 입력하세요.");
        productId = scanner.nextInt();
        ProductDto productDto = vmManagementService.showOneVMData(productId);

        if (productDto == null) {
            System.out.println("해당 제품이 존재하지 않습니다.");
        } else {
            PrintInfo.line();
            PrintInfo.productTitle();
            PrintInfo.line();
            System.out.println(productDto);
            PrintInfo.line();
        }
    }
}
