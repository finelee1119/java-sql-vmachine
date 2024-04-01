package vendingMachine.first;

import db.DBConn;
import dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstUserService {
    public int signUpService(UserDto userDto) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt;
        int result = 0;

        try {
            String query = "insert into user(u_id, u_pw, u_name, u_tel, u_balance) values(?, ?, ?, ?, ?)";
            psmt = conn.prepareStatement(query);

            psmt.setString(1, userDto.userId());
            psmt.setString(2, userDto.userPw());
            psmt.setString(3, userDto.userName());
            psmt.setString(4, userDto.userTel());
            psmt.setInt(5, userDto.balance());

            result = psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public boolean signInService(String userId, String userPw) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt;
        ResultSet rs;
        boolean signInSuccess = false;

        try {
            String query = "SELECT u_pw FROM user WHERE u_id = ?";
            psmt = conn.prepareStatement(query);
            psmt.setString(1, userId);
            rs = psmt.executeQuery();

            if (rs.next()) {
                String pwFromDB = rs.getString("u_pw");
                if (pwFromDB.equals(userPw)) {
                    signInSuccess = true;
                }
            }

            psmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return signInSuccess;
    }
}
