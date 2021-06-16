package com.bitmain.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;

public class Signature {

    public static String signature = "";

    public static void contract_list_query() {

        // TODO Auto-generated method stub
        Map<String, Object> params = new TreeMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            startTime = sdf.parse("2019/01/01");
            endTime = sdf.parse("2020/10/01");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        params.put("userId", userId);
        params.put("coinType", coinTypeStr);
        params.put("contractType", contractType.toString());
        params.put("startTime", getStringFromByDate(startTime));
        params.put("endTime", getStringFromByDate(endTime));
        params.put("pageNum", pageNum != null ? pageNum.toString() : null);
        params.put("pageSize", pageSize != null ? pageSize.toString() : null);
        params.put("key", key);
        params.put("nonce", nonce);

        try {
            signature = getSignature(secret, params);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("startTime = " + getStringFromByDate(startTime));
        System.out.println("endTime = " + getStringFromByDate(endTime));
        System.out.println("contract_list_query signature = " + signature);

    }

    public static void contract_info() {

        contractSn = "HC20200109CJSADZ6253145543436676";

        Map<String, Object> params = new TreeMap<>();
        params.put("userId", userId);
        params.put("coinType", coinTypeStr);
        params.put("contractSn", contractSn);
        params.put("key", key);
        params.put("nonce", nonce);
        params.put("pageNum", pageNum != null ? pageNum.toString() : null);
        params.put("pageSize", pageSize != null ? pageSize.toString() : null);
        try {
            signature = getSignature(secret, params);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("contract_info signature = " + signature);

    }

    public static String getSignature(String secret, Map<String, Object> params) throws Exception {

        Set<String> keySet = params.keySet();
        StringBuffer encryptStr = new StringBuffer();

        for (String mapKey : keySet) {
            String value = (String) params.get(mapKey);
            System.out.println("#" + mapKey + " = " + value + "#");
            if (!("".equals(value) || value == null)) {
                encryptStr.append(value);
            }
        }

        return apiSignature(encryptStr.toString(), secret);

    }

    public static void contract_pay_list() {

        Map<String, Object> params = new TreeMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            startTime = sdf.parse("2019/01/01");
            endTime = sdf.parse("2020/10/01");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        params.put("userId", userId);
        params.put("coinType", coinTypeStr);
        params.put("pageNum", pageNum != null ? pageNum.toString() : null);
        params.put("pageSize", pageSize != null ? pageSize.toString() : null);
        params.put("startTime", getStringFromByDate(startTime));
        params.put("endTime", getStringFromByDate(endTime));
        params.put("key", key);
        params.put("nonce", nonce);

        try {
            signature = getSignature(secret, params);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("startTime = " + getStringFromByDate(startTime));
        System.out.println("endTime = " + getStringFromByDate(endTime));
        System.out.println("contract_pay_list signature = " + signature);
    }

    public static void contract_speed_list() {

        speedType = "day";
        // TODO Auto-generated method stub
        Map<String, Object> params = new TreeMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            startTime = sdf.parse("2019/01/01");
            endTime = sdf.parse("2020/10/01");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        params.put("userId", userId);
        params.put("coinType", coinTypeStr);
        params.put("contractType", contractType.toString());
        params.put("contractSn", contractSn);
        params.put("speedType", speedType);

        params.put("pageNum", pageNum != null ? pageNum.toString() : null);
        params.put("pageSize", pageSize != null ? pageSize.toString() : null);
        params.put("startTime", getStringFromByDate(startTime));
        params.put("endTime", getStringFromByDate(endTime));
        params.put("key", key);
        params.put("nonce", nonce);

        try {
            signature = getSignature(secret, params);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("startTime = " + getStringFromByDate(startTime));
        System.out.println("endTime = " + getStringFromByDate(endTime));
        System.out.println("contract_speed_list signature = " + signature);


    }

    public static void contract_user_status() {
        Map<String, Object> params = new TreeMap<>();
        params.put("userId", userId);
        params.put("coinType", coinTypeStr);
        params.put("key", key);
        params.put("nonce", nonce);

        try {
            signature = getSignature(secret, params);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("contract_user_status signature = " + signature);

    }

    public static void contract_user_hash_query() {

        Map<String, Object> params = new TreeMap<>();
        params.put("userId", userId);
        params.put("coinType", coinTypeStr);
        params.put("key", key);
        params.put("nonce", nonce);

        try {
            signature = getSignature(secret, params);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("contract_user_hash_query signature = " + signature);

    }

    public static void contract_recv_list() {

        Map<String, Object> params = new TreeMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            startTime = sdf.parse("2019/01/01");
            endTime = sdf.parse("2020/10/01");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        params.put("userId", userId);
        params.put("coinType", coinTypeStr);
        params.put("pageNum", pageNum != null ? pageNum.toString() : null);
        params.put("pageSize", pageSize != null ? pageSize.toString() : null);
        params.put("startTime", getStringFromByDate(startTime));
        params.put("endTime", getStringFromByDate(endTime));
        params.put("key", key);
        params.put("nonce", nonce);

        try {
            signature = getSignature(secret, params);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("startTime = " + getStringFromByDate(startTime));
        System.out.println("endTime = " + getStringFromByDate(endTime));
        System.out.println("contract_recv_list signature = " + signature);


    }

    public static void contract_transfer() {
        // TODO Auto-generated method stub
        Map<String, Object> params = new TreeMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            startTime = sdf.parse("2019/01/01");
            endTime = sdf.parse("2020/10/01");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        params.put("userId", userId);
        params.put("coinType", coinTypeStr);
        params.put("destUserId", destUserId);
        params.put("transferType", transferType);
        params.put("percent", percent);
        params.put("hashrete", hashrete);
        params.put("startTime", getStringFromByDate(startTime));
        params.put("endTime", getStringFromByDate(endTime));
        params.put("key", key);
        params.put("nonce", nonce);

        try {
            signature = getSignature(secret, params);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("startTime = " + getStringFromByDate(startTime));
        System.out.println("endTime = " + getStringFromByDate(endTime));
        System.out.println("contract_transfer signature = " + signature);


    }

    public static String apiSignature(String encryptStr, String secret) throws Exception {
        encryptStr = encryptStr == null ? "" : encryptStr.trim();
        secret = secret == null ? "" : secret.trim();

        Mac hmacSha256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        hmacSha256.init(secretKey);
        return Hex.encodeHexString(hmacSha256.doFinal(encryptStr.getBytes())).toUpperCase();
    }

    public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getStringFromByDate(Date date) {
        if (null == date) {
            return null;
        }
        return format.format(date);
    }

    public static String userId = "APTest";
    public static String coinTypeStr = "BTC";
    public static String key = "a65ee33632064d77bb939f9a39dba85a";
    public static String nonce = "123456";
    public static String secret = "441268b91c7548eba74870c4e12b9a6d";
    public static String contractSn = "HC20200109CJSADZ6253145543436676";

    public static String contractType = "0";
    public static String pageNum = null;
    public static String pageSize = null;
    public static Date startTime = null;
    public static Date endTime = null;
    public static String speedType = "day";
    public static String destUserId = "jini";
    public static String transferType = "0"; //转出合约类型（0：算力值；1：比例）
    public static String percent = "0.1"; //转出比例(小数0.1;0.2...)
    public static String hashrete = "10000000000000";//10T

    public static void main(String[] args) {
        System.out.println("########################查看合约列表签名");
        contract_list_query();
        System.out.println("########################合约详情签名");
        contract_info();
        System.out.println("########################查看支付列表");
        contract_pay_list();
        System.out.println("########################查看当前用户算力转让状态API");
        contract_user_status();
        System.out.println("########################查看实时算力、24小时算力");
        contract_user_hash_query();
        System.out.println("########################查看合约算力列表");
        contract_speed_list();
        System.out.println("########################查看收益列表");
        contract_recv_list();
        System.out.println("########################合约转出api");
        contract_transfer();
    }
}
