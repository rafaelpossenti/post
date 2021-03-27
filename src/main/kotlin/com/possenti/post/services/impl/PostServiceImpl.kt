package com.possenti.post.services.impl

import com.possenti.post.documents.Post
import com.possenti.post.repositories.PostRepository
import com.possenti.post.services.PostService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(val postRepository: PostRepository) : PostService {

    override fun save(post: Post): Post {
        return postRepository.save(post)
    }

//override fun findAll(pageRequest: PageRequest) = postRepository.findAll(pageRequest)

    override fun findByUserId(userId: String, pageRequest: PageRequest) = postRepository.findByUserId(userId, pageRequest)
}