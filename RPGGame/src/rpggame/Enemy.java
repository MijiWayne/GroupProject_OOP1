/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpggame;

/**
 *
 * @author Oniji
 */
class Enemy extends Character {
    public Enemy(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    @Override
    public void attack(Character target) {
        System.out.println(name + " attacks " + target.getName() + " for " + attackPower + " damage.");
        target.takeDamage(attackPower);
    }

    public void useSpecialSkill(Character target) {
        System.out.println(name + " uses a special skill on " + target.getName() + ", dealing 20 damage.");
        target.takeDamage(20);
    }
}