package bluedot.electrochemistry.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import sun.security.provider.MD5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class EncryptUtilTest {

    @Test
    void md5Decode() throws FileNotFoundException {
        String salt = EncryptUtil.getSalt();
        String s = EncryptUtil.md5Decode("12345", salt);

        System.out.println(s);
        boolean b = EncryptUtil.md5Encode("12345", s, salt);
        System.out.println(b);
        System.out.println(salt);
        File file = new File("D:\\SMPPClient\\logs\\error.log");
        System.out.println(file.exists());
        FileInputStream fileInputStream = new FileInputStream(file);
    }

    @Test
    void md5Encode() {

    }
}