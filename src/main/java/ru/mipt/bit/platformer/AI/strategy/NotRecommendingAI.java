package ru.mipt.bit.platformer.AI.strategy;

import ru.mipt.bit.platformer.AI.AI;
import ru.mipt.bit.platformer.AI.Recommendation;
import ru.mipt.bit.platformer.AI.state.GameState;

import java.util.Collections;
import java.util.List;

public class NotRecommendingAI implements AI {
    @Override
    public List<Recommendation> recommend(GameState gameState) {
        return Collections.emptyList();
    }
}
