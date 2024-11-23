package hobom.backend.domain.user

import hobom.backend.domain.common.BaseEntity
import hobom.backend.domain.monthreview.MonthReview
import hobom.backend.domain.seasonreveiw.SeasonReview
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val seasonId: Long,

    @Column(nullable = false, unique = true, length = 30)
    val nickname: String,

    @Column(name = "enc_password", nullable = false)
    val password: String,

    @Column(nullable = false, length = 50)
    val name: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role: UserRole,

    @OneToMany(mappedBy = "writer", cascade = [CascadeType.ALL], orphanRemoval = true)
    val seasonReviews: MutableList<SeasonReview> = mutableListOf(),

    @OneToMany(mappedBy = "writer", cascade = [CascadeType.ALL], orphanRemoval = true)
    val monthReviews: MutableList<MonthReview> = mutableListOf(),
) : BaseEntity()
