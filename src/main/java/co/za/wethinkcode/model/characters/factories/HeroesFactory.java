package co.za.wethinkcode.model.characters.factories;

import co.za.wethinkcode.model.characters.heroes.AboutHero;
import co.za.wethinkcode.model.characters.heroes.Stats;
import co.za.wethinkcode.model.characters.heroes.HeroName;

import java.util.HashMap;

public class HeroesFactory {
    private HeroName heroName;
    private Stats stats;
    private AboutHero aboutHero;
    private HashMap<String, Integer> weapons = new HashMap<>();


    public HeroesFactory(Build builder) {
        this.heroName = builder.heroName;
        this.stats = builder.stats;
        this.aboutHero = builder.aboutHero;

        weapons.put("Ninja Stars", 5);
        weapons.put("Sword", 6);
        weapons.put("Axe", 7);
        weapons.put("Cutlass", 8);
        weapons.put("Sjambok", 3);
        weapons.put("Infinity Glove", 10);
        weapons.put("Kunai", 5);
        weapons.put("Invincible fist", 10);
        weapons.put("Knife", 4);
    }

    public HashMap<String, Integer> getWeapons() {
        return weapons;
    }

    public HeroName getHeroName() {
        return heroName;
    }

    public Stats getStats() {
        return stats;
    }

    public AboutHero getAboutHero() {
        return aboutHero;
    }

    public static class Build {
        private HeroName heroName;
        private Stats stats;
        private AboutHero aboutHero;

        public Build heroName(HeroName heroName){
            this.heroName = heroName;
            return this;
        }

        public Build artifacts(Stats stats){
            this.stats = stats;
            return this;
        }

        public Build aboutHero(AboutHero aboutHero){
            this.aboutHero = aboutHero;
            return this;
        }

        public  HeroesFactory build(){
            return new HeroesFactory(this);
        }
    }
}
