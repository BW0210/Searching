package com.saba.searching.core.base

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.saba.searching.core.data.ErrorModel
import com.saba.searching.customClass.Alerter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

class BaseObserverError {

    suspend fun setErrorInfo(e: Throwable? = null) {
        Timber.tag("Error_Network").e(e)
        when (e) {
            is HttpException -> {
                val error = getErrorMessage(e.response()!!.errorBody()?.string())
                manageHttpCode(e.code(), error?.message)
            }
            is ConnectException, is UnknownHostException, is SSLHandshakeException, is SocketTimeoutException -> {

                setAlerter("ارتباط برقرار نشد! لطفا اتصال خود را با اینترنت بررسی کنید")

            }
            is JsonSyntaxException, is IllegalStateException -> {

                setAlerter("خطا در پردازش اطلاعات")

            }
        }
    }

    fun getErrorMessage(e: String?): ErrorModel? {
        try {
            return Gson().fromJson(e, ErrorModel::class.java)
        } catch (e1: Exception) {
            e1.printStackTrace()
        }

        return null
    }

    suspend fun manageHttpCode(code: Int, error: String?): Boolean {
        return when (code) {
            400 -> {
                if (error != null)
                    setAlerter(error)
                else
                    setAlerter("درخواست نامعتبر")
                true
            }
            401 -> {
                setAlerter("احراز هویت کامل نشد")
                true
            }
            403 -> {
                if (error != null)
                    setAlerter(error)
                else
                    setAlerter("دسترسی وجود ندارد")
                true
            }

            422, 405 -> {
                if (error != null)
                    setAlerter(error)
                else
                    setAlerter("دسترسی وجود ندارد")
                true
            }
            406 -> {
                if (error != null)
                    setAlerter(error)
                else
                    setAlerter("اطلاعات ناقص است")
                true
            }

            404 -> {
                if (error != null)
                    setAlerter(error)
                else
                    setAlerter("درخواست یافت نشد")
                true
            }
            429 -> {
                if (error != null)
                    setAlerter(error)
                else
                    setAlerter("پاسخ از سمت سرور دریافت نشد")
                true

            }
            500, 502, 503 -> {
                if (error != null)
                    setAlerter(error)
                else
                    setAlerter("خطا در ارتباط با سرور")
                true

            }
            else -> false
        }
    }

    private suspend fun setAlerter(message: String, isError: Boolean = true) {

        isError.apply {
            if (this) {
                try {
                    withContext(Dispatchers.Main) {
                        Alerter(Alerter.Status.E, "خطا", message)
                    }
                } catch (e: Exception) {
                }
                Timber.tag("amir").e(message)

            } else {
                // Alerter(I, "", message)
            }
        }

    }
}
