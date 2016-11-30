package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 24.11.2016.
 */
@Controller
public class TweetsPerSecondsController {

    private static final String TWITTER_CONSUMER_KEY = "teLe4sJsmVO6sWhWdAE2O2poI";
    private static final String TWITTER_SECRET_KEY = "5erE9geWlVyQgwPSu3YL1BcCVugs8ndGagnbE3YqgIEPyryL1W";
    private static final String TWITTER_ACCESS_TOKEN = "788799710236639232-NKYD3eWa5AVVHYpKp2au03dd6GeZZSv";
    private static final String TWITTER_ACCESS_TOKEN_SECRET = "Elke2O0EFJYNBq3FeoDQyP0xer7OAFDtDO7cZphAdiF5c";

    @RequestMapping(value = "/tweetspercall", method = org.springframework.web.bind.annotation.RequestMethod.GET)
    @ResponseBody
    public  Integer getNumberOfTweetsPerCall(){

        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
                .setOAuthConsumerSecret(TWITTER_SECRET_KEY)
                .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET);
        //cb.setOAuth2AccessToken()

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        List<String> hashtagsResults = new ArrayList<String>();
        List<Status> allTheTweets = new ArrayList<Status>();

        int resCount = 0;
        List<Status> tweets = null;
        try {
            Query query = new Query("#smalls");
            QueryResult result;

            do {
                result = twitter.search(query);

                //List<Status> tweets = result.getTweets();
                tweets = result.getTweets();
                for (Status tweet : tweets) {
                    resCount ++;
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
        return allTheTweets.size();
    }
}
