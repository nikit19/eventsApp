package org.fossasia.openevent.general

import android.app.ProgressDialog
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.support.annotation.StringRes
import android.support.design.widget.TextInputLayout
import android.widget.Toast

import org.fossasia.openevent.general.model.Login
import org.fossasia.openevent.general.rest.ApiClient
import org.fossasia.openevent.general.model.LoginResponse
import org.fossasia.openevent.general.utils.ConstantStrings
import org.fossasia.openevent.general.utils.SharedPreferencesUtil
import org.fossasia.openevent.general.utils.Utils

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

import org.fossasia.openevent.general.utils.AuthUtil.EMPTY
import org.fossasia.openevent.general.utils.AuthUtil.INVALID
import org.fossasia.openevent.general.utils.AuthUtil.VALID

class LoginActivityViewModel : ViewModel() {

    private var loginResponse: MutableLiveData<LoginResponse>? = null
    private val compositeDisposable = CompositeDisposable()

    fun loginUser(email: String, password: String, emailWrapper: TextInputLayout, passwordWrapper: TextInputLayout, context: Context, progressDialog: ProgressDialog): LiveData<LoginResponse> {
        if (loginResponse == null) {
            loginResponse = MutableLiveData()
        }

        if (validateCredentials(email, password, emailWrapper, passwordWrapper, context)) {
            compositeDisposable.add(ApiClient.getClient2().login(Login(email, password))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        if (response.isSuccessful) {
                            val accessToken = response.body()!!.accessToken
                            SharedPreferencesUtil.putString(ConstantStrings.TOKEN, accessToken)
                            Timber.d("Success")
                            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                            loginResponse!!.setValue(LoginResponse(VALID, accessToken))
                        } else {
                            Toast.makeText(context, "Unable to Login", Toast.LENGTH_SHORT).show()
                            Timber.d("Error " + response.code() + "\n" + "Error body " + response.errorBody())
                            loginResponse!!.setValue(LoginResponse(INVALID, ""))
                        }
                        progressDialog.cancel()
                    }) { throwable ->
                        Toast.makeText(context, "Unable to Login", Toast.LENGTH_SHORT).show()
                        Timber.e("Failure" + "\n" + throwable.toString())
                        loginResponse!!.setValue(LoginResponse(INVALID, ""))
                        progressDialog.cancel()
                    })
        } else {
            loginResponse!!.setValue(LoginResponse(EMPTY, ""))
            progressDialog.cancel()
        }
        return loginResponse as MutableLiveData<LoginResponse>
    }

    private fun validateCredentials(email: String, password: String, emailWrapper: TextInputLayout, passwordWrapper: TextInputLayout, context: Context): Boolean {

        if (Utils.isEmpty(email)) {
            handleError(emailWrapper, R.string.error_email_required, context)
            return false
        } else if (!Utils.isEmailValid(email)) {
            handleError(emailWrapper, R.string.error_enter_valid_email, context)
            return false
        }

        if (Utils.isEmpty(password)) {
            handleError(passwordWrapper, R.string.error_password_required, context)
            return false
        }
        return true
    }

    private fun handleError(textInputLayout: TextInputLayout, @StringRes id: Int, context: Context) {
        textInputLayout.error = context.getString(id)
        textInputLayout.requestFocus()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
