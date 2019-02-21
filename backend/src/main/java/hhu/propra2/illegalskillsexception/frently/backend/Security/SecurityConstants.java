package hhu.propra2.illegalskillsexception.frently.backend.Security;

class SecurityConstants {
    static final String SECRET = "SecretKeyToGenJWTs";
    static final long EXPIRATION_TIME = 864_000_000; // 10 days
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    static final String SIGN_UP_URL = "/users/sign-up";
    static final String LOGIN_URL = "/login";
    static final String PROPAY_TEST1 = "/propay/test1";
    static final String PROPAY_TEST2 = "/propay/test2";
    static final String PROPAY_TEST3 = "/propay/test3";
    static final String PROPAY_TEST4 = "/propay/test4";
    static final String PROPAY_TEST5 = "/propay/test5";
    static final String PROPAY_TEST6 = "/propay/test6";
    static final String PROPAY_TEST7 = "/propay/test7";
    static final String PROPAY_TEST8 = "/propay/test8";
    static final String PROPAY_TEST9 = "/propay/test9";
    static final String PROPAY_TEST10 = "/propay/test10";
}