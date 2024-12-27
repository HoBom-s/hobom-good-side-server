package hobom.backend.domain.model.category

import hobom.backend.domain.model.common.BaseEntity
import hobom.backend.domain.model.monthreview.MonthReview
import hobom.backend.domain.model.seasonreveiw.SeasonReview
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
    val id: Long = 0L,

    @Column(nullable = false, length = 100)
    var title: String,

    @Column(nullable = false, length = 200)
    var path: String,

    @Column(nullable = false)
    var sortIndex: Int,

    @OneToMany(
        mappedBy = "category",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    val seasonReviews: MutableList<SeasonReview>? = mutableListOf(),

    @OneToMany(
        mappedBy = "category",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    val monthReviews: MutableList<MonthReview>? = mutableListOf(),
) : BaseEntity() {
    fun update(title: String?, path: String?, sortIndex: Int?) {
        title?.let { this.title = it }
        path?.let { this.path = it }
        sortIndex?.let { this.sortIndex = it }
    }
}
