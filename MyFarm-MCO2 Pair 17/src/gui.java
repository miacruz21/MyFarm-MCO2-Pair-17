import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gui extends JFrame implements ActionListener{
    //variables
    private JLabel header;
    private JLabel stats;
    private Container container;
    private JButton button[][];
    private JButton regButton;
    private JButton advDayButton;
    private Tile[][] farmTiles;

    private JLayeredPane layeredPane;
    private ImageIcon farmbg;
    private JLabel background;

    private Player player1 = new Player(0, 100, 1, 0);;

    /*Creates the main gui
     */
    public Gui(){
        //container
        setTitle("MyFarm");
        setBounds(550, 250, 800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);
        
        //background
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 800, 500);

        farmbg = new ImageIcon("farmbg.jpg");
        background = new JLabel(farmbg);
        background.setSize(800, 500);
        background.setLocation(0, 0);
        background.setIcon(farmbg);

        //header
        header = new JLabel("MyFarm");
        header.setFont(new Font("Calibri", Font.BOLD, 25));
        header.setSize(200, 50);
        header.setLocation(345, 20);

        stats = new JLabel("Player Stats: \nFarmer type - " + 
        player1.getFarmerType().getName() + " | Level - " + player1.getLevel() + 
        " | XP - " + player1.getXp() + " | Objectcoins - " + player1.getObjectCoins() + 
        " | Day " + player1.getDay());
        stats.setFont(new Font("Arial", Font.BOLD, 15));
        stats.setSize(2000, 50);
        stats.setLocation(100, 50);

        //buttons
        button = new JButton[10][5];
        farmTiles = new Tile[10][5];
        regButton = new JButton("Register Farmer");
        advDayButton = new JButton("Advance to Next Day");

        regButton.setFocusable(false);
        regButton.addActionListener(this);
        regButton.setLocation(300, 90);
        regButton.setSize(170, 35);

        advDayButton.setFocusable(false);
        advDayButton.addActionListener(this);
        advDayButton.setLocation(300, 400);
        advDayButton.setSize(170, 35);

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 5; j++){
                farmTiles[i][j] = new Tile(false, false, false, false, 0, 0);
                button[i][j] = new JButton();
                button[i][j].setFont(new Font("Calibri", Font.BOLD, 25));
                button[i][j].setFocusable(false);
                button[i][j].addActionListener(this);
                button[i][j].setLocation(i*50+150, j*50+150);
                button[i][j].setSize(25, 25);

                button[i][j].setBackground(new Color(170, 100, 50)); //brown for unplowed
                layeredPane.add(button[i][j]);
            }
        }

        //generating rocks
        farmTiles[0][2].setRocks(true);
        farmTiles[7][2].setRocks(true);
        farmTiles[4][4].setRocks(true);
        farmTiles[3][4].setRocks(true);
        farmTiles[6][1].setRocks(true);
        farmTiles[8][0].setRocks(true);
        farmTiles[2][2].setRocks(true);
        farmTiles[1][3].setRocks(true);
        farmTiles[9][2].setRocks(true);
        farmTiles[3][1].setRocks(true);
        farmTiles[5][4].setRocks(true);
        farmTiles[5][2].setRocks(true);
        farmTiles[0][3].setRocks(true);
        farmTiles[6][3].setRocks(true);
        farmTiles[0][0].setRocks(true);
        farmTiles[9][3].setRocks(true);
        farmTiles[2][1].setRocks(true);

        check();
        
        layeredPane.add(regButton);
        layeredPane.add(advDayButton);
        layeredPane.add(header);
        layeredPane.add(stats);
        layeredPane.add(background);
        container.add(layeredPane);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // TODO Auto-generated method stub
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 5; j++){
                if(event.getSource() == button[i][j]) {
                    new tileInfo(farmTiles[i][j], button[i][j]);
                }
            }
        }
        if (event.getSource() == advDayButton) {
            int advDaychoice = JOptionPane.showConfirmDialog(this, "Would you like to advance to the next day?", 
                        "Next Day?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (advDaychoice == 0) {
                for(int i = 0; i < 10; i++){
                    for(int j = 0; j < 5; j++){
                        if (farmTiles[i][j].getSeed() != null) {
                            player1.advanceDay(farmTiles[i][j]);
                            player1.checkWithered(farmTiles[i][j]);
                        }
                    }
                }
                player1.setDay(player1.getDay()+1);
                JOptionPane.showMessageDialog(this, "Good morning! It is now Day " + 
                        player1.getDay(), "Next Day", JOptionPane.INFORMATION_MESSAGE);
                if (player1.levelUp())
                    JOptionPane.showMessageDialog(this, "Congratulations! You are now Level " + 
                                player1.getLevel(), "Level Up!", JOptionPane.WARNING_MESSAGE);
                stats.setText("Player Stats: \nFarmer type - " + 
                player1.getFarmerType().getName() + " | Level - " + player1.getLevel() + 
                " | XP - " + player1.getXp() + " | Objectcoins - " + player1.getObjectCoins() + 
                " | Day " + player1.getDay());
                
            }
        }
        if (event.getSource() == regButton) {
            new regFarmer();
        }

        check();
    }

    public void check(){
        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                if (farmTiles[i][j].getRocks()) {
                    button[i][j].setBackground(Color.GRAY);
                } else if (farmTiles[i][j].getWither()) {
                    button[i][j].setBackground(Color.BLACK);
                } else if (farmTiles[i][j].getPlowed()) {
                    if (farmTiles[i][j].getSeed().getName().equals("Turnip")) {
                        button[i][j].setBackground(Color.GREEN);
                    } else if(farmTiles[i][j].getSeed().getName().equals("Carrot")){
                    button[i][j].setBackground(Color.CYAN);
                    } else if(farmTiles[i][j].getSeed().getName().equals("Potato")){
                    button[i][j].setBackground(Color.ORANGE);
                    } else if(farmTiles[i][j].getSeed().getName().equals("Rose")){
                    button[i][j].setBackground(Color.MAGENTA);
                    } else if(farmTiles[i][j].getSeed().getName().equals("Turnips")){
                    button[i][j].setBackground(Color.BLUE);
                    } else if(farmTiles[i][j].getSeed().getName().equals("Sunflower")){
                    button[i][j].setBackground(Color.YELLOW);
                    } else if(farmTiles[i][j].getSeed().getName().equals("Mango")){
                    button[i][j].setBackground(Color.PINK);
                    } else if(farmTiles[i][j].getSeed().getName().equals("Apple")){
                    button[i][j].setBackground(Color.RED);
                }else
                    button[i][j].setBackground(Color.ORANGE);
                } else {
                    button[i][j].setBackground(Color.WHITE);
                }
                
                
                }
            }
        }

    /*Creates the tileInfo window separate of the main gui
    */
    public class tileInfo extends JFrame implements ActionListener {
        private JLabel header;
        private JLabel currPlant;
        private JLabel plowed;
        private JLabel wither;
        private JLabel timesWatered;
        private JLabel timesFertilized;
    
        private JButton info;
        private JButton water;
        private JButton plow;
        private JButton shovel;
        private JButton pickaxe;
    
        private JButton fertilize;
        private JButton harvest;
        private JButton plant;
    
        private Container container;
    
        private Tile tile;
        private JButton button;
    
        public tileInfo(Tile tile, JButton button) {
            this.tile = tile;
            this.button = button;
    
            setTitle("Tile Info");
            setBounds(0, 100, 500, 800);
            setDefaultCloseOperation(HIDE_ON_CLOSE);
            setResizable(false);
    
            container = getContentPane();
            container.setLayout(null);
    
            header = new JLabel("Tile Info");
            header.setFont(new Font("Calibri", Font.BOLD, 25));
            header.setSize(200, 50);
            header.setLocation(200, 5);
            container.add(header);
    
            currPlant = new JLabel();
            if(tile.getRocks()){
                currPlant.setText("Plant: Rock");
            } else if(tile.getSeed() != null) {
                currPlant.setText("Plant: " + tile.getSeed().getName());
            } else 
                currPlant.setText("Plant: Empty");
            currPlant.setFont(new Font("Calibri", Font.BOLD, 25));
            currPlant.setSize(200, 25);
            currPlant.setLocation(25, 100);
            container.add(currPlant);
    
            plowed = new JLabel("Plowed: " + tile.getPlowed());
            plowed.setFont(new Font("Calibri", Font.BOLD, 25));
            plowed.setSize(200, 25);
            plowed.setLocation(25, 140);
            container.add(plowed);
    
            wither = new JLabel("Withered: " + tile.getWither());
            wither.setFont(new Font("Calibri", Font.BOLD, 25));
            wither.setSize(200, 25);
            wither.setLocation(25, 180);
            container.add(wither);
    
            timesWatered = new JLabel("Times Watered: " + tile.getTimesWatered());
            timesWatered.setFont(new Font("Calibri", Font.BOLD, 25));
            timesWatered.setSize(200, 25);
            timesWatered.setLocation(25, 220);
            container.add(timesWatered);
    
            timesFertilized = new JLabel("Times Fertilized: " + tile.getTimesFertilized());
            timesFertilized.setFont(new Font("Calibri", Font.BOLD, 25));
            timesFertilized.setSize(200, 25);
            timesFertilized.setLocation(25, 260);
            container.add(timesFertilized);
    
            info = new JButton("Plant's Info");
            info.setFocusable(false);
            info.setFont(new Font("Calibri", Font.BOLD, 25));
            info.setSize(200, 25);
            info.setLocation(25, 300);
            info.addActionListener(this);
            container.add(info);
    
            plow = new JButton("Plow");
            plow.setFocusable(false);
            plow.setFont(new Font("Calibri", Font.BOLD, 25));
            plow.setSize(200, 25);
            plow.setLocation(25, 340);
            plow.addActionListener(this);
            container.add(plow);
    
            water = new JButton("Water");
            water.setFocusable(false);
            water.setFont(new Font("Calibri", Font.BOLD, 25));
            water.setSize(200, 25);
            water.setLocation(25, 380);
            water.addActionListener(this);
            container.add(water);
    
            shovel = new JButton("Shovel");
            shovel.setFocusable(false);
            shovel.setFont(new Font("Calibri", Font.BOLD, 25));
            shovel.setSize(200, 25);
            shovel.setLocation(25, 420);
            shovel.addActionListener(this);
            container.add(shovel);
    
            pickaxe = new JButton("Pickaxe");
            pickaxe.setFocusable(false);
            pickaxe.setFont(new Font("Calibri", Font.BOLD, 25));
            pickaxe.setSize(200, 25);
            pickaxe.setLocation(25, 460);
            pickaxe.addActionListener(this);
            container.add(pickaxe);
    
            fertilize = new JButton("Fertilize");
            fertilize.setFocusable(false);
            fertilize.setFont(new Font("Calibri", Font.BOLD, 25));
            fertilize.setSize(200, 25);
            fertilize.setLocation(25, 500);
            fertilize.addActionListener(this);
            container.add(fertilize);
    
            harvest = new JButton("Harvest");
            harvest.setFocusable(false);
            harvest.setFont(new Font("Calibri", Font.BOLD, 25));
            harvest.setSize(200, 25);
            harvest.setLocation(25, 540);
            harvest.addActionListener(this);
            container.add(harvest);
    
            plant = new JButton("Plant");
            plant.setFocusable(false);
            plant.setFont(new Font("Calibri", Font.BOLD, 25));
            plant.setSize(200, 25);
            plant.setLocation(25, 580);
            plant.addActionListener(this);
            container.add(plant);
    
            setVisible(true);
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            ActionStrategy actionStrategy = null;
            
            if(e.getSource() == info) {
                actionStrategy = new InfoActionStrategy();
            } else if(e.getSource() == plow) {
                actionStrategy = new PlowActionStrategy();
            } else if(e.getSource() == water) {
                actionStrategy = new WaterActionStrategy();
            } else if(e.getSource() == shovel) {
                actionStrategy = new ShovelActionStrategy();
            } else if(e.getSource() == pickaxe) {
                actionStrategy = new PickaxeActionStrategy();
            } else if(e.getSource() == fertilize) {
                actionStrategy = new FertilizeActionStrategy();
            } else if(e.getSource() == harvest) {
                actionStrategy = new HarvestActionStrategy();
            } else if(e.getSource() == plant) {
                if (tile.getWither()) {
                    JOptionPane.showMessageDialog(this, "The tile has withered, we might need to use Shovel", "Withered", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } 
                
                if (tile.getRocks()) {
                    JOptionPane.showMessageDialog(this, "We cannot plant the tile because there is a rock on it, we night need to use Pickaxe", "Rock", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                
                if (tile.getSeed() != null) {
                    JOptionPane.showMessageDialog(this, "There is a seed in this tile, we might need to use Shovel", "Seed", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
        
                if (!tile.getPlowed()) {
                    JOptionPane.showMessageDialog(this, "The tile is not plowed yet, please use Plow", "Need Plow", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
        
                new plantSeed(tile, button);
            }

            if (actionStrategy != null) {
                actionStrategy.execute(player1, tile, this, button, stats);
            }
    
            if(tile.getRocks()){
                currPlant.setText("Plant: Rock");
            } else if(tile.getSeed() != null) {
                currPlant.setText("Plant: " + tile.getSeed().getName());
            } else {
                currPlant.setText("Plant: Empty");
            }
    
            plowed.setText("Plowed: " + tile.getPlowed());
            wither.setText("Withered: " + tile.getWither());
            timesWatered.setText("Times Watered: " + tile.getTimesWatered());
            timesFertilized.setText("Times Fertilized: " + tile.getTimesFertilized());
        }

        JLabel getTimesFertilized() {
            return timesFertilized;
        }
    }

    /*Creates the window to plant a seed in a tile
     */
    public class plantSeed extends JFrame implements ActionListener{
        private JLabel header;
        private JLabel turnip;
        private JLabel carrot;
        private JLabel potato;
        private JLabel rose;
        private JLabel turnips;
        private JLabel sunflower;
        private JLabel mango;
        private JLabel apple;

        private JButton turnipButt;
        private JButton carrotButt;
        private JButton potatoButt;
        private JButton roseButt;
        private JButton turnipsButt;
        private JButton sunflowerButt;
        private JButton mangoButt;
        private JButton appleButt;

        private Container container;

        private Tile tile;
        private JButton button;

        public plantSeed(Tile tile, JButton button) {
            this.tile = tile;
            this.button = button;
            
            setTitle("Plant Seed");
            setBounds(0, 100, 800, 600);
            setDefaultCloseOperation(HIDE_ON_CLOSE);
            setResizable(false);
            
            container = getContentPane();
            container.setLayout(null);

            header = new JLabel("Choose a seed to Plant:");
            header.setFont(new Font("Calibri", Font.BOLD, 25));
            header.setSize(500, 25);
            header.setLocation(25, 40);
            container.add(header);

            turnip = new JLabel("Turnip (Type: Root crop | Cost: 5)");
            turnip.setFont(new Font("Calibri", Font.BOLD, 25));
            turnip.setSize(500, 25);
            turnip.setLocation(25, 100);
            container.add(turnip);
            turnipButt = new JButton("Plant Turnip");
            turnipButt.setFocusable(false);
            turnipButt.addActionListener(this);
            turnipButt.setLocation(420, 100);
            turnipButt.setSize(170, 30);
            container.add(turnipButt);

            carrot = new JLabel("Carrot (Type: Root crop | Cost: 10)");
            carrot.setFont(new Font("Calibri", Font.BOLD, 25));
            carrot.setSize(500, 25);
            carrot.setLocation(25, 150);
            container.add(carrot);
            carrotButt = new JButton("Plant Carrot");
            carrotButt.setFocusable(false);
            carrotButt.addActionListener(this);
            carrotButt.setLocation(420, 150);
            carrotButt.setSize(170, 30);
            container.add(carrotButt);

            potato = new JLabel("Potato (Type: Root crop | Cost: 20)");
            potato.setFont(new Font("Calibri", Font.BOLD, 25));
            potato.setSize(500, 25);
            potato.setLocation(25, 200);
            container.add(potato);
            potatoButt = new JButton("Plant Potato");
            potatoButt.setFocusable(false);
            potatoButt.addActionListener(this);
            potatoButt.setLocation(420, 200);
            potatoButt.setSize(170, 30);
            container.add(potatoButt);

            rose = new JLabel("Rose (Type: Flower | Cost: 5)");
            rose.setFont(new Font("Calibri", Font.BOLD, 25));
            rose.setSize(500, 25);
            rose.setLocation(25, 250);
            container.add(rose);
            roseButt = new JButton("Plant Rose");
            roseButt.setFocusable(false);
            roseButt.addActionListener(this);
            roseButt.setLocation(420, 250);
            roseButt.setSize(170, 30);
            container.add(roseButt);

            turnips = new JLabel("Turnips (Type: Flower | Cost: 10)");
            turnips.setFont(new Font("Calibri", Font.BOLD, 25));
            turnips.setSize(500, 25);
            turnips.setLocation(25, 300);
            container.add(turnips);
            turnipsButt = new JButton("Plant Turnips");
            turnipsButt.setFocusable(false);
            turnipsButt.addActionListener(this);
            turnipsButt.setLocation(420, 300);
            turnipsButt.setSize(170, 30);
            container.add(turnipsButt);

            sunflower = new JLabel("Sunflower (Type: Flower | Cost: 20)");
            sunflower.setFont(new Font("Calibri", Font.BOLD, 25));
            sunflower.setSize(500, 25);
            sunflower.setLocation(25, 350);
            container.add(sunflower);
            sunflowerButt = new JButton("Plant Sunflower");
            sunflowerButt.setFocusable(false);
            sunflowerButt.addActionListener(this);
            sunflowerButt.setLocation(420, 350);
            sunflowerButt.setSize(170, 30);
            container.add(sunflowerButt);

            mango = new JLabel("Mango (Type: Fruit tree | Cost: 100))");
            mango.setFont(new Font("Calibri", Font.BOLD, 25));
            mango.setSize(500, 25);
            mango.setLocation(25, 400);
            container.add(mango);
            mangoButt = new JButton("Plant Mango");
            mangoButt.setFocusable(false);
            mangoButt.addActionListener(this);
            mangoButt.setLocation(420, 400);
            mangoButt.setSize(170, 30);
            container.add(mangoButt);

            apple = new JLabel("Apple (Type: Fruit tree | Cost: 200)");
            apple.setFont(new Font("Calibri", Font.BOLD, 25));
            apple.setSize(500, 25);
            apple.setLocation(25, 450);
            container.add(apple);
            appleButt = new JButton("Plant Apple");
            appleButt.setFocusable(false);
            appleButt.addActionListener(this);
            appleButt.setLocation(420, 450);
            appleButt.setSize(170, 30);
            container.add(appleButt);

            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == turnipButt) {
                if (player1.getObjectCoins() >= 5) {
                    player1.plant(tile, 1);
                    JOptionPane.showMessageDialog(this, "Succesfully planted "+tile.getSeed().getName()+"!", 
                                                "Success", JOptionPane.INFORMATION_MESSAGE);
                    button.setBackground(Color.GREEN);
                } else
                JOptionPane.showMessageDialog(this, "You don't have enough Objectcoins to plant this seed", 
                                                "Not Enough Money", JOptionPane.ERROR_MESSAGE);
            } else if (e.getSource() == carrotButt) {
                if (player1.getObjectCoins() >= 10) {
                    player1.plant(tile, 2);
                    JOptionPane.showMessageDialog(this, "Succesfully planted "+tile.getSeed().getName()+"!", 
                                                "Success", JOptionPane.INFORMATION_MESSAGE);
                    button.setBackground(Color.CYAN);
                } else
                JOptionPane.showMessageDialog(this, "You don't have enough Objectcoins to plant this seed", 
                                                "Not Enough Money", JOptionPane.ERROR_MESSAGE);
            } else if (e.getSource() == potatoButt) {
                if (player1.getObjectCoins() >= 20) {
                    player1.plant(tile, 3);
                    JOptionPane.showMessageDialog(this, "Succesfully planted "+tile.getSeed().getName()+"!", 
                                                "Success", JOptionPane.INFORMATION_MESSAGE);
                    button.setBackground(Color.ORANGE);                          
                } else
                JOptionPane.showMessageDialog(this, "You don't have enough Objectcoins to plant this seed", 
                                                "Not Enough Money", JOptionPane.ERROR_MESSAGE);
            } else if (e.getSource() == roseButt) {
                if (player1.getObjectCoins() >= 5) {
                    player1.plant(tile, 4);
                    JOptionPane.showMessageDialog(this, "Succesfully planted "+tile.getSeed().getName()+"!", 
                                                "Success", JOptionPane.INFORMATION_MESSAGE);
                    button.setBackground(Color.MAGENTA);
                } else
                JOptionPane.showMessageDialog(this, "You don't have enough Objectcoins to plant this seed", 
                                                "Not Enough Money", JOptionPane.ERROR_MESSAGE);
            } else if (e.getSource() == turnipsButt) {
                if (player1.getObjectCoins() >= 10) {
                    player1.plant(tile, 5);
                    JOptionPane.showMessageDialog(this, "Succesfully planted "+tile.getSeed().getName()+"!", 
                                                "Success", JOptionPane.INFORMATION_MESSAGE);
                    button.setBackground(Color.BLUE);
                } else
                JOptionPane.showMessageDialog(this, "You don't have enough Objectcoins to plant this seed", 
                                                "Not Enough Money", JOptionPane.ERROR_MESSAGE);
            } else if (e.getSource() == sunflowerButt) {
                if (player1.getObjectCoins() >= 20) {
                    player1.plant(tile, 6);
                    JOptionPane.showMessageDialog(this, "Succesfully planted "+tile.getSeed().getName()+"!", 
                                                "Success", JOptionPane.INFORMATION_MESSAGE);
                    button.setBackground(Color.YELLOW);
                } else
                JOptionPane.showMessageDialog(this, "You don't have enough Objectcoins to plant this seed", 
                                                "Not Enough Money", JOptionPane.ERROR_MESSAGE);
            } else if (e.getSource() == mangoButt) {
                if (player1.getObjectCoins() >= 100) {
                    player1.plant(tile, 7);
                    JOptionPane.showMessageDialog(this, "Succesfully planted "+tile.getSeed().getName()+"!", 
                                                "Success", JOptionPane.INFORMATION_MESSAGE);
                    button.setBackground(Color.PINK);
                } else
                JOptionPane.showMessageDialog(this, "You don't have enough Objectcoins to plant this seed", 
                                                "Not Enough Money", JOptionPane.ERROR_MESSAGE);
            } else if (e.getSource() == appleButt) {
                if (player1.getObjectCoins() >= 200) {
                    player1.plant(tile, 8);
                    JOptionPane.showMessageDialog(this, "Succesfully planted "+tile.getSeed().getName()+"!", 
                                                "Success", JOptionPane.INFORMATION_MESSAGE);
                    button.setBackground(Color.RED);
                } else
                JOptionPane.showMessageDialog(this, "You don't have enough Objectcoins to plant this seed", 
                                                "Not Enough Money", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    /*Creates the window to register farmer type
     */
    public class regFarmer extends JFrame implements ActionListener{
        private JLabel header;
        private JLabel currStats;
        private JLabel nextStats;
        private JButton register;

        private Container container;

        public regFarmer() {
            setTitle("Farmer Type");
            setBounds(0, 100, 1000, 500);
            setDefaultCloseOperation(HIDE_ON_CLOSE);
            setResizable(false);
            

            container = getContentPane();
            container.setLayout(null);

            header = new JLabel("Farmer Type Registry");
            header.setFont(new Font("Calibri", Font.BOLD, 25));
            header.setSize(2000, 25);
            header.setLocation(25, 100);
            container.add(header);

            currStats = new JLabel("Current Farmer Type: "+player1.getFarmerType().getName()+
                " | Bonus Earnings - "+player1.getFarmerType().getEarningsBonus()+" | Seed cost Discount - "+
                player1.getFarmerType().getSeedCostReduce());
            currStats.setFont(new Font("Calibri", Font.BOLD, 25));
            currStats.setSize(2000, 25);
            currStats.setLocation(25, 180);
            container.add(currStats);

            nextStats = new JLabel();
            if (player1.getFarmerType().getName().equals("Farmer")) {
                nextStats.setText("Next Farmer Type: Registered Farmer | Required Level - 5 |"+
                    " Registration Fee - 200.00");
            } else if (player1.getFarmerType().getName().equals("Registered Farmer")) {
                nextStats.setText("Next Farmer Type: Distinguished Farmer | Required Level - 10 |"+
                    " Registration Fee - 300.00");
            } else if (player1.getFarmerType().getName().equals("Distinguished Farmer")) {
                nextStats.setText("Next Farmer Type: Legendary Farmer | Required Level - 15 |"+
                    " Registration Fee - 400.00");
            } else
                nextStats.setText("Top of the world...in farming");
            nextStats.setFont(new Font("Calibri", Font.BOLD, 25));
            nextStats.setSize(2000, 25);
            nextStats.setLocation(25, 220);
            container.add(nextStats);

            register = new JButton("Register for next type");
            register.setFocusable(false);
            register.addActionListener(this);
            register.setLocation(400, 325);
            register.setSize(170, 50);
            container.add(register);

            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == register) {
                if (player1.getFarmerType().getName().equals("Farmer")) {
                   if (player1.getObjectCoins() < 200.00 || player1.getLevel() < 5) {
                    JOptionPane.showMessageDialog(this, "You don't have enough Objectoins or are not high enough level to register this type!", "Not Qualified", JOptionPane.ERROR_MESSAGE);
                   } else {
                    player1.register(1);
                    JOptionPane.showMessageDialog(this, "Congratulations! You are now a "+player1.getFarmerType().getName()+"!", "Registered!", JOptionPane.INFORMATION_MESSAGE);
                    stats.setText("Player Stats: \nFarmer type - " + 
                        player1.getFarmerType().getName() + " | Level - " + player1.getLevel() + 
                        " | XP - " + player1.getXp() + " | Objectcoins - " + player1.getObjectCoins() + 
                        " | Day " + player1.getDay());
                   }
                } else if (player1.getFarmerType().getName().equals("Registered Farmer")) {
                    if (player1.getObjectCoins() < 300.00 || player1.getLevel() < 10) {
                     JOptionPane.showMessageDialog(this, "You don't have enough Objectoins or are not high enough level to register this type!", "Not Qualified", JOptionPane.ERROR_MESSAGE);
                    } else {
                     player1.register(2);
                     JOptionPane.showMessageDialog(this, "Congratulations! You are now a "+player1.getFarmerType().getName()+"!", "Registered!", JOptionPane.INFORMATION_MESSAGE);
                     stats.setText("Player Stats: \nFarmer type - " + 
                        player1.getFarmerType().getName() + " | Level - " + player1.getLevel() + 
                        " | XP - " + player1.getXp() + " | Objectcoins - " + player1.getObjectCoins() + 
                        " | Day " + player1.getDay());
                    }
                } else if (player1.getFarmerType().getName().equals("Distinguished Farmer")) {
                    if (player1.getObjectCoins() < 400.00 || player1.getLevel() < 15) {
                     JOptionPane.showMessageDialog(this, "You don't have enough Objectoins or are not high enough level to register this type!", "Not Qualified", JOptionPane.ERROR_MESSAGE);
                    } else {
                     player1.register(3);
                     JOptionPane.showMessageDialog(this, "Congratulations! You are now a "+player1.getFarmerType().getName()+"!", "Registered!", JOptionPane.INFORMATION_MESSAGE);
                     stats.setText("Player Stats: \nFarmer type - " + 
                        player1.getFarmerType().getName() + " | Level - " + player1.getLevel() + 
                        " | XP - " + player1.getXp() + " | Objectcoins - " + player1.getObjectCoins() + 
                        " | Day " + player1.getDay());
                    }
                } else
                    JOptionPane.showMessageDialog(this, "You are already the best farmer!", "Suffering from Success", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}