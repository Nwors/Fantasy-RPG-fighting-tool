
class FightingTool() {


    companion object {

        private fun afterEffect(x: Int, attacker: IPlayer, defender: IPlayer) {

            fun getWeaponEffect(weapon:IEquipment, x: Int) {
                when(weapon.type) {
                    "Blade" -> {defender.stats.persistence -= x; defender.stats.coordination -= x; defender.stats.reaction -= x; defender.stats.strength -= x}
                    "Piercing" -> {var deathRoulette = (1..(20-x)).random(); if (deathRoulette == x) {defender.alive = false}}
                }
            }

            when (x) {
                0 -> defender.endurance -= Dice.roll()
                1 -> defender.endurance -= Dice.roll(numOfFacets = 12)
                2 -> {
                    defender.endurance -= Dice.roll(2, 12)
                    defender.equipment.forEach { item ->
                        if (item is Armor && item.equipped) {
                            item.stat -= 1
                        }
                    }
                }
                3 -> attacker.equipment.forEach {item ->
                    if (item is Weapon && item.equipped) {
                        getWeaponEffect(item,3)
                    }
                }
                4 -> attacker.equipment.forEach {item ->
                    if (item is Weapon && item.equipped) {
                        getWeaponEffect(item,6)
                    }
                }
                5 -> defender.alive = false
            }
            if (attacker.endurance == 0) {
                attacker.stunned = true
            }
            if (defender.endurance == 0) {
                defender.stunned = true
            }
        }

        fun attack(attacker:IPlayer, defender:IPlayer) {
            //Fight stages
            fun dodgeTest() = (Dice.roll(numOfFacets = 12) + attacker.weaponBonus()) < (Dice.roll(numOfFacets = 12) + defender.evasion())
            fun parryTest() = (Dice.roll(numOfFacets = 12) + attacker.weaponBonus()) < (Dice.roll(numOfFacets = 12) + defender.parry())
            fun armorTest() = (Dice.roll(numOfFacets = 12) + attacker.weaponBonus()) < (Dice.roll(numOfFacets = 12) + defender.armour())
            fun staminaTest() = (Dice.roll(numOfFacets = 12) + attacker.weaponBonus()) < (Dice.roll(numOfFacets = 12) + defender.stamina())
            fun fortitudeTest() = (Dice.roll(numOfFacets = 12) + attacker.weaponBonus()) < (Dice.roll(numOfFacets = 12) + defender.fortitude())
            //

            if (dodgeTest()) return afterEffect(0, attacker, defender)
            if (parryTest()) return afterEffect(1, attacker, defender)
            if (armorTest()) return afterEffect(2, attacker, defender)
            if (staminaTest()) return afterEffect(3, attacker, defender)
            if (fortitudeTest()) return afterEffect(4, attacker, defender)
            return afterEffect(5, attacker, defender)
        }

        fun fight (player1: IPlayer, player2: IPlayer) {
            var numOfTurns = 0
            var winner: String
            while (!player1.stunned && !player2.stunned && player1.alive && player2.alive) {
                numOfTurns++
                attack(player1, player2)
                if (!player2.stunned && player2.alive) {
                    attack(player2, player1)
                }
            }
            winner = if (player1.alive && !player1.stunned) {
                player1.name
            } else {
                player2.name
            }
            println("A GREAT WINNER IS $winner and he defeated his opponent in $numOfTurns turns ")
        }
    }

}