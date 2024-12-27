package hobom.backend.common.bus

import hobom.backend.common.handler.QueryHandler
import hobom.backend.common.query.Query
import hobom.backend.common.query.Response
import jakarta.annotation.PostConstruct
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import java.lang.reflect.ParameterizedType

@Component
class QueryBus(private val applicationContext: ApplicationContext) {
    private val handlers = mutableMapOf<Class<*>, QueryHandler<*, *>>()

    @PostConstruct
    fun registerHandlers() {
        val beans = applicationContext.getBeansOfType(QueryHandler::class.java)

        beans.values.forEach { bean ->
            val genericType = (bean.javaClass.genericInterfaces.first() as ParameterizedType).actualTypeArguments[0]
            val handlerType = genericType as Class<*>

            handlers[handlerType] = bean
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <Q : Query, R : Response?> execute(query: Q): R {
        val handler = handlers[query::class.java] as? QueryHandler<Q, R>
            ?: throw IllegalArgumentException("No handler found for query: ${query::class.simpleName}")

        return handler.handle(query)
    }
}
