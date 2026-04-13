package security

import Status

interface TokenService {

    fun generateAccessToken(userId: Long, username: String, role: String): Status<String>
    fun generateRefreshToken(byteLength: Int = 32): Status<Pair<String, Long>>
    fun getPair(): TokenPair
    fun validateAccessToken(token: String): Status<Boolean>

}