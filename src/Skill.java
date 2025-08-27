public class Skill {
    String title;
    int dmgBase;
    String type;
    int defEft;
    int cureEft;
    int furyEft;
    Character attribute;
    Job hability;

    Skill(String title, int dmgBase, String type, int defEft, int cureEft, int furyEft){
        this.title = title;
        this.dmgBase = dmgBase;
        this.type = type;
        this.defEft = defEft;
        this.cureEft = cureEft;
        this.furyEft = furyEft;
    }
    int calcDMG(Character atk, Character trg){
        int damage = dmgBase + (int)(1.2 * atk.hit) - trg.resistance;

        if (this.type.equals(trg.weakness)) {
            damage = (int)(damage * 1.5);
            System.out.println("Perfect!");
        }

        return Math.max(0, damage);
    }
}
