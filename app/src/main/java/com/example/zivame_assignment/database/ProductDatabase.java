package com.example.zivame_assignment.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.zivame_assignment.model.Products;

@Database(
        entities = {Products.class},
        version = 1
)
public abstract class ProductDatabase extends RoomDatabase {

    public abstract ProductDao getProductDao();

    private static ProductDatabase INSTANCE;

    public static ProductDatabase getInstance(final Context context) {

        if (INSTANCE == null) {
            synchronized (ProductDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context,
                            ProductDatabase.class,
                            "products-database"
                    ).fallbackToDestructiveMigration()
                            .addCallback(callback)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }

        return INSTANCE;
    }

   static Callback callback=new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new AsyncTask(INSTANCE);
        }
    };
    static  class AsyncTask extends android.os.AsyncTask<Void,Void,Void>
    {
        private ProductDao productDao;
        AsyncTask(ProductDatabase productDatabase)
        {
            productDao=productDatabase.getProductDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productDao.deleteAll();
            return null;
        }
    }
}
