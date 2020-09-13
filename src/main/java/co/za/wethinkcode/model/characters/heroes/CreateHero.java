package co.za.wethinkcode.model.characters.heroes;

import co.za.wethinkcode.model.characters.factories.HeroesFactory;
import co.za.wethinkcode.storage.HeroStorage;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;

public class CreateHero extends HeroStorage {
    private static Validator validator;
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
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        this.setStats(type);
        this.name = new HeroName.Build().name(name).type(type).build();
        this.stats = new Stats.Build().armor(this.armor).helm(this.helm).weapon(this.weapon).build();
        this.aboutHero = new AboutHero.Build().attack(this.attack).defence(this.defence).experience(0).level(this.level).hitPoints(this.hitPoints).build();
        this.heroesFactory = new HeroesFactory.Build().aboutHero(aboutHero).artifacts(stats).heroName(this.name).build();

        //validate(this.name);
        Set<ConstraintViolation<HeroName>> constraintViolations = validator.validate(this.name);

        if (constraintViolations.size() > 0) {
            for (ConstraintViolation<HeroName> violation : constraintViolations) {
                System.out.println(violation.getMessage());
            }
            return null;
        }
        this.saveHero(this.heroesFactory);
        return this.heroesFactory;
    }

//    public static void validate(HeroName heroName) {
//
//        Set<ConstraintViolation<HeroName>> cvs = validator.validate(heroName);
//
//        for (ConstraintViolation<HeroName> cv : cvs) {
//            System.out.println(cv.getPropertyPath() + ": " + cv.getMessage());
//        }
//    }

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
