import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Job Sorcerer = new Job("Sorcerer", 20, 7, 10);
        Job Warrior = new Job("Warrior", 25, 12, 7);
        Job Rogue = new Job("Rogue", 18, 6, 15);
        Job Priest = new Job("Priest", 19, 10, 8);

        Skill BN = new Skill("Black Knife", 7, "sharp", 3, 4, 5);
        Skill LH = new Skill("Luminous Hammer", 6, "sacred", 5, 6, 3);

        Character C = new Character("char1", 80, 80, Rogue, "sacred", BN);
        Character C2 = new Character("char2", 100, 100, Priest, "sharp", LH);

        C.showStats();
        C2.showStats();

        System.out.println("FIGHT!");
        while (C.HP > 0 && C2.HP > 0) {
            C.strike(C, C2);
            C.strikeSkill(C.hability, C2);

            if (C2.HP <= 0) {
                System.out.println(C.name + " defeated " + C2.name + "\u2620");
                break;}

            C2.strike(C2, C);
            C2.strikeSkill(C2.hability, C);

            if (C.HP <= 0) {
                System.out.println(C2.name + " defeated " + C.name +  "\u2620");
                break;}
        }
    }
}