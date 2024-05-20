import java.util.*;
public class Player {
    private String playerName = "";
    private double playerHealth;
    private double playerAttackDamage;
    private double playerSpeed;
    // Location
    private static int row = (int) (Math.random()*6);;
    private static int col = 0;

    private ArrayList<Supply> playerInventory;


    public Player(String str) {
        playerName = str;
        playerInventory = new ArrayList<Supply>();
        playerHealth = 25.0;
        playerAttackDamage = 5.0;
        playerSpeed = 5.0;
    }

    // Main Player Functions
    public boolean canMove(String direction) { // RIGHT, LEFT, UP, DOWN
        if(direction.equalsIgnoreCase("right")) {
            return col + 1 < Main.map[0].length;
        }
        else if (direction.equalsIgnoreCase("left")) {
            return col - 1 >= 0;
        }
        else if (direction.equalsIgnoreCase("up")) {
            return row - 1 >= 0;
        }
        else if (direction.equalsIgnoreCase("down")) {
            return row + 1 < Main.map.length;
        } else {
            return false;
        }
    }

    public void move(String direction) {
        if(direction.equalsIgnoreCase("right")) {
            col += 1;

        }
        else if (direction.equalsIgnoreCase("left")) {
            col -= 1;
        }
        else if (direction.equalsIgnoreCase("up")) {
            row -= 1;
        }
        else if (direction.equalsIgnoreCase("down")) {
            row += 1;
        }
    }



    public void loseHealth(double damage) {
        playerHealth -= damage;
    }


    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }

    public String getStats() {
        String stats =  "Player Name: " + playerName + "\nPlayer Health: " + playerHealth;
        stats +=  "\nPlayer Damage: " + playerAttackDamage + "\nPlayer Speed: " + playerSpeed;
        return stats;
    }
    public String printInventory() {
        String inventory = "Inventory: ";
        if(playerInventory.size() == 0) {
            return "Empty";
        }
        for(Supply L : playerInventory) {
            if(L instanceof Weapon) {
                inventory += "Weapon" + ", ";
            } else if (L instanceof Potion) {
                inventory += "Potion" + ", ";
            }
        }
        return inventory;
    }

    public String getName() {
        return playerName;
    }

    public double getHealth() {
        return playerHealth;
    }

    public double getAttackValue() {
        return playerAttackDamage;
    }

    public double getSpeed() {
        return playerSpeed;
    }

    public ArrayList<Supply> getInventory() {
        return playerInventory;
    }


    // Supply Functions
    public void collectItem(Supply s) {
        playerInventory.add(s);
    }

    public void removeItem(Supply s) {
        playerInventory.remove(s);
    }



    public String useWeapon() {
        String result = "";
        for(int i = 0; i < playerInventory.size(); i++) {
            Supply s = playerInventory.get(i);
            if(s instanceof Weapon) {
                playerAttackDamage += s.getWeaponDamage();
                playerSpeed += s.getWeaponSpeed();
                playerInventory.remove(s);
                result = "Weapon used.\nPlayer Damage increased by " + s.getWeaponDamage() +
                        "\nPlayer Speed increased by " + s.getWeaponSpeed();
                return result;
            }
        }
        result = "Uh-oh. No weapon in inventory. Action unable to be completed.";
        return result;
    }

    public String usePotion() {
        String result = "";
        for(int i = 0; i < playerInventory.size(); i++) {
            Supply s = playerInventory.get(i);
            if(s instanceof Potion) {
                playerHealth += s.getHealingCapacity();
                playerInventory.remove(s);
                result = "Potion used. Player Health increased by " + s.getHealingCapacity();
                return result;
            }
        }
        result = "Uh-oh. No potion in inventory. Action unable to be completed.";
        return result;
    }




    public String toString() {
        return "P ";
    }

}






