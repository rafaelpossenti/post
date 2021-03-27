package com.possenti.post.controller

import com.possenti.post.documents.Post
import com.possenti.post.dtos.PostDto
import com.possenti.post.response.Response
import com.possenti.post.services.PostService
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/post/posts")
class PostController(val postService: PostService) {

    @Value("\${paginacao.qtd_por_pagina}")
    val qtdPorPagina: Int = 15

    @PostMapping
    fun save(@Valid @RequestBody postDto: PostDto): ResponseEntity<String> {

        val post = turnDtoToPost(postDto)
        val postDb = postService.save(post)

        return ResponseEntity.ok(postDb.id)
    }

    @GetMapping("/{user_id}")
    fun get(@RequestParam(value = "pag", defaultValue = "0") pag: Int,
            @RequestParam(value = "ord", defaultValue = "id") ord: String,
            @RequestParam(value = "dir", defaultValue = "DESC") dir: String,
            @PathVariable("user_id") userId: String):
            ResponseEntity<List<PostDto>> {

        val pageRequest: PageRequest = PageRequest.of(pag, qtdPorPagina, Sort.Direction.valueOf(dir), ord)
        val users = postService.findByUserId(userId, pageRequest)

        val usersDto = users.map { post -> turnPostDto(post) }

        return ResponseEntity(usersDto, HttpStatus.OK)
    }

    private fun turnDtoToPost(postDto: PostDto): Post =
            Post(postDto.text, postDto.userId, postDto.id)

    private fun turnPostDto(post: Post) =
            PostDto(post.text, post.userId, post.id)
}