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

class Warrior extends Character {
    private List<Skill> skills;
    private int stamina;
    private int maxStamina;

    public Warrior(String name, int health, int attackPower, int maxStamina) {
        super(name, health, attackPower);
        this.maxStamina = maxStamina;
        this.stamina = maxStamina; 
        this.skills = new ArrayList<>();
        initializeSkills();
    }

    private void initializeSkills() {
        
        skills.add(new Skill("Heavy Slash", 0, 30, "A powerful melee attack."));
        skills.add(new Skill("Defensive Stance", 0, 0, "Reduces incoming damage by 50% for the next turn."));
        skills.add(new Skill("Shield Bash", 0, 25, "Bashes the enemy with a shield, dealing damage and stunning for one turn."));
    }

    public int getStamina() {
        return stamina;
    }

    public void useStamina(int amount) {
        stamina -= amount;
        if (stamina < 0) stamina = 0;
    }

    public void regenerateStamina(int amount) {
        stamina += amount;
        if (stamina > maxStamina) stamina = maxStamina;
    }

    @Override
    public void attack(Character target) {
        System.out.println(name + " strikes " + target.getName() + " for " + attackPower + " damage.");
        target.takeDamage(attackPower);
    }

    public void useSkill(int skillIndex, Character target) {
        if (skillIndex < 0 || skillIndex >= skills.size()) {
            System.out.println("Invalid skill choice.");
            return;
        }

        Skill chosenSkill = skills.get(skillIndex);

        if (chosenSkill.getDamage() > 0) {
            System.out.println(name + " uses " + chosenSkill.getName() + " on " + target.getName() + "!");
            System.out.println("It deals " + chosenSkill.getDamage() + " damage.");
            target.takeDamage(chosenSkill.getDamage());
            if (chosenSkill.getName().equals("Shield Bash")) {
                System.out.println(target.getName() + " is stunned for one turn!");
            }
        } else {
            System.out.println(name + " uses " + chosenSkill.getName() + "!");
            System.out.println("Effect: " + chosenSkill.getEffect());
        }
    }

    public void listSkills() {
        System.out.println("Available Skills:");
        for (int i = 0; i < skills.size(); i++) {
            Skill skill = skills.get(i);
            System.out.println((i + 1) + ". " + skill.getName() + " (Damage: " + skill.getDamage() + ") - " + skill.getEffect());
        }
    }
}