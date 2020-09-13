package co.za.wethinkcode.model.game;

import co.za.wethinkcode.model.characters.factories.EnemiesFactory;
import co.za.wethinkcode.model.characters.factories.HeroesFactory;

import javax.validation.Validator;
import java.util.ArrayList;

public abstract class AbGame {
    protected static ArrayList<String> battleSimulation = new ArrayList<String>();
    protected int playerRow;
    protected int playerCol;
    public boolean droppedArtifact = false;
    protected EnemiesFactory theVillain;
    public boolean newLevel =  false;
    /*
     * This will keep track of the enemy location*/
    protected int playerEnemyRow;
    protected int playerEnemyCol;

    public int getPlayerRow() {
        return playerRow;
    }

    public void setPlayerRow(int playerRow) {
        this.playerRow = playerRow;
    }

    public int getPlayerCol() {
        return playerCol;
    }

    public void setPlayerCol(int playerCol) {
        this.playerCol = playerCol;
    }

    public int getPlayerEnemyRow() {
        return playerEnemyRow;
    }

    public void setPlayerEnemyRow(int playerEnemyRow) {
        this.playerEnemyRow = playerEnemyRow;
    }

    public int getPlayerEnemyCol() {
        return playerEnemyCol;
    }

    public void setPlayerEnemyCol(int playerEnemyCol) {
        this.playerEnemyCol = playerEnemyCol;
    }

    protected void increaseAttack(HeroesFactory hero) {
        hero.getAboutHero().setAttack(hero.getAboutHero().getAttack() + hero.getWeapons().get(theVillain.getArtifacts().getWeapon())-2);
        hero.getStats().setWeapon(theVillain.getArtifacts().getWeapon());
    }

    protected void increaseDefence(HeroesFactory hero) {
        int defence= theVillain.getAboutEnemy().getDefence();
        if (defence <= 70 && defence >= 59)
            hero.getAboutHero().setDefence(hero.getAboutHero().getDefence() + 3);
        else  if (defence <= 80 && defence > 70)
            hero.getAboutHero().setDefence(hero.getAboutHero().getDefence() + 5);
        else
            hero.getAboutHero().setDefence(hero.getAboutHero().getDefence() + 7);

        hero.getStats().setArmor(theVillain.getArtifacts().getArmor());
    }

    protected void increaseHP(HeroesFactory hero) {
        int HP = theVillain.getAboutEnemy().getHitPoints();
        if (HP <= 45)
            hero.getAboutHero().setHitPoints(hero.getAboutHero().getHitPoints() + 2);
        else if (HP > 45)
            hero.getAboutHero().setHitPoints(hero.getAboutHero().getHitPoints() + 4);

        hero.getStats().setHelm(theVillain.getArtifacts().getHelm());
    }

    protected abstract void levelUp();
}
