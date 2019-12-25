package com.purplemobilestudio.purpletodolist.uı.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.purplemobilestudio.purpletodolist.Model.Task
import com.purplemobilestudio.purpletodolist.R
import com.purplemobilestudio.purpletodolist.db.TaskRepository
import kotlinx.android.synthetic.main.activity_task.*

class TaskActivity : AppCompatActivity()
{
    lateinit var taskRepo:TaskRepository

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        taskRepo= TaskRepository(this)

        add_btn.setOnClickListener{
            if(!TextUtils.isEmpty(title_edt.text.toString())&&!TextUtils.isEmpty(content_edt.text.toString())
                && !TextUtils.isEmpty(deadline_edt.text.toString()))
            {
                val name=title_edt.text.toString()
                val content=content_edt.text.toString()
                val deadline=deadline_edt.text.toString()

                val rowId=taskRepo.insertTask(Task(title = name,content = content,deadline = deadline))

                if(rowId>-1) Toast.makeText(this,"Eklendi",Toast.LENGTH_SHORT).show()
                else Toast.makeText(this,"Ekleme başarısız",Toast.LENGTH_SHORT).show()
            }

            else
            {
                Toast.makeText(this,"Alanlar boş geçilemez",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
