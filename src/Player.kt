
class Player (
    override val name: String,
    override val stats: PlayerStats,
    override var abilities: List<IAbility> = emptyList(),
    override var equipment: List<IEquipment> = emptyList(),
    override var endurance: Int = 50 + stats.persistence*5
): IPlayer {
    override var alive = true
    override var stunned = false

    override fun evasion(): Int = 10 + stats.reaction

    override fun parry(): Int {
        var shieldBonus = 0;
        equipment.forEach {item ->
            if (item is Shield && item.equipped) {
                shieldBonus += item.stat
            }
        }
        return 10 + stats.coordination + shieldBonus
    }

    override fun armour(): Int {
        var totalArmor = 0
        equipment.forEach {item ->
            if (item is Armor && item.equipped) {
                totalArmor += item.stat
            }
        }
        return 10 + stats.strength + totalArmor;
    }

    override fun weaponBonus(): Int {
      var weaponBonus = 0;
        equipment.forEach { item ->
            if (item is Weapon && item.equipped) {
                weaponBonus = if (item.fencing) {
                    stats.coordination + item.stat
                } else {
                    stats.strength + item.stat
                }
            }
        }
        return 10 + weaponBonus
    }

    override fun stamina(): Int = 10 + stats.persistence

    override fun fortitude(): Int = 10 + stats.will

    override fun speed(): Int = 4 + stats.coordination / 3
}