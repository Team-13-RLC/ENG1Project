package com.dragonboatrace.tools;

import com.dragonboatrace.entities.boats.Boat;

/**
 * Functional interface, used to name the type of the stored lambda expressions,
 * which dictate what the powerup does.
 * This is very similar to built in Consumer&lt;T> interface.
 * However it avoids the need to pass the type to it. It is also more clearly named.
 */
public interface CollidableEffect {
    void invoke(Boat boat);
}
