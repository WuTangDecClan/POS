package com.example.pos.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pos.model.Customer
import com.example.pos.model.Order

/* Contains the database holder and serves as the main access point for the
 underlying connection to your app's persisted, relational data.
 */
/* Ctrl + Q. - fixes the formatting */
/* Annotates class to be a Room Database with a table (entity) of the Word class. */
@Database(entities = [Customer::class, Order::class], version = 1, exportSchema = false) /* Export Schema keeps a Version history of the Database. */
public abstract class POSRoomDatabase: RoomDatabase() {

    abstract fun POSDao(): POSDao
    /* Having multiple versions of a Database is taxing on performance. The following code insures there is only one version. */
    companion object{ /* Companion Object makes it visible to other classes. */

        @Volatile
        private var INSTANCE: POSRoomDatabase? = null /* Singleton prevents multiple instances of database opening at the same time. (Only one instance).*/
        /* Instance already exists. if not null. */
        fun getDatabase(context: Context): POSRoomDatabase {
            /* If the INSTANCE is not null, then return it, if it is, then create the database. */
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this){ /* The Database is now being built for the first time. */
                val instance = Room.databaseBuilder( /*Creating an instance of the ROOM Database. */
                    context.applicationContext,
                    POSRoomDatabase::class.java,
                    "pos_database"
                ).build() /* Building the database for the first time. */
                INSTANCE = instance
                return instance /* Returning the Instance of the newly created Database. */
            }
        }
    }
}