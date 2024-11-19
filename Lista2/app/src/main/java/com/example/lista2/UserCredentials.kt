package com.example.lista2

import androidx.lifecycle.ViewModel

data class User(
    val username: String,
    val password: String,
    val passwordRepeat: String
)
class UserCredentials : ViewModel() {
    var username: String? = null
    var password: String? = null
    var passwordRepeat: String? = null
    
    private val listOfUsers = mutableListOf(
        User("abcd","abcd","abcd"),
        User("efgh","efgh","efgh"),
        User("ijkl","ijkl","ijkl"),
        User("mnop","mnop","mnop"),
        User("qrst","qrst","qrst")
            )

    var loggedInUser: User? = null
    fun validate(username: String, password:String,passwordRepeat:String):Boolean
    {
        val user = listOfUsers.find{it.username == username && it.password == password && it.passwordRepeat == passwordRepeat}
        loggedInUser = user
        return user != null
    }
    fun registerUser(username:String, password:String,passwordRepeat:String):Boolean
    {
        if(listOfUsers.any{it.username == username}) {
            return false
        }
        listOfUsers.add(User(username,password,passwordRepeat))
        return true

    }
}