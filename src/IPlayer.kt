
interface IPlayer {
    val name: String
    val stats: PlayerStats
    var abilities: List<IAbility>
    var equipment: List<IEquipment>
    var endurance: Int
    var alive: Boolean
    var stunned: Boolean

    fun evasion(): Int
    fun parry(): Int
    fun armour(): Int
    fun stamina(): Int
    fun fortitude(): Int
    fun speed(): Int
    fun weaponBonus(): Int
}
