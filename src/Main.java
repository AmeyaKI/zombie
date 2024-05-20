import java.util.*;

public class Main {
    public static Object[][] map = new Object[10][10];
    public static boolean gameStatus; // True means game in progress - exit not reached
    public static Scanner console = new Scanner(System.in);
    public static void main(String[] args)  {
        System.out.print("Enter \"Ready\" if ready to begin game: ");
        String begin = console.next();
        if(!begin.equals("Ready")) {
            System.out.println("Error. Restart Game.");
            System.exit(0);
        }
        System.out.print("Enter your name: ");
        String name = console.next();
        Player P = new Player(name);
        System.out.println("Welcome " + name + "!");

        // Initialize MAP
        generateMapFeatures();
        map[P.getRow()][0] = P;
        map[9][9] = "EX";
        System.out.println("Game starts in....\n3\n2\n1\n");

        gameStatus = true;
        while(gameStatus) {
            boolean moveo = true;
            while(moveo) {
                System.out.println("Enter \"left\", \"right\", \"up\", or \"down\" to move.");
                printMap();
                System.out.print("Move: ");
                String n = console.next();
                boolean validMove = P.canMove(n);
                if(validMove) {
                    int previousRow = P.getRow();
                    int previousCol = P.getCol();
                    P.move(n);
                    Object cell = map[P.getRow()][P.getCol()];
                    if(cell.equals("__")) {
                        System.out.println("Moved to empty space. No fighting required.");
                    } else if(cell instanceof Zombie) {
                        if(cell instanceof strongZombie) {
                            strongZombie sZom = (strongZombie) cell;
                            System.out.println("Strong Zombie encountered! Time to fight.");
                            boolean result = attack(P, sZom);
                            if(result) { // result = true, player kills zombie & lives
                                System.out.println("Outcome: Zombie slain!");
                            }
                            else { // result = false, player dies
                                System.out.println("Outcome: Player killed.\nYou lose.\nGame over!");
                                System.exit(0);
                            }
                        } else if(cell instanceof fastZombie) {
                            fastZombie fZom = (fastZombie) cell;
                            System.out.println("fast Zombie encountered! Time to fight.");
                            boolean result = attack(P, fZom);
                            if(result) { // result = true, player kills zombie & lives
                                System.out.println("Outcome: Zombie slain!");
                            }
                            else { // result = false, player dies
                                System.out.println("Outcome: Player killed.\nYou lose.\nGame over!");
                                System.exit(0);
                            }
                        } else { // normal Zombie
                            Zombie zom = (Zombie) cell;
                            System.out.println("Regular Zombie encountered! Time to fight.");
                            boolean result = attack(P, zom);
                            if(result) { // result = true, player kills zombie & lives
                                System.out.println("Outcome: Zombie slain!");
                            }
                            else { // result = false, player dies
                                System.out.println("Outcome: Player killed.\nYou lose.\nGame over!");
                                System.exit(0);
                            }
                        }
                    } else if(cell instanceof Supply) {
                        if(cell instanceof Weapon) {
                            Weapon colW = (Weapon) cell;
                            P.collectItem(colW);
                            System.out.println("You've successfully collected a weapon!");
                            System.out.println("It has a permanent damage enhancement of " + colW.getWeaponDamage() +
                                    " and a permanent speed enhancement of " + colW.getWeaponSpeed());
                        } else if(cell instanceof Potion) {
                            Potion po = (Potion) cell;
                            P.collectItem(po);
                            System.out.println("You've successfully collected a potion!");
                            System.out.println("It has a permanent health enhancement of " + po.getHealingCapacity());
                        }
                    } else if(cell.equals("EX")){
                        System.out.print("Exit reached\nGame Over!\nPlayer " + name + " wins!");
                        gameStatus = false;
                        System.exit(0);
                    }
                    map[previousRow][previousCol] = "__";
                    map[P.getRow()][P.getCol()] = P;
                    moveo = false;
                } else {
                    System.out.println("Incorrect Move. Try again.");
                }
            }


            // Actions
            System.out.println("""
                \nTime for post-movement actions!
                Enter "weapon" to use weapon.
                Enter "potion" to use potion.
                Enter "stats" to see player stats.
                Enter "inventory" to see player inventory.
                Enter "done" to stop actions and go to next move.""");
            boolean post = true;
            while(post) {
                String postAttackActionResponse = console.next();
                if(postAttackActionResponse.equalsIgnoreCase("weapon")) {
                    String lol = P.useWeapon();
                    System.out.println(lol);
                } else if(postAttackActionResponse.equalsIgnoreCase("potion")) {
                    String dam = P.usePotion();
                    System.out.println(dam);
                } else if(postAttackActionResponse.equalsIgnoreCase("stats")) {
                    System.out.println(P.getStats());
                } else if(postAttackActionResponse.equalsIgnoreCase("inventory")) {
                    System.out.println(P.printInventory());
                }
                else if(postAttackActionResponse.equalsIgnoreCase("done")) {
                    post = false;
                } else {
                    System.out.println("Error. Try Again.");
                }
            }
            System.out.println();
            System.out.println("Turn over.");
            System.out.println();
            map[P.getRow()][P.getCol()] = P;

        }
        console.close();
    }







