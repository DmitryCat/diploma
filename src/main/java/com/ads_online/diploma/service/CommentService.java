package com.ads_online.diploma.service;

import com.ads_online.diploma.dto.CommentDTO;
import com.ads_online.diploma.dto.CreateOrUpdateCommentDTO;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

public interface CommentService {

    Comments getComments(Long id, Authentication authentication);

    CommentDTO createComment(Long adId, CreateOrUpdateCommentDTO comment, Authentication authentication);

    void removalComment(Long adId, Long commentId, Authentication authentication);

    CommentDTO editComment(Long adId, Long commentId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO, Authentication authentication);

    void deleteAll();
    CommentDTO getComment(Long pk);
}
