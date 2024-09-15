package ru.mipt.bit.platformer.util.leveling;

import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;

public class FileReador {

    public static List<GridPoint2> calculateTreeCoordinates(List<String> lines){
        List<GridPoint2> treeCoordinates = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (lines.get(i).charAt(j) == 'T'){
                    treeCoordinates.add(new GridPoint2(j, i));
                }
            }
        }

        return treeCoordinates;
    }

    public static List<GridPoint2> calculateTankCoordinates(List<String> lines){
        List<GridPoint2> tankCoordinates = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (lines.get(i).charAt(j) == 'X'){
                    tankCoordinates.add(new GridPoint2(j, i));
                }
            }
        }

        return tankCoordinates;
    }

}
