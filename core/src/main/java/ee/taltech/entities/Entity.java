package ee.taltech.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    private Texture texture;
    protected Vector2 position;
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();

    protected Rectangle bounds;

    public Entity(float x, float y, String texturePath, float width, float height, float collisionWidth, float collisionHeight) {
        Texture originalTexture = new Texture(texturePath);
        // Resize texture to passed width and height
        originalTexture.getTextureData().prepare();
        Pixmap original = originalTexture.getTextureData().consumePixmap();
        Pixmap resized = new Pixmap((int) width, (int) height, original.getFormat());
        resized.drawPixmap(original, 0, 0, original.getWidth(), original.getHeight(),
            0, 0, (int) width, (int) height);
        this.texture = new Texture(resized);
        this.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        original.dispose();
        resized.dispose();
        originalTexture.dispose();

        this.position = new Vector2(x, y);
        this.bounds = new Rectangle(x + (this.texture.getWidth() - collisionWidth) / 2, y, collisionWidth, collisionHeight);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, texture.getWidth(), texture.getHeight());
    }

    public void debugRender() {
        Gdx.gl.glLineWidth(4);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
        shapeRenderer.end();
    }

    public void dispose() {
        texture.dispose();
        shapeRenderer.dispose();
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
