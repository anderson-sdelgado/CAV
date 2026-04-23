package br.com.usinasantafe.cav.utils

import br.com.usinasantafe.cav.lib.Errors
import timber.log.Timber

fun resultFailure(
    context: String,
    cause: Throwable
): Result<Nothing>  {
    return resultFailure(
        context = context,
        message = cause.message,
        cause = cause.cause
    )
}

fun resultFailure(
    context: String,
    cause: Exception
): Result<Nothing>  {
    return resultFailure(
        context = context,
        message = "-",
        cause = cause
    )
}

fun resultFailure(
    context: String,
    message: String?,
    cause: Throwable? = null
): Result<Nothing>  {
    return Result.failure(
        AppError(
            context = context,
            message = message,
            cause = cause
        )
    )
}

class AppError(
    context: String,
    message: String?,
    cause: Throwable? = null
) : Exception(removeRepeatedCalls("$context${if (message == null) "" else if (message == "-") "" else " -> $message"}"), cause)

fun failure(classAndMethod: String, error: Throwable) : String {
    return removeRepeatedCalls("$classAndMethod -> ${error.message} -> ${error.cause.toString()}")
}

fun removeRepeatedCalls(path: String): String {
    return path
        .split(" -> ")
        .asReversed()
        .distinct()
        .asReversed()
        .joinToString(" -> ")
}


fun <T> handleFailure(
    failure: String,
    classAndMethod: String,
    block: (String) -> T,
) {
    val fail = "$classAndMethod -> $failure"
    Timber.e(fail)
    block(fail)
}

fun <T> handleFailure(
    error: Throwable,
    classAndMethod: String,
    block: (String) -> T,
) {
    val cause = if(error.cause != null) " -> ${error.cause.toString()}" else ""
    val failure = "${error.message}$cause"
    handleFailure(failure, classAndMethod, block)
}

fun Result<*>.onFailureHandled(
    classAndMethod: String,
    block: (String) -> Unit
) {
    onFailure { error ->
        handleFailure(error, classAndMethod, block)
    }
}

inline fun handleFailure(
    classAndMethod: String,
    error: Errors = Errors.INVALID,
    crossinline onError: (String, Errors) -> Unit,
    failure: String = ""
) {
    val failure = failure.ifEmpty { failure(error) }
    handleFailure(failure, classAndMethod) {
        onError(it, error)
    }
}

inline fun handleFailure(
    classAndMethod: String,
    failure: String,
    crossinline onError: (String, Errors) -> Unit,
) {
    handleFailure(failure, classAndMethod) {
        onError(it, Errors.INVALID)
    }
}


fun failure(error: Errors): String {
    return when(error){
        else -> error.toString()
    }
}