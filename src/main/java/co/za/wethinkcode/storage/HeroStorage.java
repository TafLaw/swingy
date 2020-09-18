package co.za.wethinkcode.storage;

import co.za.wethinkcode.model.characters.factories.HeroesFactory;
import co.za.wethinkcode.model.files.ReadFile;
import co.za.wethinkcode.model.files.WriteFile;

import java.util.ArrayList;

public class HeroStorage {
    private WriteFile writeFile;
    ReadFile readFile;
    public HeroStorage() {
        writeFile = new WriteFile();
        readFile = new ReadFile();
    }

    public ArrayList<String> allAvailableHeroes(){
        ReadFile.allHeroes.clear();
        readFile.readFromFile("heroes.txt");
        return ReadFile.allHeroes;
    }

    private String newHero(HeroesFactory heroesFactory){
        return heroesFactory.getHeroName().getName() + ","
                + heroesFactory.getHeroName().getType() + ","
                + heroesFactory.getAboutHero().getLevel() + ","
                + heroesFactory.getAboutHero().getHitPoints() + ","
                + heroesFactory.getAboutHero().getExperience() + ","
                + heroesFactory.getAboutHero().getDefence() + ","
                + heroesFactory.getAboutHero().getAttack() + ","
                + heroesFactory.getStats().getWeapon() + ","
                + heroesFactory.getStats().getArmor() + ","
                + heroesFactory.getStats().getHelm();

    }

    private ArrayList<String> addToHeroesList(HeroesFactory heroesFactory){
        String newHero = newHero(heroesFactory);
        ArrayList<String> heroes = this.allAvailableHeroes();
        heroes.add(newHero);
        return heroes;
    }

    public void saveHero(HeroesFactory heroesFactory){
        writeFile.writeToFile(addToHeroesList(heroesFactory));
//        ReadFile.allHeroes.clear();
//        readFile.readFromFile("heroes.txt");
    }

    public void saveAndExit(HeroesFactory hero, int index) {
        ArrayList<String> heroes = this.allAvailableHeroes();
        String newHero = newHero(hero);

        writeFile.writeToFile(replaceHero(heroes, index, newHero));
    }

    private ArrayList<String> replaceHero(ArrayList<String> heroes, int index, String newHero) {
        int i = 0;
        while (i < heroes.size()){
            if (i == index){
                heroes.remove(index);
                heroes.add(index, newHero);
            }
            i++;
        }
        return heroes;
    }
}
