package com.example.xortranslator.utils

import android.util.Base64
import java.nio.charset.Charset
import kotlin.experimental.xor

object EncryptionHelper {

    fun encode(str: String, key: String = "ku3ikqienNb0kqao"): String {
        return try {
            val decrypted = str.toByteArray(Charsets.UTF_8)
            val encrypted = ByteArray(decrypted.size)
            for (i in decrypted.indices) {
                encrypted[i] = (decrypted[i] xor key[i % key.length].code.toByte())
            }
            Base64.encodeToString(encrypted, Base64.NO_WRAP)
        } catch (e: Exception) {
            e.toString()
        }
    }

//    inline fun String.encode(key: String = "ku3ikqienNb0kqao") : String {
//        return try {
//            val decrypted = this.toByteArray(Charsets.UTF_8)
//            val encrypted = ByteArray(decrypted.size)
//            for (i in decrypted.indices) {
//                encrypted[i] = (decrypted[i] xor key[i % key.length].code.toByte())
//            }
//            Base64.encodeToString(encrypted, Base64.NO_WRAP)
//        } catch (e: Exception) {
//            e.toString()
//        }
//    }

    fun decode(str: String, key: String = "ku3ikqienNb0kqao"): String {
        return try {
            val decoded = Base64.decode(str, Base64.DEFAULT)
            val result = ByteArray(decoded.size)
            for (c in decoded.indices) {
                result[c] = (decoded[c].toUInt() xor key[c % key.length].code.toUInt()).toByte()
            }
            String(result, Charset.defaultCharset())
        } catch (e: Exception) {
            e.toString()
        }
    }

//    inline fun String.decode(key: String = "ku3ikqienNb0kqao"): String {
//        return try {
//            val decoded = Base64.decode(this, Base64.DEFAULT)
//            val result = ByteArray(decoded.size)
//            for (c in decoded.indices) {
//                result[c] = (decoded[c].toUInt() xor key[c % key.length].code.toUInt()).toByte()
//            }
//            String(result, Charset.defaultCharset())
//        } catch (e: Exception) {
//            e.toString()
//        }
//    }

}