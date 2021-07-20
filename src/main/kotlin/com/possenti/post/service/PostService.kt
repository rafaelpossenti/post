package com.possenti.post.service

import com.possenti.post.document.Post
import com.possenti.post.dto.PostSaveDto
import com.possenti.post.repository.PostRepository
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PostService(val postRepository: PostRepository) {

    fun save(postSaveDto: PostSaveDto, userEmail: String): Post {
        val post = turnPostSaveDtoToPost(postSaveDto, userEmail)
        return postRepository.save(post)
    }

    fun update(postSaveDto: PostSaveDto, postId: String, userId: String): Post {
        val postDb = this.findById(postId)

        if (postDb.userId != userId) throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Usuário logado diferente do usuário que fez o post.")

        postDb.text = postSaveDto.text
        return postRepository.save(postDb)
    }

    fun delete(postId: String) {
        this.findById(postId)
        postRepository.deleteById(postId)
    }

    fun findById(postId: String) = postRepository.findById(postId).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Post inexistente.") }

    fun findByUserId(userId: String, pageable: Pageable) = postRepository.findByUserId(userId, pageable)

    private fun turnPostSaveDtoToPost(postSaveDto: PostSaveDto, userEmail: String): Post =
            Post(postSaveDto.text, userEmail, null)




}