    // Combat!
    public static boolean attack(Player player, Zombie zombie) {
        // returns true if player lives
        // returns false if player dies
        if(player.getSpeed() > zombie.getSpeed()) {
            // Player attacks first
            zombie.loseHealth(player.getAttackValue());
            if(zombie.getHealth() <= 0) {
                return true;
            }
            // Zombie retaliates
            player.loseHealth(zombie.getAttackValue());
            if(player.getHealth() <= 0) {
                return false;
            }

        } else { // zombie speed greater than or equal to player speed
            // Zombie attacks first
            player.loseHealth(zombie.getAttackValue());
            if(player.getHealth() <= 0) {
                return false;
            }
            // Player retaliates
            zombie.loseHealth(player.getAttackValue());
            if(zombie.getHealth() <= 0) {
                return true;
            }

        }
        return attack(player, zombie);
    }

    // MAP
    public static void generateMapFeatures() {
        for(int c = 0; c < map[0].length; c++) {
            for(int r = 0; r < map.length; r++) {
                int num = (int)(Math.random()*100+1);
                double health = Math.round((Math.random() * 10 + 10) * 10.0) / 10.0;
                double damage = Math.round((Math.random() * 2 + 4) * 10.0) / 10.0;
                double speed = Math.round((Math.random() * 4 + 2) * 10.0) / 10.0;
                double weaponPower = Math.round((Math.random() * 4 + 1) * 10.0) / 10.0;
                double weaponSpeed = Math.round((Math.random() * 4 + 1) * 10.0) / 10.0;
                double healPower = Math.round((Math.random() * 4 + 1) * 10.0) / 10.0;
                if(num > 0 && num <= 20 && map[r][c] == null) { // 20% chance of spawning normal Zombie
                    Zombie zombie = new Zombie(health, damage, speed);
                    map[r][c] = zombie;
                } else if(num > 20 && num <= 30 && map[r][c] == null) { // 10% chance of spawning fast zombie
                    fastZombie fastZom = new fastZombie(health, damage, speed);
                    map[r][c] = fastZom;
                } else if(num > 30 & num <= 40 && map[r][c] == null) {  // 10% chance of spawning strong zombie
                    strongZombie strongZom = new strongZombie(health, damage, speed);
                    map[r][c] = strongZom;
                } else if (num > 40 && num <= 50 && map[r][c] == null) {
                    Weapon weapon = new Weapon(weaponPower, weaponSpeed);
                    map[r][c] = weapon;
                } else if (num > 50 && num <= 60 && map[r][c] == null) {
                    Potion potion = new Potion(healPower);
                    map[r][c] = potion;
                } else {
                    map[r][c] = "__";
                }

            }
        }
    }

    public static void printMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }





}

