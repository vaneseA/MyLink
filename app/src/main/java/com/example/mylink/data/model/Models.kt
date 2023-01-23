package com.example.mylink

data class SjDomain(
    var name: String,
    var url: String
)

data class SjLink(
    var name: String,
    val domain: SjDomain,
    var url: String,
    val tags: SjTag
) {
    companion object {
        fun getFullUrl(link: SjLink): String {
            return "${link.domain.url}${link.url}"
        }
    }
}

data class SjTag(
    var name: String
)
