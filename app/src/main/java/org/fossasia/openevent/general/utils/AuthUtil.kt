package org.fossasia.openevent.general.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.annotation.StringRes
import android.support.v4.app.ActivityCompat
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import okhttp3.Authenticator
import org.fossasia.openevent.general.MainActivity
import org.fossasia.openevent.general.OpenEventGeneral
import timber.log.Timber

object AuthUtil {

    const val VALID = 0
    const val EMPTY = 1
    const val INVALID = 2

    private var authenticator: Authenticator? = null

    @JvmStatic
    val authorization: String?
        get() = formatToken(token)

    private val token: String?
        get() = SharedPreferencesUtil.getString(ConstantStrings.TOKEN, null)

    @JvmStatic
    val isUserLoggedIn: Boolean
        get() {
            val token = token
            return token != null && !JWTUtils.isExpired(token)
        }

    @JvmStatic
    fun logout(context: Context) {
        SharedPreferencesUtil.remove(ConstantStrings.TOKEN)

        goToMainActivity(context);
        Timber.d("Removed token & email and logged out successfully")
    }

    @JvmStatic
    fun getAuthenticator(): Authenticator {
        if (authenticator == null) {
            authenticator = Authenticator { _, response ->
                if (response.request().header("Authorization") != null) {
                    return@Authenticator null // Give up, we've already failed to authenticate.
                }

                val token = token

                if (token == null) {
                    Timber.wtf("Someone tried to access authenticated resource without auth token")
                    return@Authenticator null
                }

                response.request().newBuilder()
                        .header("Authorization", formatToken(token))
                        .build()
            }
        }
        return authenticator as Authenticator
    }

    private fun formatToken(token: String?): String {
        return String.format("JWT $token")
    }

    @JvmStatic
    private fun goToMainActivity(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
        ActivityCompat.finishAffinity(context as Activity)
    }

    private fun showProgressBar(progressBar: ProgressBar, show: Boolean) {
        if (show) progressBar.visibility = View.VISIBLE else progressBar.visibility = View.GONE
    }

    private fun showMessage(@StringRes id: Int) {
        Toast.makeText(OpenEventGeneral.getAppContext(), id, Toast.LENGTH_SHORT).show()
    }
}
