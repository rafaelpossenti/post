package com.possenti.post.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/post/posts")
class TestController {

    @GetMapping
    fun listarPorId(): String {
        return "TESTE"
    }
}