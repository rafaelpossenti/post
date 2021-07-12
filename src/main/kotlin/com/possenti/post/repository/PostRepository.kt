package com.possenti.post.repository

import com.possenti.post.document.Post
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.repository.MongoRepository

interface PostRepository : MongoRepository<Post, String> {

    fun findByUserId(userId: String): Post?

    fun findByUserId(userId: String, pageRequest: PageRequest) : List<Post>
}