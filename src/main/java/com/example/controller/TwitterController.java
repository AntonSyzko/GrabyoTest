package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Admin on 28.11.2016.
 */
@Controller
@RequestMapping("/")
@EnableWebSecurity
public class TwitterController {

    private static final String TWITTER_CONSUMER_KEY = "teLe4sJsmVO6sWhWdAE2O2poI";
    private static final String TWITTER_SECRET_KEY = "5erE9geWlVyQgwPSu3YL1BcCVugs8ndGagnbE3YqgIEPyryL1W";
    private static final String TWITTER_ACCESS_TOKEN = "788799710236639232-NKYD3eWa5AVVHYpKp2au03dd6GeZZSv";
    private static final String TWITTER_ACCESS_TOKEN_SECRET = "Elke2O0EFJYNBq3FeoDQyP0xer7OAFDtDO7cZphAdiF5c";

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    @RequestMapping(value="/",method= RequestMethod.GET)
    public String liveTwo(Model model) {
       return "livetwo";
    }

    //produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    @RequestMapping(value = "/london", method = RequestMethod.GET)
    @ResponseBody
    public  String   accesTwitter(){


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
            Query query = new Query("#london");
            QueryResult result;

            do {result = twitter.search(query);

                //List<Status> tweets = result.getTweets();
                tweets = result.getTweets();

                for (Status tweet : tweets) {
                    resCount ++;
                    System.out.println(" count "+ resCount+ " TWEET : "+ tweet.getUser().getScreenName() + " : " + tweet.getText());
                    //   allTheTweets.add(tweet);
                }

            } while ((query = result.nextQuery()) != null);
            //   System.out.println(tweets.size());
            System.out.println(allTheTweets.size());

        } catch(TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
        String res = String.valueOf(resCount);
        return res;
    }

    @RequestMapping(value = "/sparklinetest", method = RequestMethod.GET)
    @ResponseBody
    public  String constructJSONArray() throws org.json.JSONException {

        org.json.JSONObject one = new org.json.JSONObject();
        org.json.JSONObject two = new org.json.JSONObject();
        org.json.JSONObject three = new org.json.JSONObject();

        org.json.JSONArray result = new org.json.JSONArray();
        Random r = new Random();
        int[] r1 = { r.nextInt(10), r.nextInt(10), r.nextInt(10), r.nextInt(10), r.nextInt(10), r.nextInt(10), r.nextInt(10), r.nextInt(10) ,r.nextInt(1000)};

        one.put("one", r1);

        result.put(one);

        org.json.JSONObject jsonObj = new org.json.JSONObject();
        jsonObj.put("sparkData", result);
        System.out.println("Sendig this data to view (sparkline.jsp): " + jsonObj.toString());

        return jsonObj.toString();
    }

    @ModelAttribute("liveRandom")
    public Integer randomDataForLiveChart(){
        //Integer random = ThreadLocalRandom.current().nextInt(1,100);
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(70);


        System.out.println(" random was attr " + randomInt);

        return randomInt;
    }

    //,produces = "application/json"
    @RequestMapping(value = "/liveRandomTW", method = RequestMethod.GET)
    @ResponseBody
    public String randomDataForLiveChart2(){
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(70);


        String res = String.valueOf(randomInt);
        System.out.println(" random was REST" + res);

        return res;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({Exception.class})
    public ModelAndView getGeoIpTrackingUnavailable(Exception ex) {
        return new ModelAndView("error", "error", ex.getMessage());
    }





}
