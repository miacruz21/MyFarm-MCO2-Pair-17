import java.awt.*;

import javax.swing.*;

public class seedInfo extends JFrame {
    private JLabel header;
    private JLabel name;
    private JLabel type;
    private JLabel seedPrice;
    private JLabel farmTime;
    private JLabel waterNeeds;
    private JLabel waterBonusLim;
    private JLabel fertilizerNeeds;
    private JLabel fertilizerBonusLim;
    private JLabel produceMin;
    private JLabel produceMax;
    private JLabel productsProduced;
    private JLabel sellingPrice;
    private JLabel xpGain;

    private Container container;

    public seedInfo(Seed seed) {

        setTitle("Seed Info");
        setBounds(1400, 100, 500, 800);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        header = new JLabel("Seed Info");
        header.setFont(new Font("Calibri", Font.BOLD, 25));
        header.setSize(200, 50);
        header.setLocation(200, 5);
        container.add(header);

        name = new JLabel("Name: " + seed.getName());
        name.setFont(new Font("Calibri", Font.BOLD, 25));
        name.setSize(300, 25);
        name.setLocation(25, 100);
        container.add(name);

        type = new JLabel("Type: " + seed.getType());
        type.setFont(new Font("Calibri", Font.BOLD, 25));
        type.setSize(300, 25);
        type.setLocation(25, 140);
        container.add(type);

        seedPrice = new JLabel("Seed Price: " + seed.getSeedPrice());
        seedPrice.setFont(new Font("Calibri", Font.BOLD, 25));
        seedPrice.setSize(300, 25);
        seedPrice.setLocation(25, 180);
        container.add(seedPrice);

        farmTime = new JLabel("Farm Time: " + seed.getFarmTime());
        farmTime.setFont(new Font("Calibri", Font.BOLD, 25));
        farmTime.setSize(300, 25);
        farmTime.setLocation(25, 220);
        container.add(farmTime);

        waterNeeds = new JLabel("Water Needs: " + seed.getWaterNeeds());
        waterNeeds.setFont(new Font("Calibri", Font.BOLD, 25));
        waterNeeds.setSize(300, 25);
        waterNeeds.setLocation(25, 260);
        container.add(waterNeeds);

        waterBonusLim = new JLabel("Water Bonus Limit: " + seed.getWaterBonusLim());
        waterBonusLim.setFont(new Font("Calibri", Font.BOLD, 25));
        waterBonusLim.setSize(300, 25);
        waterBonusLim.setLocation(25, 300);
        container.add(waterBonusLim);

        fertilizerNeeds = new JLabel("Fertilizer Needs: " + seed.getFertilizerNeeds());
        fertilizerNeeds.setFont(new Font("Calibri", Font.BOLD, 25));
        fertilizerNeeds.setSize(300, 25);
        fertilizerNeeds.setLocation(25, 340);
        container.add(fertilizerNeeds);

        fertilizerBonusLim = new JLabel("Fertilizer Bonus Limit: " + seed.getFertilizerBonusLim());
        fertilizerBonusLim.setFont(new Font("Calibri", Font.BOLD, 25));
        fertilizerBonusLim.setSize(300, 25);
        fertilizerBonusLim.setLocation(25, 380);
        container.add(fertilizerBonusLim);

        produceMin = new JLabel("Produce Min: " + seed.getProduceMin());
        produceMin.setFont(new Font("Calibri", Font.BOLD, 25));
        produceMin.setSize(300, 25);
        produceMin.setLocation(25, 420);
        container.add(produceMin);

        produceMax = new JLabel("Produce Max: " + seed.getProduceMax());
        produceMax.setFont(new Font("Calibri", Font.BOLD, 25));
        produceMax.setSize(300, 25);
        produceMax.setLocation(25, 460);
        container.add(produceMax);

        productsProduced = new JLabel("Products Produced: " + seed.getProductsProduced());
        productsProduced.setFont(new Font("Calibri", Font.BOLD, 25));
        productsProduced.setSize(300, 25);
        productsProduced.setLocation(25, 500);
        container.add(productsProduced);

        sellingPrice = new JLabel("Selling Price: " + seed.getSellingPrice());
        sellingPrice.setFont(new Font("Calibri", Font.BOLD, 25));
        sellingPrice.setSize(300, 25);
        sellingPrice.setLocation(25, 540);
        container.add(sellingPrice);

        xpGain = new JLabel("XP Gain: " + seed.getXpGain());
        xpGain.setFont(new Font("Calibri", Font.BOLD, 25));
        xpGain.setSize(300, 25);
        xpGain.setLocation(25, 580);
        container.add(xpGain);

        setVisible(true);
    }
}
