package com.possenti.post.controller

import com.possenti.post.dtos.ReactionSaveDto
import com.possenti.post.services.PostService
import com.possenti.post.services.ReactionService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/reactions/{post_id}")
class ReactionController(val reactionService: ReactionService) {

    @PostMapping("/{user_id}")
    fun save(@Valid @RequestBody reactionSaveDto: ReactionSaveDto,
             @PathVariable("post_id") postId: String,
             @PathVariable("user_id") userId: String): ResponseEntity<String> {
        val postDb = reactionService.save(reactionSaveDto, postId, userId)

        return ResponseEntity.ok(postDb.id)
    }
//
//    @GetMapping("/{user_id}")
//    fun get(@RequestParam(value = "pag", defaultValue = "0") pag: Int,
//            @RequestParam(value = "ord", defaultValue = "id") ord: String,
//            @RequestParam(value = "dir", defaultValue = "DESC") dir: String,
//            @PathVariable("user_id") userId: String):
//            ResponseEntity<List<PostDto>> {
//
//        LOGGER.info("getting all posts from the user: $userId")
//
//        val pageRequest: PageRequest = PageRequest.of(pag, qtdPorPagina, Sort.Direction.valueOf(dir), ord)
//        val users = postService.findByUserId(userId, pageRequest)
//
//        val usersDto = users.map { post -> turnPostDto(post) }
//
//        return ResponseEntity(usersDto, HttpStatus.OK)
//    }
//
//
//
//    private fun turnPostDto(post: Post) =
//            PostDto(post.text, post.userId, post.id)
}