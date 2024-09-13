package com.ads_online.diploma.service.impl;

import com.ads_online.diploma.dto.CommentDto;
import com.ads_online.diploma.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class CommentServiceImpl implements CommentService {
    @Override
    public List<CommentDto> getComments(int adId) {
        return null;
    }

    @Override
    public CommentDto addComment(int adId, CommentDto commentDto) {
        return null;
    }
}
