import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import parts.Header
import parts.CustomPayload
import parts.Payload
import security.TokenPair
import security.TokenService
import java.security.MessageDigest
import java.security.SecureRandom
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import java.util.Base64

class JWT: TokenService {

    private val secret = System.getenv("JWT_SECRET")
    private val mapper = jacksonObjectMapper()

    private fun generateHMACSHA256(data: String): ByteArray  {
        val hmacSHA256 = Mac.getInstance("HmacSHA256")
        val secretKeySpec = SecretKeySpec(secret.toByteArray(), "HmacSHA256")
        hmacSHA256.init(secretKeySpec)
        return hmacSHA256.doFinal(data.toByteArray(Charsets.UTF_8))
    }

    private fun Base64UrlEncoder(data: ByteArray): String {
        return Base64.getUrlEncoder()
            .withoutPadding()
            .encodeToString(data)
    }

    private fun Base64UrlDecoder(data: String): ByteArray {
        return Base64.getUrlDecoder()
            .decode(data)
    }


    override fun generateAccessToken(userId: Long, username: String, role: String): Status<String> {
        // 15 минут
        val tokenLifetime = System.currentTimeMillis() + 1000 * 60 * 15
        val subject = "Access"
        val issuer = "meat-server"
        val headerToken = mapper.writeValueAsString(Header(alg = "HS256", typ = "JWT"))
        val payloadToken = mapper.writeValueAsString(Payload(
            issuer = issuer,
            subject = subject,
            exp = tokenLifetime,
            otherFields = CustomPayload(
                id = userId,
                username = username,
                role = role
            )
        ))
        val encodedHeader = Base64UrlEncoder(data = headerToken.encodeToByteArray())
        val encodedPayload = Base64UrlEncoder(data = payloadToken.encodeToByteArray())
        val encodedSignature = Base64UrlEncoder(data = generateHMACSHA256(data = "${encodedHeader}.${encodedPayload}"))
        return Status.Success(data = "${encodedHeader}.${encodedPayload}.${encodedSignature}")

    }

    override fun validateAccessToken(token: String): Status<Boolean> {
        try {
            val partsOfToken = token.split(".")
            val header: Header = mapper.readValue(
                Base64UrlDecoder(data = partsOfToken[0])
                    .decodeToString()
            )
            val payload: Payload = mapper.readValue(
                Base64UrlDecoder(data = partsOfToken[1])
                    .decodeToString()
            )
            val signature = Base64UrlDecoder(data = partsOfToken[2])
            val currentTime = System.currentTimeMillis()
            if (partsOfToken.size != 3) {
                return Status.Error(
                    message = "Invalid token",
                    data = false,
                    typeError = Errors.TestError
                )
            }
            if (payload.exp < currentTime) {
                return Status.Error(
                    message = "Token is dead :(",
                    data = false,
                    typeError = Errors.TestError
                )
            }
            val comparativeSignature = generateHMACSHA256(data = "${partsOfToken[0]}.${partsOfToken[1]}")
            if (!MessageDigest.isEqual(comparativeSignature, signature)) {
                return Status.Error(
                    message = "Invalid signature",
                    data = false,
                    typeError = Errors.TestError
                )
            }
            return Status.Success(data = true)
        } catch (e: Exception) {
            return Status.Error(
                message = "Invalid signature",
                data = false,
                typeError = Errors.TestError
            )
        }

    }

    override fun getPair(): TokenPair {
        return TokenPair(
            refresh = "1",
            refreshExp = 12,
            access = "1"
        )
    }

    override fun generateRefreshToken(byteLength: Int): Status<Pair<String, Long>> {
        val random = SecureRandom()
        val bytes = ByteArray(byteLength)
        random.nextBytes(bytes)
        val refreshToken = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes)
        val tokenLifetime = System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000
        return Status.Success(Pair(refreshToken, tokenLifetime))
    }

}

fun main() {
    val test = JWT()
    val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3N1ZXIiOiJtZWF0LXNlcnZlciIsInN1YmplY3QiOiJBdXRoIiwiZXhwIjoxNzc1NTIxNjc1NTU2LCJvdGhlckZpZWxkcyI6eyJpZCI6MSwidXNlcm5hbWUiOiJURVNUIiwicm9sZSI6IlRFU1QifX0.Q2l5vkqM2P12rIfmkILM9garCkC92B2RFhUohCWQP4w"
    println(test.generateRefreshToken().data)
    /*println(test.generateAccessToken(
        customPayload = CustomPayload(
            id = 1,
            username = "TEST",
            role = "TEST"
        )
    ))*/
}