package hobom.backend.domain.user

enum class UserRole {
    ADMIN,
    MENTOR,
    MENTEE,
}

fun UserRole.isAdmin(): Boolean {
    return this == UserRole.ADMIN
}

fun UserRole.isMentor(): Boolean {
    return this == UserRole.MENTOR
}

fun UserRole.isMentee(): Boolean {
    return this == UserRole.MENTEE
}