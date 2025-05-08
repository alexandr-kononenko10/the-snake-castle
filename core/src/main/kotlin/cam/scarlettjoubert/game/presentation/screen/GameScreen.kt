package cam.scarlettjoubert.game.presentation.screen

import cam.scarlettjoubert.game.presentation.viewmodel.GameViewModelContract
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.graphics.use

class GameScreen(
    private val viewModel: GameViewModelContract
) : KtxScreen {
    private val viewport = FitViewport(800f, 600f)
    private val shapeRenderer = ShapeRenderer()

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun render(delta: Float) {
        clearScreen(0.1f, 0.1f, 0.1f, 1f) // Серый фон
        viewport.apply()

        val gameState = viewModel.getGameState()

        // Отрисовка лабиринта и игрока
        shapeRenderer.use(ShapeRenderer.ShapeType.Filled) {
            val tileSize = 60f // Размер клетки

            // Лабиринт
            it.color = Color.BROWN // Цвет колонн
            for (row in gameState.maze.indices) {
                for (col in gameState.maze[0].indices) {
                    if (gameState.maze[row][col] == 1) {
                        it.rect(
                            col * tileSize + 100f, // Смещение по X
                            (7 - row) * tileSize + 60f, // Смещение по Y
                            tileSize,
                            tileSize
                        )
                    }
                }
            }

            // Игрок
            it.color = Color.GREEN // Цвет игрока
            it.rect(
                gameState.playerPosition.x * tileSize + 100f,
                (7 - gameState.playerPosition.y) * tileSize + 60f,
                tileSize,
                tileSize
            )
        }

        // Тестовое управление (для проверки)
        if (Gdx.input.isTouched) {
            val touchX = Gdx.input.x.toFloat() / Gdx.graphics.width * 800f
            val touchY = (1f - Gdx.input.y.toFloat() / Gdx.graphics.height) * 600f
            if (touchX < 200f) viewModel.movePlayer(com.badlogic.gdx.math.Vector2(-1f, 0f)) // Влево
            else if (touchX > 600f) viewModel.movePlayer(com.badlogic.gdx.math.Vector2(1f, 0f)) // Вправо
            else if (touchY > 400f) viewModel.movePlayer(com.badlogic.gdx.math.Vector2(0f, 1f)) // Вверх
            else if (touchY < 200f) viewModel.movePlayer(com.badlogic.gdx.math.Vector2(0f, -1f)) // Вниз
        }
    }

    override fun dispose() {
        shapeRenderer.dispose()
    }
}
