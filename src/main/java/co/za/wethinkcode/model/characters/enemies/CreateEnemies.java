package co.za.wethinkcode.model.characters.enemies;

import co.za.wethinkcode.model.characters.factories.EnemiesFactory;

import java.util.ArrayList;
import java.util.List;

public class CreateEnemies {
    public static ArrayList<String> enemyNames = new ArrayList<String>();
    public static List<EnemiesFactory> enemiesFactory = new ArrayList<EnemiesFactory>();
    private AboutEnemy aboutEnemy;
    private Artifacts artifacts;
    private int attack;
    private int defence;
    private int hitPoints;
    private String weapon;
    private String helm;
    private String armor;

    public CreateEnemies() {
        this.initNames();
    }

    private void initNames() {
        enemyNames.add("Thanos");
        enemyNames.add("Crocodile");
        enemyNames.add("Madara");
        enemyNames.add("Ripper");
        enemyNames.add("Freezer");
        enemyNames.add("Hulk");
    }

    public void createEnemies(){
        for (int i = 0; i < enemyNames.size(); i++) {
            this.enemiesFactory.add(this.enemy(enemyNames.get(i)));
        }
    }

    private EnemiesFactory enemy(String enemyName){
        this.setStats(enemyName);
        this.aboutEnemy = new AboutEnemy.Build().name(enemyName).defence(this.defence).attack(this.attack).hitPoints(this.hitPoints).build();
        this.artifacts = new Artifacts.Build().armor(this.armor).helm(this.helm).weapon(this.weapon).build();
        return new EnemiesFactory.Build().aboutEnemy(this.aboutEnemy).artifacts(this.artifacts).build();
    }

    private void setStats(String enemyName) {
        switch (enemyName){
            case "Thanos":
                this.attack = 88;
                this.defence = 80;
                this.hitPoints = 50;
                this.armor = "Vambrace";
                this.weapon = "Infinity Glove";
                this.helm = "Cement";
                break;
            case "Crocodile":
                this.attack = 78;
                this.defence = 70;
                this.hitPoints = 45;
                this.armor = "Pauldron";
                this.weapon = "Sword";
                this.helm = "Rusty";
                break;
            case "Madara":
                this.attack = 68;
                this.defence = 80;
                this.hitPoints = 45;
                this.armor = "Gauntlet";
                this.weapon = "Kunai";
                this.helm = "Mask";
                break;
            case "Ripper":
                this.attack = 58;
                this.defence = 60;
                this.hitPoints = 40;
                this.armor = "Couter";
                this.weapon = "Knife";
                this.helm = "Sphere";
                break;
            case "Freezer":
                this.attack = 60;
                this.defence = 60;
                this.hitPoints = 50;
                this.armor = "Gauntlet";
                this.weapon = "Sjambok";
                this.helm = "Sphere";
                break;
            case "Hulk":
                this.attack = 80;
                this.defence = 80;
                this.hitPoints = 50;
                this.armor = "Vambrace";
                this.weapon = "Invincible fist";
                this.helm = "Rusty";
                break;
        }

    }
}
