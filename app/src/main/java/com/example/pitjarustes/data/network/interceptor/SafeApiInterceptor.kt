package com.example.pitjarustes.data.network.interceptor

import com.example.pitjarustes.common.BaseResponse
import retrofit2.Response

inline fun <T> safeApiCall(apiCall: () -> Response<T>): BaseResponse<T> {
    return try {
        BaseResponse.success(apiCall.invoke())
    } catch (e: Exception) {
        BaseResponse.failure(e)
    }
}