
interface IPlayer {
    val name: String
    val stats: Stats
    var weapon: IWeapon
    var armor: IArmor
    var abilities: List<IAbility>

    fun updateStats()
}