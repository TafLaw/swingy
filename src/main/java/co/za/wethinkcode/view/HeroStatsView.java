package co.za.wethinkcode.view;

import co.za.wethinkcode.model.characters.factories.HeroesFactory;

public class HeroStatsView {
    public void myHeroStats(HeroesFactory heroesFactory){
        System.out.println("\n________________YOUR HERO STATS_________________\n\n\n");
        System.out.println("Name       : " + heroesFactory.getHeroName().getName());
        System.out.println("Type       : " + heroesFactory.getHeroName().getType());
        System.out.println("HP         : " + heroesFactory.getAboutHero().getHitPoints());
        System.out.println("Attack     : " + heroesFactory.getAboutHero().getAttack());
        System.out.println("Defence    : " + heroesFactory.getAboutHero().getDefence());
        System.out.println("Experience : " + heroesFactory.getAboutHero().getExperience());
        System.out.println("Level      : " + heroesFactory.getAboutHero().getLevel());
        System.out.println("Armor      : " + heroesFactory.getStats().getArmor());
        System.out.println("Helmet     : " + heroesFactory.getStats().getHelm());
        System.out.println("Weapon     : " + heroesFactory.getStats().getWeapon());
        System.out.println("\n\nChoose Option : \n");
        System.out.println("1. Start Game\n2. Go back\n3. Exit\n\n");
        System.out.println("Option : ");
    }
}
