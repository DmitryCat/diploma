package com.ads_online.diploma.dto;

import java.util.List;

public class CommentsDto {
    private Integer count;
    private List<CommentDto> results;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<CommentDto> getResults() {
        return results;
    }

    public void setResults(List<CommentDto> results) {
        this.results = results;
    }
}