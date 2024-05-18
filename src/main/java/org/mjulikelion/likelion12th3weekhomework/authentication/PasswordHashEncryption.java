package org.mjulikelion.likelion12th3weekhomework.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;


@Component
public class PasswordHashEncryption {

    private static final String PBKDF2_WITH_SHA1 = "PBKDF2WithHmacSHA1";

    private final String salt;// salt는 암호화할 때 사용하는 임의의 값
    private final int iterationCount;// 반복 횟수(암호화 강도)
    private final int keyLength;// 키 길이(암호화된 비밀번호 길이)

    public PasswordHashEncryption(@Value("${encryption.pbkdf2.salt}") final String salt,
                                  @Value("${encryption.pbkdf2.iteration-count}") final int iterationCount,  //반복 횟수
                                  @Value("${encryption.pbkdf2.key-length}") final int keyLength) {  //람호화 되고서 키의 길이
        this.salt = salt;
        this.iterationCount = iterationCount;
        this.keyLength = keyLength;
    }

    //암호화된 문자열을 반환한다
    public String encrypt(final String plainPassword) {
        try {
            KeySpec spec = new PBEKeySpec(plainPassword.toCharArray(), salt.getBytes(), iterationCount, keyLength);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PBKDF2_WITH_SHA1);
            byte[] encodedPassword = keyFactory.generateSecret(spec)
                    .getEncoded();
            return Base64.getEncoder()
                    .withoutPadding()
                    .encodeToString(encodedPassword);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Cannot encrypt password");
        }
    }

    //DB의 비번과 입력한 비번을 비교
    public boolean matches(final String plainPassword, final String hashedPassword) {
        return encrypt(plainPassword).equals(hashedPassword);
    }
}

