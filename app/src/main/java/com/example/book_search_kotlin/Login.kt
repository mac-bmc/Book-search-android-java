package com.example.book_search_kotlin

class Login {
    fun LoginAuth(username:String,password:String) : Boolean
    {
        var key : Boolean = false;
        if(username==null||password==null)
        {
            key=false;
        }
        else if (username.length>0&&password.length>0)
        {
            key=true;
        }
        return key
    }
}