package com.possenti.post.repository

import com.possenti.post.document.Reaction
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.repository.MongoRepository

interface ReactionRepository : MongoRepository<Reaction, String> {

    fun findByPostId(postId: String, pageRequest1: PageRequest): List<Reaction>

}