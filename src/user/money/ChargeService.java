package user.money;

import db.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChargeService {
    public int updateBalance(String userId, int amount) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt;
        ResultSet rs;
        int currentBalance = 0;
        int newBalance = 0;

        try {
            // 현재 잔액 조회
            String query = "SELECT u_balance FROM user WHERE u_id = ?";
            psmt = conn.prepareStatement(query);
            psmt.setString(1, userId);
            rs = psmt.executeQuery();

            if (rs.next()) {
                currentBalance = rs.getInt("u_balance");
            }

            // 새로운 잔액 계산
            newBalance = currentBalance + amount;

            // 잔액 업데이트
            String updateQuery = "UPDATE user SET u_balance = ? WHERE u_id = ?";
            psmt = conn.prepareStatement(updateQuery);
            psmt.setInt(1, newBalance);
            psmt.setString(2, userId);
            psmt.executeUpdate();

            // 닫기
            psmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return newBalance;
    }

    public int getBalance(String userId) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt;
        ResultSet rs;
        int balance = 0;

        try {
            String query = "SELECT u_balance FROM user WHERE u_id = ?";
            psmt = conn.prepareStatement(query);
            psmt.setString(1, userId);
            rs = psmt.executeQuery();

            if (rs.next()) {
                balance = rs.getInt("u_balance");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return balance;
    }

    public int returnBalance(String userId) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt;
        ResultSet rs;
        int currentBalance = 0;

        try {
            // 현재 잔액 조회
            String selectQuery = "SELECT u_balance FROM user WHERE u_id = ?";
            psmt = conn.prepareStatement(selectQuery);
            psmt.setString(1, userId);
            rs = psmt.executeQuery();

            if (rs.next()) {
                currentBalance = rs.getInt("u_balance");
            }

            // 잔액 업데이트
            String updateQuery = "UPDATE user SET u_balance = 0 WHERE u_id = ?";
            psmt = conn.prepareStatement(updateQuery);
            psmt.setString(1, userId);
            psmt.executeUpdate();

            // 닫기
            psmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return currentBalance;
    }
}
