package com.mavene.expensetracker.constants;

public class Contants {
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String USER_REGEX = "^[a-zA-Z0-9_]{3,}$";
    public static final String API_SECRET_KEY = "myexpensetrackerappsecretkey";
    public static final long ACCESS_TOKEN_VALIDITY_MILLISECONDS = 2 * 60 * 60 * 1000;
}
