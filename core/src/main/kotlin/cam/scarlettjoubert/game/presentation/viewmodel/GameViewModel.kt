package cam.scarlettjoubert.game.presentation.viewmodel


import cam.scarlettjoubert.game.domain.entities.GameState
import com.badlogic.gdx.math.Vector2

interface GameViewModelContract {
    fun startGame()
    fun isGameStarted(): Boolean
    fun movePlayer(direction: Vector2)
    fun getGameState(): GameState
}

class GameViewModel : GameViewModelContract {
    private var gameState = GameState()

    override fun startGame() {
        gameState = gameState.copy(isGameStarted = true)
    }

    override fun isGameStarted(): Boolean = gameState.isGameStarted

    override fun movePlayer(direction: Vector2) {
        val newPosition = gameState.playerPosition.cpy().add(direction)
        // Проверка, что новая позиция в пределах лабиринта и не стена
        val row = newPosition.y.toInt()
        val col = newPosition.x.toInt()
        if (row in gameState.maze.indices && col in gameState.maze[0].indices && gameState.maze[row][col] == 0) {
            gameState = gameState.copy(playerPosition = newPosition)
        }
    }

    override fun getGameState(): GameState = gameState
}
