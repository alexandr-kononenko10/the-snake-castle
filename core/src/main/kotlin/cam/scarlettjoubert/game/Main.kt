package cam.scarlettjoubert.game

import cam.scarlettjoubert.game.presentation.screen.GameScreen
import cam.scarlettjoubert.game.presentation.screen.StartScreen
import cam.scarlettjoubert.game.presentation.viewmodel.GameViewModel
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Texture.TextureFilter.Linear
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile
import ktx.async.KtxAsync
import ktx.graphics.use

class Main : KtxGame<KtxScreen>() {
    override fun create() {
        val viewModel = GameViewModel()
        addScreen(StartScreen(this, viewModel))
        addScreen(GameScreen(viewModel))
        setScreen<StartScreen>()
    }
}

