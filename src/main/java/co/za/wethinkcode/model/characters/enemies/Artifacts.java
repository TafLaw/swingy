package co.za.wethinkcode.model.characters.enemies;

public class Artifacts {
    private String weapon;
    private String armor;
    private String helm;

    public Artifacts(Build build) {
        this.weapon = build.weapon;
        this.armor = build.armor;
        this.helm = build.helm;
    }

    public String getWeapon() {
        return weapon;
    }

    public String getArmor() {
        return armor;
    }

    public String getHelm() {
        return helm;
    }

    public static class Build{
        private String weapon;
        private String armor;
        private String helm;

        public Build weapon(String weapon){
            this.weapon = weapon;
            return this;
        }

        public Build armor(String armor){
            this.armor = armor;
            return this;
        }

        public Build helm(String helm){
            this.helm = helm;
            return this;
        }

        public Artifacts build(){
            return new Artifacts(this);
        }
    }
}
