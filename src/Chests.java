import java.util.Random;

public class Chests {

    int x_position;
    int y_position;
    private boolean was_taken = false;
    private int treasure;

    Chests(int x_position, int y_position) {
        this.x_position = x_position;
        this.y_position = y_position;

        Random generator = new Random();
        int random = generator.nextInt(155);

        if (random < 10)
            treasure = 0;
        else if (random < 20)
            treasure = 1;
        else if (random < 80)
            treasure = Mixtures.dropMixture();
        else if (random < 100)
            treasure = 8;
        else if (random < 120)
            treasure = 9;
        else if (random < 125)
            treasure = 10;
        else if (random < 135)
            treasure = 50;
        else if (random < 145)
            treasure = Rings.dropRing();
        else if (random < 150)
            treasure = Swords.dropSword();
        else if (random < 55)
            treasure = Armors.dropArmor();
    }

    void checkTreasure() {

        if (!was_taken) {
            if (Interface.inventory[9] == 0) {
                if (treasure == 0)
                    Interface.newEvent("There is nothing here.");
                else if (treasure > 0 && treasure < 8)
                    Interface.newItem(Mixtures.prevPotion(treasure - 1) + 1);
                else if (treasure == 8 || treasure == 9 || treasure == 10)
                    Interface.newItem(treasure);
                else if (treasure == 50) {
                    Items.scrolls += 1;
                    Interface.newEvent("I found an identify scroll!");
                } else if (treasure >= 11 && treasure <= 16) {
                    Interface.newItem(treasure);
                } else if (treasure == 21 || treasure == 22 || treasure == 23) {
                    Interface.newItem(treasure);
                } else if (treasure == 31 || treasure == 32 || treasure == 33) {
                    Interface.newItem(treasure);
                } else if (treasure == 41 || treasure == 42 || treasure == 43) {
                    Interface.newItem(treasure);
                }
                was_taken = true;
            } else
                Interface.newEvent("Inventory full!");
        } else
            Interface.newEvent("This chest is empty.");
    }
}
