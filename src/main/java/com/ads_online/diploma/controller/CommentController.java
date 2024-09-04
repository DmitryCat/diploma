package com.ads_online.diploma.controller;

import com.ads_online.diploma.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ads/{adId}/comments")
public class CommentController {

    @GetMapping
    public ResponseEntity<CommentsDto> getComments(@PathVariable Integer adId) {
        // Заглушка
        return ResponseEntity.ok(new CommentsDto());
    }

    @PostMapping
    public ResponseEntity<CommentDto> addComment(@PathVariable Integer adId, @RequestBody CreateOrUpdateCommentDto commentDto) {
        // Заглушка
        return ResponseEntity.ok(new CommentDto());
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId, @PathVariable Integer commentId) {
        // Заглушка
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer adId, @PathVariable Integer commentId, @RequestBody CreateOrUpdateCommentDto commentDto) {
        // Заглушка
        return ResponseEntity.ok(new CommentDto());
    }
}