package user;

public record UserDto(String userId, String userPw, String userName, String userTel, int userMoney) {
    // dto: 데이터베이스로부터 필요한 만큼의 데이터만 추려서 가지고 다니는 용도

    public static UserDto allData(String userId, String userPw, String userName, String userTel, int userMoney) {
        return new UserDto(userId, userPw, userName, userTel, userMoney);
    }

    @Override
    public String toString() {
        return userId + "   \t " + userPw + "    \t " + userName + " \t " + userTel + " \t " + userMoney;
    }

}
