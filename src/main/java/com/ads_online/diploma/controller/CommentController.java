package com.ads_online.diploma.controller;

import com.ads_online.diploma.dto.CommentDto;
import com.ads_online.diploma.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ads/{adId}/comments")
@Tag(name = "Comments", description = "Управление комментариями к объявлениям")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
public class CommentController {

    private final CommentService commentService;


    @Operation(summary = "Получение комментариев к объявлению")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Комментарии успешно получены")
    })
    @GetMapping
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable int adId) {
        List<CommentDto> comments = commentService.getComments(adId);
        return ResponseEntity.ok(comments);
    }

    @Operation(summary = "Добавление комментария")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Комментарий успешно добавлен"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные")
    })
    @PostMapping
    public ResponseEntity<CommentDto> addComment(@PathVariable int adId, @RequestBody CommentDto commentDto) {
        CommentDto addedComment = commentService.addComment(adId, commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedComment);
    }
}