package com.example.diapplication.data.model

import android.content.Intent
import android.net.Uri
import com.example.diapplication.common.Constants

class Redirected {
    companion object {
        fun redirectUser(urlRedirected: String): Intent {
            return if (urlRedirected == "form"){
                Intent(Intent.ACTION_VIEW, Uri.parse(Constants.FEED_BACK_URL))
            } else{
                Intent(Intent.ACTION_VIEW, Uri.parse(Constants.GIT_HUB_URL))
            }
        }
    }
}