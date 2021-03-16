package pt.fct.ipm2.TrainingsRecyclerView



import pt.fct.ipm2.ExercisesRecyclerView.Exercises
import pt.fct.ipm2.ui.trainings.Trainings


class TrainingsDB () {
    companion object{


        fun createDataSet():MutableList<Trainings>{
            var list: MutableList<Trainings> = mutableListOf()

            val object1 =Exercises("Sobe e Desce", "Pescoço","image","Com os braços junto ao corpo, erga o queixo e depois desça ele até o peito. Repita este movimento 5 vezes, vagarosamente.")
            val object2 =Exercises("180°", "Pescoço","image","Gire a cabeça pra esquerda, o máximo que conseguir. Depois gire para a direita. Repita os dois movimentos 5 vezes. Pronto – agora nenhum torcicolo vai te pegar.")
            val object3 =Exercises("Mão na Orelha", "Pescoço","image","Passe o braço sobre a cabeça e puxe para o lado até sentir resistência. Inverta o lado. Repita este movimento 5 vezes, vagarosamente.")
            val exercises :MutableList<Exercises> = arrayListOf(object1,object2,object3)
            val training1 = Trainings("Torcicolo", exercises)
            list.add(training1)
            val training2 = Trainings("Tendinite", exercises)
            list.add(training2)
            val training3 = Trainings("Alongamento", exercises)
            list.add(training3)
            val training4 = Trainings("Entorse", exercises)
            list.add(training4)
            return list
        }

    }




}