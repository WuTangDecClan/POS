package com.example.pos.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pos.model.Customer

/* Contains the database holder and serves as the main access point for the
 underlying connection to your app's persisted, relational data.
 */
/* Ctrl + Q. */
// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Customer::class], version = 1, exportSchema = false)
public abstract class POSRoomDatabase: RoomDatabase() {

    abstract fun POSDao(): POSDao

    companion object{ /* compnaion object makes it visible to other classes. */
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: POSRoomDatabase? = null
        /* instance already exists. if not null */
        fun getDatabase(context: Context): POSRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    POSRoomDatabase::class.java,
                    "pos_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}