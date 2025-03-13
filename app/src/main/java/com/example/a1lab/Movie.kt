package com.example.a1lab

import android.os.Parcel
import android.os.Parcelable

data class Movie(
    val title: String,
    val year: Int,
    val cast: List<String>,
    val genres: List<String>,
    val href: String,
    val extract: String,
    val thumbnail: String,
    val thumbnailWidth: Int,
    val thumbnailHeight: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.createStringArrayList()!!,
        parcel.createStringArrayList()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeInt(year)
        parcel.writeStringList(cast)
        parcel.writeStringList(genres)
        parcel.writeString(href)
        parcel.writeString(extract)
        parcel.writeString(thumbnail)
        parcel.writeInt(thumbnailWidth)
        parcel.writeInt(thumbnailHeight)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}