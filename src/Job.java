public class Job {
    String name;
    int hit;
    int resistance;
    int stamina;

    Job(String name, int hit, int resistance, int stamina) {
        this.name = name;
        this.hit = hit;
        this.resistance = resistance;
        this.stamina = stamina;
    }
    void levelUP(){
        this.hit++;
        this.resistance++;
        this.stamina++;
    }
}
