data class Cell (val x: Int, val y: Int) {

    fun getNeighbours(): List<Cell> {
        return listOf(
            // Bottom row
            Cell(x - 1, y - 1),  // Left column
            Cell(x, y -1),       // Center column
            Cell(x + 1, y - 1),  // Right column

            // Center row
            Cell(x - 1, y),      // Left column
            Cell(x + 1, y),      // Right column

            // Upper row
            Cell(x - 1, y + 1),  // Left column
            Cell(x, y + 1),      // Center column
            Cell(x + 1, y + 1),  // Right column
        )
    }
}