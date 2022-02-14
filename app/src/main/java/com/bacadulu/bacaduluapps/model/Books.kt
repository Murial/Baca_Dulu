package com.bacadulu.bacaduluapps.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Books(
    val icon: Int,
    val title: String,
    val about: String
) : Parcelable