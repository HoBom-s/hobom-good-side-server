package hobom.backend.domain.model.season

enum class Quarter {
    FIRST,
    LATTER,
}

fun Quarter.isFirst(): Boolean {
    return this == Quarter.FIRST
}

fun Quarter.isLatter(): Boolean {
    return this == Quarter.LATTER
}
