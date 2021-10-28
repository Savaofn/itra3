import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PCMoveAttributes {
    private final int pcMove;
    private final String key;
    private final String HMAC;
    private final int numberOfAvailableMoves;

    public PCMoveAttributes(int numberOfAvailableMoves) throws NoSuchAlgorithmException, InvalidKeyException {
        this.numberOfAvailableMoves = numberOfAvailableMoves;
        this.pcMove = generatePCMove();
        this.key = generateKey();
        this.HMAC = generateHMAC();
    }

    public int generatePCMove() {
        SecureRandom random = new SecureRandom();
        int pcMove = random.nextInt(numberOfAvailableMoves) + 1;
        return pcMove;
    }

    public String generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        SecureRandom random = new SecureRandom();
        keyGen.init(random);
        SecretKey secretKey = keyGen.generateKey();
        String key = new BigInteger(1, secretKey.getEncoded()).toString(16);
        return key;
    }

    public String generateHMAC() throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        sha256_HMAC.init(new SecretKeySpec(key.getBytes(), "HmacSHA256"));
        byte[] result = sha256_HMAC.doFinal(Integer.toString(pcMove).getBytes());
        String hmac = DatatypeConverter.printHexBinary(result);
        return hmac;
    }

    public int getPcMove() {
        return pcMove;
    }

    public String getHMAC() {
        return HMAC;
    }

    public String getKey() {
        return key;
    }
}
