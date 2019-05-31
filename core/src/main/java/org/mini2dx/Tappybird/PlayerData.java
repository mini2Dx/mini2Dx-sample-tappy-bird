package org.mini2dx.Tappybird;

import com.badlogic.gdx.utils.GdxRuntimeException;
import org.mini2Dx.core.Mdx;
import org.mini2Dx.core.playerdata.PlayerDataException;
import org.mini2Dx.core.serialization.annotation.Field;


public class PlayerData {
    @Field
    private int highScore;

    private static final String DATA_LOCATION = "playerdata.json";

    public int getHighScore(){
        return highScore;
    }

    public void setHighScore(int highScore){
        this.highScore = highScore;
    }

    public void savePlayerData(int highScore){
        try{
            PlayerData data = new PlayerData();
            data.setHighScore(highScore);
            Mdx.playerData.writeJson(data, DATA_LOCATION);
        } catch (PlayerDataException e) {
            System.out.println("Unable to save data.");
            e.printStackTrace();
        }
    }

    public void loadPlayerData(){
        try{
            PlayerData data = Mdx.playerData.readJson(PlayerData.class, DATA_LOCATION);
            setHighScore(data.getHighScore());
        } catch (PlayerDataException e) {
            setHighScore(0);
        } catch (GdxRuntimeException e) {
            setHighScore(0);
        }
    }
}
