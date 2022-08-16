package com.theappland.notetakingapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "notes")
data class NoteModel(
    @ColumnInfo(name = "title")
    val noteTitle: String,
    @ColumnInfo(name = "details")
    val noteDetails: String,
    @ColumnInfo(name = "date")
    val noteDate: String,
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0

}