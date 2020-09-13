package co.za.wethinkcode.model.characters.enemies;

public class AboutEnemy {
    private final String name;
    private final  int attack;
    private final  int defence;
    private int hitPoints;

    public AboutEnemy(Build build) {
        this.name = build.name;
        this.attack = build.attack;
        this.defence = build.defence;
        this.hitPoints = build.hitPoints;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public static class Build{
        private  String name;
        private  int attack;
        private  int defence;
        private int hitPoints;

        public Build name(String name){
            this.name = name;
            return this;
        }

        public Build defence(int defence){
            this.defence = defence;
            return this;
        }

        public Build attack(int attack){
            this.attack = attack;
            return this;
        }

        public Build hitPoints(int hitPoints){
            this.hitPoints = hitPoints;
            return this;
        }

        public AboutEnemy build(){
            return new AboutEnemy(this);
        }
    }
}
