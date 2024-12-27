package hobom.backend.application.user.dto.query

import hobom.backend.common.query.Query

data class GetUserByNickname(
    val nickname: String,
) : Query
