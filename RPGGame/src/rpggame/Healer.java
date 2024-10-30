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

class Healer extends Character {
    private int mana;
    private int maxMana;
    private List<Skill> skills;

    public Healer(String name, int health, int attackPower, int mana) {
        super(name, health, attackPower);
        this.mana = mana;
        this.maxMana = mana;
        this.skills = new ArrayList<>();
        initializeSkills();
    }

    private void initializeSkills() {
        
        skills.add(new Skill("Healing Touch", 10, 0, "Heals 30 health points."));
        skills.add(new Skill("Holy Light", 15, 20, "A radiant light that burns the enemy."));
        skills.add(new Skill("Protective Aura", 20, 0, "Reduces all damage taken by 50% for two turns."));
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
        System.out.println(name + " uses a basic attack on " + target.getName() + " for " + attackPower + " damage.");
        target.takeDamage(attackPower);
    }

    public void useSkill(int skillIndex, Character target) {
        if (skillIndex < 0 || skillIndex >= skills.size()) {
            System.out.println("Invalid skill choice.");
            return;
        }

        Skill chosenSkill = skills.get(skillIndex);
        int manaCost = chosenSkill.getManaCost();

        if (mana >= manaCost) {
            System.out.println(name + " uses " + chosenSkill.getName() + "!");
            if (chosenSkill.getName().equals("Healing Touch")) {
                health += 30; 
                System.out.println(name + " heals 30 health points.");
            } else {
                System.out.println("It deals " + chosenSkill.getDamage() + " damage.");
                target.takeDamage(chosenSkill.getDamage());
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