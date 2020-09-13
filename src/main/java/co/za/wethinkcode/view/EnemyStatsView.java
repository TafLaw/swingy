package co.za.wethinkcode.view;

import co.za.wethinkcode.model.characters.factories.EnemiesFactory;

public class EnemyStatsView {
    public void enemyStats(EnemiesFactory enemy) {
        System.out.println("You encountered an enemy, what would you like to do?\n");
        System.out.println("\n________________ENEMY STATS_________________\n\n\n");
        System.out.println("Name       : " + enemy.getAboutEnemy().getName());
//        System.out.println("Type       : " + enemy.getHeroName().getType());
        System.out.println("Attack     : " + enemy.getAboutEnemy().getAttack());
        System.out.println("Defence    : " + enemy.getAboutEnemy().getDefence());
//        System.out.println("Experience : " + enemy.getAboutHero().getExperience());
//        System.out.println("Level      : " + enemy.getAboutHero().getLevel());
        System.out.println("Armor      : " + enemy.getArtifacts().getArmor());
        System.out.println("Helmet     : " + enemy.getArtifacts().getHelm());
        System.out.println("Weapon     : " + enemy.getArtifacts().getWeapon());
        System.out.println("\n1. Fight");
        System.out.println("2. Run\n\n");
    }
}
