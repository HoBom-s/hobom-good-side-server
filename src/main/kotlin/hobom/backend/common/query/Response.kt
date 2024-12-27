package hobom.backend.common.query

interface Response

data class ListResponse<T : Response>(val items: List<T>) : Response
