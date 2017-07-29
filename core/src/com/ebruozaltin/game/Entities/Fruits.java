package com.ebruozaltin.game.Entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ebruozaltin.game.Utilities.Constants;

import java.util.Random;

/**
 * Created by uguresin on 17.06.2017.
 */

public class Fruits {
    public static final String TAG = Fruits.class.getName();

    public int fruitsDodged;
    DelayedRemovalArray<Fruit> fruitList;
    Viewport viewport;


    TextureRegion textureRegion;

    public Fruits(Viewport viewport, TextureRegion region){
        this.viewport = viewport;
        init(region);
    }

    public void init(TextureRegion region){
        fruitList = new DelayedRemovalArray<Fruit>(false, Constants.INITIAL_FRUITS_ARRAY_CAPACITY);
        fruitsDodged = 0;
        textureRegion=region;            float minX = 0.0f;
        float maxX = 3.5f;
        Random rand = new Random(System.nanoTime());
        for (int i=0; i<4; i++) {
            Vector2 newFruitPosition = new Vector2(viewport.getWorldWidth() * Constants.FRUIT_COORDINATES[i], viewport.getWorldHeight());

            float finalX = rand.nextFloat() * (maxX - minX) + minX;
            Fruit newFruit = new Fruit(newFruitPosition, region, i, finalX);
            fruitList.add(newFruit);
        }

    }

    public void update(float delta, TextureRegion region){

        //TODO: update çağrıldığı yeri değiştirmeli (Less significant)

        for (Fruit fruit : fruitList){
            fruit.update(delta);
        }

        //TODO: Fruitlerin üzerine sayı yazılsın. >Biri doğru cevap, diğerleri ise random


        fruitList.begin();
        for(int i = 0; i < fruitList.size; i++){
            if ( fruitList.get(i).position.y < -Constants.FRUITS_HEIGHT){
                fruitsDodged +=1;
                fruitList.removeIndex(i);
            }
        }
        fruitList.end();
    }

    public void render(SpriteBatch batch, TextureRegion region){
        //TODO: Fruitlerin hepsi aynı zamanda oluşmasın, birbirlerine yakın zamanlarda rastgele sırayla oluşsun.
        for(Fruit fruit : fruitList){
            fruit.render(batch,region);
        }
    }

    public DelayedRemovalArray<Fruit> getFruitList() {
        return fruitList;
    }
}
