public class Weapon extends Supply {
    private double weaponDamage;
    private double weaponSpeed;

    public Weapon(double wD, double wS) {
        weaponDamage = wD;
        weaponSpeed = wS;
    }

    public double getWeaponDamage() {
        return weaponDamage;
    }
    public double getWeaponSpeed() {
        return weaponSpeed;
    }

    public String toString() {
        return "Weapon";
    }



}
