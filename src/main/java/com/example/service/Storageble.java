package com.example.service;


import com.example.model.TwitterResult;

import java.util.List;

/**
 * Created by Admin on 22.11.2016.
 */
public interface Storageble {
      void   storeTweets(TwitterResult res);
     List<TwitterResult> extractFromStorage();
      List<TwitterResult> deliverReportFilteredByHashtag(List<TwitterResult> extractFromStorage, String hashfilter);
      List<TwitterResult> deliverReportFilteredByNameTag(List<TwitterResult> extractFromStorage, String hashfilter);
     List<TwitterResult> deliverReportFilteredByWord(List<TwitterResult> extractFromStorage);
    }
