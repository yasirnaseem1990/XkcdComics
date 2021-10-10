
package com.xkcd.comics

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class XkcdComicsApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}
