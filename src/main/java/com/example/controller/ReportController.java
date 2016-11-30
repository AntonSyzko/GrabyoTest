package com.example.controller;


import com.example.model.TwitterResult;
import com.example.service.StorageService;
import com.example.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Admin on 22.11.2016.
 */
@Controller
public class ReportController {
    static AtomicBoolean flag = new AtomicBoolean(true);
    @Autowired
  static StorageService storageService = new StorageService();
    @Autowired
    static TwitterService twitterService = new TwitterService();

    @Value("${spring.data.mongodb.database}")
  static   String name;

    public static void main(String[] args) {
        String hash = "#";
        String keyWord = "jazz";
        ReportController report = new ReportController();
        report.accessAndStore(hash,keyWord);
        report.deliverReport(hash+keyWord);
        List<TwitterResult> fromDB = storageService.extractFromStorage();
        for(TwitterResult each : fromDB){
            System.out.println(each.toString());
        }

        System.out.println(fromDB.size());

        System.out.println(name);

    }

    /*
    method for timed calls on backen side - every minute
     */
    public void accessAndStore(String hash, String keyWord){
        twitterService.accesTwitterAndStoreToDB(hash,keyWord);
//        Thread t = new Thread() {
//            @Override
//            public void run() {
//                while(flag.get()) {
//                    try {
//                      //  Thread.sleep(1000*60*60);
//                       Thread.sleep(10000);
//
//                        twitterService.accesTwitterAndStoreToDB(hash,keyWord);
//
//
//                    } catch (InterruptedException ie) {
//                    }
//                }
//            }
//        };
//        t.start();
    }

    public void deliverReport(String filter){
        storageService.deliverReportFilteredByHashtag(storageService.extractFromStorage(), filter);
    }



}
