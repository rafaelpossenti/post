package com.possenti.post.controller

import com.possenti.post.document.Post
import com.possenti.post.dto.PostDto
import com.possenti.post.dto.PostSaveDto
import com.possenti.post.service.PostService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/posts")
class PostController(val postService: PostService) {

    @Value("\${paginacao.qtd_por_pagina}")
    val qtdPorPagina: Int = 15

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
    fun findAllByUser(@RequestParam(value = "pag", defaultValue = "0") pag: Int,
                      @RequestParam(value = "ord", defaultValue = "id") ord: String,
                      @RequestParam(value = "dir", defaultValue = "DESC") dir: String,
                      @RequestHeader("x-user-email") userEmail: String):
            ResponseEntity<List<PostDto>> {

        log.info("getting all posts from the user: $userEmail")

        val pageRequest: PageRequest = PageRequest.of(pag, qtdPorPagina, Sort.Direction.valueOf(dir), ord)
        val users = postService.findByUserId(userEmail, pageRequest)

        val usersDto = users.map { post -> turnPostDto(post) }

        return ResponseEntity(usersDto, HttpStatus.OK)
    }


    private fun turnPostDto(post: Post) =
            PostDto(post.text, post.userId, post.id)
}