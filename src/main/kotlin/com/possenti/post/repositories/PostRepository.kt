package com.possenti.post.repositories

import com.possenti.post.documents.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.repository.MongoRepository

interface PostRepository : MongoRepository<Post, String> {

    fun findByUserId(userId: String): Post?

    fun findByUserId(userId: String, pageRequest: PageRequest) : List<Post>
}