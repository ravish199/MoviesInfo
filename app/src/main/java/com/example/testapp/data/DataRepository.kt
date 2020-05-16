package com.example.testapp.data

import android.app.Application
import com.example.testapp.db.OfflineDataRepo
import com.example.testapp.utils.AppUtilities

 object DataRepository {
       var onlineStorage: OnlineStorage? = null
      var offlineStorage: OfflineStorage? = null
    fun getDataSource(application: Application,  offlineDataRepo: OfflineDataRepo): Storage{
          if(AppUtilities.isNetworkConnected(application)) {
             if(onlineStorage == null)
                 onlineStorage = OnlineStorage(offlineDataRepo)
             return onlineStorage as OnlineStorage
         }
        else {
              if(offlineStorage == null)
                  offlineStorage = OfflineStorage(offlineDataRepo)
              return offlineStorage as OfflineStorage
          }
    }
}