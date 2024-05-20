public class strongZombie extends Zombie {
    public strongZombie(double strongHealth, double strongDamage, double strongSpeed) {
        super(strongHealth * 1.3, strongDamage * 1.3, strongSpeed * 0.7);
    }
    public String toString() {
        return "sZ";
    }

}
