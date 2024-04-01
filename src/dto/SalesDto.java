package dto;

public record SalesDto(int productId, String productName, int sumSalesAmount, int countSales, String userId, String userName, int userBalance, int totalSalesAmount, int totalSalesQuantity) {

    public static SalesDto ofProductName(int productId, String productName, int sumSalesAmount, int countSales) {
        return new SalesDto(productId, productName, sumSalesAmount, countSales, null, null, 0,0,0);
    }

    public static SalesDto ofUser(String userId, String userName, int sumSalesAmount, int userBalance) {
        return new SalesDto(0, null, sumSalesAmount, 0, userId, userName, userBalance,0,0);
    }

    public static SalesDto ofTotal(int totalSalesAmount, int totalSalesQuantity) {
        return new SalesDto(0, null, 0, 0, null, null, 0, totalSalesAmount, totalSalesQuantity);
    }

    @Override
    public String toString() {
        if (totalSalesAmount != 0 && totalSalesQuantity != 0) {
            return " " + totalSalesAmount + " \t\t\t " + totalSalesQuantity;
        } else if (userId != null && userName != null) {
            return " " + userId + "    \t " + userName + "  \t " + sumSalesAmount + "   \t\t " + userBalance;
        } else {
            return " " + productId + " \t\t " + productName + "    \t " + sumSalesAmount + " \t\t " + countSales;
        }
    }
}
