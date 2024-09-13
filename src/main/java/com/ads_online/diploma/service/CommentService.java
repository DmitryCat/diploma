package com.ads_online.diploma.service;

import com.ads_online.diploma.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getComments(int adId);

    CommentDto addComment(int adId, CommentDto commentDto);
}
