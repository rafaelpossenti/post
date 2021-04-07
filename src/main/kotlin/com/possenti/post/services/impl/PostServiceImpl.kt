package com.possenti.post.services.impl

import com.possenti.post.client.UserClient
import com.possenti.post.documents.Post
import com.possenti.post.dtos.PostSaveDto
import com.possenti.post.exception.PostNotFoundException
import com.possenti.post.repositories.PostRepository
import com.possenti.post.services.PostService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(
        val postRepository: PostRepository,
        val userClient: UserClient
) : PostService {

    override fun save(postSaveDto: PostSaveDto, userId: String): Post {
        if (userClient.exists(userId).body == false) {
           throw PostNotFoundException()
        }
        val post = turnPostSaveDtoToPost(postSaveDto, userId)
        return postRepository.save(post)
    }

    override fun findByUserId(userId: String, pageRequest: PageRequest) = postRepository.findByUserId(userId, pageRequest)

    private fun turnPostSaveDtoToPost(postSaveDto: PostSaveDto, userId: String): Post =
            Post(postSaveDto.text, userId, null)

}