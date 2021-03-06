import java.util.Arrays;
import java.util.Random;

public class Mixtures {

    static boolean[] mixtures_known = new boolean[7];
    private static int randomize;
    static int character_paralyze = 0;
    static int character_confused = 0;
    static int character_harm = 0;
    static int character_full = 0;
    private static Random generator = new Random();

    Mixtures() {
        Arrays.fill(mixtures_known, false);
        randomize = generator.nextInt(7);
    }

    static void mixtureType(int index) {

        int type = Math.floorMod((randomize + index), 7);
        String[] mixtures = new String[7];
        mixtures[0] = "confusion";
        mixtures[1] = "paralytic gas";
        mixtures[2] = "strength";
        mixtures[3] = "dexterity";
        mixtures[4] = "healing";
        mixtures[5] = "experience";
        mixtures[6] = "harm";
        String[] colors = new String[7];
        colors[0] = "yellow";
        colors[1] = "blue";
        colors[2] = "red";
        colors[3] = "purple";
        colors[4] = "green";
        colors[5] = "aqua";
        colors[6] = "orange";

        if (!mixtures_known[index])
            Interface.newEvent("Bottle of " + colors[index] + " mixture. Unknown effects.");
        else
            Interface.newEvent("It is potion of " + mixtures[type] + ". Are you sure you want to drink it?");
    }

    static void drinkPotion(int index) {

        int type = Math.floorMod((randomize + index), 7);
        if (type == 0) {
            Interface.newEvent("What is happening?");
            character_confused += 15;
        } else if (type == 1) {
            Interface.newEvent("Probably nothing happened ... I guess.");
            character_paralyze += 10;
        } else if (type == 2) {
            Interface.newEvent("New found strength surges through your body.");
            Character.strength_points += 2;
        } else if (type == 3) {
            Interface.newEvent("You feel much more skillful.");
            Character.dexterity_points += 2;
        } else if (type == 4) {
            Interface.newEvent("Delicious and refreshing. Do I have more of it?");
            Character.health_points = Character.max_health;
            character_paralyze = 0;
            character_confused = 0;
            character_harm = 0;
        } else if (type == 5) {
            Interface.newEvent("The enlightenment of the ancestors is coming upon you.");
            Character.experience(Character.next_level - Character.experience);
        } else if (type == 6) {
            Interface.newEvent("Pain stabs your body. It was a poison.");
            character_harm += 25;
        }
        mixtures_known[index] = true;
    }

    static void identify(int index) {
        mixtures_known[index] = true;
        mixtureType(index);
    }

    static int dropMixture() {

        int random = generator.nextInt(60);

        if (random < 10)
            return 2;
        else if (random < 15)
            return 3;
        else if (random < 20)
            return 4;
        else if (random < 45)
            return 5;
        else if (random < 50)
            return 6;
        else
            return 7;
    }

    static int prevPotion(int numOf) {
        return Math.floorMod((numOf - randomize), 7);
    }

    static int nextPotion(int numOf) {
        return Math.floorMod((numOf + randomize), 7);
    }
}
