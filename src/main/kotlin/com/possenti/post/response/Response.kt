package com.possenti.post.response

class Response<T> (
        val erros: ArrayList<String> = arrayListOf(),
        var data: T? = null
)