package pt.fct.ipm2.ExercisesRecyclerView

import pt.fct.ipm2.R

class ExercisesDB() {
    companion object {


        fun createDataSet(): MutableList<Exercises> {
            var list: MutableList<Exercises> = mutableListOf()

            val object1 = Exercises(
                "Esticar a Perna",
                "Perna",
                R.drawable.pernaparacima,
                "Enquanto está deitado de costas no chão, levante a perna com um joelho reto. Mantenha o joelho oposto dobrado com o pé apoiado no chão.",
                "02:00"
            )
            list.add(object1)
            val object7 = Exercises(
                "Abdução da Anca",
                "Perna",
                R.drawable.pernadelado,
                "Enquanto está deitado de lado, levante lentamente a perna de cima para o lado. Mantenha o joelho reto e os dedos dos pés apontados para a frente o tempo todo. Mantenha a perna alinhada com o corpo.\n" +
                        "\n" +
                        "A perna de baixo pode ser dobrada para estabilizar o corpo.",
                "01:00"
            )
            list.add(object7)
            val object9 = Exercises(
                "Alongamento Sentado do Tendão",
                "Perna",
                R.drawable.esticarpernasentado,
                "Enquanto estiver sentado, apoie o calcanhar no chão com o joelho reto e incline-se suavemente para a frente até sentir um alongamento atrás do joelho / coxa.\n" +
                        "\n" +
                        "Mantenha a coluna reta o tempo todo.",
                "03:00"
            )
            list.add(object9)


            val object2 = Exercises(
                "Rotação dos Ombros",
                "Ombro",
                R.drawable.rotacaoombro,
                "Mova os ombros num padrão circular. Faça pequenos círculos, tanto numa direção como na outra.",
                "01:00"
            )
            list.add(object2)
            val object3 = Exercises(
                "Rotação Externa do Ombro com Banda Elástica",
                "Ombro",
                R.drawable.puxarcorda,
                "Enquanto segura um elástico ao lado do corpo com o cotovelo dobrado, comece com a mão perto do estômago e puxe o elástico. Mantenha sempre o cotovelo encostado ao corpo .",
                "02:00"
            )
            list.add(object3)


            val object4 = Exercises(
                "180º",
                "Cervical",
                R.drawable.rodarcabeca,
                "Gire a cabeça para a esquerda, o máximo que conseguir. Depois gire para a direita e assim sucessivamente, lentamente",
                "01:00"
            )
            list.add(object4)
            val object5 = Exercises(
                "Mão na Orelha",
                "Cervical",
                R.drawable.alongarpescoco,
                "Passe o braço sobre a cabeça e puxe para o lado até sentir resistência.Segure durante 10 segundos e inverta o lado.",
                "01:00"
            )
            list.add(object5)
            val object6 = Exercises(
                "Sobe e Desce",
                "Cervical",
                R.drawable.sobeedesce,
                "Com os braços junto ao corpo, erga o queixo e depois desça-o até ao peito. Repita o movimento lentamente",
                "01:00"
            )
            list.add(object6)


            val object8 = Exercises(
                "Alongamento dos flexores do Pulso",
                "Mão",
                R.drawable.extensaopulso,
                "Use sua mão não afetada para dobrar o pulso afetado para baixo, " +
                        "conforme mostrado. Segure duranto 10 segundos e faça o mesmo mas dobrando o pulso para cima. Mantenha sempre o cotovelo reto no lado afetado.",
                "01:00"
            )
            list.add(object8)
            val object10 = Exercises(
                "Segurar toalha",
                "Mão",
                R.drawable.toalhapulso,
                "Coloque uma toalha enrolada na mão e aperte por algum tempo. Repita o processo.",
                "05:00"
            )
            list.add(object10)

            val object11 = Exercises(
                "Levantamento dos Calcanhares",
                "Pé",
                R.drawable.dedocadeira,
                "De pé, segurando-se numa cadeira, levante-se apoiado na ponta dos pés ao mesmo tempo que levanta os calcanhares do chão. Mantenha-se apoiado na ponta dos pés durante algum tempo e repita.",
                "02:00"
            )
            list.add(object11)
            val object12 = Exercises(
                "Movimento do Tornozelo",
                "Pé",
                R.drawable.alongarpes,
                "Dobre o pé para cima e para baixo, conforme mostrado.",
                "01:00"
            )
            list.add(object12)


            return list
        }

    }
}
