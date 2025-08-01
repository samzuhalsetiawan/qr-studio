package com.samzuhalsetiawan.qrstudio.domain.model

class QRCode(
    val data: Array<Array<Boolean>>,
    val dimension: Int = 256,
) {
    init {
        require(data.size == dimension) { "data size must match dimension property" }
    }
    operator fun get(x: Int, y: Int): Boolean {
        return data[x][y]
    }
}

fun QRCode(dimension: Int = 256, init: (x: Int, y: Int) -> Boolean): QRCode {
    val matrix2d = Array(dimension) { x ->
        Array(dimension) { y ->
            init(x, y)
        }
    }
    return QRCode(matrix2d, dimension)
}