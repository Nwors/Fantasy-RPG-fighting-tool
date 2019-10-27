
interface IPlayer {
    val name: String
    val stats: PlayerStats
    var abilities: List<IAbility>
    var equipment: List<IEquipment>
    var endurance: Int

    fun evasion(): Int
    fun parry(): Int
    fun armour(): Int
    fun stamina(): Int
    fun fortitude(): Int
    fun speed(): Int
    fun weaponBonus(): Int

    fun isStunned(): Boolean
    fun isAlive(): Boolean
    fun getKilled()
    fun getStunned()
}
