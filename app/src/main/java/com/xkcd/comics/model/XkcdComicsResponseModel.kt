package com.xkcd.comics.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Result(
    val img: String,
    val num: Int,
    val safe_title: String,
    val title: String,
    val transcript: String,
    val year: String
    ) : Parcelable
