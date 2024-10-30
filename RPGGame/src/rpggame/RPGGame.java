/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rpggame;


import java.util.Scanner;
/**
 *
 * @author Oniji
 */

import java.util.Scanner;

public class RPGGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuePlaying = true;

        System.out.println("Welcome to CIT: Shadow of the Abyss!");
        System.out.println("You are on a journey to stop the Abyssal corruption from spreading and to defeat the Abyssal Demon Lord.");
        System.out.println("Only then can order be restored to the school. Prepare yourself for battle!\n");

        while (continuePlaying) {
            System.out.println("Select your character:");
            System.out.println("(1) Mage (I.T.)");
            System.out.println("(2) Warrior (Engineering)");
            System.out.println("(3) Healer (Nursing)");
            System.out.print("input choice: ");
            
            int choice = scanner.nextInt();
            Character player;

            switch (choice) {
                case 1:
                    player = new Mage("I.T. Mage", 300, 15, 50);
                    System.out.println("You have selected the Mage (I.T.). Prepare for battle!\n");
                    break;
                case 2:
                    player = new Warrior("Engineering Warrior", 500, 20, 50);
                    System.out.println("You have selected the Warrior (Engineering). Prepare for battle!\n");
                    break;
                case 3:
                    player = new Healer("Nursing Healer", 300, 10, 60);
                    System.out.println("You have selected the Healer (Nursing). Prepare for battle!\n");
                    break;
                default:
                    System.out.println("Invalid choice. Exiting the game.");
                    scanner.close();
                    return;
            }

            Enemy[] enemies = {
                new Enemy("Corrupt Security Guard", 100, 10), 
                new Enemy("Undead Janitor", 100, 15),        
                new Enemy("Skeleton Construction Worker", 100, 20) 
            };

            System.out.println("You enter the first dungeon, where the forces of Abyssal corruption await you...\n");

            for (Enemy enemy : enemies) {
                System.out.println("\n" + player.getName() + " encounters a " + enemy.getName() + "!");

                while (player.isAlive() && enemy.isAlive()) {
                    System.out.println("\n" + player.getName() + " - Health: " + player.getHealth());
                    if (player instanceof Mage) {
                        System.out.println("Mana: " + ((Mage) player).getMana());
                    } else if (player instanceof Healer) {
                        System.out.println("Mana: " + ((Healer) player).getMana());
                    } else if (player instanceof Warrior) {
                        System.out.println("Stamina: " + ((Warrior) player).getStamina());
                    }
                    System.out.println(enemy.getName() + " - Health: " + enemy.getHealth());
                    System.out.println("Choose an action: (1) Basic Attack, (2) Use Skill");

                    int actionChoice = scanner.nextInt();

                    if (actionChoice == 1) {
                        player.attack(enemy);
                    } else if (actionChoice == 2) {
                        if (player instanceof Mage) {
                            ((Mage) player).listSkills();
                            System.out.print("Choose a skill: ");
                            int skillChoice = scanner.nextInt() - 1;
                            ((Mage) player).useSkill(skillChoice, enemy);
                        } else if (player instanceof Warrior) {
                            ((Warrior) player).listSkills();
                            System.out.print("Choose a skill: ");
                            int skillChoice = scanner.nextInt() - 1;
                            ((Warrior) player).useSkill(skillChoice, enemy);
                        } else if (player instanceof Healer) {
                            ((Healer) player).listSkills();
                            System.out.print("Choose a skill: ");
                            int skillChoice = scanner.nextInt() - 1;
                            ((Healer) player).useSkill(skillChoice, enemy);
                        }
                    } else {
                        System.out.println("Invalid choice. Please select 1 or 2.");
                        continue;
                    }

                    if (enemy.isAlive()) {
                        if (Math.random() < 0.5) {
                            enemy.useSpecialSkill(player);
                        } else {
                            enemy.attack(player);
                        }
                    }

                    if (player instanceof Mage) {
                        ((Mage) player).regenerateMana(5);
                    } else if (player instanceof Healer) {
                        ((Healer) player).regenerateMana(5);
                    } else if (player instanceof Warrior) {
                        ((Warrior) player).regenerateStamina(5);
                    }
                }

                if (player.isAlive()) {
                    System.out.println("\n" + player.getName() + " has defeated the " + enemy.getName() + "!");
                } else {
                    System.out.println("\n" + player.getName() + " has been defeated by the " + enemy.getName() + "...");
                    continuePlaying = false; 
                    break; 
                }
            }

            if (player.isAlive()) {
                System.out.println("Congratulations! You have cleared the dungeon!");
            }

            System.out.println("You can select another character. Would you like to continue playing? (1: Yes, 2: No)");
            int continueChoice = scanner.nextInt();
            if (continueChoice != 1) {
                continuePlaying = false;
            }
        }

        System.out.println("Thank you for playing!");
        scanner.close();
    }
}