package com.purplemobilestudio.purpletodolist.ui.activity

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

        if(intent.extras!=null)
        {
            val task:Task= intent.extras!!.getSerializable(MainActivity.EXTRA_TASK) as Task
            title_edt.setText(task.title)
            content_edt.setText(task.content)
            deadline_edt.setText(task.deadline)
        }

        add_btn.setOnClickListener{

            if(intent.extras!=null)
            {
                val task:Task= intent.extras!!.getSerializable(MainActivity.EXTRA_TASK) as Task
                val rowId=taskRepo.updateTask(Task(task.id,title_edt.text.toString(),content_edt.text.toString(),deadline_edt.text.toString()))

                if(rowId>-1) Toast.makeText(this,"Güncellendi",Toast.LENGTH_SHORT).show()
                else Toast.makeText(this,"Güncelleme başarısız",Toast.LENGTH_SHORT).show()
            }

            else {

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
}
