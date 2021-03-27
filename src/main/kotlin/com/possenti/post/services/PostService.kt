package com.possenti.post.services

import com.possenti.post.documents.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface PostService {

    fun save(post: Post): Post

//    fun findAll(pageRequest: PageRequest) : Page<Post>

    fun findByUserId(userId: String, pageRequest: PageRequest) : List<Post>
}