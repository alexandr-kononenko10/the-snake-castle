package cam.scarlettjoubert.game.domain.entities

import com.badlogic.gdx.math.Vector2

data class GameState(
    val isGameStarted: Boolean = false,
    val playerPosition: Vector2 = Vector2(1f, 1f), // Начальная позиция игрока (в клетках лабиринта)
    val maze: Array<IntArray> = arrayOf( // Тот же лабиринт, что в GameScreen
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
        intArrayOf(1, 0, 0, 0, 1, 0, 1, 0, 0, 1),
        intArrayOf(1, 0, 1, 0, 0, 0, 0, 0, 1, 1),
        intArrayOf(1, 0, 0, 0, 1, 0, 1, 0, 0, 1),
        intArrayOf(1, 1, 1, 0, 0, 0, 1, 0, 1, 1),
        intArrayOf(1, 0, 0, 0, 1, 0, 0, 0, 0, 1),
        intArrayOf(1, 0, 1, 0, 0, 0, 1, 0, 0, 1),
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
    )
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameState

        if (isGameStarted != other.isGameStarted) return false
        if (playerPosition != other.playerPosition) return false
        if (!maze.contentDeepEquals(other.maze)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = isGameStarted.hashCode()
        result = 31 * result + playerPosition.hashCode()
        result = 31 * result + maze.contentDeepHashCode()
        return result
    }
}
