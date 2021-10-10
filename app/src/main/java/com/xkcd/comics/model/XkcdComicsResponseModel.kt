package com.xkcd.comics.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class XkcdComicsResponseModel(
    val data: Data
) {

    data class Data(
        val count: Int,
        val limit: Int,
        val offset: Int,
        val results: List<Result>,
        val total: Int
    ) {
        @Parcelize
        data class Result(
            val description: String,
            val format: String,
            val id: Int,
            val images: List<Image>,
            val issueNumber: Int,
            val title: String
        ) : Parcelable {

            @Parcelize
            data class Image(
                val extension: String,
                val path: String
            ): Parcelable

        }
    }
}