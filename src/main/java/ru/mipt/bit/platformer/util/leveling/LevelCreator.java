package ru.mipt.bit.platformer.util.leveling;

import com.badlogic.gdx.math.GridPoint2;

import java.io.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class LevelCreator {
    private List<GridPoint2> tankCoordinates;
    private List<GridPoint2> treeCoordinates;

    private int screenSide1;
    private int screenSide2;

    public LevelCreator(){
        tankCoordinates = new ArrayList<>();
        treeCoordinates = new ArrayList<>();
        screenSide1 = 0;
        screenSide2 = 0;
    }

    public void generateLevelFromFile(String pathToFile){
        try {
            fieldSize("src/main/resources/fieldSize");
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
    public int getSide1() {
        return screenSide1;
    }

    public int getSide2() {
        return screenSide2;
    }

    private void fieldSize(String filePath){
        try {

            FileReader fr = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();
            String[] props;

            if (line != null) {
                props = line.split(" ");
                screenSide1 = Integer.parseInt(props[0]);
                screenSide2 = Integer.parseInt(props[1]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
