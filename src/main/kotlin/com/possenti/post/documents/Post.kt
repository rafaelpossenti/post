package com.possenti.post.documents

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Post (
        val text: String? = null,
        val userId: String? = null,
        @Id val id: String? = null
)