package com.example.service;

import com.example.model.TwitterResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 22.11.2016.
 */
public class TwitterService implements TwitterableToDb {

    @Autowired
    static StorageService storageService = new StorageService();

    private static final String TWITTER_CONSUMER_KEY = "teLe4sJsmVO6sWhWdAE2O2poI";
    private static final String TWITTER_SECRET_KEY = "5erE9geWlVyQgwPSu3YL1BcCVugs8ndGagnbE3YqgIEPyryL1W";
    private static final String TWITTER_ACCESS_TOKEN = "788799710236639232-NKYD3eWa5AVVHYpKp2au03dd6GeZZSv";
    private static final String TWITTER_ACCESS_TOKEN_SECRET = "Elke2O0EFJYNBq3FeoDQyP0xer7OAFDtDO7cZphAdiF5c";
    static final String hashSymbol = "#";


    public void accesTwitterAndStoreToDB(String hash, String key) {

        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
                .setOAuthConsumerSecret(TWITTER_SECRET_KEY)
                .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET);

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        List<String> hashtagsResults = new ArrayList<String>();
        List<String> userNames = new ArrayList<String>();


        try {
            Query query = new Query(hash+key);
            QueryResult result;

            do {
                result = twitter.search(query);

                List<Status> tweets = result.getTweets();

                int idGenerateor = 0;

                for (Status tweet : tweets) {
                    TwitterResult currentResult = new TwitterResult(idGenerateor++, tweet.getText());
                    storageService.storeTweets(currentResult);

                }
            } while ((query = result.nextQuery()) != null);
            //  System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
    }
}
