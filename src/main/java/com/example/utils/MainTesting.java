package com.example.utils;

/**
 * Created by Admin on 22.11.2016.
 */


import com.example.service.StorageService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainTesting {

    @Autowired
 static StorageService storageService = new StorageService();

    private static final String TWITTER_CONSUMER_KEY = "teLe4sJsmVO6sWhWdAE2O2poI";
    private static final String TWITTER_SECRET_KEY = "5erE9geWlVyQgwPSu3YL1BcCVugs8ndGagnbE3YqgIEPyryL1W";
    private static final String TWITTER_ACCESS_TOKEN = "788799710236639232-NKYD3eWa5AVVHYpKp2au03dd6GeZZSv";
    private static final String TWITTER_ACCESS_TOKEN_SECRET = "Elke2O0EFJYNBq3FeoDQyP0xer7OAFDtDO7cZphAdiF5c";
    static AtomicBoolean flag = new AtomicBoolean(true);


    public static void main(String[] args) {
      //  accesTwitter("#jazz");



        Thread t = new Thread() {
            @Override
            public void run() {
                while(flag.get()) {
                    try {
                        Thread.sleep(1000*10);
                        accesTwitter("#jazz");
                        System.out.println(" ****************************************************************************************************** ");

                    } catch (InterruptedException ie) {
                    }
                }
            }
        };
        t.start();

    }
    public void testSearch(){
        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
                .setOAuthConsumerSecret(TWITTER_SECRET_KEY)
                .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET);

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
    }
    @Test
    public  void mainTest() {
       accesTwitter("#jazz");



    }

    public  static void  accesTwitter(String searchKey){


        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
                .setOAuthConsumerSecret(TWITTER_SECRET_KEY)
                .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET);

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        List<String> hashtagsResults = new ArrayList<String>();
        List<Status> allTheTweets = new ArrayList<Status>();

        int resCount = 0;
        List<Status> tweets = null;
        try {
            Query query = new Query(searchKey);
            QueryResult result;

            do {
                result = twitter.search(query);

                //List<Status> tweets = result.getTweets();
               tweets = result.getTweets();


                for (Status tweet : tweets) {
resCount ++;


//                   if( tweet.getUser().getName().contains("@jazz")){
//                       userNames.add(tweet.getUser().getName());
//                 }
//
//                   if(tweet.getUser().getScreenName().contains("jazz")){
//                       System.out.println(tweet.getText());
//                   }
//                    if(tweet.getText().startsWith("#")){
//                        hashtagsResults.add(tweet.getText());
//                    }
//                    if(tweet.getText().startsWith("@")){
//                        userNames.add(tweet.getUser().getName());
//                    }

                    System.out.println(" count "+ resCount+ " TWEET : "+ tweet.getUser().getScreenName() + " : " + tweet.getText());
                  //  System.out.println();
                    allTheTweets.add(tweet);

                }

            } while ((query = result.nextQuery()) != null);
         //   System.out.println(tweets.size());
            System.out.println(allTheTweets.size());

        } catch(TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }

        System.out.println(" hashed results ");

        for(String each : hashtagsResults){
            System.out.println(each);
        }

        System.out.println(" usernames ");

        for(String each : hashtagsResults){
            System.out.println(each);
        }
    }

     }