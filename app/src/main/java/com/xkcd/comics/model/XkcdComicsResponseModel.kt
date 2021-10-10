package com.xkcd.comics.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Result(
    val alt: String,
    val day: String,
    val img: String,
    val link: String,
    val month: String,
    val news: String,
    val num: Int,
    val safe_title: String,
    val title: String,
    val transcript: String,
    val year: String
    ) : Parcelable
