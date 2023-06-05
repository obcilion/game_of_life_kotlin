
fun main() {
    val initialBoardState = mutableSetOf<Cell>(
        Cell(-1, 0),
        Cell(0,0),
        Cell(1,0)
    )

    val board = Board(initialBoardState)
    val boardRenderer = BoardRenderer(board)

    boardRenderer.render()
    board.performGameStep()
    boardRenderer.render()
    board.performGameStep()
    boardRenderer.render()
}