import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.SecureRandom;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class App {

    public static void main(String[] args) throws Exception {
        final var aes = new AesEncryptionUtil("Y2lhbzU2Nzg5MTIzNDU2Cg==");
        final var encryptedText = aes.encrypt("Hello, World!");
        final var decryptedText = aes.decrypt(encryptedText);

        final Logger log = Logger.getLogger(App.class.getName());
        log.info(MessageFormat.format("encryptedText ---> {0}", encryptedText));
        log.info(MessageFormat.format("decryptedText ---> {0}", decryptedText));
    }
}

/**
 * AesEncryptionUtil
 */
class AesEncryptionUtil {
    private static final String AES_GCM_NO_PADDING = "AES/GCM/NoPadding";
    private static final String ALGORITHM = "AES";
    private static final int IV_LENGTH = 12;
    private static final int T_LEN = 96;

    private final Key key;

    public AesEncryptionUtil(String pwdKey) {
        final byte[] decodedPwd = Base64.getDecoder().decode(pwdKey);
        this.key = new SecretKeySpec(decodedPwd, ALGORITHM);
    }

    public String encrypt(String text) throws GeneralSecurityException {
        byte[] iv = new byte[IV_LENGTH];
        (new SecureRandom()).nextBytes(iv);

        Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);
        GCMParameterSpec ivSpec = new GCMParameterSpec(T_LEN, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

        byte[] ciphertext = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
        byte[] encrypted = new byte[iv.length + ciphertext.length];
        System.arraycopy(iv, 0, encrypted, 0, iv.length);
        System.arraycopy(ciphertext, 0, encrypted, iv.length, ciphertext.length);

        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String encryptedText) throws GeneralSecurityException {
        byte[] decoded = Base64.getDecoder().decode(encryptedText);

        byte[] iv = Arrays.copyOfRange(decoded, 0, IV_LENGTH);

        Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);
        GCMParameterSpec ivSpec = new GCMParameterSpec(T_LEN, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);

        byte[] decryptedText = cipher.doFinal(Arrays.copyOfRange(decoded, IV_LENGTH, decoded.length));

        return new String(decryptedText, StandardCharsets.UTF_8);
    }
}
