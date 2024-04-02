package manager.sales;


import db.DBConn;
import dto.SalesDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesService {
    public SalesDto getProductSalesReport(int productId) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt;
        ResultSet rs;
        SalesDto salesDto = null;

        try {
            String query = "SELECT p.p_id, p.p_name, SUM(s.s_salesAmount) AS sumSalesAmount, COUNT(*) AS countSales " +
                    "FROM sales s " +
                    "JOIN product p ON s.p_id = p.p_id " +
                    "WHERE p.p_id = ? " +
                    "GROUP BY p.p_id, p.p_name";
            psmt = conn.prepareStatement(query);
            psmt.setInt(1, productId);
            rs = psmt.executeQuery();

            if (rs.next()) {
                int sumSalesAmount = rs.getInt("sumSalesAmount");
                int countSales = rs.getInt("countSales");
                String productName = rs.getString("p_name");
                salesDto = SalesDto.ofProductName(productId, productName, sumSalesAmount, countSales);
            }

            psmt.close();
            rs.close();
            return salesDto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public SalesDto getUserSalesReport(String userId) {
        //회원별 판매현황: 회원별로 회원아이디, 회원명, 총판매금액, 충전잔액
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt;
        ResultSet rs;
        SalesDto salesDto = null;

        try {
            String query = "SELECT u.u_id, u.u_name, SUM(s.s_salesAmount) AS sumSalesAmount, u.u_balance " +
                    "FROM sales s " +
                    "INNER JOIN user u ON s.u_id = u.u_id " +
                    "WHERE u.u_id = ? " +
                    "GROUP BY u.u_id, u.u_name, u.u_balance";
            psmt = conn.prepareStatement(query);
            psmt.setString(1, userId);
            rs = psmt.executeQuery();

            if (rs.next()) {
                String userName = rs.getString("u_name");
                int sumSalesAmount = rs.getInt("sumSalesAmount");
                int userBalance = rs.getInt("u_balance");
                salesDto = SalesDto.ofUser(userId, userName, sumSalesAmount, userBalance);
            }

            psmt.close();
            rs.close();
            return salesDto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public SalesDto getTotalSalesReport() {
        //총판매현황: 총판매금액, 총판매수량
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt;
        ResultSet rs;
        SalesDto salesDto = null;

        try {
            String query = "SELECT SUM(s.s_salesAmount) AS totalSalesAmount, COUNT(*) AS totalSalesQuantity " +
                    "FROM sales s " +
                    "INNER JOIN product p ON s.p_id = p.p_id " +
                    "INNER JOIN user u ON s.u_id = u.u_id";
            psmt = conn.prepareStatement(query);
            rs = psmt.executeQuery();

            if (rs.next()) {
                int totalSalesAmount = rs.getInt("totalSalesAmount");
                int totalSalesQuantity = rs.getInt("totalSalesQuantity");
                salesDto = SalesDto.ofTotal(totalSalesAmount, totalSalesQuantity);
            }

            psmt.close();
            rs.close();
            return salesDto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
