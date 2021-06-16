package com.bitmain.demo;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

@Slf4j
public class TestJob {
    public static void main(String args[]) throws InterruptedException {
        long total = 0;
//        for (int i = 500; i >= 0; i--) {
//
//            Date currentTime = new Date();
//            long startTime = currentTime.getTime();
//            log.info("开始执行任务：startTime:{}", startTime);
//            Random r = new Random();
//            long ran1 = r.nextInt(3000);
//            Thread.sleep(ran1);
//            currentTime = new Date();
//            long endTime = currentTime.getTime();
//            total += endTime - startTime;
//            log.info("执行结束：endTime:{}", endTime);
//
//            System.out.println("执行时间为：" + (endTime - startTime) + "ms");
//        }
//        log.info("执行成功次数：489");
//        log.info("执行失败次数：11");
//        log.info("总耗时:{}ms",total);
//        log.info("平均耗时:{}ms",total/500);
        String testString  = "中文字符串牛逼";
        System.out.println(testString.length());
        BigDecimal bigDecimal = new BigDecimal("10000000000000000");
        BigDecimal time = new BigDecimal("86400");
        BigDecimal bd2_32 = new BigDecimal(4294967296L);
        BigDecimal share = bigDecimal.multiply(time).divide(bd2_32).setScale(2,BigDecimal.ROUND_HALF_UP);
        System.out.println(share);
    }


}
