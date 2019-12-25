package com.purplemobilestudio.purpletodolist.Model

import java.io.Serializable

data class Task (var id:Int=0,var title:String, var content:String, var deadline:String ):Serializable
{
}