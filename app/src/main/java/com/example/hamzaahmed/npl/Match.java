package com.example.hamzaahmed.npl;

import java.util.UUID;

/**
 * Created by Hamza Ahmed on 15-Jul-17.
 */

public class Match {

    private Integer Image1Id;
    private Integer Image2Id;
    private String matchDate;
    private UUID Id;
    private String matchResult;
    private String matchNo;
    private String venue;

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public Integer getImage1Id() {
        return Image1Id;
    }

    public void setImage1Id(Integer image1Id) {
        Image1Id = image1Id;
    }

    public Integer getImage2Id() {
        return Image2Id;
    }

    public void setImage2Id(Integer image2Id) {
        Image2Id = image2Id;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(String matchResult) {
        this.matchResult = matchResult;
    }

    public String getMatchNo() {
        return matchNo;
    }

    public void setMatchNo(String matchNo) {
        this.matchNo = matchNo;
    }
}
