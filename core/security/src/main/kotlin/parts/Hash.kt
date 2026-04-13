package parts

data class Hash(
    val iterations: Int,
    val salt: String,
    val hash: String,
)