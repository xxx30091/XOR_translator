package com.example.xortranslator.utils

import android.graphics.Color
import android.text.*
import android.text.style.ForegroundColorSpan
import org.json.JSONArray
import org.json.JSONObject

class Formatter {

    object JSONFormatter {
        fun jsonFormatWithWarning(responseBodyString: String): SpannableString {
            return try {
                val content = when {
                    responseBodyString.startsWith("{") -> JSONObject(responseBodyString).toString(4)
                    responseBodyString.startsWith("[") -> JSONArray(responseBodyString).toString(4)
                    else -> responseBodyString
                }
                SpannableString(content)
            } catch (_: Exception) {
                val errorTitle = SpannableString("Can not reformat all string：")
                errorTitle.setSpan(ForegroundColorSpan(Color.RED), 0, errorTitle.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

                val spannableStringBuilder = SpannableStringBuilder().apply {
                    append(errorTitle)
                    append("\n")
                    append(responseBodyString)
                }
                SpannableString(spannableStringBuilder)
            }
        }
    }

}