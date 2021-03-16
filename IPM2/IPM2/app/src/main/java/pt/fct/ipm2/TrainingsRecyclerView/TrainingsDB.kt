package pt.fct.ipm2.TrainingsRecyclerView


import pt.fct.ipm2.ExercisesRecyclerView.Exercises
import pt.fct.ipm2.R
import pt.fct.ipm2.ui.trainings.Trainings


class TrainingsDB() {
    companion object {


        fun createDataSet(): MutableList<Trainings> {
            var list: MutableList<Trainings> = mutableListOf()

            val object4 = Exercises(
                "180º",
                "Cervical",
                R.drawable.rodarcabeca,
                "Gire a cabeça para a esquerda, o máximo que conseguir. Depois gire para a direita e assim sucessivamente, lentamente",
                "01:00"
            )
            val object5 = Exercises(
                "Mão na Orelha",
                "Cervical",
                R.drawable.alongarpescoco,
                "Passe o braço sobre a cabeça e puxe para o lado até sentir resistência.Segure durante 10 segundos e inverta o lado.",
                "01:00"
            )
            val object6 = Exercises(
                "Sobe e Desce",
                "Cervical",
                R.drawable.sobeedesce,
                "Com os braços junto ao corpo, erga o queixo e depois desça-o até ao peito. Repita o movimento lentamente",
                "01:00"
            )
            val exercisesTrain1: MutableList<Exercises> = arrayListOf(object4, object5, object6)


            val training1 = Trainings("Torcicolo", exercisesTrain1)
            list.add(training1)

            val object8 = Exercises(
                "Alongamento dos flexores do Pulso",
                "Mao",
                R.drawable.extensaopulso,
                "Use sua mão não afetada para dobrar o pulso afetado para baixo, " +
                        "conforme mostrado. Segure duranto 10 segundos e faça o mesmo mas dobrando o pulso para cima. Mantenha sempre o cotovelo reto no lado afetado.",
                "01:00"
            )
            val object10 = Exercises(
                "Segurar toalha",
                "Mao",
                R.drawable.toalhapulso,
                "Coloque uma toalha enrolada na mão e aperte por algum tempo. Repita o processo.",
                "05:00"
            )
            val exercisesTrain2: MutableList<Exercises> = arrayListOf(object8, object10)
            val training2 = Trainings("Tendinite no Pulso", exercisesTrain2)
            list.add(training2)

            val object11 = Exercises(
                "Levantamento dos Calcanhares",
                "Pé",
                R.drawable.dedocadeira,
                "De pé, segurando-se numa cadeira, levante-se apoiado na ponta dos pés ao mesmo tempo que levanta os calcanhares do chão. Mantenha-se apoiado na ponta dos pés durante algum tempo e repita.",
                "02:00"
            )
            val object12 = Exercises(
                "Movimento do Tornozelo",
                "Pé",
                R.drawable.alongarpes,
                "Dobre o pé para cima e para baixo, conforme mostrado.",
                "01:00"
            )
            val exercisesTrain4: MutableList<Exercises> = arrayListOf(object11, object12)
            val training4 = Trainings("Entorse do Calcanhar", exercisesTrain4)
            list.add(training4)


            val object7 = Exercises(
                "Abdução da Anca",
                "Perna",
                R.drawable.pernadelado,
                "Enquanto está deitado de lado, levante lentamente a perna de cima para o lado. Mantenha o joelho reto e os dedos dos pés apontados para a frente o tempo todo. Mantenha a perna alinhada com o corpo.\n" +
                        "\n" +
                        "A perna de baixo pode ser dobrada para estabilizar o corpo."
                ,
                "01:00"
            )
            val object1 = Exercises(
                "Esticar Perna",
                "Perna",
                R.drawable.pernaparacima,
                "Enquanto está deitado de costas no chão, levante a perna com um joelho reto. Mantenha o joelho oposto dobrado com o pé apoiado no chão.",
                "02:00"
            )
            val object9 = Exercises(
                "Alongamento Sentado do Tendão",
                "Perna",
                R.drawable.esticarpernasentado,
                "Enquanto estiver sentado, apoie o calcanhar no chão com o joelho reto e incline-se suavemente para a frente até sentir um alongamento atrás do joelho / coxa.\n" +
                        "\n" +
                        "Mantenha a coluna reta o tempo todo."
                ,
                "03:00"
            )

            val exercisesTrain3: MutableList<Exercises> = arrayListOf(object7, object9, object1)
            val training3 = Trainings("Alongamento da Perna", exercisesTrain3)
            list.add(training3)

            return list
        }

    }


}