package com.possenti.post.controller

import com.possenti.post.document.Reaction
import com.possenti.post.dto.PostDto
import com.possenti.post.dto.ReactionSaveDto
import com.possenti.post.service.ReactionService
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/reactions")
class ReactionController(val reactionService: ReactionService) {

    @Value("\${paginacao.qtd_por_pagina}")
    val qtdPorPagina: Int = 15

    @PostMapping("/{post_id}")
    fun save(@Valid @RequestBody reactionSaveDto: ReactionSaveDto,
             @PathVariable("post_id") postId: String,
             @RequestHeader("x-user-email") userEmail: String): ResponseEntity<String> {
        val postDb = reactionService.save(reactionSaveDto, postId, userEmail)

        return ResponseEntity.ok(postDb.id)
    }

    @DeleteMapping("/{reaction_id}")
    fun delete(@PathVariable("reaction_id") reactionId: String) : ResponseEntity<String> {
        reactionService.delete(reactionId)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{post_id}")
    fun findAllByPost(@RequestParam(value = "pag", defaultValue = "0") pag: Int,
                      @RequestParam(value = "ord", defaultValue = "id") ord: String,
                      @RequestParam(value = "dir", defaultValue = "DESC") dir: String,
                      @PathVariable("post_id") postId: String,):
            ResponseEntity<List<Reaction>> {

        val pageRequest: PageRequest = PageRequest.of(pag, qtdPorPagina, Sort.Direction.valueOf(dir), ord)
        val reactions = reactionService.findByPostId(postId, pageRequest)

        return ResponseEntity(reactions, HttpStatus.OK)
    }


}