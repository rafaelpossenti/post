package com.possenti.post.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Post (
        var image: String? = null,
        var text: String? = null,
        val userId: String? = null,
        @Id val id: String? = null
)