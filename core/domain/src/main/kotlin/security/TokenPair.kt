package security

data class TokenPair(
    val refresh: String,
    val refreshExp: Long,
    val access: String
)
