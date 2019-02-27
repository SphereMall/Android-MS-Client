package com.spheremall.spheremallandroidsdk

import android.app.Application
import com.spheremall.core.SMClient
import com.spheremall.core.api.auth.BasicAuthCredentials
import com.spheremall.core.api.configuration.ApiConstants

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        SMClient.initialize(
                this,
                ApiConstants.API_GATEWAY_URL,
                ApiConstants.API_CLIENT_ID,
                ApiConstants.API_SECRET_KEY,
                ApiConstants.API_VERSION)
        SMClient.get().setBacisAuth(BasicAuthCredentials("user", "q1w2e3"))
    }
}