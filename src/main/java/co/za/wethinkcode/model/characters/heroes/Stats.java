package co.za.wethinkcode.model.characters.heroes;

public class Stats {
    private String weapon;
    private String armor;
    private String helm;


    public Stats(Build build) {
        this.weapon = build.weapon;
        this.armor = build.armor;
        this.helm = build.helm;
    }

    public Stats() {
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public String getHelm() {
        return helm;
    }

    public void setHelm(String helm) {
        this.helm = helm;
    }

    public  static class Build{
        private String weapon;
        private String armor;
        private String helm;

        public Build weapon(String weapon){
            this.weapon = weapon;
            return this;
        }

        public Build helm(String helm){
            this.helm = helm;
            return this;
        }

        public Build armor(String armor){
            this.armor = armor;
            return this;
        }

        public Stats build(){
            return new Stats(this);
        }
    }
}
