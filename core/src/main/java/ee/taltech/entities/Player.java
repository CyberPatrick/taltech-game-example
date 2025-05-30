package ee.taltech.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Entity {
    private float speed = 200f;

    public Player(float x, float y, String texturePath, float width, float height, float collisionWidth, float collisionHeight) {
        super(x, y, texturePath, width, height, collisionWidth, collisionHeight);
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
    }

    public void update() {
        handleInput();
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            float changeX = speed * Gdx.graphics.getDeltaTime();
            getPosition().x -= changeX;
            bounds.x -= changeX;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            float changeX = speed * Gdx.graphics.getDeltaTime();
            getPosition().x += changeX;
            bounds.x += changeX;
        }
    }
}
