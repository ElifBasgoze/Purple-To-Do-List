package com.purplemobilestudio.purpletodolist.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.purplemobilestudio.purpletodolist.Model.Task
import com.purplemobilestudio.purpletodolist.R
import com.purplemobilestudio.purpletodolist.db.TaskRepository
import com.purplemobilestudio.purpletodolist.ui.adapter.TaskAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TaskAdapter.OnTaskCompleteListener, TaskAdapter.OnTaskEditListener {



    private lateinit var taskrepo: TaskRepository
    private lateinit var taskadapter: TaskAdapter
    private lateinit var list: ArrayList<Task>

    companion object
    {
        const val EXTRA_TASK="extra_task"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title ="Purple To Do List"

        taskrepo= TaskRepository(this)
        list=taskrepo.getAllTask()


        recyclerview.layoutManager=LinearLayoutManager(this)
        taskadapter=TaskAdapter(this,list)

        recyclerview.adapter=taskadapter

        taskadapter.setOnTaskCompleteListener(this)
        taskadapter.setOnEditListener(this)

        floatingActionButton.setOnClickListener { startActivity(Intent(this,TaskActivity::class.java)) }

    }

    override fun onTaskComplete(taskId: Int)
    {
        taskrepo.deleteTask(taskId)
        list=taskrepo.getAllTask()
        taskadapter.UpdateList(list)
    }

    override fun onEditTask(task: Task) {
       val intent=Intent(this, TaskActivity::class.java)
        intent.putExtra(EXTRA_TASK,task)
        startActivity(intent)
    }

    override fun onResume()
    {
        super.onResume()
        list=taskrepo.getAllTask()
        taskadapter.UpdateList(list)

    }
}
