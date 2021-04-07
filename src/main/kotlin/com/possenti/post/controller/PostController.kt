package com.possenti.post.controller

import com.possenti.post.documents.Post
import com.possenti.post.dtos.PostDto
import com.possenti.post.dtos.PostSaveDto
import com.possenti.post.services.PostService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/posts")
class PostController(val postService: PostService) {

    @Value("\${paginacao.qtd_por_pagina}")
    val qtdPorPagina: Int = 15

    val LOGGER = LoggerFactory.getLogger(PostController::class.java)

    @PostMapping("/{user_id}")
    fun save(@Valid @RequestBody postSaveDto: PostSaveDto,
             @PathVariable("user_id") userId: String): ResponseEntity<String> {
        val postDb = postService.save(postSaveDto, userId)

        return ResponseEntity.ok(postDb.id)
    }

    @GetMapping("/{user_id}")
    fun get(@RequestParam(value = "pag", defaultValue = "0") pag: Int,
            @RequestParam(value = "ord", defaultValue = "id") ord: String,
            @RequestParam(value = "dir", defaultValue = "DESC") dir: String,
            @PathVariable("user_id") userId: String):
            ResponseEntity<List<PostDto>> {

        LOGGER.info("getting all posts from the user: $userId")

        val pageRequest: PageRequest = PageRequest.of(pag, qtdPorPagina, Sort.Direction.valueOf(dir), ord)
        val users = postService.findByUserId(userId, pageRequest)

        val usersDto = users.map { post -> turnPostDto(post) }

        return ResponseEntity(usersDto, HttpStatus.OK)
    }



    private fun turnPostDto(post: Post) =
            PostDto(post.text, post.userId, post.id)
}