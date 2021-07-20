package com.possenti.post.repository

import com.possenti.post.document.Comment
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface CommentRepository : MongoRepository<Comment, String> {

    fun findByPostId(postId: String, pageable: Pageable): Page<Comment>

}