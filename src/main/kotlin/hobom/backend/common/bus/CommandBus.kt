package hobom.backend.common.bus

import hobom.backend.common.command.Command
import hobom.backend.common.command.Response
import hobom.backend.common.handler.CommandHandler
import jakarta.annotation.PostConstruct
import org.springframework.aop.framework.AopProxyUtils
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import java.lang.reflect.ParameterizedType

@Component
class CommandBus(private val applicationContext: ApplicationContext) {
    private val handlers = mutableMapOf<Class<*>, CommandHandler<*, *>>()

    @PostConstruct
    fun registerHandlers() {
        val beans = applicationContext.getBeansOfType(CommandHandler::class.java)

        beans.values.forEach { bean ->
            val targetClass = AopProxyUtils.ultimateTargetClass(bean)
            val interfaces = targetClass.genericInterfaces
            val handlerInterface = interfaces.firstOrNull { it is ParameterizedType }

            if (handlerInterface != null) {
                val genericType = (handlerInterface as ParameterizedType).actualTypeArguments[0]
                val handlerType = genericType as Class<*>

                handlers[handlerType] = bean
            } else {
                val handlerType = targetClass
                handlers[handlerType] = bean
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <C : Command, R : Response> execute(command: C): R {
        val handler = handlers[command::class.java] as? CommandHandler<C, R>
            ?: throw IllegalArgumentException("No handler found for command: ${command::class.simpleName}")

        return handler.handle(command)
    }
}
