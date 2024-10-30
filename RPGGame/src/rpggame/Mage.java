/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpggame;

/**
 *
 * @author Oniji
 */
import java.util.ArrayList;
import java.util.List;

class Mage extends Character {
    private int mana;
    private int maxMana;
    private List<Skill> skills;

    public Mage(String name, int health, int attackPower, int mana) {
        super(name, health, attackPower);
        this.mana = mana;
        this.maxMana = mana;
        this.skills = new ArrayList<>();
        initializeSkills();
    }

    private void initializeSkills() {
        
        skills.add(new Skill("Fireball", 10, 25, "A basic fire attack."));
        skills.add(new Skill("Lightning Strike", 20, 40, "A powerful electric shock."));
        skills.add(new Skill("Ice Shield", 15, 0, "Grants 20 temporary health."));
    }

    public int getMana() {
        return mana;
    }

    public void useMana(int amount) {
        mana -= amount;
        if (mana < 0) mana = 0;
    }

    public void regenerateMana(int amount) {
        mana += amount;
        if (mana > maxMana) mana = maxMana;
    }

    @Override
    public void attack(Character target) {
        System.out.println(name + " attacks " + target.getName() + " for " + attackPower + " damage.");
        target.takeDamage(attackPower);
    }

    public void useSkill(int skillIndex, Character target) {
        if (skillIndex < 0 || skillIndex >= skills.size()) {
            System.out.println("Invalid skill choice.");
            return;
        }

        Skill chosenSkill = skills.get(skillIndex);
        int manaCost = chosenSkill.getManaCost();
        int damage = chosenSkill.getDamage();

        if (mana >= manaCost) {
            System.out.println(name + " uses " + chosenSkill.getName() + " on " + target.getName() + "!");
            if (damage > 0) {
                System.out.println("It deals " + damage + " damage.");
                target.takeDamage(damage);
            } else {
                System.out.println("Effect: " + chosenSkill.getEffect());
                if (chosenSkill.getName().equals("Ice Shield")) {
                    health += 20; 
                    System.out.println(name + " gains 20 temporary health.");
                }
            }
            useMana(manaCost);
        } else {
            System.out.println(name + " does not have enough mana to use " + chosenSkill.getName() + ".");
        }
    }

    public void listSkills() {
        System.out.println("Available Skills:");
        for (int i = 0; i < skills.size(); i++) {
            Skill skill = skills.get(i);
            System.out.println((i + 1) + ". " + skill.getName() + " (Mana Cost: " + skill.getManaCost() + ", Damage: " + skill.getDamage() + ") - " + skill.getEffect());
        }
    }
}