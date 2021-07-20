package com.possenti.post.service

import com.possenti.post.document.Comment
import com.possenti.post.dto.CommentSaveDto
import com.possenti.post.repository.CommentRepository
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CommentService(val commentRepository: CommentRepository, val postService: PostService) {

    fun save(commentSaveDto: CommentSaveDto, postId: String, userId: String): Comment {

        postService.findById(postId)

        //TODO: Create a message to an kafka Topic, telling that a new comment was made in his post with: userId/text of who comments

        val comment = turnCommentSaveDtoToComment(commentSaveDto, postId, userId)
        return commentRepository.save(comment)
    }

    fun update(commentSaveDto: CommentSaveDto, commentId: String, userId: String): Comment {
        val commentDb = this.findById(commentId)

        if (commentDb.userId != userId) throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Usuário logado diferente do usuário que fez o comentario.")

        commentDb.text = commentSaveDto.text
        return commentRepository.save(commentDb)
    }

    fun delete(commentId: String) {
        this.findById(commentId)
        commentRepository.deleteById(commentId)
    }

    fun findById(commentId: String) = commentRepository.findById(commentId).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Comentário inexistente.") }

    fun findByPostId(postId: String, pageable: Pageable) = commentRepository.findByPostId(postId, pageable)

    private fun turnCommentSaveDtoToComment(commentSaveDto: CommentSaveDto, postId: String, userId: String): Comment =
            Comment(commentSaveDto.text, postId, userId, null)




}