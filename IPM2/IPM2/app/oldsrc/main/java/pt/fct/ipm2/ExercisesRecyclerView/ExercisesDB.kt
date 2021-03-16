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
            val object4 = Exercises("180º","Cervical","imagem","Explicar como fazer o exercicio")
            list.add(object4)
            val object5 = Exercises("Mão na Orelha","Cervical","imagem","Explicar como fazer o exercicio")
            list.add(object5)
            val object6 = Exercises("Sobe e Desce","Cervical","imagem","Explicar como fazer o exercicio")
            list.add(object6)
            val object7 = Exercises("Levantamento Terra uma perna","Joelho","imagem","Explicar como fazer o exercicio")
            list.add(object7)
            val object8 = Exercises("Deslizamento de tendões","Mao","imagem","Explicar como fazer o exercicio")
            list.add(object8)


            return list
        }

    }




}