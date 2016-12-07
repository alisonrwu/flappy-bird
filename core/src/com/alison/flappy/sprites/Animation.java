package com.alison.flappy.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Alison on 2016-12-06.
 */
public class Animation {
    private Array<TextureRegion> frames; //stores all frames
    private float maxFrameTime; //how long frame stays in view
    private float currentFrameTime;
    private int frameCount; //# frames in animation
    private int frame; //current frame

    public Animation(TextureRegion region, int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for(int i=0; i<frameCount; i++){
            frames.add(new TextureRegion(region, i*frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float dt){
        currentFrameTime += dt;
        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount)
            frame = 0;
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}