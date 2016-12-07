package com.alison.flappy.states;

import com.alison.flappy.FlappyBird;
import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input; //for button LEFT
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Alison on 2016-12-05.
 */
public class MenuState extends State{
    private Texture background;
    private Texture playBtn;

//    float playBtnX, playBtnY;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBird.WIDTH/2, FlappyBird.HEIGHT/2);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");

//        playBtnX = cam.position.x - playBtn.getWidth()/2;
//        playBtnY = cam.position.y;
//        System.out.println("playBtn drawn at (" + playBtnX +", "+ playBtnY+")");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){ //TODO: change to button later
            gsm.set(new PlayState(gsm));
//            dispose(); //already disposed when state is popped in GSM
        }

//        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
//            System.out.println("left button clicked at (" + Gdx.input.getX() +", "+ Gdx.input.getY()+")");
//
//            if(inRange(Gdx.input.getX(), playBtnX, playBtnX+playBtn.getWidth())
//                    && inRange(Gdx.input.getY(), FlappyBird.HEIGHT-playBtnY, FlappyBird.HEIGHT-playBtnY+playBtn.getHeight())) {
//                gsm.set(new PlayState(gsm));
//            }
//        }
    }

    @Override
    public void update(float dt) {
        handleInput(); //always check user input
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin(); //open 'box' to start drawing
//        sb.draw(background, 0, 0, FlappyBird.WIDTH, FlappyBird.HEIGHT);
        sb.draw(background, 0, 0); //allow android device to fill width/height
//        sb.draw(playBtn, (FlappyBird.WIDTH/2) - (playBtn.getWidth()/2), FlappyBird.HEIGHT/2);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth()/2, cam.position.y); //added projection matrix
        sb.end(); //close 'box'
    }

    @Override
    public void dispose(){
        background.dispose();
        playBtn.dispose();
        System.out.println("MenuState disposed");
    }

//    private boolean inRange(float input, float a, float b){
//        return input >= a && input <= b;
//    }
}
