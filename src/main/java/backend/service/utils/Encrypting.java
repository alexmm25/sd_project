package backend.service.utils;

import com.lambdaworks.crypto.SCryptUtil;


public class Encrypting {

    private static final int N = 16384;
    private static final int r = 8;
    private static final int p = 1;

    public static String encrypt(String password) {
        return SCryptUtil.scrypt(password, N, r, p);
    }

    public static Boolean check(String password, String stored) {
        try {
            return SCryptUtil.check(password, stored);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}