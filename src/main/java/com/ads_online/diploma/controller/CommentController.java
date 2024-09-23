package com.ads_online.diploma.controller;

import com.ads_online.diploma.dto.CommentDTO;
import com.ads_online.diploma.dto.CommentsDTO;
import com.ads_online.diploma.dto.CreateOrUpdateCommentDTO;
import com.ads_online.diploma.entity.Comment;
import com.ads_online.diploma.exeptions.EntityNotFoundException;
import com.ads_online.diploma.exeptions.ForbiddenException;
import com.ads_online.diploma.exeptions.UnauthorizedException;
import com.ads_online.diploma.service.CommentService;
import com.ads_online.diploma.utils.MethodLog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;


@Slf4j
@RestController
@Tag(name = "Комментарии")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
public class CommentController {

    private final CommentService commentService;

    @Operation(
            tags = "Комментарии",
            summary = "Получение комментариев объявления"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(
                                    implementation = CommentsDTO.class
                            ))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @GetMapping(path = "/{id}/comments")
    public ResponseEntity<CommentsDTO> getComments(@PathVariable Long id,
                                                   Authentication authentication) {
        log.info("Использован метод {}", MethodLog.getMethodName());

        try {
            return ResponseEntity.ok(commentService.getComments(id, authentication));
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (ForbiddenException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Operation(
            tags = "Комментарии",
            summary = "Добавление комментария к объявлению"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(
                                    implementation = CommentDTO.class
                            ))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    @PostMapping(path = "/{id}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable Long id,
                                                    @RequestBody CreateOrUpdateCommentDTO createOrUpdateCommentDTO,
                                                    Authentication authentication) {
        log.info("Использован метод {}", MethodLog.getMethodName());

        try {
            return ResponseEntity.ok(commentService.createComment
                    (id, createOrUpdateCommentDTO, authentication));
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(
            tags = "Комментарии",
            summary = "Удаление комментария"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    @DeleteMapping(path = "/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> removalComment(@PathVariable Long adId,
                                                  @PathVariable Long commentId,
                                                  Authentication authentication) {
        log.info("Использован метод {}", MethodLog.getMethodName());

        try {
            commentService.removalComment(adId, commentId, authentication);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (ForbiddenException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(
            tags = "Комментарии",
            summary = "Обновление комментария"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(
                                    implementation = CommentDTO.class
                            ))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)

    })
    @PatchMapping(path = "/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> editComment(@PathVariable Long adId,
                                                  @PathVariable Long commentId,
                                                  @RequestBody CreateOrUpdateCommentDTO createOrUpdateCommentDTO,
                                                  Authentication authentication) {
        log.info("Использован метод {}", MethodLog.getMethodName());

        try {
            return ResponseEntity.ok(commentService.editComment(adId, commentId, createOrUpdateCommentDTO, authentication));
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (ForbiddenException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}