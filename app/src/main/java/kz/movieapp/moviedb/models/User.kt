package kz.movieapp.moviedb.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.VisibleForTesting
import com.google.firebase.internal.FirebaseAppHelper.getUid
import com.google.firebase.auth.UserInfo
import com.google.firebase.auth.FirebaseUser


class User {
    lateinit var uid: String
    @Nullable
    @get:Nullable
    lateinit var username: String
    @Nullable
    @get:Nullable
    var email: String? = null
    @VisibleForTesting
    @Nullable
    internal var password: String? = null
    @Nullable
    @get:Nullable
    lateinit var provider: String
    @Nullable
    @get:Nullable
    lateinit var photo_url: String
    @Nullable
    @get:Nullable
    var name: String? = null

    constructor() {}

    constructor(uid: String) {
        this.uid = uid
    }

    constructor(uid: String, username: String, email: String, provider: String, photo_url: String, name: String) {
        this.uid = uid
        this.username = username
        this.email = email
        this.provider = provider
        this.photo_url = photo_url
        this.name = name
    }

    companion object {

        fun newInstance(firebaseUser: FirebaseUser, provider: UserInfo): User {
            val user = User(firebaseUser.uid)
            user.provider = provider.providerId
            // TODO : refactoring
            if (provider.providerId == "password") {
                user.email = firebaseUser.email
            } else if (provider.providerId == "facebook.com") {
                user.name = provider.displayName
                user.photo_url = provider.photoUrl!!.toString()
            } else if (provider.providerId == "google.com") {
                user.email = firebaseUser.email
                user.name = provider.displayName
                user.photo_url = provider.photoUrl!!.toString()
            } else {

            }

            return user
        }
    }
}