package com.possenti.post.enum

enum class PostStatus {
    USER_NOT_FOUND, POST_NOT_FOUND
}

data class CustomStatus(
    val status: PostStatus
)
