package com.bitmain.demo;

import com.alibaba.fastjson.JSON;
import java.lang.reflect.Constructor;
import org.apache.commons.codec.binary.Hex;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.alibaba.fastjson.JSONArray;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.time.ZoneOffset.UTC;

@SpringBootApplication
public class SignatureApplication {

    static {
        System.out.println("start");
    }

    public static String main2(String[] args) {

        try {
            String a = "      ";
            String b = "  ";
            DateTime dateTime = new DateTime(DateTimeZone.UTC);
            System.out.println(dateTime);
            DateTime lastHour = DateTime.now().withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).toDateTime(DateTimeZone.UTC);
            System.out.println(lastHour);
            System.out.println(lastHour.getMillis());
            java.util.Calendar cal = java.util.Calendar.getInstance();
//2、取得时间偏移量：
            int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
//3、取得夏令时差：
            int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
//4、从本地时间里扣除这些差量，即可以取得UTC时间：
            cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
            System.out.println(cal.getTimeInMillis());

            BigDecimal zero = new BigDecimal(0.00);
            System.out.println(zero.doubleValue());
            System.out.println(Calendar.getInstance(TimeZone.getTimeZone(UTC)));
            System.out.println(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis());
            System.out.println(apiSignature("antester", "e40098565a9c4baea7130caee59d94b2", "1566203668", "952a30a06864482fb7e3867e7bafba53"));
            String af = "dfsdf";
            int tt = 1;
            int i = 2;
            System.out.println(af + tt + i);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        SpringApplication.run(SignatureApplication.class, args);
        return "";

    }

    public static String userId = "APTest";
    public static String coinTypeStr = "BTC";
    public static String key = "a65ee33632064d77bb939f9a39dba85a";
    public static String nonce = "123456";
    public static String secret = "441268b91c7548eba74870c4e12b9a6d";
    public static String contractSn = "HC20200109CJSADZ6253145543436676";
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String contractType = "0";
    public static String pageNum = null;
    public static String pageSize = null;

