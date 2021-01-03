## 作业
```java
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class PassWordTest {

    private static final String SHA1 = "SHA-1";
    private static final String MD5 = "MD5";
    private static final int HASH_INTERATIONS = 1024;
    private static final int SALT_SIZE = 8;

    // 模拟数据库
    private static Map<String, User> dbMap = new HashMap<>(2);

    public static void main(String[] args) {
        // 注册用户
        register("xiaowang", "1234567890");
        System.out.println(dbMap.get("xiaowang"));
        register("xiaozhang", "2234567890");
        System.out.println(dbMap.get("xiaozhang"));
        // 登录验证
        System.out.println("用户xiaowang登录结果：" + checkPWD("xiaowang", "1234567890"));
        System.out.println("用户xiaozhang登录结果：" + checkPWD("xiaozhang", "12311234565"));
    }

    // 注册用户
    public static void register(String userName, String pwd) {
        // 随机盐值
        SecureRandom random = new SecureRandom();
        byte[] rdm = new byte[SALT_SIZE];
        random.nextBytes(rdm);
        String salt = Hex.encodeHexString(rdm);
        // 密码加密
        pwd = encryptString(pwd, salt);
        // 保存用户信息
        dbMap.put(userName, new User(userName, pwd, salt));
    }

    /**
     * 加密字符串
     *
     * @param str
     * @return
     */
    public static String encryptString(String str, String salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance(SHA1);
//            MessageDigest digest = MessageDigest.getInstance(MD5);

            if (salt != null) {
                digest.update(salt.getBytes());
            }

            byte[] result = digest.digest(str.getBytes());

            for (int i = 1; i < HASH_INTERATIONS; i++) {
                digest.reset();
                result = digest.digest(result);
            }
            return Hex.encodeHexString(result);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 校验用户密码
     */
    public static Boolean checkPWD(String userName, String pwd) {
        if (StringUtils.isAnyBlank(userName, pwd)) {
            return false;
        }
        if (dbMap.get(userName) == null) {
            return false;
        }
        // 从数据库获取用户数据
        User dbUser = dbMap.get(userName);
        // 对输入的明文密码进行加密
        pwd = encryptString(pwd, dbUser.salt);
        // 判断相等
        return dbUser.pwd.equals(pwd);
    }

    /**
     * 用户
     */
    static class User {
        String userName;
        String pwd;
        String salt;

        public User(String userName, String pwd, String salt) {
            this.userName = userName;
            this.pwd = pwd;
            this.salt = salt;
        }

        @Override
        public String toString() {
            return "User{" +
                    "userName='" + userName + "'" +
                    ", pwd='" + pwd + "'" +
                    ", salt='" + salt + "'" +
                    "}";
        }
    }

}
```

## 笔记总结
[安全稳定思维导图](安全稳定.png)