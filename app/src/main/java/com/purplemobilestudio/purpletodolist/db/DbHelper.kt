package com.purplemobilestudio.purpletodolist.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION)
{
    companion object
    {
        private val DATABASE_VERSION=1
        private val DATABASE_NAME="todolist.db"

        val TABLE_NAME="table_task"
        val KEY_ID="_id"
        val KEY_TITLE="title"
        val KEY_CONTENT="content"
        val KEY_DEADLINE="deadline"


       private var mInstance:DbHelper?=null
        @Synchronized fun getInstance(context:Context):DbHelper
        {
            if(mInstance==null)
            {
              mInstance= DbHelper(context.applicationContext)
            }

            return mInstance as DbHelper
        }

    }

    override fun onCreate(db: SQLiteDatabase?) = createTable(db)

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
       db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }

    fun createTable(db: SQLiteDatabase?)
    {
      val CREATE_TASK_TABLE= "CREATE TABLE $TABLE_NAME($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
              "$KEY_TITLE TEXT,$KEY_CONTENT TEXT,$KEY_DEADLINE TEXT)"
        db?.execSQL(CREATE_TASK_TABLE)
    }

}