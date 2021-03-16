package pt.fct.ipm2

import pt.fct.ipm2.ExercisesRecyclerView.Exercises
import pt.fct.ipm2.ui.trainings.Trainings

class Database () {

    companion object{


        var favorites: MutableList<Exercises> = mutableListOf()
        var mytrainings: MutableList<Trainings> = mutableListOf()
        var users: MutableMap<String,User> = mutableMapOf()


        fun  trainingsNames():ArrayList<String>{
            val names = arrayListOf<String>()
            for (i in mytrainings){
                names.add(i.title)
            }
            return names
        }


        fun addUser(u:User){
            users.put(u.email, u)
        }

        fun clearUsers(){
            users.clear()
        }

        fun checkUser(username:String): String? {
            val u = users.get(username)
            if (u!= null){
                return u.password
            }else{
                return null
            }
        }

    }




}
