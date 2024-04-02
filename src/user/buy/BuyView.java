package user.buy;

import dto.ProductDto;
import dto.UserDto;
import manager.member.MemberService;
import manager.product.ProductService;
import manager.product.ProductView;
import user.money.ChargeService;

import java.util.Scanner;

public class BuyView {
    Scanner scanner;
    BuyService buyService;
    ChargeService chargeService;
    ProductView productView;
    ProductService productService;
    MemberService memberService;
    ProductDto productDto;
    UserDto userDto;
    int productId;
    String userId;

    public BuyView() {
        this.scanner = new Scanner(System.in);
        this.buyService = new BuyService();
        this.chargeService = new ChargeService();
        this.productView = new ProductView();
        this.productService = new ProductService();
        this.memberService = new MemberService();
    }

    public void buyProductView(String userId) {
        productView.showAllProductView();
        selectProduct();

        productDto = hasProduct();
        userDto = hasUser(userId);

        if (productDto != null) {
            int balance = chargeService.getBalance(userDto.userId());

            if (balance >= productDto.productPrice() && productDto.productStock() > 0) {
                deductMoney(userId);
                deductStock();

                provideProduct(userId);

                buyService.recordSalesData(productDto, userDto);
            } else {
                System.out.println("해당 제품을 구입할 수 없습니다.");
                //충전잔액이 충분하지 않다면 메시지를 출력하고 금액을 충전하게 한다.
                //재고가 충분하지 않다면 메시지를 출력하고 다른 메뉴를 선택하게 한다.
            }

        } else {
            System.out.println("해당 제품이 존재하지 않습니다.");
        }
    }



    private void selectProduct() {
        System.out.println("1회당 1개의 제품만 구입 가능합니다.");
        System.out.println("제품번호 입력: ");
        productId = scanner.nextInt();
    }
    private ProductDto hasProduct() {
        return productService.showOneProductData(productId);
    }
    private UserDto hasUser(String userId) {
        return memberService.showOneUserData(userId);
    }
    private void deductMoney(String userId) {
        chargeService.updateBalance(userId, -productDto.productPrice());
    }
    private void deductStock() {
        productService.updateProductData(
                new ProductDto(
                        productId,
                        productDto.productName(),
                        productDto.productPrice(),
                        productDto.productStock() - 1
                )
        );
    }
    private void provideProduct(String userId) {
        System.out.println(productDto.productName() + " 나왔습니다. 잔액: " + chargeService.getBalance(userId));
    }
}
