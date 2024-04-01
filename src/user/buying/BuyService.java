package user.buying;

import db.DBConn;
import dto.ProductDto;
import dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BuyService {
    public void recordSalesData(ProductDto productDto, UserDto userDto) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt;

        try {
            String sql = "INSERT INTO sales (u_id, p_id, s_salesAmount) VALUES (?, ?, ?)";
            psmt = conn.prepareStatement(sql);

            psmt.setString(1, userDto.userId()); // 회원 아이디
            psmt.setInt(2, productDto.productId()); // 제품 아이디
            psmt.setInt(3, productDto.productPrice()); // 판매당시 제품 가격

            psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
