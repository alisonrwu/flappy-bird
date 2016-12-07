package com.alison.flappy.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Alison on 2016-12-05.
 */
public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Texture birds;
//    private Texture bird;
    private Sound flap; //sound is load/run from RAM

    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
//        bird = new Texture("bird.png");
        birds = new Texture("birdanimation.png"); //TODO: should be able to pass Texture to Animation, NOT TextureRegion
        birdAnimation = new Animation(new TextureRegion(birds), 3, 0.5f);
        bounds = new Rectangle(x, y, birds.getWidth()/3, birds.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float dt){
        birdAnimation.update(dt);
        if(position.y > 0)
            velocity.add(0, GRAVITY, 0);
        velocity.scl(dt); //multiply everything by delta time
        position.add(MOVEMENT*dt, velocity.y, 0);
        if(position.y < 0)
            position.y = 0;

        velocity.scl(1/dt); //turn back scale
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() { //TODO: change back to Texture???
//        return bird;
        return birdAnimation.getFrame();
    }

    public void jump(){
        velocity.y = 250;
        flap.play(0.5f); //half volume sound
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
//        bird.dispose();
        birds.dispose();
        flap.dispose();
    }
}
