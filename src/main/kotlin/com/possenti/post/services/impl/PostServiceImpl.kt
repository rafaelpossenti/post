package com.possenti.post.services.impl

import com.possenti.post.client.UserClient
import com.possenti.post.documents.Post
import com.possenti.post.repositories.PostRepository
import com.possenti.post.services.PostService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class PostServiceImpl(
        val postRepository: PostRepository,
        val userClient: UserClient
) : PostService {

    override fun save(post: Post): Post {
        if (userClient.exists(post.userId!!).body == false ) {
           throw RuntimeException("usuário inexistente!")
        }
        return postRepository.save(post)
    }

//override fun findAll(pageRequest: PageRequest) = postRepository.findAll(pageRequest)

    override fun findByUserId(userId: String, pageRequest: PageRequest) = postRepository.findByUserId(userId, pageRequest)
}