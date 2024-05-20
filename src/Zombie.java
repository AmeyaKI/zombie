public class Zombie {
    private double zombieHealth;
    private double zombieAttackDamage;
    private double zombieSpeed;

    public Zombie(double health, double damage, double speed) {
        zombieHealth = health;
        zombieAttackDamage = damage;
        zombieSpeed = speed;
    }

    public void loseHealth(double damage) {
        zombieHealth -= damage;
    }
    public String getStats() {
        String stats =  "Zombie Health: " + zombieHealth;
        stats +=  "\nZombie Damage: " + zombieAttackDamage + "\nZombie Speed: " + zombieSpeed;
        return stats;
    }
    public double getHealth() {
        return zombieHealth;
    }

    public double getAttackValue() {
        return zombieAttackDamage;
    }

    public double getSpeed() {
        return zombieSpeed;
    }
    public String toString() {
        return "Zombie";
    }
}
