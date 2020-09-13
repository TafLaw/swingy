package co.za.wethinkcode.model.characters.factories;

import co.za.wethinkcode.model.characters.enemies.AboutEnemy;
import co.za.wethinkcode.model.characters.enemies.Artifacts;

public class EnemiesFactory {
    private AboutEnemy aboutEnemy;
    private Artifacts artifacts;

    public EnemiesFactory(Build build)
    {
        this.artifacts = build.artifacts;
        this.aboutEnemy = build.aboutEnemy;
    }

    public AboutEnemy getAboutEnemy() {
        return aboutEnemy;
    }

    public Artifacts getArtifacts() {
        return artifacts;
    }

    public static class Build{
        private AboutEnemy aboutEnemy;
        private Artifacts artifacts;

        public Build aboutEnemy(AboutEnemy aboutEnemy){
            this.aboutEnemy = aboutEnemy;
            return this;
        }

        public Build artifacts(Artifacts artifacts){
            this.artifacts = artifacts;
            return this;
        }

        public EnemiesFactory build(){
            return new EnemiesFactory(this);
        }
    }
}
