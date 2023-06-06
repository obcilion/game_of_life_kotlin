

class Board(initialBoardState: Set<Cell>) {
    private val liveCells = initialBoardState.toMutableSet()
    private val cellsToKill = mutableSetOf<Cell>()
    private val cellsToRevive = mutableSetOf<Cell>()

    fun performGameStep(): Unit {
        prepareCellChanges()
        executeCellChanges()
    }

    fun getLiveCells(): Set<Cell> {
        return liveCells
    }

    private fun prepareCellChanges(): Unit {
        val cellsCheckedThisStep = mutableSetOf<Cell>()

        for(liveCell in liveCells) {
            processLiveCell(liveCell)
            cellsCheckedThisStep.add(liveCell)

            setNeighboursToRevive(liveCell, cellsCheckedThisStep)
        }
    }

    private fun setNeighboursToRevive(cell: Cell, cellsChecked: MutableSet<Cell>) {
        val neighboursOf = cell.getNeighbours()
        for(neighbourCell in neighboursOf) {
            if(cellsChecked.contains(neighbourCell) || liveCells.contains(neighbourCell)) {
                continue
            }

            val liveNeighboursCount = countLiveNeighbours(neighbourCell)
            if(liveNeighboursCount == 3) {
                cellsToRevive.add(neighbourCell)
            }

            cellsChecked.add(neighbourCell)
        }
    }

    private fun processLiveCell(cell: Cell) {
        val liveNeighboursCount = countLiveNeighbours(cell)

        if (liveNeighboursCount < 2) { // underpopulation
            cellsToKill.add(cell)
        } else if (liveNeighboursCount > 3) { // overpopulation
            cellsToKill.add(cell)
        }
    }

    private fun countLiveNeighbours(cell: Cell): Int {
        var liveNeighboursCount = 0
        val neighbourCells = cell.getNeighbours()

        for(neighbourCell in neighbourCells) {
            if(liveCells.contains(neighbourCell)) {
                liveNeighboursCount++
            }
        }

        return liveNeighboursCount
    }

    private fun executeCellChanges(): Unit {
        for(cellToKill in cellsToKill) {
            liveCells.remove(cellToKill)
        }

        for(cellToRevive in cellsToRevive) {
            liveCells.add(cellToRevive)
        }

        cellsToKill.clear()
        cellsToRevive.clear()
    }
}