package com.bitmain.demo;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

@lombok.Data
@Slf4j
public class ddfd {
	BigDecimal aa;
	Date fff;


    public synchronized void log1(String msg1, String msg2){
        log.info(msg1);
        log.info(msg2);
    }

    public void log2(String msg1, String msg2){
        synchronized(this){
            log.info(msg1);
            log.info(msg2);
        }
    }

    public static  void main(String[] args){

    }
}
