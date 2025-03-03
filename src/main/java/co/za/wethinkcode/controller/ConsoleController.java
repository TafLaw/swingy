package co.za.wethinkcode.controller;

import co.za.wethinkcode.model.characters.factories.EnemiesFactory;
import co.za.wethinkcode.model.characters.factories.HeroesFactory;
import co.za.wethinkcode.model.game.ConsoleGame;
import co.za.wethinkcode.view.ConsoleViews;
import co.za.wethinkcode.view.EnemyStatsView;
import co.za.wethinkcode.view.HeroStatsView;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleController {
    private ConsoleViews consoleViews;
    private HeroStatsView heroStatsView;
    private EnemyStatsView enemyStatsView;
    private ConsoleGame consoleGameObj;
    private Scanner scanner;
    private String option;

    public ConsoleController(ConsoleGame consoleGameObj) {
        consoleViews = new ConsoleViews();
        heroStatsView = new HeroStatsView();
        enemyStatsView = new EnemyStatsView();
        this.consoleGameObj = consoleGameObj;
        this.scanner = this.consoleGameObj.scanner;
    }

    public void startScreen(){
        consoleViews.consoleEntry();
    }

    public void selectHeroType(){
        consoleViews.consoleHeroType();
    }

    public void heroName(){
        consoleViews.consoleHeroName();
    }

    public void myHeroStats(HeroesFactory hero){
        heroStatsView.myHeroStats(hero);
    }

    public void availableHeroes(ArrayList<String> allHeroes){
        consoleViews.availableHeroes(allHeroes);
    }

    public void enemyStats(EnemiesFactory enemy) {
        enemyStatsView.enemyStats(enemy);
    }

    public void noHeroesAvailable(){
        consoleViews.noHeroes();
    }

    public void gameOver(ArrayList<String> results) {
        consoleViews.gameOver(results);
        option = scanner.nextLine();
    }

    public void wonBattle(ArrayList<String> results) {
        int check = 0;

        consoleViews.wonBattle(results);
        if (consoleGameObj.droppedArtifact){
            do {
                try {
                    consoleViews.takeArtifact();
                    option = scanner.nextLine();
                    check = Integer.parseInt(option);

                } catch (NumberFormatException e) {
                    check = -1;
                }
            }while (check < 1 && check > 2);
            if (check == 1)
                consoleGameObj.pickUpWeapon();
        }
        else{
            consoleViews.enter();
            option = scanner.nextLine();
        }

    }

    public void cantLevelUp() {
        consoleViews.cantLevelUp();
        option = scanner.nextLine();
        consoleGameObj.saveAndExit();
    }
}
