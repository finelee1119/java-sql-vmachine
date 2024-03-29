package user;

import db.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserService {

    public int signUpService(UserDto userDto) {
        Connection conn = DBConn.getConnection();
        PreparedStatement psmt = null;
        int result = 0;

        try {
            String query = "insert into user(u_id, u_pw, u_name, u_tel, u_money) values(?, ?, ?, ?, ?)";
            psmt = conn.prepareStatement(query);

            psmt.setString(1, userDto.userId());
            psmt.setString(2, userDto.userPw());
            psmt.setString(3, userDto.userName());
            psmt.setString(4, userDto.userTel());
            psmt.setInt(5, userDto.userMoney());

            result = psmt.executeUpdate(); // executeUpdate: (insert, update, delete) 수정한 레코드 개수 반환
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
