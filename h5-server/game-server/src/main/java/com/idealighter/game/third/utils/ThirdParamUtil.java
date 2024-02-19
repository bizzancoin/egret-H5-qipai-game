package com.idealighter.game.third.utils;

import com.idealighter.game.core.error.ErrorCode;
import com.idealighter.game.core.exception.IdeaRunTimeException;
import com.idealighter.game.core.util.NetUtil;
import com.idealighter.utils.exception.CyptoRuntimeException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import lombok.extern.slf4j.Slf4j;

import org.jboss.resteasy.util.Base64;

@Slf4j
public class ThirdParamUtil {

  public static final Charset UTF8 = Charset.forName("UTF-8");
  public static final String DES_ALGORITHM = "desede";

  public static final String DES_ALGORITHM_DETAIL = "desede/CBC/PKCS5Padding";

  public static byte[] keyiv;

  static {
    try {
      keyiv = Base64.decode("AAAAAAAAAAA=");
    } catch (IOException e) {
      keyiv = new byte[0];
    }
  }

  /**
   * 解密.
   *
   * @author abin
   * @date 2018年6月19日 上午11:06:10
   * @param content 文本.
   * @param password 密码.
   * @return 文本.
   */
  public static byte[] encrypt(String content, String password) {
    byte[] rawKeyData = password.getBytes(UTF8);
    try {
      DESedeKeySpec dks = new DESedeKeySpec(rawKeyData);
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_ALGORITHM);
      SecretKey key = keyFactory.generateSecret(dks);
      Cipher cipher = Cipher.getInstance(DES_ALGORITHM_DETAIL);
      IvParameterSpec ips = new IvParameterSpec(keyiv);
      cipher.init(Cipher.ENCRYPT_MODE, key, ips);
      byte[] byteContent = content.getBytes(UTF8);
      byte[] result = cipher.doFinal(byteContent);
      return result;
    } catch (InvalidKeySpecException | InvalidKeyException | NoSuchAlgorithmException
        | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException
        | InvalidAlgorithmParameterException exception) {
      log.error("encrypt 失败", exception);
      throw new IdeaRunTimeException(ErrorCode.BAD_REQUEST);
    }
  }

  /**
   * 解密 .
   *
   * @author abin
   * @date 2018年6月19日 上午11:06:40
   * @param content 文本.
   * @param password 密码.
   * @return 文本.
   */
  public static byte[] decrypt(byte[] content, String password) {
    try {
      byte[] decryptFrom = content;
      byte[] rawKeyData = password.getBytes(UTF8);
      DESedeKeySpec dks = new DESedeKeySpec(rawKeyData);

      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_ALGORITHM);
      SecretKey key = keyFactory.generateSecret(dks);

      Cipher cipher = Cipher.getInstance(DES_ALGORITHM_DETAIL);
      IvParameterSpec ips = new IvParameterSpec(keyiv);
      cipher.init(Cipher.DECRYPT_MODE, key, ips);

      byte[] decryptedData = cipher.doFinal(decryptFrom);

      return decryptedData;
    } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException
        | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException
        | InvalidAlgorithmParameterException exception) {
      log.error("decrypt 失败", exception);
      throw new IdeaRunTimeException(ErrorCode.BAD_REQUEST);
    }
  }

  /**
   * 获取请求参数 .
   *
   * @author abin
   * @date 2018年6月14日 下午9:40:01
   * @param request 请求参数.
   * @return 参数map.
   */
  private static RequestParam getRequestMap(String request) {
    Map<String, String> map = NetUtil.getQueryMap(request);
    RequestParam param = new RequestParam(map);
    return param;
  }

  /**
   * map to query.
   *
   * @author abin
   * @date 2018年6月15日 下午5:34:45
   * @param map 参数map.
   * @return query.
   */
  private static String getMapToQuery(Map<String, String> map) {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<?, ?> entry : map.entrySet()) {
      if (sb.length() > 0) {
        sb.append("&");
      }
      sb.append(String.format("%s=%s", entry.getKey().toString(), entry.getValue().toString()));
    }
    return sb.toString();
  }

  /**
   * 解密字符串 .
   *
   * @author abin
   * @date 2018年6月15日 下午5:11:41
   * @param request 请求.
   * @param desKey deskey.
   * @return 解码字符串.
   */
  private static String decodeRequestString(String request, String desKey) {
    try {

      byte[] decodedBytes = Base64.decode(request);
      byte[] decode = decrypt(decodedBytes, desKey);

      return new String(decode, UTF8);
    } catch (CyptoRuntimeException | IOException exception) {
      log.error("decodeRequestString 失败", exception);
      throw new IdeaRunTimeException(ErrorCode.BAD_REQUEST);
    }
  }

  private static String encodeRequestString(String request, String desKey) {
    try {
      byte[] encode = encrypt(request, desKey);
      return Base64.encodeBytes(encode);

    } catch (CyptoRuntimeException | NullPointerException exception) {
      log.error("encodeRequestString 失败", exception);
      throw new IdeaRunTimeException(ErrorCode.BAD_REQUEST);
    }
  }


  /**
   * decode 参数 到 map.
   *
   * @author abin
   * @date 2018年6月15日 下午5:21:09
   * @param request 请求.
   * @param desKey deskey.
   * @return 参数map.
   */
  public static RequestParam decodeRequest(String request, String desKey) {
    String decodeString = decodeRequestString(request, desKey);

    RequestParam requestParam = getRequestMap(decodeString);

    return requestParam;
  }

  /**
   * 加密参数 .
   *
   * @author abin
   * @date 2018年6月15日 下午5:37:43
   * @param map map.
   * @param desKey des.
   * @return 结果.
   */
  public static String encodeParam(LinkedHashMap<String, String> map, String desKey) {
    String rawString = getMapToQuery(map);

    System.out.println(rawString);

    String encodeString = encodeRequestString(rawString, desKey);

    return encodeString;
  }


}
