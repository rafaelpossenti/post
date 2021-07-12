package com.possenti.post.service

import com.possenti.post.client.UserClient
import com.possenti.post.component.UserComponent
import com.possenti.post.document.Post
import com.possenti.post.dto.PostSaveDto
import com.possenti.post.exception.PostNotFoundException
import com.possenti.post.exception.UserNotFoundException
import com.possenti.post.repository.PostRepository
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class PostService(
        val postRepository: PostRepository,
        val userComponent: UserComponent
) {

    fun save(postSaveDto: PostSaveDto): Post {
        userComponent.validateWhetherUserExists(postSaveDto.userId!!)

        val post = turnPostSaveDtoToPost(postSaveDto)
        return postRepository.save(post)
    }

    fun findByUserId(userId: String, pageRequest: PageRequest) = postRepository.findByUserId(userId, pageRequest)

    private fun turnPostSaveDtoToPost(postSaveDto: PostSaveDto): Post =
            Post(postSaveDto.text, postSaveDto.userId, null)

}