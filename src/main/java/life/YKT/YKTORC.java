package life.YKT;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by wuhaolin on 9/20/14.
 * :一卡通验证码识别
 * 这个网页的http://192.168.44.7:10000/sisms/index.php/login/index
 */
public class YKTORC {

  /**
   * 验证码中一个数字的像素大小
   */
  private static final int SubImgWidth = 8, SubImgHeight = 10;

  /**
   * 把验证码中的数值进行分离后,一个个数值的MD5值,从0~9
   */
  private static byte[][] MD5 = {
    {-51, 101, 59, 86, -69, 101, 109, 75, 124, -44, 112, 29, -2, -123, -39, -60},
    {-16, -49, 68, 66, -81, 74, 90, -113, -25, 18, -94, -77, 57, -121, -62, -117},
    {105, 32, -88, -69, 34, -69, 43, 108, 53, 120, 86, -42, 107, -76, 29, 121},
    {4, -95, -40, -19, -84, 99, 23, -78, 27, 43, -70, 111, 27, 56, 112, 121},
    {-75, 59, 64, -62, 11, -66, 36, -53, -73, 69, 88, -22, 6, 7, 52, -11},
    {-55, -24, -109, 57, -82, 94, 72, -95, -98, -78, 113, -102, -22, 111, 64, 94},
    {24, 105, -74, -6, -47, -2, -24, 121, 77, 21, -96, 89, -15, -37, 13, -127},
    {2, -69, 83, 87, 22, -9, 71, 86, 112, -121, -28, -38, -99, 66, 86, -49},
    {2, -42, -59, -49, 79, -71, -92, -20, -108, 111, 1, -12, -63, -108, 110, 111},
    {96, 76, 74, 116, -114, 107, 119, -58, -10, -17, 87, 107, -83, -86, -14, 10}
  };

  /**
   * 对一卡通登入网站的验证码进行识别
   *
   * @param file 验证码的图片文件
   * @return 识别的结果
   */
  public static String orc(File file) {
    String re = "";
    try {
      BufferedImage bufferedImage = ImageIO.read(file);
      for (int i = 0; i < 4; i++) {

        BufferedImage subImg = bufferedImage.getSubimage(i * (SubImgWidth + 1) + 1, 3, SubImgWidth, SubImgHeight);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(subImg, "png", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();

        MessageDigest messageDigest = null;
        try {
          messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
          e.printStackTrace();
        }
        messageDigest.update(imageInByte);
        byte[] resultByteArray = messageDigest.digest();
        for (int j = 0; j < MD5.length; j++) {
          if (Arrays.equals(resultByteArray, MD5[j])) {
            re += Integer.toString(j);
            break;
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return re;
  }

  /**
   * 对一卡通登入网站的验证码进行识别
   *
   * @param bytes 验证码
   * @return 识别的结果
   */
  public static String orc(byte[] bytes) {
    String re = "";
    try {
      InputStream in = new ByteArrayInputStream(bytes);
      BufferedImage bufferedImage = ImageIO.read(in);
      for (int i = 0; i < 4; i++) {

        BufferedImage subImg = bufferedImage.getSubimage(i * (SubImgWidth + 1) + 1, 3, SubImgWidth, SubImgHeight);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(subImg, "png", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();

        MessageDigest messageDigest = null;
        try {
          messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
          e.printStackTrace();
        }
        messageDigest.update(imageInByte);
        byte[] resultByteArray = messageDigest.digest();
        for (int j = 0; j < MD5.length; j++) {
          if (Arrays.equals(resultByteArray, MD5[j])) {
            re += Integer.toString(j);
            break;
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return re;
  }

}
