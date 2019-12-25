package com.purplemobilestudio.purpletodolist.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.purplemobilestudio.purpletodolist.Model.Task

class TaskRepository(var context: Context)

{
    private var mDbHelper: DbHelper= DbHelper.getInstance(context)

    fun getAllTask():ArrayList<Task>
    {
       val list= ArrayList<Task>()
        val db=mDbHelper.readableDatabase
        val query=
            "SELECT ${DbHelper.KEY_ID}, ${DbHelper.KEY_TITLE}, ${DbHelper.KEY_CONTENT}, ${DbHelper.KEY_DEADLINE} FROM ${DbHelper.TABLE_NAME}"
        val cursor : Cursor=db.rawQuery(query,null)

        if(cursor.moveToFirst())
        {
            do
            {
                val id=cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_ID))
                val title=cursor.getString(cursor.getColumnIndex(DbHelper.KEY_TITLE))
                val content=cursor.getString(cursor.getColumnIndex(DbHelper.KEY_CONTENT))
                val deadline=cursor.getString(cursor.getColumnIndex(DbHelper.KEY_DEADLINE))

                val task=Task(id, title, content, deadline)
                list.add(task)
            }
                while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return list
    }


    fun insertTask(task: Task) :Int
    {
        val db=mDbHelper.writableDatabase

        val values=ContentValues()
        values.apply()
        {
           put(DbHelper.KEY_TITLE,task.title)
            put(DbHelper.KEY_CONTENT,task.content)
            put(DbHelper.KEY_DEADLINE,task.deadline)
        }

        val deger= db.insert(DbHelper.TABLE_NAME,null,values)
        db.close()

        return deger.toInt()

    }

    fun deleteTask(taskId: Int)
    {
       val db=mDbHelper.writableDatabase
       db.delete(DbHelper.TABLE_NAME,DbHelper.KEY_ID + "=?", arrayOf(taskId.toString()))
        db.close()
    }

    fun updateTask(task: Task):Int
    {
       val db=mDbHelper.writableDatabase
        val values=ContentValues()
        values.apply {
            put(DbHelper.KEY_TITLE,task.title)
            put(DbHelper.KEY_CONTENT,task.content)
            put(DbHelper.KEY_DEADLINE,task.deadline)
        }

        val id:Int=db.update(DbHelper.TABLE_NAME,values,DbHelper.KEY_ID+"=?", arrayOf(task.id.toString()))

        return id
    }
}