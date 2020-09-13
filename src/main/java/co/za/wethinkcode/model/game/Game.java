package co.za.wethinkcode.model.game;

import co.za.wethinkcode.controller.MapController;
import co.za.wethinkcode.model.characters.factories.HeroesFactory;
import co.za.wethinkcode.model.characters.heroes.AboutHero;
import co.za.wethinkcode.model.characters.heroes.HeroName;
import co.za.wethinkcode.model.characters.heroes.Stats;

public class Game {
    private HeroesFactory hero;

    public HeroesFactory storePlayer(String[] hero){
        HeroName heroName = new HeroName();
        AboutHero aboutHero = new AboutHero();
        Stats heroStats = new Stats();
        this.hero = new HeroesFactory.Build().heroName(heroName).aboutHero(aboutHero).artifacts(heroStats).build();

        this.hero.getHeroName().setName(hero[0]);
        this.hero.getHeroName().setType(hero[1]);
        this.hero.getAboutHero().setLevel(Integer.parseInt(hero[2]));
        this.hero.getAboutHero().setHitPoints(Integer.parseInt(hero[3]));
        this.hero.getAboutHero().setExperience(Integer.parseInt(hero[4]));
        this.hero.getAboutHero().setDefence(Integer.parseInt(hero[5]));
        this.hero.getAboutHero().setAttack(Integer.parseInt(hero[6]));
        this.hero.getStats().setWeapon(hero[7]);
        this.hero.getStats().setArmor(hero[8]);
        this.hero.getStats().setHelm(hero[9]);
        return this.hero;
    }

    public void movePlayer(MapController mapController, AbGame consoleGame, String direction){
        int currentRow = mapController.playerPosition.getPlayerRow();
        int currentCol = mapController.playerPosition.getPlayerColumn();

        consoleGame.setPlayerRow(currentRow);
        consoleGame.setPlayerCol(currentCol);

        switch (direction) {
            case "up":
                if (currentRow == 0)
                    break;
                mapController.playerPosition.setPlayerRow(currentRow-1);
                consoleGame.setPlayerEnemyRow(mapController.playerPosition.getPlayerRow());
                consoleGame.setPlayerEnemyCol(mapController.playerPosition.getPlayerColumn());
                break;
            case "down":

                if (mapController.initMap.getRows() == currentRow +1)
                    break;
                mapController.playerPosition.setPlayerRow(currentRow+1);
                consoleGame.setPlayerEnemyRow(mapController.playerPosition.getPlayerRow());
                consoleGame.setPlayerEnemyCol(mapController.playerPosition.getPlayerColumn());
                break;
            case "left":
                if (currentCol == 0)
                    break;
                mapController.playerPosition.setPlayerColumn(currentCol-1);
                consoleGame.setPlayerEnemyRow(mapController.playerPosition.getPlayerRow());
                consoleGame.setPlayerEnemyCol(mapController.playerPosition.getPlayerColumn());
                break;
            case "right":
                if (mapController.initMap.getRows() == currentCol+1)
                    break;
                mapController.playerPosition.setPlayerColumn(currentCol+1);
                consoleGame.setPlayerEnemyRow(mapController.playerPosition.getPlayerRow());
                consoleGame.setPlayerEnemyCol(mapController.playerPosition.getPlayerColumn());
                break;
            default: break;
        }
    }
}
