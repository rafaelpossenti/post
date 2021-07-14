package com.possenti.post.repository

import com.possenti.post.document.Comment
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.repository.MongoRepository

interface CommentRepository : MongoRepository<Comment, String> {

    fun findByPostId(postId: String, pageRequest1: PageRequest): List<Comment>

}