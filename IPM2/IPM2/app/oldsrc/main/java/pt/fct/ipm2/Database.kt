package pt.fct.ipm2

import pt.fct.ipm2.ExercisesRecyclerView.Exercises
import pt.fct.ipm2.ui.trainings.Trainings

class Database () {

    companion object{

        var favorites: MutableList<Exercises> = mutableListOf()
        var mytrainings: MutableList<Trainings> = mutableListOf()


        fun createDataSet():MutableList<Exercises>{
            var list: MutableList<Exercises> = mutableListOf()
            val object1 = Exercises("Extensão quadril","Perna","imagem","Explicar como fazer o exercicio")
            list.add(object1)
            val object2 = Exercises("Rotação Bilateral Externa","Ombro","imagem","Explicar como fazer o exercicio")
            list.add(object2)
            val object3 = Exercises("Rotação Bilateral Interna","Ombro","imagem","Explicar como fazer o exercicio")
            list.add(object3)


            return list
        }

        fun  trainingsNames():ArrayList<String>{
            val names = arrayListOf<String>()
            for (i in mytrainings){
                names.add(i.title)
            }
            return names
        }

    }




}
