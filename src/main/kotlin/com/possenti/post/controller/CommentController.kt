package com.possenti.post.controller

import com.possenti.post.document.Comment
import com.possenti.post.dto.CommentSaveDto
import com.possenti.post.service.CommentService
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
@RequestMapping("/comments")
class CommentController(val commentService: CommentService) {

    @Value("\${paginacao.qtd_por_pagina}")
    val qtdPorPagina: Int = 15

    @PostMapping("/{post_id}")
    fun save(@Valid @RequestBody commentSaveDto: CommentSaveDto,
             @PathVariable("post_id") postId: String,
             @RequestHeader("x-user-email") userEmail: String): ResponseEntity<String> {
        val postDb = commentService.save(commentSaveDto, postId, userEmail)

        return ResponseEntity.ok(postDb.id)
    }

    @PutMapping("/{comment_id}")
    fun update(@Valid @RequestBody commentSaveDto: CommentSaveDto,
               @PathVariable("comment_id") commentId: String,
               @RequestHeader("x-user-email") userEmail: String): ResponseEntity<String> {

        val commentDb = commentService.update(commentSaveDto, commentId, userEmail)

        return ResponseEntity.ok(commentDb.id)
    }

    @DeleteMapping("/{comment_id}")
    fun delete(@PathVariable("comment_id") commentId: String) : ResponseEntity<String> {
        commentService.delete(commentId)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{post_id}")
    fun findAllByPost(@RequestParam(value = "pag", defaultValue = "0") pag: Int,
                      @RequestParam(value = "ord", defaultValue = "id") ord: String,
                      @RequestParam(value = "dir", defaultValue = "DESC") dir: String,
                      @PathVariable("post_id") postId: String,):
            ResponseEntity<List<Comment>> {

        val pageRequest: PageRequest = PageRequest.of(pag, qtdPorPagina, Sort.Direction.valueOf(dir), ord)
        val reactions = commentService.findByPostId(postId, pageRequest)

        return ResponseEntity(reactions, HttpStatus.OK)
    }


}