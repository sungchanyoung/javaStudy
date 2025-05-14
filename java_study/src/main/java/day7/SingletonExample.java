package day7;

import day7.데이터베이스관리자.DatabaseConnectionManager;
import day7.설정관리자.ConfigurationManager;
import day7.스레드풀메니저.ThreadPoolManager;
import day7.캐쉬매니저.CacheManager;

import java.lang.module.Configuration;
import java.sql.SQLException;

public class SingletonExample {
    public static void main(String[] args) {
//
        ThreadPoolManager threadPoolManager = ThreadPoolManager.getInstance();
        threadPoolManager.execute(() -> {
            System.out.println("cpu 집악적 작업 수행 - " + Thread.currentThread().getName());
        }, true);

        threadPoolManager.execute(() -> {
            System.out.println("io 집악적 작업 수행 - " + Thread.currentThread().getName());
        }, false);

        threadPoolManager.scheduleAtFixedRate(() -> {
            System.out.println("예약된 작업 수행 - " + Thread.currentThread().getName());
        },100,100);
        System.out.println("동일 객체 여부 "+ (threadPoolManager == ThreadPoolManager.getInstance()));
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
