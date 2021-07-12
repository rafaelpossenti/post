package com.possenti.post.document

import com.possenti.post.enum.ReactionTypeEnum
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Reaction (
        val reactionType: ReactionTypeEnum,
        val postId: String? = null,
        val userId: String? = null,
        @Id val id: String? = null
)