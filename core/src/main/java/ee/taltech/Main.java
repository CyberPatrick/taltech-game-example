package ee.taltech;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import ee.taltech.entities.Door;
import ee.taltech.entities.Player;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;

    private Player player;
    private Door door;

    private BitmapFont usageLabel;
    private final String usageText = "Vajuta 'E', et uks avada";
    private GlyphLayout layout;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        player = new Player(100, 100, "player_texture.png", 250, 250, 110, 240);
        door = new Door(400, 100, "door_opened.jpg", "door_closed.jpg", 250, 250, 250, 250);
        usageLabel = new BitmapFont();
        layout = new GlyphLayout();
        layout.setText(usageLabel, usageText);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClearColor(0, 0, 0, 1); // Set background to black

        batch.begin();
        player.update();
        batch.draw(image, 140, 210);

        if (player.getBounds().overlaps(door.getBounds()) && !door.isOpen()) {
            usageLabel.draw(batch, usageText, (Gdx.graphics.getWidth() - layout.width) / 2, 20);

            if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                door.setOpen(true);
            }
        }

        door.render(batch);
        player.render(batch);
        batch.end();

        door.debugRender();
        player.debugRender();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        player.dispose();
        usageLabel.dispose();
    }
}
