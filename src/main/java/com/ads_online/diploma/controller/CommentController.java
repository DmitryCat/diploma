package com.ads_online.diploma.controller;

import com.ads_online.diploma.dto.CommentDto;
import com.ads_online.diploma.dto.CommentsDto;
import com.ads_online.diploma.dto.CreateOrUpdateCommentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ads/{adId}/comments")
public class CommentController {

    /**
     * Получение списка комментариев для объявления.
     *
     * Возвращает список всех комментариев, связанных с конкретным объявлением.
     *
     * @param adId Идентификатор объявления.
     * @return Список объектов CommentDto.
     */
    @GetMapping
    public ResponseEntity<CommentsDto> getComments(@PathVariable("adId") Integer adId) {
        CommentsDto commentsDto = new CommentsDto();
        return ResponseEntity.ok(commentsDto);
    }

    /**
     * Добавление нового комментария к объявлению.
     *
     * Добавляет комментарий к объявлению по его ID.
     *
     * @param adId Идентификатор объявления.
     * @param createOrUpdateCommentDto Объект с данными для создания комментария.
     * @return Объект CommentDto с информацией о добавленном комментарии.
     */
    @PostMapping
    public ResponseEntity<CommentDto> addComment(@PathVariable("adId") Integer adId, @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        CommentDto commentDto = new CommentDto();
        return ResponseEntity.ok(commentDto);
    }

    /**
     * Удаление комментария по его ID.
     *
     * Удаляет комментарий к объявлению по его ID и ID комментария.
     *
     * @param adId Идентификатор объявления.
     * @param commentId Идентификатор комментария.
     * @return Ответ с кодом 200 (OK) в случае успешного удаления.
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("adId") Integer adId, @PathVariable("commentId") Integer commentId) {
        return ResponseEntity.ok().build();
    }

    /**
     * Обновление комментария по его ID.
     *
     * Обновляет комментарий к объявлению по его ID и ID комментария.
     *
     * @param adId Идентификатор объявления.
     * @param commentId Идентификатор комментария.
     * @param createOrUpdateCommentDto Объект с обновленными данными комментария.
     * @return Обновленный объект CommentDto с новой информацией.
     */
    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable("adId") Integer adId,
            @PathVariable("commentId") Integer commentId,
            @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        CommentDto commentDto = new CommentDto();
        return ResponseEntity.ok(commentDto);
    }
}