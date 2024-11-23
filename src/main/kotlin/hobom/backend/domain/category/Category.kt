package hobom.backend.domain.category

import hobom.backend.domain.common.BaseEntity
import hobom.backend.domain.monthreview.MonthReview
import hobom.backend.domain.seasonreveiw.SeasonReview
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "category")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, length = 100)
    val title: String,

    @Column(nullable = false, length = 200)
    val path: String,

    @Column(nullable = false)
    val sortIndex: Int,

    @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL], orphanRemoval = true)
    val seasonReviews: MutableList<SeasonReview> = mutableListOf(),

    @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL], orphanRemoval = true)
    val monthReviews: MutableList<MonthReview> = mutableListOf(),
) : BaseEntity()
