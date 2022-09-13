package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
public class PhysBody {
    public Vector2 position;
    public Vector2 size;
    public String name;

    public PhysBody(String name, Vector2 position, Vector2 size) {
        this.position = position;
        this.size = size;
        this.name = name;
    }
}
