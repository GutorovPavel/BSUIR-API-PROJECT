package com.example.bsuirmentors.common.extensions

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder


fun NavController.navigate(route: String, param: Pair<String, Parcelable>?, builder: NavOptionsBuilder.() -> Unit = {}) {
    param?.let { this.currentBackStackEntry?.arguments?.putParcelable(param.first, param.second)  }

    navigate(route, builder)
}

fun NavController.navigate(route: String, params: List<Pair<String, Parcelable>>?, builder: NavOptionsBuilder.() -> Unit = {}) {
    params?.let {
        val arguments = this.currentBackStackEntry?.arguments
        params.forEach { arguments?.putParcelable(it.first, it.second) }
    }

    navigate(route, builder)
}

fun NavController.navigate(route: String, params: Bundle?, builder: NavOptionsBuilder.() -> Unit = {}) {
    this.currentBackStackEntry?.arguments?.putAll(params)

    navigate(route, builder)
}