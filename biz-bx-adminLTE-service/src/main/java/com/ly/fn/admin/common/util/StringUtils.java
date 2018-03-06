package com.ly.fn.admin.common.util;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * 类名称：StringUtils
 * 类描述：
 * 创建人：张波波【zbb08364】
 * 创建时间：2018/3/6.13:44
 * 修改备注：
 *
 * @version 1.0.0
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils{

    private static Logger logger = LoggerFactory.getLogger(StringUtils.class);

    final static int BUFFER_SIZE = 4096;

    private static String getIntRandom(int length) {
        int[] array = new int[length];
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < length; i++) {
            array[i] = (int) (Math.random() * 10);
            str.append(array[i]);
        }
        return str.toString();
    }

    /**
     * 将输入流转换成字符串
     * @param inputStream
     */
    public static String InputStreamTOString(InputStream inputStream) {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        String string = null;
        int count = 0;
        try {
            while ((count = inputStream.read(data, 0, BUFFER_SIZE)) != -1)
                outStream.write(data, 0, count);
            string = new String(outStream.toByteArray(), "UTF-8");
        } catch (Exception e) {
            logger.error("字符串工具类：输入流转换成字符串异常",e);
        }
        return string;
    }

    /**
     * 将InputStream转换成某种字符编码的String
     * @param inputStream
     * @param encode
     */
    public static String InputStreamTOString(InputStream inputStream, String encode) {
        String string = null;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        try {
            while ((count = inputStream.read(data, 0, BUFFER_SIZE)) != -1)
                outStream.write(data, 0, count);
            string = new String(outStream.toByteArray(), encode);
        } catch (Exception e) {
            logger.error("字符串工具类：InputStream转换成某种字符编码的String异常",e);
        }
        return string;
    }

    /**
     * 将String转换成InputStream
     * @param inputStream
     */
    public static InputStream StringTOInputStream(String inputStream) throws Exception {
        ByteArrayInputStream is = new ByteArrayInputStream(inputStream.getBytes("UTF-8"));
        return is;
    }

    /**
     * 将String转换成InputStream
     * @param inputStream
     */
    public static byte[] StringTOByte(String inputStream) {
        byte[] bytes = null;
        try {
            bytes = InputStreamTOByte(StringTOInputStream(inputStream));
        } catch (Exception e) {
            logger.error("字符串工具类：String转换成InputStream异常",e);
        }
        return bytes;
    }


    /**
     * 将输入流转换成字节
     * @param inputStream
     */
    public static byte[] InputStreamTOByte(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = inputStream.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);
        return outStream.toByteArray();
    }

    /**
     * 将byte数组转换成InputStream
     * @param in
     */
    public static InputStream byteTOInputStream(byte[] in) throws Exception {
        ByteArrayInputStream is = new ByteArrayInputStream(in);
        return is;
    }

    /**
     * 将byte数组转换成String
     * @param in
     */
    public static String byteTOString(byte[] in) {

        InputStream is = null;
        try {
            is = byteTOInputStream(in);
        } catch (Exception e) {
            logger.error("字符串工具类：byte数组转换成String异常",e);
        }
        return InputStreamTOString(is, "UTF-8");
    }

    /**
     * 根据文件路径创建文件输入流处理
     * 以字节为单位（非 unicode ）
     * @param filepath
     */
    public static FileInputStream getFileInputStream(String filepath) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filepath);
        } catch (FileNotFoundException e) {
            logger.error("字符串工具类：根据文件路径创建文件输入流处理异常",e);
        }
        return fileInputStream;
    }
    /**
     * 根据文件对象创建文件输入流处理
     * 以字节为单位（非 unicode ）
     * @param file
     */
    public static FileInputStream getFileInputStream(File file) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            logger.error("字符串工具类：根据文件对象创建文件输入流处理异常",e);
        }
        return fileInputStream;
    }


    /**
     * 获取本机ip地址
     * @return String
     * @throws UnknownHostException
     */
    public static String getLocalHost() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    public static String getLocalHostName() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            return address.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字符串按照指定字符分割，并返回一个List<Long>
     * @Title: parseString
     * @param str
     * @param regex
     * @return: List<Long>
     */
    public static List<Long> parseString(String str, String regex){
        List<Long> list = new ArrayList<Long>();
        String[] ids = str.split(regex);
        for(String id:ids){
            if(isNumeric(id)){
                list.add(Long.parseLong(id));
            }
        }
        return list;
    }


    /**
     * 把用逗号分隔的数字字符串转化成List
     * @param s
     * @return
     */
    public static List<Long> StringToList(String s) {
        List<Long> ids = new ArrayList<>();
        String[] idStr = s.split(",");
        for(int i = 0; i < idStr.length; i++) {
            ids.add(Long.valueOf(idStr[i]));
        }
        return ids;
    }

    /**
     * 把用逗号分隔的字符串转化成List
     * @param s
     * @return
     */
    public static List<String> StringToStringList(String s) {
//        List<String> ids = new ArrayList<>();
        String[] idStr = s.split(",");
        return Arrays.asList(idStr);
    }

    /**
     * 江苏省-苏州市-吴中区  -->  json
     */
    public static String addressHeadToJson(String addressHead) {
        String[] heads = addressHead.split("-");
        Map<String, String> map = new HashMap<>();
        map.put("provinceName", heads[0]);
        map.put("cityName", heads[1]);
        map.put("areaName", heads[2]);
        return new Gson().toJson(map);
    }

    /**
     * 给姓名加*
     * @param name 姓名
     * @return 加*的姓名
     */
    public static String encryptName(String name) {
        if (isEmpty(name)) {
            return null;
        }
        if (name.length() <= 1) {
            return name;
        } else if (name.length() == 2){
            String lastName = name.substring(name.length() - 1, name.length());
            return  "*" + lastName;
        } else {
            String lastName = name.substring(name.length() - 2, name.length());
            return  "*" + lastName;
        }
    }

    /**
     * 给保单号加密
     * @param policyCode
     * @return
     */
    public static String encryptPolicy(String policyCode) {
        if (isEmpty(policyCode)) {
            return null;
        }
        if (policyCode.length() <= 4) {
            return policyCode;
        } else {
            String head = policyCode.substring(0, 1);
            String lastNum = policyCode.substring(policyCode.length() - 3, policyCode.length());
            return  head + "******************" + lastNum;
        }
    }

    /**
     * 给手机号加*
     * 例如：136****8888
     * @param phone 手机号
     * @return 加*的手机号
     */
    public static String encryptPhone(String phone) {
        if (isEmpty(phone)) {
            return null;
        }
        if (phone.length() < 7) {
            return phone;
        } else {
            String head = phone.substring(0, 3);
            String tail = phone.substring(phone.length() - 4, phone.length());
            return head + "****" + tail;
        }
    }

    /**
     * 给指定的字符串加*做隐位处理。
     * @param string 要做隐位处理的字符串
     * @param begin 第一个加*的字符的索引
     * @param length 需要加*的字符的总个数
     * @return 加*后的字符串
     */
    public static String asterisk(String string, int begin, int length) {
        if (StringUtils.isEmpty(string)) {
            return string;
        }
        if ((begin + length) > string.length() - 1) {
            return string;
        }
        if (length == 0) {
            return string;
        }
        String asterisks = "";
        for (int i = 0; i < length; i++) {
            asterisks += "*";
        }
        String header = string.substring(0, begin);
        String footer = string.substring((begin + length), string.length());
        return header + asterisks + footer;
    }

//    private static boolean isEmpty(String string) {
//        return false;
//    }

    public static String trim(String str) {
        if (!StringUtils.isEmpty(str)) {
            return str.replaceAll(" ", "");
        } else {
            return str;
        }
    }

    public static boolean hasEmpty(String... strings) {
        for (String string : strings) {
            if (isEmpty(string)) {
                return true;
            }
        }
        return false;
    }

    public static String arrayToString(String[] strArray) {
        if (strArray != null && strArray.length > 0) {
            StringBuffer sb = new StringBuffer();
            for (String s : strArray) {
                sb.append(s).append(",");
            }
            return sb.substring(0, sb.length() - 1);
        }
        return "";
    }
}
