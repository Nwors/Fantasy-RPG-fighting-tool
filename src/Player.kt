
class Player (
    override val name: String,
    override val stats: PlayerStats,
    override var abilities: List<IAbility> = emptyList(),
    override var equipment: List<IEquipment>,
    override var endurance: Int = 50 + stats.persistence*5
): IPlayer {
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

    override fun stamina(): Int = 10 + stats.persistence

    override fun fortitude(): Int = 10 + stats.will

    override fun speed(): Int = 4 + stats.coordination / 3
}