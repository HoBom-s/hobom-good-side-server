package hobom.backend.common.handler

import hobom.backend.common.query.Query
import hobom.backend.common.query.Response

interface QueryHandler<Q : Query, R : Response?> {
    fun handle(query: Q): R
}
