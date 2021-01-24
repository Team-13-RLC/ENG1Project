package com.dragonboatrace.tools;

import com.dragonboatrace.entities.boats.Boat;

/**
 * Functional interface, used to name the type of the stored lambda expressions,
 * which dictate what the collidable does upon collision.
 * This is very similar to built in Consumer&lt;T> interface.
 * However it does not allow the creation of a lambda taking any other parameter type.
 * It is also more clearly named.
 */
public interface CollidableEffect {
    void invoke(Boat boat);
}
