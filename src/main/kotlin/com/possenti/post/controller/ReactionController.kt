package com.possenti.post.controller

import com.possenti.post.document.Reaction
import com.possenti.post.dto.ReactionSaveDto
import com.possenti.post.service.ReactionService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/reactions")
class ReactionController(val reactionService: ReactionService) {

    @PostMapping("/{post_id}")
    fun save(
        @Valid @RequestBody reactionSaveDto: ReactionSaveDto,
        @PathVariable("post_id") postId: String,
        @RequestHeader("x-user-email") userEmail: String
    ): ResponseEntity<String> {
        val postDb = reactionService.save(reactionSaveDto, postId, userEmail)

        return ResponseEntity.ok(postDb.id)
    }

    @DeleteMapping("/{reaction_id}")
    fun delete(@PathVariable("reaction_id") reactionId: String): ResponseEntity<String> {
        reactionService.delete(reactionId)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{post_id}")
    fun findAllByPost(
        @PathVariable("post_id") postId: String,
        pageable: Pageable
    ):
            ResponseEntity<Page<Reaction>> {

        val reactions = reactionService.findByPostId(postId, pageable)

        return ResponseEntity(reactions, HttpStatus.OK)
    }


}