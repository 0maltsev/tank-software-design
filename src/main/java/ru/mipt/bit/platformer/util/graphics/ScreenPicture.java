package ru.mipt.bit.platformer.util.graphics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.util.objects.Player;
import ru.mipt.bit.platformer.util.objects.Tree;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class ScreenPicture {
    public static void clearScreen(){
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    public static void draw(Batch batch, Player player, Tree tree) {
        batch.begin();
        drawTextureRegionUnscaled(batch, player.getTexture().getGraphics(), player.getTexture().getRectangle(), player.getRotation());
        drawTextureRegionUnscaled(batch, tree.getGraphics(), tree.getRectangle(), 0f);

        batch.end();
    }
}
