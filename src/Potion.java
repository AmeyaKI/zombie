public class Potion extends Supply {
    private double healingCapacity;
    public Potion(double hC) {
        healingCapacity = hC;
    }
    public double getHealingCapacity() {
        return healingCapacity;
    }
    public String toString() {
        return "Potion";
    }


}
