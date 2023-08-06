package com.example.diapplication.data.model

import android.content.Intent
import android.net.Uri
import com.example.diapplication.domain.utils.Constants

class Redirected {
    companion object {
        fun redirectUser(urlRedirected: String): Intent {
            if (urlRedirected == "form"){
                return Intent(Intent.ACTION_VIEW, Uri.parse(Constants.FEED_BACK_URL))
            }
            else{
                return Intent(Intent.ACTION_VIEW, Uri.parse(Constants.GIT_HUB_URL))
            }
        }
    }
}