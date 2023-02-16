package com.example.mylink.data.model

import androidx.room.*

// entities
@Entity
data class SjDomain(
    @PrimaryKey(autoGenerate = true) val did: Int = 0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "url") var url: String
)

@Entity
data class SjLink(
    @PrimaryKey(autoGenerate = true) val lid: Int = 0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "did") var did: Int,
    @ColumnInfo(name = "url") var url: String,
)

@Entity
data class SjTag(
    @PrimaryKey(autoGenerate = true) val tid: Int = 0,
    @ColumnInfo(name = "name") var name: String
)

@Entity
data class SjSearch(
    @PrimaryKey(autoGenerate = true) val sid: Int = 0,
    @ColumnInfo(name = "keyword") var keyword: String
)


// entities :: mapping table
@Entity(primaryKeys = ["sid", "tid"])
data class SearchTagCrossRef(
    val sid: Int,
    val tid: Int
)

@Entity(primaryKeys = ["lid", "tid"])
data class LinkTagCrossRef(
    val lid: Int,
    val tid: Int
)


// n by m relation :: search and tag
data class SjSearchWithTags(
    @Embedded val search: SjSearch,
    @Relation(
        parentColumn = "sid",
        entityColumn = "tid",
        associateBy = Junction(SearchTagCrossRef::class)
    ) val tags: List<SjTag>
)


//n by m relation :: link and tag
data class SjLinkWithTags(
    @Embedded val link: SjLink,
    @Relation(
        parentColumn = "lid",
        entityColumn = "tid",
        associateBy = Junction(LinkTagCrossRef::class)
    ) val tags: List<SjTag>
)

data class SjTagWithLinks(
    @Embedded val tag: SjTag,
    @Relation(
        parentColumn = "tid",
        entityColumn = "lid",
        associateBy = Junction(LinkTagCrossRef::class)
    ) val links: List<SjLink>
)


// 1 by n by m relation :: domain and link and tag
data class SjLinksAndDomainsWithTags(
    @Embedded val link: SjLink,
    @Relation(
        parentColumn = "did",
        entityColumn = "did"
    ) val domain: SjDomain,
    @Relation(
        parentColumn = "lid",
        entityColumn = "tid",
        associateBy = Junction(LinkTagCrossRef::class)
    ) val tags: List<SjTag>
)


// 1 by n relation :: domain and link
data class SjLinkAndDomain(
    @Embedded val link: SjLink,
    @Relation(
        parentColumn = "did",
        entityColumn = "did"
    )
    val domain: SjDomain
)

data class SjDomainWithLinks(
    @Embedded val domain: SjDomain,
    @Relation(
        parentColumn = "did",
        entityColumn = "did"
    )
    val links: List<SjLink>
)