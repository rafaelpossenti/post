package com.possenti.post.repository

import com.possenti.post.document.Reaction
import org.springframework.data.mongodb.repository.MongoRepository

interface ReactionRepository : MongoRepository<Reaction, String> {

}