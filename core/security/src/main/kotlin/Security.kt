import security.SecurityService
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.*
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec


class SecurityServiceImpl: SecurityService {

    private fun generateSalt(length: Int = 32): ByteArray {
        val random = SecureRandom()
        val bytes = ByteArray(length)
        random.nextBytes(bytes)
        return bytes
    }

    override fun hashingPassword(password: String, amountIterations: Int, bitsQuantity: Int): String {
        val salt = generateSalt()
        val spec = PBEKeySpec(password.toCharArray(), salt, amountIterations, bitsQuantity)
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
        val hash = Base64.getEncoder().encodeToString(factory.generateSecret(spec).encoded)
        val encodedSalt = Base64.getEncoder().encodeToString(salt)
        spec.clearPassword()
        return "${amountIterations}:${encodedSalt}:${hash}"
    }

    override fun validatePassword(hashedPassword: String, password: String): Status<Boolean> {
        val partsOfHash = hashedPassword.split(":")
        val amountIterations = partsOfHash[0].toInt()
        val salt = Base64.getDecoder().decode(partsOfHash[1])
        val hash = Base64.getDecoder().decode(partsOfHash[2])
        if (partsOfHash.size != 3) return Status.Error(message = "Invalid password", data = false, typeError = Errors.TestError)
        val spec = PBEKeySpec(password.toCharArray(), salt, amountIterations, hash.size * 8)
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
        val checkPassword = factory.generateSecret(spec).encoded
        spec.clearPassword()
        return Status.Success(MessageDigest.isEqual(hash, checkPassword))
    }



}
