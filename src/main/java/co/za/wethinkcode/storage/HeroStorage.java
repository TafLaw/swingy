package co.za.wethinkcode.storage;

import co.za.wethinkcode.model.characters.factories.HeroesFactory;
import co.za.wethinkcode.model.files.ReadFile;
import co.za.wethinkcode.model.files.WriteFile;

import java.util.ArrayList;

public class HeroStorage {
    private WriteFile writeFile;
    public HeroStorage() {
        writeFile = new WriteFile();
    }

    public ArrayList<String> allAvailableHeroes(){
        return ReadFile.allHeroes;
    }

    public void saveHero(HeroesFactory heroesFactory){
        String newHero = heroesFactory.getHeroName().getName() + ","
                        + heroesFactory.getHeroName().getType() + ","
                        + heroesFactory.getAboutHero().getLevel() + ","
                        + heroesFactory.getAboutHero().getHitPoints() + ","
                        + heroesFactory.getAboutHero().getExperience() + ","
                        + heroesFactory.getAboutHero().getDefence() + ","
                        + heroesFactory.getAboutHero().getAttack() + ","
                        + heroesFactory.getStats().getWeapon() + ","
                        + heroesFactory.getStats().getArmor() + ","
                        + heroesFactory.getStats().getHelm();
        ArrayList<String> heroes = this.allAvailableHeroes();
        heroes.add(newHero);
        writeFile.writeToFile(heroes);
    }
}
