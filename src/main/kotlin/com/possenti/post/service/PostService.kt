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
class PostService(val postRepository: PostRepository) {

    fun save(postSaveDto: PostSaveDto, userEmail: String): Post {
        val post = turnPostSaveDtoToPost(postSaveDto, userEmail)
        return postRepository.save(post)
    }

    fun update(postSaveDto: PostSaveDto, postId: String): Post {
        val postDb = this.findById(postId)
        postDb.text = postSaveDto.text
        return postRepository.save(postDb)
    }

    fun delete(postId: String) {
        this.findById(postId)
        postRepository.deleteById(postId)
    }

    fun findById(postId: String) = postRepository.findById(postId).orElseThrow { PostNotFoundException() }

    fun findByUserId(userId: String, pageRequest: PageRequest) = postRepository.findByUserId(userId, pageRequest)

    private fun turnPostSaveDtoToPost(postSaveDto: PostSaveDto, userEmail: String): Post =
            Post(postSaveDto.text, userEmail, null)




}