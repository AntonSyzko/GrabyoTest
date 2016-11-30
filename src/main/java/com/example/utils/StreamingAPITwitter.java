package com.example.utils;

import ch.qos.logback.core.status.*;
import twitter4j.*;
import twitter4j.Status;
import twitter4j.StatusListener;
import twitter4j.conf.ConfigurationBuilder;


import java.util.List;

/**
 * Created by Admin on 23.11.2016.
 */
public class StreamingAPITwitter {
    private static final String TWITTER_CONSUMER_KEY = "teLe4sJsmVO6sWhWdAE2O2poI";
    private static final String TWITTER_SECRET_KEY = "5erE9geWlVyQgwPSu3YL1BcCVugs8ndGagnbE3YqgIEPyryL1W";
    private static final String TWITTER_ACCESS_TOKEN = "788799710236639232-NKYD3eWa5AVVHYpKp2au03dd6GeZZSv";
    private static final String TWITTER_ACCESS_TOKEN_SECRET = "Elke2O0EFJYNBq3FeoDQyP0xer7OAFDtDO7cZphAdiF5c";

    public static void main(String[] args) {
       // GetTweetStreamForKeywords();
        limits();
    }

    private static void GetTweetStreamForKeywords()
    {
        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
                .setOAuthConsumerSecret(TWITTER_SECRET_KEY)
                .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET);


        //  Logger logWriter = new
        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();

        StatusListener statusListener = new StatusListener() {
            @Override
            public void onStatus(Status status) {

                // The main section that you get the tweet. You can access it by status object.
                // You can save it in a database table.
                System.out.println( status.getUser().getScreenName());


            }


            @Override
            public void onDeletionNotice(StatusDeletionNotice sdn) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void onTrackLimitationNotice(int i) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void onScrubGeo(long l, long l1) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void onStallWarning(StallWarning sw) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void onException(Exception ex) {
                //  logWriter.WriteErrorLog(ex, "onException()");
            }


        };

        FilterQuery fq = new FilterQuery();

        String keywords[] = {"#sport"};

        fq.track(keywords);

        twitterStream.addListener(statusListener);
        twitterStream.filter(fq);

    }

    public static void limits(){
        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
                .setOAuthConsumerSecret(TWITTER_SECRET_KEY)
                .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET);

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        Query query = new Query("#jazz");
        query.count(Integer.MAX_VALUE); //100 is the max allowed
        try {
            QueryResult result = twitter.search(query);
            List<Status> tweets = result.getTweets();
      long tweetCounter = 0L;
            for (Status tweet : tweets) {
                System.out.println(" TWEET : " + tweet.getUser().getScreenName() + " has  posted " + tweet.getText());
                tweetCounter++;
            }

            System.out.println(" ****************************   TOATAL TWEETS  ***************************" + tweetCounter);

            } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
