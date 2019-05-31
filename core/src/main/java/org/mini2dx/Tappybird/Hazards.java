package org.mini2dx.Tappybird;

import org.mini2Dx.core.engine.geom.CollisionPoint;

import static org.mini2dx.Tappybird.TappyBirdGame.flyingspeed;

public class Hazards {

    CollisionPoint point = new CollisionPoint();

    void generateHazardAtPos(float xPos, float yPos) {
        point.set(xPos, yPos);
    }

    void update() {
        point.preUpdate();
        point.set(point.getX() - flyingspeed, point.getY());
    }

    void interpolate(float alpha) {
        point.interpolate(null, alpha);
    }


    public float getHazardXPos() {
        return point.getX();
    }
}
