package org.mini2dx.Tappybird;

import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.viewport.FitViewport;
import org.mini2Dx.core.graphics.viewport.Viewport;

import java.util.Random;


public class TappyBirdGame extends BasicGame {
    public static final String GAME_IDENTIFIER = "org.mini2Dx.TappyBird";

    public static float GRAVITY;
    public static float GAME_GRAVITY = 0.5f;
    public static float flyingspeed;
    public static final float gameWidth = 800;
    public static final float gameHeight = 500;

    private static int MAX_PILLARS = 5;
    private static int PILLAR_TIMING = 60;
    private static float PILLAR_Y_MIN = -150f;
    private static float PILLAR_Y_MAX = 0f;
    private static float PILLAR_GAP_MIN = 150f;
    private static float PILLAR_GAP_MAX = 225f;

    private boolean inGame;

    Viewport fitViewport;
    InputHandler inputHandler;
    Player player;
    TopBottomEdge ground1, ground2;
    Background background1, background2;
    Pillars[] pillars;
    PillarModel pillarModel;
    BackgroundModel backgroundModel;
    TopBottomEdgeModel topBottomEdgeModel;
    PlayerModel playerModel;

    CollisionBox[] collisionRectanglesBottom, collisionRectanglesTop;

    int pillarIndexHead, pillarIndexTail;
    int pillarTiming;

    @Override
    public void initialise() {
        GRAVITY = 0.0f;
        flyingspeed = 10f;
        inGame = false;

        pillars = new Pillars[MAX_PILLARS];
        collisionRectanglesBottom = new CollisionBox[MAX_PILLARS];
        collisionRectanglesTop = new CollisionBox[MAX_PILLARS];

        playerModel = new PlayerModel();
        backgroundModel = new BackgroundModel();
        pillarModel = new PillarModel();
        topBottomEdgeModel = new TopBottomEdgeModel();

        inputHandler = new InputHandler();
        player = new Player(playerModel);
        ground1 = new TopBottomEdge(topBottomEdgeModel);
        ground2 = new TopBottomEdge(topBottomEdgeModel);
        ground2.generateHazardAtPos(gameWidth, gameHeight - ground1.getGroundTextureHeight());
        background1 = new Background(backgroundModel);
        background2 = new Background(backgroundModel);
        background2.generateHazardAtPos(gameWidth, 0.0f);
        fitViewport = new FitViewport(gameWidth, gameHeight);

        pillarTiming = 60;
    }

    @Override
    public void update(float delta) {

        pillarTiming -= new Random().nextInt(2) + 1;

        if (inputHandler.spacePressed()) {
            GRAVITY = GAME_GRAVITY;
            inGame = true;
        }


        if (inGame && pillarTiming < 0) {
            pillarTiming = PILLAR_TIMING;
            pillars[pillarIndexTail] = new Pillars(pillarModel);
            pillars[pillarIndexTail].generateHazardAtPos(gameWidth + 100f,
                    randomFloatMinMax(PILLAR_Y_MIN, PILLAR_Y_MAX), randomFloatMinMax(PILLAR_GAP_MIN, PILLAR_GAP_MAX));
            collisionRectanglesBottom[pillarIndexTail] = pillars[pillarIndexTail].collisionRectBottom;
            collisionRectanglesTop[pillarIndexTail] = pillars[pillarIndexTail].collisionRectTop;
            pillarIndexTail = (pillarIndexTail + 1) % MAX_PILLARS;
        }

        for (int i = 0; i < MAX_PILLARS; i++) {
            if (pillars[i] != null) {
                pillars[i].update();
                if (pillars[pillarIndexHead] != null && pillars[pillarIndexHead].getHazardXPos() < -100) {
                    pillars[pillarIndexHead] = null;
                    collisionRectanglesBottom[pillarIndexHead] = null;
                    collisionRectanglesTop[pillarIndexHead] = null;
                    pillarIndexHead = (pillarIndexHead + 1) % MAX_PILLARS;
                }
            }
        }


        player.update(inputHandler.spacePressed());
        ground1.update();
        ground2.update();
        background1.update();
        background2.update();

        if (player.getPlayerY() < 0 || player.getPlayerY() > gameHeight - player.getPlayerTextureHeight()) {
            initialise();
        }

        checkCollisions();

        //TODO Code here to exit the game.
        if (inputHandler.escPressed()) {

        }
    }

    @Override
    public void interpolate(float alpha) {
        ground1.interpolate(alpha);
        ground2.interpolate(alpha);
    }

    @Override
    public void render(Graphics g) {
        fitViewport.apply(g);
        background1.render(g);
        background2.render(g);
        player.render(g);


        for (int i = 0; i < MAX_PILLARS; i++) {
            if (pillars[i] != null) {
                pillars[i].render(g);
            }
        }

        ground1.render(g);
        ground2.render(g);
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
                    initialise();
                }
            }
            if (collisionRectanglesTop[i] != null) {
                if (player.playerCollisionBox.intersects(collisionRectanglesTop[i])) {
                    initialise();
                }
            }
        }
    }
}
