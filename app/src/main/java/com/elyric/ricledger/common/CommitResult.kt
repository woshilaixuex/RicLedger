package com.elyric.ricledger.common

sealed class CommitResult {
    object Success : CommitResult()
    data class Error(val msg: String) : CommitResult()
}
