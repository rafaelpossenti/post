package com.possenti.post.repository

import com.possenti.post.document.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface PostRepository : MongoRepository<Post, String> {

    fun findByUserId(userId: String): Post?

    fun findByUserId(userId: String, pageable: Pageable): Page<Post>
}