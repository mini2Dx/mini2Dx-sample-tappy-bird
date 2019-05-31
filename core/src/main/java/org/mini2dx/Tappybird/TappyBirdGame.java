package org.mini2dx.Tappybird;

import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.viewport.FitViewport;
import org.mini2Dx.core.graphics.viewport.Viewport;

import java.util.Random;

import static org.mini2dx.Tappybird.Sounds.playPillarPassSound;


public class TappyBirdGame extends BasicGame {
    public static final String GAME_IDENTIFIER = "org.mini2Dx.TappyBird";

    public static float GRAVITY;
    public static float FLYING_SPEED;
    public static final float GAME_WIDTH = 800;
    public static final float GAME_HEIGHT = 500;

    //These variables change how it feels to play the game.
    private static float GAME_GRAVITY = 0.6f;
    private static float GAME_FLYING_SPEED = 8f;
    private static boolean IS_ROTATING = false;
    private static boolean IS_TESTING = false;
    private static int MAX_PILLARS = 8;
    private static int PILLAR_TIMING = 60;  //Lower will increase the frequency of pillars.
    private static float PILLAR_Y_MIN = -150f;
    private static float PILLAR_Y_MAX = 0f;
    private static float PILLAR_GAP_MIN = 175f;
    private static float PILLAR_GAP_MAX = 250f;

    //Game states and player score
    private boolean inGame, isDead;
    private static int playerScore, highScore;

    Viewport fitViewport;
    InputHandler inputHandler;
    Player player;
    PlayerTexture playerTexture;
    TopBottomEdge ground1, ground2;
    TopBottomEdgeTexture topBottomEdgeTexture;
    Background background1, background2;
    BackgroundTexture backgroundTexture;
    Pillars[] pillars;
    PillarTexture pillarTexture;
    UserInterface userInterface;
    UserInterfaceTexture userInterfaceTexture;
    PlayerData playerData;
    Sounds gameSounds;

    CollisionBox[] collisionRectanglesBottom, collisionRectanglesTop;

    int pillarIndexHead, pillarIndexTail;
    int pillarTiming;

    @Override
    public void initialise() {
        GRAVITY = 0.0f;
        FLYING_SPEED = GAME_FLYING_SPEED;
        inGame = false;
        isDead = false;
        playerScore = 0;

        pillars = new Pillars[MAX_PILLARS];
        collisionRectanglesBottom = new CollisionBox[MAX_PILLARS];
        collisionRectanglesTop = new CollisionBox[MAX_PILLARS];

        playerTexture = new PlayerTexture();
        backgroundTexture = new BackgroundTexture();
        pillarTexture = new PillarTexture();
        topBottomEdgeTexture = new TopBottomEdgeTexture();
        userInterfaceTexture = new UserInterfaceTexture();

        gameSounds = new Sounds();
        //gameSounds.loopEngineSound();
        gameSounds.loopBackgroundMusic();

        inputHandler = new InputHandler();
        player = new Player(playerTexture, IS_ROTATING, IS_TESTING);
        ground1 = new TopBottomEdge(topBottomEdgeTexture);
        ground2 = new TopBottomEdge(topBottomEdgeTexture);
        ground2.generateHazardAtPos(GAME_WIDTH, GAME_HEIGHT - ground1.getGroundTextureHeight());
        background1 = new Background(backgroundTexture);
        background2 = new Background(backgroundTexture);
        background2.generateHazardAtPos(GAME_WIDTH, 0.0f);
        fitViewport = new FitViewport(GAME_WIDTH, GAME_HEIGHT);
        userInterface = new UserInterface(userInterfaceTexture);

        pillarTiming = 60;

        playerData = new PlayerData();
        playerData.loadPlayerData();
        highScore = playerData.getHighScore();
    }