    public static Date startTime;
    public static Date endTime ;
    static {
        try {
            startTime = sdf.parse( "2019-01-01 00:00:00");
            endTime =sdf.parse("2021-01-01 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public static String speedType = "day";
    public static String destUserId = "jini";
    public static String transferType = "0"; //转出合约类型（0：算力值；1：比例）
    public static String percent = "0.1"; //转出比例(小数0.1;0.2...)
    public static String hashrete = "10000000000000";//10T


    public static String signature = "";


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

    public static void main333(String[] args) {
        Date now = new Date();
        System.out.println(now.toString());
        Long halfHour = 30 * 60 * 1000L;
        Date present = new Date(new Date().getTime() - halfHour);
        System.out.println(now.getTime());
        System.out.println(present.getTime());

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

//	    params.put("pageNum", pageNum != null?pageNum.toString():null);
//	    params.put("pageSize", pageSize != null?pageSize.toString():null);
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
//	    params.put("pageNum", pageNum != null?pageNum.toString():null);
//	    params.put("pageSize", pageSize != null?pageSize.toString():null);
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


    public static void contract_pay_list() {

        Map<String, Object> params = new TreeMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            startTime = sdf.parse("2019/01/01");
            endTime = sdf.parse("2021/10/01");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        params.put("userId", userId);
        params.put("coinType", coinTypeStr);
//	    params.put("pageNum", pageNum != null?pageNum.toString():null);
//	    params.put("pageSize", pageSize != null?pageSize.toString():null);
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

    public static void contract_info() {

        contractSn = "HC20200109CJSADZ6253145543436676";

        Map<String, Object> params = new TreeMap<>();
        params.put("userId", userId);
        params.put("coinType", coinTypeStr);
        params.put("contractSn", contractSn);
//	    params.put("pageNum", pageNum != null?pageNum.toString():null);
//	    params.put("pageSize", pageSize != null?pageSize.toString():null);
        params.put("key", key);
        params.put("nonce", nonce);

        try {
            signature = getSignature(secret, params);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("contract_info signature = " + signature);

    }

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
//	    params.put("pageNum", pageNum != null?pageNum.toString():null);
//	    params.put("pageSize", pageSize != null?pageSize.toString():null);
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
        System.out.println("contract_list_query signature = " + signature);

    }


    public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getStringFromByDate(Date date) {
        if (null == date) {
            return null;
        }
        return format.format(date);
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

    public static String apiSignature(String encryptStr, String secret) throws Exception {
        encryptStr = encryptStr == null ? "" : encryptStr.trim();
        secret = secret == null ? "" : secret.trim();

        Mac hmacSha256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        hmacSha256.init(secretKey);
        return Hex.encodeHexString(hmacSha256.doFinal(encryptStr.getBytes())).toUpperCase();
    }


    public static String main1(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>();

        hashMap.put("Pen", 10);
        hashMap.put("Book", 500);
        hashMap.put("Clothes", 400);
        hashMap.put("Mobile", 5000);
        if (hashMap.containsKey("Book")) {
            Integer value = hashMap.get("Book");
            hashMap.put("Book", value + 100);
        }
        System.out.println("hashTable: "
            + hashMap.toString());
        hashMap.computeIfPresent("Pen", (key, value) -> (value + getValue()));
        System.out.println("hashTable: "
            + hashMap.toString());
        return "";
    }

    public static String apiSignature(String userId, String key, String nonce, String secret) throws Exception {
        userId = userId==null?"":userId.trim();
        key = key==null?"":key.trim();
        nonce = nonce==null?"":nonce.trim();
        secret = secret==null?"":secret.trim();

        String data = userId+key+nonce;
        Mac hmacSha256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        hmacSha256.init(secretKey);
        return Hex.encodeHexString(hmacSha256.doFinal(data.getBytes())).toUpperCase();
    }

    public static void main454(String args[]) {

        DateTime now = new DateTime();
        DateTime dt3 = new DateTime(2019, 9, 16, 12, 00, 00, 000);
        DateTime target = new DateTime(2019, 9, 19, 10, 05, 00, 000);
        DateTime target2 = target.withZone(DateTimeZone.UTC);
        System.out.printf(target.toString());
        Class ccc = target.getClass();
        Constructor[] constructors = ccc.getConstructors();
        for (Constructor constructor : constructors){
            System.out.println(constructor);
        }
        String[] test = new String[10];
        test[0] = "23r";
        test[1] = "sdfs";
        System.out.println("         ``");
        System.out.println(test.length);
        // DateTime target = new DateTime("2019,09,16,0,0,0");
        if (!now.isBefore(dt3.getMillis())) {
            System.out.println("没几22");

        }
        System.out.println("没几");
        try {
            System.out.println(apiSignature("APTest", "a65ee33632064d77bb939f9a39dba85a", "123456", "441268b91c7548eba74870c4e12b9a6d"));
            System.out.println(UserIdapiSignature("19XqbDjmEcK7LKnzLmkDaa7RMu9JfNo4zL","cce86e21489c41809e6e88505bfbbb88", "1608865206", "93717c9360464fc1a44d3a1ebb5f8409"));
            System.out.println(1/0);
        } catch (Exception e) {
            System.out.println("33333333333333343434234234234324");
        }
        System.out.println( "___________________________________________");
        String coin = "BTC";
        String opId = "ctbtcpool";
        String params = "{\"method\":\"get\",\"email\":\"aptest006@foxmail.com\",\"userId\":\"antester1\",\"permissions\":[\"poolStats\"]}";
        String data = coin + opId + params;
        String apiSecret = "123456";
        Mac hmacSha256 = null;
        try {
            hmacSha256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(apiSecret.getBytes(), "HmacSHA256");
            hmacSha256.init(secretKey);
        } catch (Exception e) {

        }

        String signTmp = Hex.encodeHexString(hmacSha256.doFinal(data.getBytes())).toUpperCase();
        System.out.println(signTmp);

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(list));
        System.out.println(String.valueOf(array));
    }

    private static String UserIdapiSignature(String userId, String key, String nonce, String secret) throws Exception {
        String data = userId + key + nonce;
        Mac hmacSha256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        hmacSha256.init(secretKey);
        return Hex.encodeHexString(hmacSha256.doFinal(data.getBytes())).toUpperCase();
    }

    public static String getUTCTimeStr() throws Exception {
        Calendar cal = Calendar.getInstance();
        return String.valueOf(cal.getTimeInMillis());// 返回的就是UTC时间
    }

    public static int getValue() {
        return 200;
    }

    public void testt() {
        for (int i = 0; i < 1000 / 1000 + 1; i++) {
        }
    }
}
