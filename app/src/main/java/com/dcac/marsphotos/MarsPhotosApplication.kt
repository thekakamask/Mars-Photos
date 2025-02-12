package com.dcac.marsphotos

import android.app.Application
import com.dcac.marsphotos.data.AppContainer
import com.dcac.marsphotos.data.DefaultAppContainer

/*CUSTOM APPLICATION CLASS THAT SERVES AS THE ENTRY POINT WHEN THE
ANDROID APP STARTS :
1️⃣ MarsPhotosApplication extends Application, meaning it is
automatically instantiated when the app launches.
2️⃣ It declares a container variable of type AppContainer, which
is an interface responsible for managing dependencies.
3️⃣ Inside onCreate() (executed when the app starts), it
/initializes container with DefaultAppContainer(), a concrete
implementation of AppContainer.*/
class MarsPhotosApplication : Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}