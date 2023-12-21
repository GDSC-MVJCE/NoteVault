package com.example.communityhubapp.data

data class PostModel(
    val title: String = "",
    val content: String = "",
    val username: String = ""
) {
    // Default constructor
    constructor() : this("", "", "")
}
