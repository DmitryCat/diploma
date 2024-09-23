package com.ads_online.diploma.service.imlp;

import com.ads_online.diploma.dto.CommentDTO;
import com.ads_online.diploma.dto.CommentsDTO;
import com.ads_online.diploma.dto.CreateOrUpdateCommentDTO;
import com.ads_online.diploma.entity.Ad;
import com.ads_online.diploma.entity.Comment;
import com.ads_online.diploma.entity.User;
import com.ads_online.diploma.exeptions.EntityNotFoundException;
import com.ads_online.diploma.exeptions.ForbiddenException;
import com.ads_online.diploma.mapper.CommentMapper;
import com.ads_online.diploma.repository.AdRepository;
import com.ads_online.diploma.repository.CommentRepository;
import com.ads_online.diploma.repository.UserRepository;
import com.ads_online.diploma.service.CommentService;
import com.ads_online.diploma.utils.CheckAdmin;
import com.ads_online.diploma.utils.CheckAuthentication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CheckAuthentication checkAuthentication;
    private final CheckAdmin checkAdmin;

    public CommentsDTO getComments(Long id, Authentication authentication) {

        checkAuthentication.checkAuthentication(authentication);

        if (adRepository.existsById(id)) {

            List<CommentDTO> commentDTOList = new ArrayList<>();
            for (Comment comment : commentRepository.findCommentsByIdAd(id)) {
                commentDTOList.add(CommentMapper.INSTANCE.commentToCommentDTO(comment, comment.getUser()));
            }

            CommentsDTO commentsDTO = new CommentsDTO();
            commentsDTO.setCount(commentRepository.findCommentsByIdAd(id).size());
            commentsDTO.setResults(commentDTOList);

            return commentsDTO;
        } else {
            log.error("Ad not found");
            throw new EntityNotFoundException("Ad not found");
        }
    }

    public CommentDTO createComment(Long adId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO, Authentication authentication) {

        checkAuthentication.checkAuthentication(authentication);

        if (adRepository.existsById(adId)) {

            LocalDateTime time = LocalDateTime.now();
            User user = userRepository.findByEmail(authentication.getName());
            Ad ad = adRepository.findById(adId).orElse(null);

            Comment comment = CommentMapper.INSTANCE.createOrUpdateCommentDTOToComment(createOrUpdateCommentDTO);

            comment.setCreatedAt(time);
            comment.setUser(user);
            comment.setAd(ad);
            commentRepository.save(comment);
            log.info("Comment created: {}", comment);

            if (user.getImage() == null) {
                log.warn("User {} does not have an image", user.getEmail());
            }

            return CommentMapper.INSTANCE.commentToCommentDTO(comment, user);
        } else {
            log.error("Ad not found");
            throw new EntityNotFoundException("Ad not found");
        }
    }

    public void removalComment(Long adId, Long commentId, Authentication authentication) {

        checkAuthentication.checkAuthentication(authentication);

        if (!adRepository.existsById(adId)) {
            log.error("Ad not found");
            throw new EntityNotFoundException("Ad not found");
        }

        Comment comment = getComment(commentId);

        if (comment.getUser().getEmail().equals(authentication.getName()) || checkAdmin.isAdmin(authentication.getName())) {

            commentRepository.delete(comment);
            log.info("Comment deleted: {}", comment);

        } else {
            throw new ForbiddenException("No authority");
        }
    }

    public CommentDTO editComment(Long adId, Long commentId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO,
                                  Authentication authentication) {

        checkAuthentication.checkAuthentication(authentication);

        if (!adRepository.existsById(adId)) {
            log.error("Ad not found");
            throw new EntityNotFoundException("Ad not found");
        }

        Comment comment = getComment(commentId);

        if (comment.getUser().getEmail().equals(authentication.getName()) || checkAdmin.isAdmin(authentication.getName())) {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.findByEmail(auth.getName());
            comment.setText(createOrUpdateCommentDTO.getText());
            commentRepository.save(comment);
            log.info("Comment edited: {}", comment);
            return CommentMapper.INSTANCE.commentToCommentDTO(comment, user);

        } else {
            throw new ForbiddenException("No authority");
        }
    }

    public Comment getComment(Long pk) {
        return commentRepository.findById(pk).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteAll() {
        commentRepository.deleteAll();
    }
}