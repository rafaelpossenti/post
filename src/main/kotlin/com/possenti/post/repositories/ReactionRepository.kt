package com.possenti.post.repositories

import com.possenti.post.documents.Reaction
import org.springframework.data.mongodb.repository.MongoRepository

interface ReactionRepository : MongoRepository<Reaction, String> {

}