package hobom.backend.common.listener

import hobom.backend.common.annotation.HashedPassword
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.lang.reflect.Field

class PasswordHashingListener {
    private val encoder = BCryptPasswordEncoder()

    @PrePersist
    @PreUpdate
    fun hashPassword(entity: Any) {
        val fields: Array<Field> = entity::class.java.declaredFields

        for (field in fields) {
            if (field.isAnnotationPresent(HashedPassword::class.java)) {
                field.isAccessible = true

                val value = field.get(entity) as? String

                if (value != null && !value.startsWith("{bcrypt}")) {
                    val hashedValue = encoder.encode(value)
                    field.set(entity, hashedValue)
                }
            }
        }
    }
}
