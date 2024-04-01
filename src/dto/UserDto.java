package dto;

public record UserDto(String userId, String userPw, String userName, String userTel, int balance) {

    public static UserDto allOf(String userId, String userPw, String userName, String userTel, int balance) {
        return new UserDto(userId,userPw,userName,userTel,balance);
    }

    public static UserDto allOfExceptBalance(String userId, String userPw, String userName, String userTel) {
        return new UserDto(userId,userPw,userName,userTel, 0);
    }


    @Override
    public String toString() {
        return userId + " \t " + userPw + "    \t " + userName + " \t " + userTel + "    \t " + balance;
    }
}
