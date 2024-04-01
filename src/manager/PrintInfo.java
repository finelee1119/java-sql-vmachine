package manager;

public class PrintInfo {
    public static void productTitle() {
        String str;
        str = String.format("제품번호\t 제품명 \t 가격\t재고");
        System.out.println(str);
    }

    public static void userTitle() {
        String str;
        str = String.format("아이디 \t 비밀번호 \t 회원명 \t\t 전화번호 \t 충전잔액");
        System.out.println(str);
    }

    public static void salesProductTitle() {
        String str;
        str = String.format("제품번호\t 제품명 \t 판매금액\t 판매수량");
        System.out.println(str);
    }

    public static void salesUserTitle() {
        String str;
        str = String.format("회원아이디\t 회원명 \t 구매금액\t 충전잔액");
        System.out.println(str);
    }

    public static void salesTotalTitle() {
        String str;
        str = String.format("총판매금액 \t 총판매수량");
        System.out.println(str);
    }

    public static void line() {
        System.out.println("======================================================");
    }

}
