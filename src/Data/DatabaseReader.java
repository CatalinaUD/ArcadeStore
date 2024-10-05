package Data;

import arcadestore.models.ArcadeEnums;
import arcadestore.models.ArcadeEnums.Game_Category;
import arcadestore.models.ArcadeEnums.Game_Type;
import arcadestore.models.Game;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Catalina
 */
public class DatabaseReader {
    private List<Game> games;
    public DatabaseReader(){};
    
    public List<Game> getGames() {
        File file = new File("Games.txt");
        Scanner sc;
        games = new ArrayList<>();
        try {
            sc = new Scanner(file);
            while(sc.hasNextLine()) {   
                games.add(getGame(sc.nextLine()));
            }            
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }        
        return games;
    }
    
    private Game getGame(String line) {
        String[] details = line.split(";");
        Game game = new Game();
        game.setName(details[0]);
        game.setCategory((Game_Category)ArcadeEnums.getEnumFromValue(Game_Category.values(), details[1]));
        game.setType((Game_Type)ArcadeEnums.getEnumFromValue(Game_Type.values(), details[2]));
        game.setMultiplayer(Boolean.parseBoolean(details[3]));
        game.setNbCoins(Integer.parseInt(details[4]));
        game.setPrice(Integer.parseInt(details[5]));
        game.setYear(Integer.parseInt(details[6]));
        game.setStoryTellingCreator(details[7]);
        game.setGraphicsCreator(details[8]);
        
        return game;
    }
}
