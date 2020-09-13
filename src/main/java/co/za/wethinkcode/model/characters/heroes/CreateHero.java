package co.za.wethinkcode.model.characters.heroes;

import co.za.wethinkcode.model.characters.factories.HeroesFactory;
import co.za.wethinkcode.storage.HeroStorage;

import java.util.ArrayList;

public class CreateHero extends HeroStorage {
    private HeroName name;
//    public static HeroesFactory hero;
    private Stats stats;
    private AboutHero aboutHero;
    public HeroesFactory heroesFactory;
    private int attack;
    private int level;
    private int defence;
    private int hitPoints;
    private String  armor;
    private String  helm;
    private String weapon;
    public static ArrayList<String> heroTypes = new ArrayList<String>();

    public CreateHero() {
        this.initTypes();
    }

    public void initTypes(){
        heroTypes.add("Ninja");
        heroTypes.add("Samurai");
        heroTypes.add("Shinobi");
        heroTypes.add("Ronin");
    }

    public HeroesFactory createHero(String name, String type){
        this.setStats(type);
        this.name = new HeroName.Build().name(name).type(type).build();
        this.stats = new Stats.Build().armor(this.armor).helm(this.helm).weapon(this.weapon).build();
        this.aboutHero = new AboutHero.Build().attack(this.attack).defence(this.defence).experience(0).level(this.level).hitPoints(this.hitPoints).build();
        this.heroesFactory = new HeroesFactory.Build().aboutHero(aboutHero).artifacts(stats).heroName(this.name).build();
//        hero = this.heroesFactory;

        this.saveHero(this.heroesFactory);
        return this.heroesFactory;
    }

    private void setStats(String heroType){
        switch (heroType){
            case "Ninja" :
                this.attack = 56;
                this.level = 1;
                this.defence = 60;
                this.hitPoints = 40;
                this.armor = "Couter";
                this.weapon = "Ninja Stars";
                this.helm = "Sphere";
                break;
            case "Samurai" :
                this.attack = 62;
                this.level = 1;
                this.defence = 65;
                this.hitPoints = 40;
                this.armor = "Gauntlet";
                this.weapon = "Sword";
                this.helm = "Violet";
                break;
            case "Shinobi" :
                this.attack = 75;
                this.level = 1;
                this.defence = 70;
                this.hitPoints = 50;
                this.armor = "Pauldron";
                this.weapon = "Axe";
                this.helm = "Rusty";
                break;
            case "Ronin" :
                this.attack = 82;
                this.level = 1;
                this.defence = 88;
                this.hitPoints = 51;
                this.armor = "Vambrace";
                this.weapon = "Cutlass";
                this.helm = "Steel";
                break;
        }
    }


}
