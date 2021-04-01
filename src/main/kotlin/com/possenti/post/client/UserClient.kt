package com.possenti.post.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient("smart")
interface UserClient {

    @GetMapping("/users/{id}/exists")
    fun exists(@PathVariable("id") id: String) : ResponseEntity<Boolean>
}