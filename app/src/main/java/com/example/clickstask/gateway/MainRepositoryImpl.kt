package com.example.nagwatask.main.connections


class MainRepository constructor(private val retrofitService: Api) {

    fun getAllArticles() = retrofitService.getAllArticles()
}