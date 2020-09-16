package co.za.wethinkcode.model.game;

import co.za.wethinkcode.controller.MapController;
import co.za.wethinkcode.model.characters.factories.EnemiesFactory;
import co.za.wethinkcode.model.characters.factories.HeroesFactory;
import co.za.wethinkcode.model.characters.heroes.CreateHero;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Random;

public abstract class AbGame {
    protected static ArrayList<String> battleSimulation = new ArrayList<String>();
    protected int playerRow;
    protected int playerCol;
    public boolean droppedArtifact = false;
    protected EnemiesFactory theVillain;
    protected CreateHero createHero;
    protected HeroesFactory hero;
    protected int heroIndex;
    protected int heroInitialHP;
    public boolean newLevel =  false;
    private int gainedHP = 0;
    private int chance;
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
        if (HP <= 45) {
            hero.getAboutHero().setHitPoints(hero.getAboutHero().getHitPoints() + 2);
            gainedHP += 2;
        }
        else{
            hero.getAboutHero().setHitPoints(hero.getAboutHero().getHitPoints() + 4);
            gainedHP += 4;
        }

        hero.getStats().setHelm(theVillain.getArtifacts().getHelm());
    }

    protected abstract void levelUp();

    public void saveAndExit() {
        hero.getAboutHero().setHitPoints(heroInitialHP + gainedHP);
        createHero.saveAndExit(hero, heroIndex);
    }

    protected String runAway(MapController mapController){
        Random random = new Random();
        int decider = random.nextInt(101);
        if (decider >= 0 && decider <= 50)
        {
            //this.fightEnemy(enemiesFactory);
            return "fight";
        }
        else {
            mapController.playerPosition.setPlayerRow(this.playerRow);
            mapController.playerPosition.setPlayerColumn(this.playerCol);
            mapController.resetEnemy(this.playerEnemyRow, this.playerEnemyCol);
            return "run";
        }
    }

    protected String fightEnemy(EnemiesFactory enemy) {
        String whatWasDropped = "";
        Random random = new Random();
        int playerHP;
        int enemyHP;
        int gainedExp;
        int resetEnemyHP = enemy.getAboutEnemy().getHitPoints();
        battleSimulation.clear();



        System.out.println("Hero : " + hero);
        String heroWeapon = hero.getStats().getWeapon();
        String enemyWeapon = enemy.getArtifacts().getWeapon();
        System.out.println(hero.getAboutHero().getHitPoints() + " " + enemy.getAboutEnemy().getHitPoints());
        while (hero.getAboutHero().getHitPoints() > 0 && enemy.getAboutEnemy().getHitPoints() > 0){
            playerHP = random.nextInt(hero.getWeapons().get(heroWeapon));
            playerHP = this.attackEnemy(playerHP, enemy);
            enemy.getAboutEnemy().setHitPoints(enemy.getAboutEnemy().getHitPoints() - playerHP);
            battleSimulation.add("You hit "+enemy.getAboutEnemy().getName()+" by "+playerHP+" hit points. His HP is " + enemy.getAboutEnemy().getHitPoints()+ "\n");
            if (enemy.getAboutEnemy().getHitPoints() <= 0)
                break;

            enemyHP = random.nextInt(hero.getWeapons().get(enemyWeapon));
            enemyHP = this.attackHero(enemyHP, enemy);
            hero.getAboutHero().setHitPoints(hero.getAboutHero().getHitPoints() - enemyHP);
            battleSimulation.add(enemy.getAboutEnemy().getName() + " hit you by "+enemyHP+", you have "+hero.getAboutHero().getHitPoints()+" HP\n");
            if (hero.getAboutHero().getHitPoints() <= 0)
                break;
        }
        if (hero.getAboutHero().getHitPoints() <= 0) {
            enemy.getAboutEnemy().setHitPoints(resetEnemyHP);
            battleSimulation.add("You fought bravely, but unfortunately your hero died!\n");
            //this.gameOver(battleSimulation);
//            hero.getAboutHero().setExperience();
            return "lost";
        }
        else {
            droppedArtifact = enemyDroppedArtifact();
            enemy.getAboutEnemy().setHitPoints(resetEnemyHP);
            if (enemy.getAboutEnemy().getAttack() > 70)
                gainedExp = 500;
            else
                gainedExp = 350;
            battleSimulation.add("You defeated "+enemy.getAboutEnemy().getName()+" and gained "+gainedExp+ " EXP\n");

            if (chance >= 0 && chance <= 2){
                switch (chance){
                    case 0:
                        whatWasDropped = enemy.getArtifacts().getWeapon();
                        break;
                    case 1:
                        whatWasDropped = enemy.getArtifacts().getArmor();
                        break;
                    case 2:
                        whatWasDropped = enemy.getArtifacts().getHelm();
                        break;
                }
                battleSimulation.add(enemy.getAboutEnemy().getName()+" dropped his "+whatWasDropped);
            }

            hero.getAboutHero().setExperience(hero.getAboutHero().getExperience() + gainedExp);
            return "won";
            //this.wonBattle(battleSimulation);
        }
//        return "";
    }

    private boolean enemyDroppedArtifact() {
        Random random = new Random();
        chance = random.nextInt(6);
        return chance < 3;
    }

    private int attackEnemy(int playerHP, EnemiesFactory enemy){
        int enemyDefended;
        Random random = new Random();

        if (enemy.getAboutEnemy().getDefence() > 72){
            enemyDefended = random.nextInt(101);
            if (enemyDefended >= 50)
                playerHP -= 6;
        }
        else if (enemy.getAboutEnemy().getDefence() <= 72 && enemy.getAboutEnemy().getDefence() > 66)
        {
            enemyDefended = random.nextInt(101);
            if (enemyDefended < 50 && enemyDefended > 20)
                playerHP -= 3;
        }
        else if (enemy.getAboutEnemy().getDefence() <= 66){
            enemyDefended = random.nextInt(101);
            if (enemyDefended >= 20)
                playerHP -= 1;
        }

        if (hero.getAboutHero().getAttack() > 65)
            playerHP += 2;
        else playerHP++;

        if (playerHP < 0)
            playerHP = 0;

        return playerHP;
    }

    private int attackHero(int enemyHP, EnemiesFactory enemy){
        int heroDefended;
        Random random = new Random();

        if (hero.getAboutHero().getDefence() > 72){
            heroDefended = random.nextInt(101);
            if (heroDefended >= 50)
                enemyHP -= 6;
        }
        else if (hero.getAboutHero().getDefence() <= 72 && hero.getAboutHero().getDefence() > 66)
        {
            heroDefended = random.nextInt(101);
            if (heroDefended < 50 && heroDefended > 20)
                enemyHP -= 3;
        }
        else if (hero.getAboutHero().getDefence() <= 66){
            heroDefended = random.nextInt(101);
            if (heroDefended >= 20)
                enemyHP -= 1;
        }

        if (enemy.getAboutEnemy().getAttack() > 65)
            enemyHP += 2;
        else enemyHP++;
        if (enemyHP < 0)
            enemyHP = 0;
        return enemyHP;
    }

    public void pickUpWeapon() {
        switch (chance){
            case 0:
                this.increaseAttack(hero);
                break;
            case 1:
                this.increaseDefence(hero);
                break;
            case 2:
                this.increaseHP(hero);
                break;
        }
    }
}
