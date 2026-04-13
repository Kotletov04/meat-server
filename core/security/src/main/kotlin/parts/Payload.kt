package parts

data class Payload(
    val issuer: String,
    val subject: String,
    val exp: Long,
    val otherFields: CustomPayload? = null
)
