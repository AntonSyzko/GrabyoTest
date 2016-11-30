package com.example.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Admin on 22.11.2016.
 */
@Document(collection = "twitter_hour_stats")
public class TwitterResult implements Comparable<TwitterResult> {
    @Id
    int id;

    @Indexed
    String searchedResult;

    public TwitterResult(int id, String searchedResult) {
        this.id = id;
        this.searchedResult = searchedResult;
    }

    public int getId() {
        return id;
    }

    public String getSearchedResult() {
        return searchedResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TwitterResult that = (TwitterResult) o;

        if (getId() != that.getId()) return false;
        return getSearchedResult() != null ? getSearchedResult().equals(that.getSearchedResult()) : that.getSearchedResult() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getSearchedResult() != null ? getSearchedResult().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TwitterResult{");
        sb.append("id=").append(id);
        sb.append(", searchedResult='").append(searchedResult).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(TwitterResult o) {
        return this.id - o.id;
    }
}
