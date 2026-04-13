
sealed class Status<T>(val data: T? = null, val message: String? = null, typeError: Errors? = null) {
    class Success<T>(data: T): Status<T>(data = data)
    class Error<T>(message: String?, data: T? = null, typeError: Errors): Status<T>(data = data, message = message, typeError = typeError)
    class Loading<T>(message: String?, data: T?): Status<T>(message = message, data = data)
}