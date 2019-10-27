
class FightingTool {
    companion object {
        private fun afterEffect(stage: Int, attacker: IPlayer, defender: IPlayer) {
            fun getWeaponEffect(weapon:IEquipment, x: Int) {
                when(weapon.type) {
                    "Blade" -> {
                        defender.stats.persistence -= x
                        defender.stats.coordination -= x
                        defender.stats.reaction -= x
                        defender.stats.strength -= x
                    }
                    "Piercing" -> {
                        val deathRoulette = (1..(20-x)).random()
                        if (deathRoulette == x) defender.getKilled()
                    }
                }
            }

            when (stage) {
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
                5 -> defender.getKilled()
            }
            if (attacker.endurance == 0) attacker.getStunned()
            if (defender.endurance == 0) defender.getStunned()
        }

        fun attack(attacker:IPlayer, defender:IPlayer) {
            //Fight stages
            fun dodgeTest() = (Dice.roll(numOfFacets = 12) + attacker.weaponBonus()) < (Dice.roll(numOfFacets = 12) + defender.evasion())
            fun parryTest() = (Dice.roll(numOfFacets = 12) + attacker.weaponBonus()) < (Dice.roll(numOfFacets = 12) + defender.parry())
            fun armorTest() = (Dice.roll(numOfFacets = 12) + attacker.weaponBonus()) < (Dice.roll(numOfFacets = 12) + defender.armour())
            fun staminaTest() = (Dice.roll(numOfFacets = 12) + attacker.weaponBonus()) < (Dice.roll(numOfFacets = 12) + defender.stamina())
            fun fortitudeTest() = (Dice.roll(numOfFacets = 12) + attacker.weaponBonus()) < (Dice.roll(numOfFacets = 12) + defender.fortitude())

            if (dodgeTest()) afterEffect(0, attacker, defender) else
            if (parryTest()) afterEffect(1, attacker, defender) else
            if (armorTest()) afterEffect(2, attacker, defender) else
            if (staminaTest()) afterEffect(3, attacker, defender) else
            if (fortitudeTest()) afterEffect(4, attacker, defender) else
            return afterEffect(5, attacker, defender)
        }

        fun fight (player1: IPlayer, player2: IPlayer) {
            var numOfTurns = 0
            while (!player1.isStunned() && !player2.isStunned() && player1.isAlive() && player2.isAlive()) {
                numOfTurns++
                attack(player1, player2)
                if (!player2.isStunned() && player2.isAlive()) {
                    attack(player2, player1)
                }
            }
            val winner = if (!player1.isStunned() && player1.isAlive())
                player1.name
            else
                player2.name
            println("A GREAT WINNER IS $winner and he defeated his opponent in $numOfTurns turns ")
        }
    }
}
