package pt.fct.ipm2

import java.io.Serializable
import java.util.*

data class User( val username:String, val email: String,val password: String, val date: String) :
    Serializable