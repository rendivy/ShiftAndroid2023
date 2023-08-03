package com.example.diapplication.data.model

import android.content.Intent
import android.net.Uri

class Redirected {
    companion object {
        private const val gitHubUrl = "https://github.com/rendivy/ShiftAndroid2023"
        private const val feedbackUrl = "https://forms.gle/oLaWJZQYNPdFCDSC6"
        fun redirectUser(urlRedirected: String): Intent {
            if (urlRedirected == "form"){
                return Intent(Intent.ACTION_VIEW, Uri.parse(feedbackUrl))
            }
            else{
                return Intent(Intent.ACTION_VIEW, Uri.parse(gitHubUrl))
            }
        }
    }
}