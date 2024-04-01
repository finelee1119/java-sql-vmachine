package dto;

public record ProductDto(int productId, String productName, int productPrice, int productStock) {

    public static ProductDto allOf(int productId, String productName, int productPrice, int productStock) {
        return new ProductDto(productId, productName,productPrice,productStock);
    }

    public static ProductDto allOfExceptId(String productName, int productPrice, int productStock) {
        return new ProductDto(0, productName,productPrice,productStock);
    }

    @Override
    public String toString() {
        return " " + productId + " \t\t " + productName + " \t " + productPrice + " \t " + productStock;
    }
}