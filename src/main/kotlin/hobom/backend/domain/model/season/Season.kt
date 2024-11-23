package hobom.backend.domain.model.season

import hobom.backend.domain.model.common.BaseEntity
import hobom.backend.domain.model.matching.Matching
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
import java.time.LocalDate

@Entity
@Table(name = "season")
data class Season(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true, length = 50)
    val season: String,

    @Column(nullable = false)
    val year: Int,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val quarter: Quarter,

    @Column(nullable = false)
    val startDate: LocalDate,

    @Column(nullable = false)
    val endDate: LocalDate,

    @OneToMany(mappedBy = "season", cascade = [CascadeType.ALL], orphanRemoval = true)
    val matchings: MutableList<Matching> = mutableListOf(),
) : BaseEntity()
