package co.za.wethinkcode.model.characters.heroes;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class HeroName {
    @NotEmpty(message = "A hero name can not be blank")
    @Length(min = 3, max = 15, message = "Hero Name should be between 3-15 characters long")
    private String name;
    private String type;

    public HeroName(Build build) {
        this.name = build.name;
        this.type = build.type;
    }

    public HeroName(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public HeroName() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class Build {
        private String name;
        private String type;

        public Build name(String name){
            this.name = name;
            return this;
        }

        public Build type(String type){
            this.type = type;
            return this;
        }

        public HeroName build(){
            return new HeroName(this);
        }
    }

}
