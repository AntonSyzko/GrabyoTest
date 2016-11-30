package com.example.service;

import com.example.config.SpringMongoConfig;
import com.example.model.TwitterResult;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 22.11.2016.
 */
@Service
public class StorageService  implements Storageble{

    ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
     MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
  //  List<TwitterResult> hastaged = null;
 //   List<TwitterResult> nameContained = null;
  //  List<TwitterResult> justText = null;


    public void storeTweets(TwitterResult res){
           mongoOperation.save(res,"twitter_hour_stats");

    }
    public List<TwitterResult> extractFromStorage(){
        List<TwitterResult> results = mongoOperation.findAll(TwitterResult.class,"twitter_hour_stats");
        return results;

    }

    public  List<TwitterResult> deliverReportFilteredByHashtag(List<TwitterResult> extractFromStorage, String hashfilter) {

        List<TwitterResult> hastaged = new ArrayList<TwitterResult>();
        for (TwitterResult each : extractFromStorage) {
            if ((each.getSearchedResult().startsWith(hashfilter))) {
                hastaged.add(each);

            }
        }
        return hastaged;

    }


        public  List<TwitterResult> deliverReportFilteredByNameTag(List<TwitterResult> extractFromStorage, String hashfilter){
            List<TwitterResult> nameContained = new ArrayList<TwitterResult>();
            for (TwitterResult each : extractFromStorage) {
                if ((each.getSearchedResult().startsWith(hashfilter))) {
                    nameContained.add(each);
                }
            }
            return nameContained;

        }


    public List<TwitterResult> deliverReportFilteredByWord(List<TwitterResult> extractFromStorage){
        List<TwitterResult> justText = new ArrayList<TwitterResult>();
        for(TwitterResult each : extractFromStorage){
            if((!each.getSearchedResult().startsWith("#")|| !each.getSearchedResult().startsWith("@"))){
                justText.add(each);

            }
        }
        return justText;
    }
}
