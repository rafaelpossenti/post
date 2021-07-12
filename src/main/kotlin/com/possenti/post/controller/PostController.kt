package com.possenti.post.controller

import com.possenti.post.document.Post
import com.possenti.post.dto.PostDto
import com.possenti.post.dto.PostSaveDto
import com.possenti.post.enum.CustomStatus
import com.possenti.post.enum.PostStatus
import com.possenti.post.exception.UserNotFoundException
import com.possenti.post.service.PostService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception
import javax.validation.Valid

@RestController
@RequestMapping("/posts")
class PostController(val postService: PostService) {

    @Value("\${paginacao.qtd_por_pagina}")
    val qtdPorPagina: Int = 15

    val LOGGER = LoggerFactory.getLogger(PostController::class.java)

    @ExceptionHandler(UserNotFoundException::class)
    fun userNotFoundException(exception: Exception): ResponseEntity<CustomStatus> {
        //TODO: insert log here with exception
        val body = CustomStatus(PostStatus.USER_NOT_FOUND)
        return ResponseEntity(body, HttpStatus.NOT_FOUND)
    }

    @PostMapping
    fun save(@Valid @RequestBody postSaveDto: PostSaveDto): ResponseEntity<String> {
        val postDb = postService.save(postSaveDto)

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