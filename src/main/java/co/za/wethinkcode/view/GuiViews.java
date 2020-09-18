package co.za.wethinkcode.view;


import co.za.wethinkcode.controller.GuiController;
import co.za.wethinkcode.controller.MapController;
import co.za.wethinkcode.model.characters.factories.EnemiesFactory;
import co.za.wethinkcode.model.characters.factories.HeroesFactory;
import org.hibernate.validator.constraints.Range;

import javax.swing.*;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GuiViews {
    JFrame window;
    Container container;

    MapPanel mapPanel;

    private String[] gameHero;
    private static int setUpRan=0;
//    @NotEmpty(message = "This field can not be blank")
    //@Range(min = 3, max = 8, message = "Hero name should have 3 to 8 characters")
    JTextField heroName;
    JPanel heroNamePanel;
    JPanel confirmHeroNameButtonPanel;
    JButton confirmHeroNameButton;

    HeroesFactory activeHero;
    JPanel titlePanel;
    JLabel titleLabel;
    Font titleFont;

    JPanel heroesTitlePanel;
    JPanel heroesPanel;
    JPanel createdheroesPanel;
    JList heroesList;
    JScrollPane scrollPanel;
    JScrollPane resultsScrollPanel;
    JLabel heroesTitleLabel;
    JTextArea heroStats;
    JTextArea createdheroStats;

    JPanel backToHeroTypesPanel;
    JButton backToHeroTypesButton;
    JPanel heroesBackButtonPanel;
    JButton heroesBackButton;
    JPanel heroesPlayButtonPanel;
    JButton heroesPlayButton;

    Font generalFont;

    JPanel instructionPanel;
    JLabel instructionLabel;

    JPanel cantLevelUpPanel;
    JTextArea cantLevelUp;
    JPanel choicesPanel;
    JPanel heroTypePanel;
    JButton createHeroButton;
    JButton availableHeroesButton;
    JButton switchToConsoleButton;
    JButton type1, type2, type3, type4;
    GuiController guiControllerObj;
    GuiController.StartScreenHandler startScreenHandler;
    GuiController.AvailableHeroesScreenHandler availableHeroesScreenHandler;
    GuiController.BackButtonHandler backButtonHandler;
    GuiController.ChoiceHandler choiceHandler;
    GuiController.PlayGameHandler playGameHandler;
    GuiController.DroppedArtifactHandler droppedArtifactHandler;

    JPanel gamePlayStatsPanel;
    JPanel enemyStatsPanel;
    JTextArea gamePlayStats;
    JTextArea enemyStats;
    JPanel moveUpButtonPanel;
    JPanel moveDownButtonPanel;
    JPanel moveLeftButtonPanel;
    JPanel moveRightButtonPanel;

    JPanel pickItemButtonPanel;
    JPanel leaveButtonPanel;

    JPanel fightButtonPanel;
    JPanel okButtonPanel;
    JPanel saveAndExitButtonPanel;
    JPanel backToMapButtonPanel;
    JPanel runButtonPanel;
    JList battleResultsList;
//    JTextArea battleResults;
    MoveUpHandler moveUpHandler;
    MoveRightHandler moveRightHandler;
    MoveLeftHandler moveLeftHandler;
    MoveDownHandler moveDownHandler;
    FightRunHandler fightRunHandler;

    JButton pickItemButton;
    JButton leaveButton;

    JButton moveUpButton;
    JButton moveDownButton;
    JButton moveLeftButton;
    JButton moveRightButton;

    JButton fightButton;
    JButton okButton;
    JButton saveAndExitButton;
    JButton backToMapButton;
    JButton runButton;
    private GuiController.SaveAndExitHandler saveAndExitHandler;
    private GuiController.SwitchToConsoleHandler switchToConsoleHandler;

    public GuiViews(GuiController.StartScreenHandler startScreenHandler) {
        this.startScreenHandler = startScreenHandler;
        window = new JFrame("Swingy");

//        startScreenHandler = new StartScreenHandler();


        titlePanel = new JPanel();
        titleLabel = new JLabel("GAME PLAY!");
        titleFont = new Font("New Times Roman", Font.PLAIN, 24);
        heroName = new JTextField(15);
        heroNamePanel = new JPanel();
        confirmHeroNameButton = new JButton("Enter");
        confirmHeroNameButtonPanel = new JPanel();

        gamePlayStatsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        enemyStatsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        gamePlayStats = new JTextArea();
        enemyStats = new JTextArea();
        moveUpButtonPanel = new JPanel();
        moveLeftButtonPanel = new JPanel();
        moveDownButtonPanel = new JPanel();
        moveRightButtonPanel = new JPanel();
        fightButtonPanel = new JPanel();
        okButtonPanel = new JPanel();
        saveAndExitButtonPanel = new JPanel();
        backToMapButtonPanel = new JPanel();
        runButtonPanel = new JPanel();
        leaveButtonPanel = new JPanel();
        pickItemButtonPanel = new JPanel();
        cantLevelUpPanel = new JPanel();
        cantLevelUp = new JTextArea();
        fightRunHandler = new FightRunHandler();

//        battleResultsPanel = new JPanel();
//        battleResults = new JTextArea();

        leaveButton = new JButton("Leave");
        pickItemButton = new JButton("Pick");
        fightButton = new JButton("Fight");
        okButton = new JButton("OK");
        saveAndExitButton = new JButton("Exit");
        backToMapButton = new JButton("OK");
        runButton = new JButton("Run");
        moveUpButton = new JButton("UP");
        moveDownButton = new JButton("DOWN");
        moveLeftButton = new JButton("LEFT");
        moveRightButton = new JButton("RIGHT");

        generalFont = new Font("New Times Roman", Font.PLAIN, 14);

        heroesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        createdheroesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));


        backToHeroTypesPanel = new JPanel();
        backToHeroTypesButton = new JButton("Back");
        heroesTitlePanel = new JPanel();
        heroesTitleLabel = new JLabel("AVAILABLE HEROES");

        heroStats = new JTextArea();
        createdheroStats = new JTextArea();
        instructionPanel = new JPanel();
        instructionLabel = new JLabel("What would you like to do?");

        heroTypePanel = new JPanel();
        choicesPanel = new JPanel();
        createHeroButton = new JButton("Create Hero");
        availableHeroesButton = new JButton("Load Hero");
        switchToConsoleButton = new JButton("Switch to console");

        type1 = new JButton("Ninja");
        type2 = new JButton("Samurai");
        type3 = new JButton("Shinobi");
        type4 = new JButton("Ronin");

        heroesBackButtonPanel = new JPanel();
        heroesBackButton = new JButton("Back");

        heroesPlayButtonPanel = new JPanel();
        heroesPlayButton = new JButton("Play");

    }

    public void backToMain(){
        titlePanel.setVisible(true);
        choicesPanel.setVisible(true);


        if (mapPanel != null){
            mapPanel.setVisible(false);
            moveUpButtonPanel.setVisible(false);
            moveDownButtonPanel.setVisible(false);
            moveLeftButtonPanel.setVisible(false);
            moveRightButtonPanel.setVisible(false);
            gamePlayStatsPanel.setVisible(false);
            saveAndExitButtonPanel.setVisible(false);
            saveAndExitButton.removeActionListener(startScreenHandler);
            moveUpButton.removeActionListener(moveUpHandler);
            moveDownButton.removeActionListener(moveDownHandler);
            moveLeftButton.removeActionListener(moveLeftHandler);
            moveRightButton.removeActionListener(moveRightHandler);

        }

        cantLevelUpPanel.setVisible(false);
        instructionPanel.setVisible(true);
        instructionLabel.setText("What would you like to do?");

        heroTypePanel.setVisible(false);
        heroesBackButtonPanel.setVisible(false);

        if (scrollPanel != null)
            scrollPanel.setVisible(false);
//        instructionPanel.setVisible(false);
        if (heroesList!=null && heroesBackButtonPanel != null && heroesPlayButtonPanel != null && heroesPanel != null) {
            heroesList.setVisible(false);
            heroesBackButtonPanel.setVisible(false);
            heroesPlayButtonPanel.setVisible(false);
            heroesPanel.setVisible(false);
        }
        System.out.println("main");
        if (resultsScrollPanel != null)
            resultsScrollPanel.setVisible(false);

        okButtonPanel.setVisible(false);
        enemyStatsPanel.setVisible(false);
    }

    public void viewSetUp(GuiController guiControllerObj){
        this.guiControllerObj = guiControllerObj;
        this.availableHeroesScreenHandler = guiControllerObj.availableHeroesScreenHandler;
        this.backButtonHandler = guiControllerObj.backButtonHandler;
        this.choiceHandler = guiControllerObj.choiceHandler;
        this.playGameHandler = guiControllerObj.playGameHandler;
        this.droppedArtifactHandler = guiControllerObj.droppedArtifactHandler;
        this.saveAndExitHandler = guiControllerObj.saveAndExitHandler;
        this.switchToConsoleHandler = guiControllerObj.switchToConsoleHandler;

        window.setSize(800, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.cyan);
        window.setLayout(null);
        window.setVisible(true);

        container = window.getContentPane();
        titlePanel.setBounds(100, 50, 600, 50);
        titlePanel.setBackground(Color.BLACK);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        instructionPanel.setBounds(250, 150, 200, 40);
        instructionPanel.setBackground(Color.BLACK);
        instructionLabel.setForeground(Color.WHITE);
        instructionLabel.setFont(generalFont);
        instructionPanel.add(instructionLabel);



        choicesPanel.setBounds(250, 250, 300, 100);
        choicesPanel.setBackground(Color.BLACK);

        createHeroButton.setForeground(Color.blue);
        createHeroButton.setFont(generalFont);
        createHeroButton.addActionListener(startScreenHandler);
        createHeroButton.setFocusPainted(false);

        availableHeroesButton.setForeground(Color.blue);
        availableHeroesButton.setFont(generalFont);
        availableHeroesButton.addActionListener(availableHeroesScreenHandler);
        availableHeroesButton.setFocusPainted(false);

        switchToConsoleButton.setForeground(Color.blue);
        switchToConsoleButton.setFont(generalFont);
        switchToConsoleButton.addActionListener(switchToConsoleHandler);
        switchToConsoleButton.setFocusPainted(false);

        choicesPanel.add(createHeroButton);
        choicesPanel.add(availableHeroesButton);
        choicesPanel.add(switchToConsoleButton);
        choicesPanel.setLayout(new GridLayout(3,1));

        container.add(titlePanel);
        container.add(instructionPanel);
        container.add(choicesPanel);
    }

    public void heroTypeScreen(){
        titlePanel.setVisible(false);
        choicesPanel.setVisible(false);



        heroesTitlePanel.setBounds(100, 50, 600, 50);
        heroesTitlePanel.setBackground(Color.BLACK);
        heroesTitleLabel.setForeground(Color.WHITE);
        heroesTitleLabel.setFont(titleFont);
        heroesTitleLabel.setText("HERO TYPE");
        heroesTitlePanel.add(heroesTitleLabel);


        heroTypePanel.setVisible(true);
        instructionPanel.setVisible(true);
        heroesTitlePanel.setVisible(true);
        heroesBackButtonPanel.setVisible(true);

        heroesBackButtonPanel.setVisible(true);
        instructionLabel.setText("Please Select Your Hero Type");

        heroTypePanel.setBounds(250, 250, 300, 150);
        heroTypePanel.setBackground(Color.BLACK);

        type1.setForeground(Color.blue);
        type1.setFocusPainted(false);
        type1.setFont(generalFont);
        type1.addActionListener(choiceHandler);
        type1.setActionCommand("Ninja");

        type2.setForeground(Color.blue);
        type2.setFont(generalFont);
        type2.setFocusPainted(false);
        type2.addActionListener(choiceHandler);
        type2.setActionCommand("Samurai");

        type3.setForeground(Color.blue);
        type3.setFont(generalFont);
        type3.setFocusPainted(false);
        type3.addActionListener(choiceHandler);
        type3.setActionCommand("Shinobi");

        type4.setForeground(Color.blue);
        type4.setFont(generalFont);
        type4.setFocusPainted(false);
        type4.addActionListener(choiceHandler);
        type4.setActionCommand("Ronin");

        heroTypePanel.add(type1);
        heroTypePanel.add(type2);
        heroTypePanel.add(type3);
        heroTypePanel.add(type4);
        heroTypePanel.setLayout(new GridLayout(4, 1));

        heroesBackButtonPanel.setBounds(250, 420, 70, 40);
        heroesBackButtonPanel.setBackground(Color.BLUE);
        heroesBackButton.setForeground(Color.blue);
        heroesBackButton.setFont(generalFont);
        heroesBackButton.setFocusPainted(false);
        heroesBackButton.addActionListener(backButtonHandler);
        heroesBackButtonPanel.add(heroesBackButton);

        container.add(heroesTitlePanel);
        container.add(heroTypePanel);
        container.add(heroesBackButtonPanel);
    }

    public void availableHeroesScreen(@NotNull(message = "There are no Heroes available") String[] allHeroes, String[] names){


        heroesList = new JList(names);
        scrollPanel = new JScrollPane(heroesList);

//        heroesList.setVisible(true);
        scrollPanel.setVisible(true);
        heroesBackButtonPanel.setVisible(true);
        heroesPlayButtonPanel.setVisible(true);
        heroesPanel.setVisible(true);
        heroesTitlePanel.setVisible(true);
        scrollPanel.setVisible(true);

        heroesTitleLabel.setText("AVAILABLE HEROES");

        heroesPanel.setAutoscrolls(true);
        heroesList.setVisibleRowCount(8);
//        heroesList.setFont(generalFont);
//        heroesList.setAutoscrolls(true);
        heroesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        heroesList.setBounds(100, 120, 400, 350);
        scrollPanel.setBounds(100, 120, 400, 350);

        heroesList.setBackground(Color.red);
        heroesList.setForeground(Color.WHITE);
        heroesList.addListSelectionListener(e -> {
            int selected = heroesList.getSelectedIndex();
//            String[] hero = heroesList.getSelectedValue().toString().split(",");
            String[] hero = allHeroes[selected].toString().split(",");
            gameHero = hero;
            heroesTitleLabel.setText(hero[0]);
            System.out.println("SELECTED: "+ selected);
            guiControllerObj.setHeroIndex(selected);
            heroStats.setText("Name: "+hero[0]
                            + "\nType : " + hero[1] + "\nLevel : " + hero[2] + "\nHP : " + hero[3] + "\nExperience : " + hero[4]
                            + "\nDefence : " + hero[5] + "\nAttack : " + hero[6] + "\nArmor : " + hero[7] + "\nWeapon : " + hero[8]
            );
        });


        heroTypePanel.setVisible(false);
        titlePanel.setVisible(false);
        choicesPanel.setVisible(false);
        instructionPanel.setVisible(false);

        heroesPlayButton.removeActionListener(playGameHandler);
        heroesTitlePanel.setBounds(100, 50, 600, 50);
        heroesTitlePanel.setBackground(Color.BLACK);
        heroesTitleLabel.setForeground(Color.WHITE);
        heroesTitleLabel.setFont(titleFont);

//        heroStats.setBounds(10,200,60,20);
        heroStats.setBackground(Color.BLUE);
        heroStats.setForeground(Color.WHITE);
        heroStats.setFont(generalFont);
        heroStats.setEditable(false);

        heroesPanel.setBounds(510, 120, 200, 350);
        heroesPanel.setBackground(Color.BLUE);
        heroesPanel.add(heroStats);

        heroesBackButtonPanel.setBounds(100, 475, 70, 40);
        heroesBackButtonPanel.setBackground(Color.BLUE);
        heroesBackButton.setForeground(Color.blue);
        heroesBackButton.setFocusPainted(false);
        heroesBackButton.setFont(generalFont);
        heroesBackButton.addActionListener(backButtonHandler);
        heroesBackButtonPanel.add(heroesBackButton);

        heroesPlayButtonPanel.setBounds(510, 475, 70, 40);
        heroesPlayButtonPanel.setBackground(Color.BLUE);
        heroesPlayButton.setForeground(Color.blue);
        heroesPlayButton.setFont(generalFont);
        heroesPlayButton.addActionListener(playGameHandler);
        heroesPlayButton.setFocusPainted(false);
        heroesPlayButtonPanel.add(heroesPlayButton);

        heroesTitlePanel.add(heroesTitleLabel);
        container.add(heroesTitlePanel);

        container.add(scrollPanel);
        container.add(heroesBackButtonPanel);
        container.add(heroesPlayButtonPanel);
        container.add(heroesPanel);
    }


    public void heroNameScreen(String heroType) {
//        String heroName;
        heroesTitlePanel.setBounds(100, 50, 600, 50);
        heroesTitlePanel.setBackground(Color.BLACK);
        heroesTitleLabel.setForeground(Color.WHITE);
        heroesTitleLabel.setFont(titleFont);

        heroesPlayButton.removeActionListener(playGameHandler);
        heroTypePanel.setVisible(false);
        heroesBackButtonPanel.setVisible(false);
        heroesTitlePanel.setVisible(true);
        instructionPanel.setVisible(false);
        heroesPlayButtonPanel.setVisible(false);
        heroesTitlePanel.setVisible(true);
        createdheroesPanel.setVisible(true);
        heroNamePanel.setVisible(true);
        confirmHeroNameButtonPanel.setVisible(true);
        backToHeroTypesPanel.setVisible(true);

        createdheroStats.setForeground(Color.WHITE);
        createdheroStats.setBackground(Color.BLUE);
        createdheroStats.setFont(generalFont);
        createdheroStats.setEditable(false);

        createdheroesPanel.setBounds(300, 165, 270, 340);
        createdheroesPanel.setBackground(Color.BLUE);
        createdheroesPanel.setForeground(Color.BLUE);
        createdheroesPanel.add(createdheroStats);

        heroesTitleLabel.setText("Give your Hero a name");
        heroesTitlePanel.add(heroesTitleLabel);


        heroesPlayButtonPanel.setBounds(500, 515, 70, 40);
        heroesPlayButtonPanel.setBackground(Color.BLACK);
        heroesPlayButton.setForeground(Color.blue);
        heroesPlayButton.setFont(generalFont);
        heroesPlayButton.setFocusPainted(false);
        heroesPlayButton.addActionListener(playGameHandler);
        heroesPlayButtonPanel.add(heroesPlayButton);


        heroNamePanel.setBounds(300, 120, 200, 50);
        heroNamePanel.setBackground(Color.BLACK);
        heroName.setFont(generalFont);
        heroName.setBounds(100, 250, 100, 50);
        heroName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = heroName.getText();
                heroName.removeActionListener(this);
                if (setUpRan == 0) {
                    HeroesFactory newhero = guiControllerObj.createHero(heroType, name);

                    if (newhero == null)
                        return;
                    heroesTitleLabel.setText(name);
                    heroName.setEditable(false);
                    heroesPlayButtonPanel.setVisible(true);
                    gameHero = newhero.toString().split(",");

                    createdheroStats.setText("Name: " + newhero.getHeroName().getName()
                            + "\nType : " + newhero.getHeroName().getType() + "\nLevel : " + newhero.getAboutHero().getLevel() + "\nHP : " + newhero.getAboutHero().getHitPoints() + "\nEXP : " + newhero.getAboutHero().getExperience()
                            + "\nDefence : " + newhero.getAboutHero().getDefence() + "\nAttack : " + newhero.getAboutHero().getAttack() + "\nArmor : " + newhero.getStats().getArmor() + "\nWeapon : " + newhero.getStats().getWeapon()
                    );
                    setUpRan = 1;
                }
            }
        });
        heroNamePanel.add(heroName);

        confirmHeroNameButtonPanel.setBounds(500, 120, 70, 40);
        confirmHeroNameButtonPanel.setBackground(Color.BLACK);
        confirmHeroNameButton.setForeground(Color.blue);
        confirmHeroNameButton.setFont(generalFont);
        confirmHeroNameButton.setFocusPainted(false);
        confirmHeroNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = heroName.getText();
                if (setUpRan == 0) {
                    HeroesFactory newhero = guiControllerObj.createHero(heroType, name);

                    if (newhero == null)
                        return;

                    heroesTitleLabel.setText(name);
                    heroName.setEditable(false);
                    heroesPlayButtonPanel.setVisible(true);

                    gameHero = guiControllerObj.heroToString(newhero).split(",");
                    //System.out.println(gameHero);
                    createdheroStats.setText("Name: " + newhero.getHeroName().getName()
                            + "\nType : " + newhero.getHeroName().getType() + "\nLevel : " + newhero.getAboutHero().getLevel() + "\nHP : " + newhero.getAboutHero().getHitPoints() + "\nEXP : " + newhero.getAboutHero().getExperience()
                            + "\nDefence : " + newhero.getAboutHero().getDefence() + "\nAttack : " + newhero.getAboutHero().getAttack() + "\nArmor : " + newhero.getStats().getArmor() + "\nWeapon : " + newhero.getStats().getWeapon()
                    );
                    setUpRan = 1;
                }
            }
        });
        confirmHeroNameButtonPanel.add(confirmHeroNameButton);

        backToHeroTypesPanel.setBounds(300, 515, 70, 40);
        backToHeroTypesPanel.setBackground(Color.BLACK);
        backToHeroTypesButton.setForeground(Color.blue);
        backToHeroTypesButton.setFocusPainted(false);
        backToHeroTypesButton.setFont(generalFont);
        backToHeroTypesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                heroName.setText("");
                setUpRan = 0;
                heroesTitleLabel.setText("Give your Hero a name");
                heroName.setEditable(true);
                heroesPlayButtonPanel.setVisible(false);
                heroTypePanel.setVisible(true);
                heroesBackButtonPanel.setVisible(true);
