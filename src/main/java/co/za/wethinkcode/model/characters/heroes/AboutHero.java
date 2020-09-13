package co.za.wethinkcode.model.characters.heroes;

import co.za.wethinkcode.model.characters.factories.HeroesFactory;

public class AboutHero {
    private int level;
    private int attack;
    private int defence;
    private int experience;
    private int hitPoints;

    public AboutHero(Build build) {
        this.level = build.level;
        this.attack = build.attack;
        this.defence = build.defence;
        this.experience = build.experience;
        this.hitPoints = build.hitPoints;
    }

    public AboutHero() {
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public static class Build {
        private int level;
        private int attack;
        private int defence;
        private int experience;
        private int hitPoints;

        public Build level(int level){
            this.level = level;
            return this;
        }

        public Build attack(int attack){
            this.attack = attack;
            return this;
        }

        public Build defence(int defence){
            this.defence = defence;
            return this;
        }

        public Build experience(int experience){
            this.experience = experience;
            return this;
        }

        public Build hitPoints(int hitPoints){
            this.hitPoints = hitPoints;
            return this;
        }

        public AboutHero build(){
            return new AboutHero(this);
        }
    }

}
