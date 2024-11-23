package hobom.backend.domain.model.user

import hobom.backend.common.annotation.HashedPassword
import hobom.backend.common.listener.PasswordHashingListener
import hobom.backend.domain.model.common.BaseEntity
import hobom.backend.domain.model.monthreview.MonthReview
import hobom.backend.domain.model.seasonreveiw.SeasonReview
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@EntityListeners(PasswordHashingListener::class)
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
    @HashedPassword
    val password: String,

    @Column(nullable = false, length = 50)
    val name: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role: UserRole,

    @OneToMany(
        mappedBy = "writer",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    val seasonReviews: MutableList<SeasonReview> = mutableListOf(),

    @OneToMany(
        mappedBy = "writer",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    val monthReviews: MutableList<MonthReview> = mutableListOf(),
) : BaseEntity()
