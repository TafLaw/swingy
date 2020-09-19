package co.za.wethinkcode.view;

import co.za.wethinkcode.model.characters.factories.HeroesFactory;
import co.za.wethinkcode.model.maps.*;

public class MapView {
    public int section;
    public void viewMap(PlayerPosition playerPosition, InitMap initMap, HeroesFactory heroesFactory){
        String value;
        if (initMap.entry)
        {
            System.out.println("You need to fight some enemies to get enough experience to move to the next level!");
            initMap.entry = false;
        }
        if (initMap.metEnemy){
            initMap.metEnemy = false;
            this.section = 2;
            this.fightOrRun();

        }
        else {
            this.section = 1;
            System.out.println("\nEXP : " + heroesFactory.getAboutHero().getExperience() + " | Weapon : " + heroesFactory.getStats().getWeapon() + " | HP : " + heroesFactory.getAboutHero().getHitPoints() + " | Level : "+heroesFactory.getAboutHero().getLevel()+ "\n");
            for (int r = 0; r < initMap.getRows(); r++) {
                for (int c = 0; c < initMap.getColumns(); c++) {
                    if ((r == playerPosition.getPlayerRow()) && (c == playerPosition.getPlayerColumn())) {
                        System.out.print(initMap.getWholeMap()[r][c]);
                    } else {
                        value = initMap.getWholeMap()[r][c];
                        System.out.print(value);
                    }
                    System.out.print(" ");
                }
                System.out.print("\n");
            }
            this.controls();
        }
    }

    private void controls(){
        System.out.println("_____________________________________________________________\n");
        System.out.println("1. Move Up");
        System.out.println("2. Move Down");
        System.out.println("3. Move Left");
        System.out.println("4. Move Right\n");
        System.out.println("\n0. Save and Exit");
    }

    private void fightOrRun(){

    }

}
