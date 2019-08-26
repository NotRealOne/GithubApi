package com.kdaydin.inginterview.data

/**
 * Created by kubilaay on 2019-08-26.
 */
class Repository {
    var id: Long = 0L
    var node_id: String = ""
    var name: String = ""
    var full_name: String = ""
    var private: Boolean = false
    var owner: RepositoryOwner? = null
    var html_url: String = ""
    var description: String = ""
    var fork: Boolean = false
    var url: String = ""
    var stargazers_count: Int = 0
    var watchers_count: Int = 0
    var language: String = ""
    var has_issues: Boolean = false
    var forks_count: Int = 0
    var open_issues_count: Int = 0
    var forks: Int = 0
    var open_issues: Int = 0
    var watchers: Int = 0
    var isFavorite: Boolean = false
}