package hobom.backend.common.handler

import hobom.backend.common.command.Command
import hobom.backend.common.command.Response

interface CommandHandler<C : Command, R : Response> {
    fun handle(command: C): R
}
