
class Player (
    override val name: String,
    override val stats: PlayerStats,
    override var abilities: List<IAbility> = emptyList(),
    override var equipment: List<IEquipment> = emptyList(),
    override var endurance: Int = 50 + stats.persistence * 5
): IPlayer {
    private val conditions = Condition()

    override fun isAlive() = conditions.alive
    override fun isStunned() = conditions.stunned

    override fun getKilled() { conditions.alive = false }
    override fun getStunned() { conditions.stunned = true }

    override fun evasion() = 10 + stats.reaction

    override fun parry(): Int {
        val shieldBonus = equipment.filter{
            it is Shield && it.equipped }.map{
            it.stat }.fold(0) {
            acc, shieldStat -> acc + shieldStat }
        return 10 + stats.coordination + shieldBonus
    }

    override fun armour(): Int {
        val totalArmor = equipment.filter {
            it is Armor && it.equipped }.map {
            it.stat }.fold(0) {
            acc, armorStat -> acc + armorStat }
        return 10 + stats.strength + totalArmor
    }

    override fun weaponBonus(): Int {
        val weaponBonus = equipment.filter {
            it is Weapon && it.equipped }.map {
            it.stat + if((it as Weapon).fencing) stats.coordination else stats.strength }.fold(0) {
            acc, weaponStat -> acc + weaponStat }
        return 10 + weaponBonus
    }

    override fun stamina() = 10 + stats.persistence

    override fun fortitude() = 10 + stats.will

    override fun speed() = 4 + stats.coordination / 3
}
