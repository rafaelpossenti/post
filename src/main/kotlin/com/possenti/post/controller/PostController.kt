package com.possenti.post.controller

import com.possenti.post.document.Post
import com.possenti.post.dto.PostDto
import com.possenti.post.dto.PostSaveDto
import com.possenti.post.service.PostService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/posts")
class PostController(val postService: PostService) {

    val log = LoggerFactory.getLogger(PostController::class.java)

    @PostMapping
    fun save(@Valid @RequestBody postSaveDto: PostSaveDto,
             @RequestHeader("x-user-email") userEmail: String): ResponseEntity<String> {

        val postDb = postService.save(postSaveDto, userEmail)

        return ResponseEntity.ok(postDb.id)
    }

    @PutMapping("/{post_id}")
    fun update(@Valid @RequestBody postSaveDto: PostSaveDto,
               @PathVariable("post_id") postId: String,
               @RequestHeader("x-user-email") userEmail: String): ResponseEntity<String> {

        val postDb = postService.update(postSaveDto, postId, userEmail)

        return ResponseEntity.ok(postDb.id)
    }

    @DeleteMapping("/{post_id}")
    fun delete(@PathVariable("post_id") postId: String): ResponseEntity<String> {
        postService.delete(postId)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{post_id}")
    fun findById(@PathVariable("post_id") post_id: String): ResponseEntity<Post> {
        val post = postService.findById(post_id)
        return ResponseEntity.ok(post)
    }

    @GetMapping
    fun findAllByUser(@RequestHeader("x-user-email") userEmail: String,
                      pageable: Pageable):
            ResponseEntity<Page<PostDto>> {

        log.info("getting all posts from the user: $userEmail")

        val users = postService.findByUserId(userEmail, pageable)

        val usersDto = users.map { post -> turnPostDto(post) }

        return ResponseEntity(usersDto, HttpStatus.OK)
    }


    private fun turnPostDto(post: Post) =
            PostDto(post.text, post.userId, post.id)
}