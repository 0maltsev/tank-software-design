package ru.mipt.bit.platformer.util.leveling;

import com.badlogic.gdx.math.GridPoint2;

import java.io.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LevelCreator {
    private List<GridPoint2> tankCoordinates;
    private List<GridPoint2> treeCoordinates;

    public LevelCreator(){
        tankCoordinates = new ArrayList<>();
        treeCoordinates = new ArrayList<>();
    }

    public void generateLevelFromFile(String pathToFile){
        try {
            File file = new File(pathToFile);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            List<String> lines = new ArrayList<>();
            String oneLine = reader.readLine();
            char[] strToChar;

            while (oneLine != null) {
                lines.add(0, oneLine);
                oneLine = reader.readLine();
            }

            FileReader FileParser = null;
            tankCoordinates.addAll(FileReador.calculateTankCoordinates(lines));
            treeCoordinates.addAll(FileReador.calculateTreeCoordinates(lines));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<GridPoint2> getTankCoordinates() {
        return tankCoordinates;
    }

    public List<GridPoint2> getTreeCoordinates() {
        return treeCoordinates;
    }
}
