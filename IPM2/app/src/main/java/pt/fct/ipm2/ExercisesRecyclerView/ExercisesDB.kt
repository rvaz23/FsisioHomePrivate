package pt.fct.ipm2.ExercisesRecyclerView

class ExercisesDB () {
    companion object{


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

    }




}