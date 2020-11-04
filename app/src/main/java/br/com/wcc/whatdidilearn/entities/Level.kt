package br.com.wcc.whatdidilearn.entities

import br.com.wcc.whatdidilearn.R

enum class Level (
    val color: Int
) {
    HIGH(R.color.green),
    MEDIUM(R.color.yellow),
    LOW(R.color.red)
}