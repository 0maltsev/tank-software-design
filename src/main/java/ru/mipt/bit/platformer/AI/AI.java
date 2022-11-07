package ru.mipt.bit.platformer.AI;

import ru.mipt.bit.platformer.AI.Recommendation;
import ru.mipt.bit.platformer.AI.state.GameState;

import java.util.List;

public interface AI {
    List<Recommendation> recommend(GameState gameState);
}
