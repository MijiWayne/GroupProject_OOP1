/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpggame;

/**
 *
 * @author Oniji
 */
class Skill {
    private String name;
    private int manaCost;
    private int damage;
    private String effect;

    public Skill(String name, int manaCost, int damage, String effect) {
        this.name = name;
        this.manaCost = manaCost;
        this.damage = damage;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getDamage() {
        return damage;
    }

    public String getEffect() {
        return effect;
    }
}