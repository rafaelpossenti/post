package com.possenti.post.controller

import com.possenti.post.document.Comment
import com.possenti.post.dto.CommentSaveDto
import com.possenti.post.service.CommentService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/comments")
class CommentController(val commentService: CommentService) {

    @PostMapping("/{post_id}")
    fun save(
        @Valid @RequestBody commentSaveDto: CommentSaveDto,
        @PathVariable("post_id") postId: String,
        @RequestHeader("x-user-email") userEmail: String
    ): ResponseEntity<String> {
        val postDb = commentService.save(commentSaveDto, postId, userEmail)

        return ResponseEntity.ok(postDb.id)
    }

    @PutMapping("/{comment_id}")
    fun update(
        @Valid @RequestBody commentSaveDto: CommentSaveDto,
        @PathVariable("comment_id") commentId: String,
        @RequestHeader("x-user-email") userEmail: String
    ): ResponseEntity<String> {

        val commentDb = commentService.update(commentSaveDto, commentId, userEmail)

        return ResponseEntity.ok(commentDb.id)
    }

    @DeleteMapping("/{comment_id}")
    fun delete(@PathVariable("comment_id") commentId: String): ResponseEntity<String> {
        commentService.delete(commentId)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{post_id}")
    @CrossOrigin
    fun findAllByPost(
        @PathVariable("post_id") postId: String,
        pageable: Pageable
    ): ResponseEntity<Page<Comment>> {

        val reactions = commentService.findByPostId(postId, pageable)

        return ResponseEntity(reactions, HttpStatus.OK)
    }


}