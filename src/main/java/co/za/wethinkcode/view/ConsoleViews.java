package co.za.wethinkcode.view;

import java.util.ArrayList;
import java.util.List;

public class ConsoleViews {
    public void consoleEntry(){
        System.out.println("\n________________GAME PLAY_________________\n\n\n");
        System.out.println("What would you like to do?\n");

        System.out.println("1. Create New Hero");
        System.out.println("2. Select Existing Hero");
        System.out.println("3. Exit\n");
    }

    public void consoleHeroType(){
        System.out.println("\n________________GAME PLAY_________________\n\n\n");
        System.out.println("Please select your hero type\n");
        System.out.println("1. Ninja");
        System.out.println("2. Samurai");
        System.out.println("3. Shinobi");
        System.out.println("4. Ronin\n");
        System.out.println("Input 0 to go back\n");
    }

    public void consoleHeroName(){
        System.out.println("Give your Hero a name\n");
    }

    public void availableHeroes(ArrayList<String> allHeroes) {
        int count = 0;
        String[] hero;
        System.out.println("\n________________AVAILABLE HEROES_________________\n\n\n");
        while (count < allHeroes.size()) {
            hero = allHeroes.get(count++).split(",");
            System.out.println(count + ". __" + hero[0] + "__");
            System.out.println("Type : " + hero[1] + " | Level : " + hero[2] + " | HP : " + hero[3] + " | Experience : " + hero[4]);
            System.out.println("Defence : " + hero[5] + " | Attack : " + hero[6] + " | Armor : " + hero[7] + " | Weapon : " + hero[8] + "\n");
        }
        System.out.println("\n====================================================");
        System.out.print("\nEnter your Hero of choice's number or 0 to go back : ");
    }

    public void noHeroes() {
        System.out.println("No Heroes available\n\n");
        System.out.println("Press any key to go back!");
    }

    public void gameOver(ArrayList<String> results) {
        for (String line:results) {
            System.out.print(line);
        }
        System.out.println("\n\n GAME OVER\n\n");
        System.out.println("Press enter key to go to the main menu");
    }

    public void wonBattle(ArrayList<String> results) {
        for (String line:results) {
            System.out.print(line);
        }
        System.out.println("\n\nYou won the battle");
    }

    public void takeArtifact() {
        System.out.println("\nWhat would you like to do?\n");
        System.out.println("1. Pick up artifact");
        System.out.println("2. Leave");
    }

    public void enter(){
        System.out.println("Press enter to continue");
    }

    public void cantLevelUp() {
        System.out.println("\nYou won the Game but you do not have enough EXP to level up\n");
        System.out.println("Press Enter to save and quit");
    }
}
