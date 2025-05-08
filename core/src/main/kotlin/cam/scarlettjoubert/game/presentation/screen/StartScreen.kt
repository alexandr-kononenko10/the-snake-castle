package cam.scarlettjoubert.game.presentation.screen


import cam.scarlettjoubert.game.presentation.viewmodel.GameViewModelContract
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.viewport.FitViewport
import ktx.actors.stage
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.scene2d.*

class StartScreen(
    private val game: KtxGame<KtxScreen>,
    private val viewModel: GameViewModelContract
) : KtxScreen {
    private val viewport = FitViewport(800f, 600f)
    private val stage = stage(viewport = viewport)

    override fun show() {
        // Создаем стили
        val buttonStyle = TextButton.TextButtonStyle().apply {
            font = BitmapFont().apply { data.setScale(2f) } // Увеличиваем шрифт
            fontColor = Color.WHITE
            downFontColor = Color.GRAY // Цвет при нажатии
        }
        val labelStyle = Label.LabelStyle().apply {
            font = BitmapFont().apply { data.setScale(3f) } // Большой шрифт для заголовка
            fontColor = Color.YELLOW
        }
        val table = Table().apply {
            setFillParent(true)
            center() // Центрируем таблицу
        }
        table.add(
            Label("BomberSnake", labelStyle)
        )
        stage.addActor(table)
        stage.addActor(table{
            setFillParent(true)
            // Заголовок

            row().padTop(50f) // Отступ перед кнопкой
            // Кнопка "Start"
            textButton("Start", buttonStyle) {
                onClick {
                    viewModel.startGame()
                    game.setScreen<GameScreen>()
                }
                pad(20f) // Отступы для кнопки
            }
        })

        // Устанавливаем Stage как обработчик ввода
        com.badlogic.gdx.Gdx.input.inputProcessor = stage
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun render(delta: Float) {
        clearScreen(0f, 0f, 0.2f, 1f) // Темно-синий фон
        stage.act(delta)
        stage.draw()
    }

    override fun dispose() {
        stage.dispose()
    }
}
