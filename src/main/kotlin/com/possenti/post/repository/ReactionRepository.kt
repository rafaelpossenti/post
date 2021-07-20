package com.possenti.post.repository

import com.possenti.post.document.Reaction
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface ReactionRepository : MongoRepository<Reaction, String> {

    fun findByPostId(postId: String, pageable: Pageable): Page<Reaction>

}