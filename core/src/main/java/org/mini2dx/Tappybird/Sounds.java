package org.mini2dx.Tappybird;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.Random;

public class Sounds{

    private static final String ENGINE_SOUND_LOCATION = "Sounds/engine4.ogg";
    private static final String PILLAR_PASS_SOUND_LOCATION = "Sounds/coin1.ogg";
    private static final String EXPLOSION_1_SOUND_LOCATION = "Sounds/explosion1.ogg";
    private static final String EXPLOSION_2_SOUND_LOCATION = "Sounds/explosion2.ogg";
    private static final String EXPLOSION_3_SOUND_LOCATION = "Sounds/explosion3.ogg";
    private static final String EXPLOSION_4_SOUND_LOCATION = "Sounds/explosion4.ogg";
    private static final String EXPLOSION_5_SOUND_LOCATION = "Sounds/explosion5.ogg";
    private static final String BACKGROUND_MUSIC_LOCATION = "Sounds/Cheerful Annoyance_v2.wav";


    Sound engineSound = Gdx.audio.newSound(Gdx.files.internal(ENGINE_SOUND_LOCATION));
    static Sound pillarPassSound = Gdx.audio.newSound(Gdx.files.internal(PILLAR_PASS_SOUND_LOCATION));

    Music backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(BACKGROUND_MUSIC_LOCATION));

    Sound[] explosionSounds = {Gdx.audio.newSound(Gdx.files.internal(EXPLOSION_1_SOUND_LOCATION)),
                                Gdx.audio.newSound(Gdx.files.internal(EXPLOSION_2_SOUND_LOCATION)),
                                Gdx.audio.newSound(Gdx.files.internal(EXPLOSION_3_SOUND_LOCATION)),
                                Gdx.audio.newSound(Gdx.files.internal(EXPLOSION_4_SOUND_LOCATION)),
                                Gdx.audio.newSound(Gdx.files.internal(EXPLOSION_5_SOUND_LOCATION))};

    long engineSoundID, backgroundSoundID;
    static long PILLAR_PASS_SOUND_ID;

    void loopEngineSound(){
        engineSoundID = engineSound.loop(0.25f);
    }

    void loopBackgroundMusic(){
        backgroundMusic.setVolume(0.1f);
        if(!backgroundMusic.isLooping()){
            backgroundMusic.play();
        }
        backgroundMusic.setLooping(true);
    }

    void disposeEngineSound(){
        engineSound.dispose();
    }

    static void playPillarPassSound(){
        PILLAR_PASS_SOUND_ID = pillarPassSound.play(1f);
        pillarPassSound.dispose();
    }

    void disposeBackgroundMusic(){
        backgroundMusic.dispose();
    }

    void playRandomExplosionSound(){
        int index = new Random().nextInt(4);
        explosionSounds[index].play(0.5f);
    }
}
