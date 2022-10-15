package hu.bme.aut.it9p0z.network.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object NetworkState {

    fun isOnline(context: Context): Boolean {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val capabilities = connectivityManager
            .getNetworkCapabilities(connectivityManager.activeNetwork)

        return if (capabilities != null) {
            val hasCellularTransport = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
            val hasWiFiTransport = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            hasCellularTransport|| hasWiFiTransport
        } else false
    }

}