package ru.ft.wol.domain.entity

import android.os.Parcel
import android.os.Parcelable

data class Client(
    val id: Int,
    val name: String,
    val address: String,
    val macAddress: String
) : Parcelable {

    // Constructor for parcelable
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    // Write values to parcel
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(name)
        dest.writeString(address)
        dest.writeString(macAddress)
    }

    // Describe the contents of this object's parcel representation
    override fun describeContents(): Int {
        return 0
    }

    // Creator object required by Parcelable interface
    companion object CREATOR : Parcelable.Creator<Client> {
        // Create a new instance of the Parcelable class, instantiating it from the given Parcel
        override fun createFromParcel(parcel: Parcel): Client {
            return Client(parcel)
        }

        // Create a new array of the Parcelable class
        override fun newArray(size: Int): Array<Client?> {
            return arrayOfNulls(size)
        }
    }
}