    @Override
    public void update(float delta) {

        if (isDead) {
            if (inputHandler.spacePressed()) {
                if (playerScore>highScore){
                    playerData.savePlayerData(playerScore);
                }
                gameSounds.disposeBackgroundMusic();
                initialise();
            }
        } else {
            pillarTiming -= new Random().nextInt(2) + 1;

            if (inputHandler.spacePressed()) {
                GRAVITY = GAME_GRAVITY;
                inGame = true;
            }


            if (inGame && pillarTiming < 0) {
                pillarTiming = PILLAR_TIMING;
                pillars[pillarIndexTail] = new Pillars(pillarTexture, IS_ROTATING);
                pillars[pillarIndexTail].generateHazardAtPos(GAME_WIDTH + 100f,
                        randomFloatMinMax(PILLAR_Y_MIN, PILLAR_Y_MAX), randomFloatMinMax(PILLAR_GAP_MIN, PILLAR_GAP_MAX));
                collisionRectanglesBottom[pillarIndexTail] = pillars[pillarIndexTail].collisionRectBottom;
                collisionRectanglesTop[pillarIndexTail] = pillars[pillarIndexTail].collisionRectTop;
                pillarIndexTail = (pillarIndexTail + 1) % MAX_PILLARS;
            }

            for (int i = 0; i < MAX_PILLARS; i++) {
                if (pillars[i] != null) {
                    pillars[i].update(FLYING_SPEED);
                    if (pillars[pillarIndexHead] != null && pillars[pillarIndexHead].getHazardXPos() < -100) {
                        pillars[pillarIndexHead] = null;
                        collisionRectanglesBottom[pillarIndexHead] = null;
                        collisionRectanglesTop[pillarIndexHead] = null;
                        pillarIndexHead = (pillarIndexHead + 1) % MAX_PILLARS;
                    }
                }
            }


            player.update(inputHandler.spacePressed(), delta);
            ground1.update();
            ground2.update();
            background1.update();
            background2.update();

            if (player.getPlayerY() < 0 || player.getPlayerY() > GAME_HEIGHT - player.getPlayerTextureHeight()) {
                setDead();
                //initialise();
            }

            checkCollisions();

            //TODO Code here to exit the game.
            if (inputHandler.escPressed()) {

            }
        }
    }

    @Override
    public void interpolate(float alpha) {
        ground1.interpolate(alpha);
        ground2.interpolate(alpha);
    }

    private float[] topCollisionVertices, bottomCollisionVertices;

    @Override
    public void render(Graphics g) {

        fitViewport.apply(g);
        background1.render(g);
        background2.render(g);
        player.render(g);

        for (int i = 0; i < MAX_PILLARS; i++) {
            if (pillars[i] != null) {
                pillars[i].render(g,IS_TESTING);
            }
        }

        ground1.render(g);
        ground2.render(g);

        userInterface.displayScore(g, playerScore);

        if (!inGame) {
            userInterface.displayGetReadyMessage(g);
            //userInterface.displayScore(g, highScore);
            userInterface.displayHighscore(g,highScore);
        }

        if (isDead) {
            userInterface.displayGameOverMessage(g);
        }

        //TODO Render code below here is for testing purposes only.
            /*
            g.setColor(Color.GREEN);
            for (int j = 0; j<MAX_PILLARS; j++){
                if(collisionRectanglesTop[j]!=null) {
                    topCollisionVertices = collisionRectanglesTop[j].getVertices();
                    bottomCollisionVertices = collisionRectanglesBottom[j].getVertices();
                    for (int i = 0; i < 7; i = i + 2) {
                        g.drawLineSegment(topCollisionVertices[i], topCollisionVertices[i + 1],
                                topCollisionVertices[(i + 2) % 8], topCollisionVertices[(i + 3) % 8]);
                        g.drawLineSegment(bottomCollisionVertices[i], bottomCollisionVertices[i + 1],
                                bottomCollisionVertices[(i + 2) % 8], bottomCollisionVertices[(i + 3) % 8]);
                    }
                }
            }
            */
    }

    float randomFloatMinMax(float min, float max) {
        float leftLimit = min;
        float rightLimit = max;
        return leftLimit + new Random().nextFloat() * (rightLimit - leftLimit);
    }

    void checkCollisions() {
        for (int i = 0; i < MAX_PILLARS; i++) {
            if (collisionRectanglesBottom[i] != null) {
                if (player.playerCollisionBox.intersects(collisionRectanglesBottom[i])) {
                    setDead();
                    //initialise();
                }
            }
            if (collisionRectanglesTop[i] != null) {
                if (player.playerCollisionBox.intersects(collisionRectanglesTop[i])) {
                    setDead();
                    //initialise();
                }
            }
        }
    }

    public static void setScore(int score) {
        playerScore = score;
        //playPillarPassSound();
    }

    public static int getScore() {
        return playerScore;
    }

    void setDead() {
        isDead = true;
        FLYING_SPEED = 0f;
        GRAVITY = 0f;
        gameSounds.disposeEngineSound();
        gameSounds.playRandomExplosionSound();
    }
}
