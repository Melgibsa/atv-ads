public class Character {
    String name;
    int HP;
    int HPMax;
    int level;
    int resistance;
    int hit;
    int HPBeforeDeath;
    Job category;
    Skill hability;
    String weakness;

    boolean effects = false;

    Character(String name, int HPMax, int HP, Job category, String weakness, Skill hability){
        this.name = name;
        this.HPMax = this.HP = HP + category.stamina;
        this.HP = HP;
        this.level = 1;
        this.category = category;
        this.resistance = category.resistance;
        this.hit = category.hit;
        this.hability = hability;
        this.weakness = this.hability.type;
    }

    void showStats(){
        System.out.println("Character name: " + name);
        System.out.println("Class: " + category.name);
        System.out.println("STATUS" + "\n" + "Level: " + level + "\n"
                + "HP: " + HP + "\n" + "Max HP: " + HPMax + "\n" + "Resistance: " + resistance + "\n"
                + "Stamina: " + category.stamina + "\n" + "Weakness: " + hability.type + "\n" + "Skill: " + hability.title
                + "(" + "base damage: " + hability.dmgBase + ", " + "type: " + hability.type + ", " +
                "defense effect: " + hability.defEft + ", "
                + "cure effect: " + hability.cureEft + ", "+ "cure effect: " + hability.furyEft + ")" + "\n");
    }
    void sofrerDano(int dano){
        if (HP > 0) {
            HPBeforeDeath = HP;
        }
        if(dano > HP){
            HP = 0;
        } else {
            HP = HP - dano;
        }
    }
    void update(){
        System.out.println("STATUS" + "\n" + "Level: " + level + "\n"
                + "HP: " + HP + "\n" + "Max HP: " + HPMax + "\n");
    }
    void resurrection(){
        if(HP == 0){
            HP = HPBeforeDeath;
        }
    }
    int getHit() {
        return HPMax / 10;
    }
    void strike(Character C, Character C2){
        int dmg = C.getHit()*2 - C2.resistance;

        System.out.println(C.name + " strikes " + C2.name + " causing " + dmg + " damage!");
        C2.sofrerDano(dmg);
    }
    void strikeSkill(Skill skill, Character target) {
        int dmg = skill.calcDMG(this, target);
        System.out.println(this.name + " uses " + skill.title + " on " + target.name);
        System.out.println("causing " + dmg + " damage!");
        target.sofrerDano(dmg);

        if(!target.effects) {
            if (target.HP <= target.HPMax * 0.5) {
                target.HP += skill.cureEft;
                category.resistance += skill.defEft;
                System.out.println("The fighter " + target.name + " activates cure effect!");
                System.out.println("The fighter " + target.name + " activates defense effect!");
            }

            if (target.HP <= target.HPMax * 0.2f) {
                category.hit += skill.furyEft;
                category.stamina += skill.furyEft;
                System.out.println("The fighter " + target.name + " activates fury effect!");
            }
            target.effects = true;
        }
    }
}
