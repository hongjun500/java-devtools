package com.hongjun.web.access;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * @author hongjun500
 * @date 2024-01-27 15:01
 * @tool ThinkPadX1隐士
 * Created with 2023.2.2.IntelliJ IDEA
 * Description: AccessGenerator
 * en: @link <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#KeyPairGenerator">en</a>
 * zh: @link <a href="https://doc.qzxdp.cn/jdk/17/zh/specs/security/standard-names.html#">zh</a>
 * 建议直接使用 hu-tool 工具包中的 SecureUtil 工具类 {@link SecureUtil}
 * @link <a href="https://doc.hutool.cn/module/crypto/">doc</a>
 */
public class AccessGenerator {
    // -------------------------------------- KeyPairGenerator --------------------------------------

    /**
     * KeyPairGenerator 算法
     *
     * @return KeyPair 密钥对
     * @throws Exception 异常
     * @link <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#KeyPairGenerator">...</a>
     */
    public static KeyPair generateKeyPair() throws Exception {
        // 默认使用 ECC 密钥对生成器生成密钥对
        return generateKeyPair("EC");
    }

    /**
     * KeyPairGenerator 算法
     *
     * @param algorithm 算法 可选值:
     *                  DiffieHellman	为 Diffie-Hellman KeyAgreement 算法生成密钥对。Note: key.getAlgorithm() 将返回“DH”而不是“DiffieHellman”。
     *                  DSA	为数字签名算法生成密钥对。
     *                  RSA	为 RSA 算法（签名/密码）生成密钥对。
     *                  RSASSA-PSS	为 RSASSA-PSS 签名算法生成密钥对。
     *                  EC	为椭圆曲线算法生成密钥对。
     *                  EdDSA	使用 RFC 8032 中定义的椭圆曲线为爱德华兹曲线签名算法生成密钥对。
     *                  Ed25519	使用 RFC 8032 中定义的 Ed25519 为爱德华兹曲线签名算法生成密钥对。
     *                  Ed448	使用 RFC 8032 中定义的 Ed448 为爱德华兹曲线签名算法生成密钥对。
     *                  XDH	使用 RFC 7748 中定义的椭圆曲线为 Diffie-Hellman 密钥协议生成密钥对。
     *                  X25519	根据 RFC 7748 中的定义，为与 Curve25519 的 Diffie-Hellman 密钥协议生成密钥对。
     *                  X448	根据 RFC 7748 中的定义，为与 Curve448 的 Diffie-Hellman 密钥协议生成密钥对。
     * @return KeyPair 密钥对
     */
    private static KeyPair generateKeyPair(String algorithm) throws Exception {
        // 使用 ECC 密钥对生成器生成密钥对
        // KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // keyPairGenerator.initialize(256); // 设置密钥长度为256位
        // return keyPairGenerator.generateKeyPair();
        return SecureUtil.generateKeyPair(algorithm);
    }

    /**
     * 使用私钥对数据进行签名
     *
     * @param data             要签名的数据
     * @param privateKeyBase64 Base64 编码的私钥字符串
     * @return Base64 编码的签名字符串
     */
    private static String signData(String data, String privateKeyBase64) throws Exception {
        Sign sign = SecureUtil.sign(SignAlgorithm.SHA512withECDSA, privateKeyBase64, null);
        byte[] signed = sign.sign(data);
        return Base64.getEncoder().encodeToString(signed);

        // 初始化签名对象
        // Signature signature = Signature.getInstance("SHA512withECDSA");
        // signature.initSign(privateKey);

        // 更新要签名的数据
        // signature.update(data.getBytes());

        // 执行签名并将签名转换为 Base64 编码的字符串
        // byte[] signedData = signature.sign();
        // return Base64.getEncoder().encodeToString(signedData);
    }

    /**
     * 使用公钥验证签名
     *
     * @param data            要验证的数据
     * @param signature       Base64 编码的签名字符串
     * @param publicKeyBase64 Base64 编码的公钥字符串
     * @return true/false
     */
    private static boolean verifySignature(String data, String signature, String publicKeyBase64) throws Exception {
        Sign sign = SecureUtil.sign(SignAlgorithm.SHA512withECDSA, null, publicKeyBase64);
        return sign.verify(data.getBytes(), Base64.getDecoder().decode(signature));

        // 初始化签名对象
        // Signature sig = Signature.getInstance("SHA512withECDSA");
        // sig.initVerify(publicKey);

        // 更新要验证的数据
        // sig.update(data.getBytes());

        // 将 Base64 编码的签名字符串解码为字节数组
        // byte[] signatureBytes = Base64.getDecoder().decode(signature);

        // 验证签名
        // return sig.verify(signatureBytes);
    }

    // -------------------------------------- Cipher --------------------------------------

    /**
     * Cipher 实现 AES/ECB/PKCS5Padding 算法 利用自定义 secretKey 对 data 进行加密
     * Cipher 算法 (对称加密)
     *
     * @param data      要加密的数据
     * @param secretKey 密钥 长度必须满足 16/24/32
     * @link <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#Cipher">...</a>
     */
    private static String encryptAes(String data, String secretKey) throws Exception {
        // 如果密钥长度不满足 16/24/32 则抛出异常
        if (secretKey.length() != 16 && secretKey.length() != 24 && secretKey.length() != 32) {
            throw new IllegalArgumentException("Invalid AES secretKey length (must be 16, 24 or 32 bytes)");
        }
        // 创建 AES 密钥规范
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");

        // 创建 AES 密码器
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        // 执行加密并将加密后的数据转换为 Base64 编码的字符串
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    /**
     * Cipher 实现 AES/ECB/PKCS5Padding 算法 利用自定义 secretKey 对 data 进行解密
     * Cipher 算法 (对称加密)
     *
     * @link <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#Cipher">...</a>
     */
    private static String decryptAes(String encryptedData, String secretKey) throws Exception {
        if (secretKey.length() != 16 && secretKey.length() != 24 && secretKey.length() != 32) {
            throw new IllegalArgumentException("Invalid AES secretKey length (must be 16, 24 or 32 bytes)");
        }
        // 创建 AES 密钥规范
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");

        // 创建 AES 密码器
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        // 执行解密并将解密后的数据转换为字符串
        byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        // return new String(decryptedData);

        return SecureUtil.aes(secretKey.getBytes()).decryptStr(encryptedData);
    }


    public static void main(String[] args) {
        try {
            // 生成密钥对
            KeyPair keyPair = generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();
            // 要加密的数据
            String dataToEncrypt = "Hello, this is a secret message!";
            // 使用私钥进行数字签名
            String signature = signData(dataToEncrypt, Base64.getEncoder().encodeToString(privateKey.getEncoded()));
            System.out.println("Digital Signature: " + signature);

            // 使用公钥验证数字签名
            boolean isSignatureValid = verifySignature(dataToEncrypt, signature, Base64.getEncoder().encodeToString(publicKey.getEncoded()));
            System.out.println("Is Signature Valid: " + isSignatureValid);

            String secretKey = "1234567890123456";
            String encryptAes = encryptAes(dataToEncrypt, secretKey);
            String decryptAes = decryptAes(encryptAes, secretKey);
            assert dataToEncrypt.equals(decryptAes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
