class BoardRenderer(private val board: Board) {

    fun render() {
        val liveCells = board.getLiveCells()
        val maxX = liveCells.maxOf { it.x }
        val maxY = liveCells.maxOf { it.y }
        val minX = liveCells.minOf { it.x }
        val minY = liveCells.minOf { it.y }

        for (y in maxY downTo minY) {
            for (x in minX..maxX) {
                val cell = Cell(x, y)
                if (liveCells.contains(cell)) {
                    print("O") // represent live cell
                } else {
                    print(".") // represent dead cell
                }
            }
            println()
        }
    }
}