//                heroesTitlePanel.setVisible(false);
                heroesTitleLabel.setText("HERO TYPE");
                instructionPanel.setVisible(true);
                createdheroesPanel.setVisible(false);
                createdheroStats.setText("");
                heroNamePanel.setVisible(false);
                confirmHeroNameButtonPanel.setVisible(false);
                backToHeroTypesPanel.setVisible(false);
            }
        });
        backToHeroTypesPanel.add(backToHeroTypesButton);

        container.add(heroesPlayButtonPanel);
        container.add(backToHeroTypesPanel);
        container.add(createdheroesPanel);
        container.add(heroesTitlePanel);
        container.add(heroNamePanel);
        container.add(confirmHeroNameButtonPanel);
    }

    public void playScreen() {
        moveUpHandler = new MoveUpHandler();
        moveDownHandler = new MoveDownHandler();
        moveLeftHandler = new MoveLeftHandler();
        moveRightHandler = new MoveRightHandler();

        mapPanel = new MapPanel(guiControllerObj.setGameHero(gameHero), new FlowLayout(FlowLayout.CENTER));
        activeHero = guiControllerObj.gameHero;

        System.out.println("Game hero: " + activeHero.getHeroName().getName());

        confirmHeroNameButtonPanel.setVisible(false);
        heroesPlayButtonPanel.setVisible(false);
        backToHeroTypesPanel.setVisible(false);
        createdheroesPanel.setVisible(false);
        heroesTitlePanel.setVisible(false);
        heroNamePanel.setVisible(false);

        heroesTitlePanel.setVisible(false);
        heroesBackButtonPanel.setVisible(false);
        heroesPlayButtonPanel.setVisible(false);
        heroesPanel.setVisible(false);

        mapPanel.setVisible(true);
        moveUpButtonPanel.setVisible(true);
        moveDownButtonPanel.setVisible(true);
        moveLeftButtonPanel.setVisible(true);
        moveRightButtonPanel.setVisible(true);
        gamePlayStatsPanel.setVisible(true);
        saveAndExitButtonPanel.setVisible(true);


        if (heroesList != null) {
            scrollPanel.setVisible(false);
            heroesList.setVisible(false);
        }

        mapPanel.setBounds(180, 50, 600, 520);
        mapPanel.setBackground(Color.cyan);

        gamePlayStats.setForeground(Color.WHITE);
        gamePlayStats.setBackground(Color.BLUE);
        gamePlayStats.setFont(generalFont);
        gamePlayStats.setEditable(false);

        gamePlayStatsPanel.setBounds(2, 50, 200, 340);
        gamePlayStatsPanel.setBackground(Color.BLUE);
        gamePlayStats.setText("EXP : " + activeHero.getAboutHero().getExperience() + "\n"
                        + "Weapon : " + activeHero.getStats().getWeapon() + "\n"
                        + "HP : " + activeHero.getAboutHero().getHitPoints() + "\n"
                        + "Level : "+ activeHero.getAboutHero().getLevel() + "\n" + guiControllerObj.initMap.metEnemy
                );
        gamePlayStatsPanel.add(gamePlayStats);

        moveUpButtonPanel.setBounds(45, 400, 70, 40);
        moveUpButtonPanel.setBackground(Color.cyan);
        moveUpButton.setFont(generalFont);
        moveUpButton.setFocusPainted(false);
        moveUpButton.setForeground(Color.blue);
        moveUpButton.setActionCommand("up");
        moveUpButton.addActionListener(moveUpHandler);
        moveUpButtonPanel.add(moveUpButton);

        moveDownButtonPanel.setBounds(35, 480, 90, 42);
        moveDownButtonPanel.setBackground(Color.cyan);
        moveDownButton.setFont(generalFont);
        moveDownButton.setFocusPainted(false);
        moveDownButton.setForeground(Color.blue);
        moveDownButton.setActionCommand("down");
        moveDownButton.addActionListener(moveDownHandler);
        moveDownButtonPanel.add(moveDownButton);

        moveLeftButtonPanel.setBounds(2, 440, 80, 40);
        moveLeftButtonPanel.setBackground(Color.cyan);
        moveLeftButton.setFont(generalFont);
        moveLeftButton.setForeground(Color.blue);
        moveLeftButton.setFocusPainted(false);
        moveLeftButton.setActionCommand("left");
        moveLeftButton.addActionListener(moveLeftHandler);
        moveLeftButtonPanel.add(moveLeftButton);

        moveRightButtonPanel.setBounds(80, 440, 85, 40);
        moveRightButtonPanel.setBackground(Color.cyan);
        moveRightButton.setFont(generalFont);
        moveRightButton.setForeground(Color.blue);
        moveRightButton.setFocusPainted(false);
        moveRightButton.setActionCommand("right");
        moveRightButton.addActionListener(moveRightHandler);
        moveRightButtonPanel.add(moveRightButton);

        saveAndExitButtonPanel.setBounds(35, 540, 85, 42);
        saveAndExitButtonPanel.setBackground(Color.BLUE);
        saveAndExitButton.setFont(generalFont);
        saveAndExitButton.setForeground(Color.blue);
        saveAndExitButton.setFocusPainted(false);
        saveAndExitButton.addActionListener(saveAndExitHandler);
        saveAndExitButtonPanel.add(saveAndExitButton);


        container.add(mapPanel);
        container.add(moveUpButtonPanel);
        container.add(moveDownButtonPanel);
        container.add(moveLeftButtonPanel);
        container.add(moveRightButtonPanel);
        container.add(gamePlayStatsPanel);
        container.add(saveAndExitButtonPanel);

//        if (guiControllerObj.initMap)
    }

    private void enemyStatsScreen(EnemiesFactory eStats) {
        gamePlayStatsPanel.setVisible(false);

        runButton.removeActionListener(fightRunHandler);
        fightButton.removeActionListener(fightRunHandler);
        moveUpButtonPanel.setVisible(false);
        moveDownButtonPanel.setVisible(false);
        moveLeftButtonPanel.setVisible(false);
        saveAndExitButtonPanel.setVisible(false);
        moveRightButtonPanel.setVisible(false);
        enemyStatsPanel.setVisible(true);
        fightButtonPanel.setVisible(true);
        runButtonPanel.setVisible(true);

        enemyStats.setForeground(Color.WHITE);
        enemyStats.setBackground(Color.BLUE);
        enemyStats.setFont(generalFont);
        enemyStats.setEditable(false);

        runButtonPanel.setBounds(80, 440, 85, 40);
        runButtonPanel.setBackground(Color.BLUE);
        runButton.setFont(generalFont);
        runButton.setForeground(Color.blue);
        runButton.setFocusPainted(false);
        runButton.setActionCommand("run");
        runButtonPanel.add(runButton);

        fightButtonPanel.setBounds(2, 440, 80, 40);
        runButton.addActionListener(fightRunHandler);
        fightButtonPanel.setBackground(Color.BLUE);
        fightButton.setFont(generalFont);
        fightButton.setForeground(Color.blue);
        fightButton.setFocusPainted(false);
        fightButton.setActionCommand("fight");
        fightButton.addActionListener(fightRunHandler);
        fightButtonPanel.add(fightButton);

        enemyStatsPanel.setBounds(2, 50, 170, 340);
        enemyStatsPanel.setBackground(Color.BLUE);
        enemyStats.setText("Name       : " + eStats.getAboutEnemy().getName() + "\n"
                                + "Attack       : " + eStats.getAboutEnemy().getAttack() + "\n"
                                + "Defence   : " + eStats.getAboutEnemy().getDefence() + "\n"
                                + "Armor        : " + eStats.getArtifacts().getArmor() + "\n"
                                + "Helmet     : " + eStats.getArtifacts().getHelm() + "\n"
                                + "Weapon   : " + eStats.getArtifacts().getWeapon()
        );
        enemyStatsPanel.add(enemyStats);

        container.add(fightButtonPanel);
        container.add(runButtonPanel);
        container.add(enemyStatsPanel);
    }

    private void wonBattleScreen(String[] battleSimulation) {
        fightButton.removeActionListener(fightRunHandler);
        battleResultsList = new JList(battleSimulation);
        resultsScrollPanel = new JScrollPane(battleResultsList);

        mapPanel.setVisible(false);
        runButtonPanel.setVisible(false);
        fightButtonPanel.setVisible(false);
        resultsScrollPanel.setVisible(true);
        backToMapButtonPanel.setVisible(true);
        enemyStatsPanel.setVisible(true);

        guiControllerObj.droppedArtifact();

        backToMapButtonPanel.setBounds(35, 480, 90, 42);
        backToMapButtonPanel.setBackground(Color.BLUE);
        backToMapButton.setFont(generalFont);
        backToMapButton.setFocusPainted(false);
        backToMapButton.setForeground(Color.blue);
//        okButton.setActionCommand("down");
        backToMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultsScrollPanel.setVisible(false);
                backToMapButtonPanel.setVisible(false);

                updateStats(guiControllerObj.gameHero);
                mapPanel.setVisible(true);
                moveUpButtonPanel.setVisible(true);
                moveDownButtonPanel.setVisible(true);
                saveAndExitButtonPanel.setVisible(true);
                moveLeftButtonPanel.setVisible(true);
                moveRightButtonPanel.setVisible(true);
                gamePlayStatsPanel.setVisible(true);
            }
        });
        backToMapButtonPanel.add(backToMapButton);

        resultsScrollPanel.setBounds(180, 50, 600, 520);
        battleResultsList.setBounds(180, 50, 600, 520);
        battleResultsList.setForeground(Color.WHITE);
        battleResultsList.setBackground(Color.BLUE);
        battleResultsList.setVisibleRowCount(8);
        battleResultsList.setEnabled(false);
        container.add(resultsScrollPanel);
        container.add(backToMapButtonPanel);
    }

    public void droppedArtifact(){
        backToMapButtonPanel.setVisible(false);
        leaveButtonPanel.setVisible(true);
        pickItemButtonPanel.setVisible(true);
        pickItemButton.removeActionListener(droppedArtifactHandler);
        leaveButton.removeActionListener(droppedArtifactHandler);



        pickItemButtonPanel.setBounds(45, 400, 70, 40);
        pickItemButtonPanel.setBackground(Color.BLUE);
        pickItemButton.setFont(generalFont);
        pickItemButton.setFocusPainted(false);
        pickItemButton.setForeground(Color.blue);
        pickItemButton.setActionCommand("pick");
        pickItemButton.addActionListener(droppedArtifactHandler);
        pickItemButtonPanel.add(pickItemButton);

        leaveButtonPanel.setBounds(35, 480, 90, 42);
        leaveButtonPanel.setBackground(Color.BLUE);
        leaveButton.setFont(generalFont);
        leaveButton.setFocusPainted(false);
        leaveButton.setForeground(Color.blue);
        leaveButton.setActionCommand("leave");
        leaveButton.addActionListener(droppedArtifactHandler);
        leaveButtonPanel.add(leaveButton);

        container.add(pickItemButtonPanel);
        container.add(leaveButtonPanel);

    }

    public void leave(){
        pickItemButtonPanel.setVisible(false);
        leaveButtonPanel.setVisible(false);
        resultsScrollPanel.setVisible(false);
        backToMapButtonPanel.setVisible(false);

        updateStats(guiControllerObj.gameHero);
        mapPanel.setVisible(true);
        moveUpButtonPanel.setVisible(true);
        moveDownButtonPanel.setVisible(true);
        saveAndExitButtonPanel.setVisible(true);
        moveLeftButtonPanel.setVisible(true);
        moveRightButtonPanel.setVisible(true);
        gamePlayStatsPanel.setVisible(true);
    }

    private void lostBattleScreen(String[] battleSimulation) {


        battleResultsList = new JList(battleSimulation);
        resultsScrollPanel = new JScrollPane(battleResultsList);

        mapPanel.setVisible(false);
        runButtonPanel.setVisible(false);
        fightButtonPanel.setVisible(false);
        resultsScrollPanel.setVisible(true);
        okButtonPanel.setVisible(true);
        enemyStatsPanel.setVisible(true);

        okButton.removeActionListener(guiControllerObj.backButtonHandler);
        okButtonPanel.setBounds(35, 480, 90, 42);
        okButtonPanel.setBackground(Color.BLUE);
        okButton.setFont(generalFont);
        okButton.setFocusPainted(false);
        okButton.setForeground(Color.blue);
//        okButton.setActionCommand("down");
        okButton.addActionListener(guiControllerObj.backButtonHandler);
        okButtonPanel.add(okButton);



        resultsScrollPanel.setBounds(180, 50, 600, 520);
        battleResultsList.setBounds(180, 50, 600, 520);
        battleResultsList.setForeground(Color.WHITE);
        battleResultsList.setBackground(Color.BLUE);
        battleResultsList.setVisibleRowCount(8);
        battleResultsList.setEnabled(false);
        container.add(resultsScrollPanel);
        container.add(okButtonPanel);
    }

    private void updateStats(HeroesFactory activeHero){
        gamePlayStats.setText("EXP : " + activeHero.getAboutHero().getExperience() + "\n"
                + "Weapon : " + activeHero.getStats().getWeapon() + "\n"
                + "HP : " + activeHero.getAboutHero().getHitPoints() + "\n"
                + "Level : "+ activeHero.getAboutHero().getLevel() + "\n" + guiControllerObj.initMap.metEnemy
        );
    }

    public void rePaint(){
        enemyStatsPanel.setVisible(false);
        fightButtonPanel.setVisible(false);
        runButtonPanel.setVisible(false);

        gamePlayStatsPanel.setVisible(true);
        moveUpButtonPanel.setVisible(true);
        saveAndExitButtonPanel.setVisible(true);
        moveRightButtonPanel.setVisible(true);
        moveLeftButtonPanel.setVisible(true);
        moveDownButtonPanel.setVisible(true);

        mapPanel.setMap(guiControllerObj.initMap);
        MapPanel.setCols(guiControllerObj.initMap.getColumns());
        MapPanel.setRows(guiControllerObj.initMap.getRows());
        mapPanel.repaint();
    }


    public void cantLevelUpScreen() {
        cantLevelUpPanel.setBounds(180, 50, 600, 520);
        cantLevelUpPanel.setBackground(Color.cyan);
        cantLevelUp.setText("You won the Game but\n you do not have enough EXP to level up\n\n Press Exit to continue");
        cantLevelUp.setForeground(Color.black);
        cantLevelUp.setFont(titleFont);
        cantLevelUp.setEditable(false);
        cantLevelUpPanel.add(cantLevelUp);

        cantLevelUpPanel.setVisible(true);

        if (mapPanel != null){
            mapPanel.setVisible(false);
            container.remove(moveDownButtonPanel);
            container.remove(moveUpButtonPanel);
            container.remove(moveRightButtonPanel);
            container.remove(moveLeftButtonPanel);
            moveUpButtonPanel.setVisible(false);
            moveDownButtonPanel.setVisible(false);
            moveLeftButtonPanel.setVisible(false);
            moveRightButtonPanel.setVisible(false);

        }

        moveUpButton.removeActionListener(moveUpHandler);
        moveDownButton.removeActionListener(moveDownHandler);
        moveLeftButton.removeActionListener(moveLeftHandler);
        moveRightButton.removeActionListener(moveRightHandler);

        container.add(cantLevelUpPanel);
    }

    public void error() {
        JOptionPane.showMessageDialog(window, "Hero Name should be between 3-15 characters long");
    }

    public void closeWindow() {
        window.dispose();
    }

    private class MoveUpHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            activeHero = guiControllerObj.gameHero;
            guiControllerObj.movePlayer(e.getActionCommand());
            updateStats(activeHero);
            mapPanel.repaint();

            if (guiControllerObj.initMap.metEnemy){
                enemyStatsScreen(guiControllerObj.getEnemyStats());
                guiControllerObj.resetMetEnemy();
            }
        }
    }

    private class MoveRightHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            activeHero = guiControllerObj.gameHero;
            guiControllerObj.movePlayer(e.getActionCommand());
            updateStats(activeHero);
            mapPanel.repaint();

            if (guiControllerObj.initMap.metEnemy){
                enemyStatsScreen(guiControllerObj.getEnemyStats());
                guiControllerObj.resetMetEnemy();
            }
        }
    }

    private class MoveLeftHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            activeHero = guiControllerObj.gameHero;
            guiControllerObj.movePlayer(e.getActionCommand());
            updateStats(activeHero);
            mapPanel.repaint();

            if (guiControllerObj.initMap.metEnemy){
                enemyStatsScreen(guiControllerObj.getEnemyStats());
                guiControllerObj.resetMetEnemy();
            }
        }
    }

    private class MoveDownHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            activeHero = guiControllerObj.gameHero;
            guiControllerObj.movePlayer(e.getActionCommand());
            updateStats(activeHero);
            mapPanel.repaint();

            if (guiControllerObj.initMap.metEnemy){
                enemyStatsScreen(guiControllerObj.getEnemyStats());
                guiControllerObj.resetMetEnemy();
            }
        }
    }

    private class FightRunHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command){
                case "run":
                    String decision = guiControllerObj.run();
                    if (decision.equalsIgnoreCase("fight"))
                        command = "fight";
                    else {
                        rePaint();
                        break;
                    }
                case "fight":
                    String result = guiControllerObj.fight();
                    switch (result){
                        case "lost":
                            lostBattleScreen(guiControllerObj.getBattleSimulation());
                            break;
                        case "won":
                            wonBattleScreen(guiControllerObj.getBattleSimulation());
                            break;
                    }
                    break;
            }

        }
    }


}
