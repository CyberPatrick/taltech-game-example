package ee.taltech.entities;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Door extends Entity {
    private boolean isOpen = false;
    private Texture textureOpened;

    public Door(float x, float y, String texturePathOpened, String texturePathClosed, float width, float height, float collisionWidth, float collisionHeight) {
        super(x, y, texturePathClosed, width, height, collisionWidth, collisionHeight);

        Texture texture = new Texture(texturePathOpened);
        // Resize texture to passed width and height
        texture.getTextureData().prepare();
        Pixmap original = texture.getTextureData().consumePixmap();
        Pixmap resized = new Pixmap((int) width, (int) height, original.getFormat());
        resized.drawPixmap(original, 0, 0, original.getWidth(), original.getHeight(),
            0, 0, (int) width, (int) height);
        this.textureOpened = new Texture(resized);
        this.textureOpened.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    public void dispose() {
        super.dispose();
        textureOpened.dispose();
    }

    @Override
    public void render(SpriteBatch batch) {
        if (isOpen) {
            batch.draw(textureOpened, position.x, position.y, textureOpened.getWidth(), textureOpened.getHeight());
        } else {
            super.render(batch);
        }
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
