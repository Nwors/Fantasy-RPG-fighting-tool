
interface IPlayer {
    val name: String
    val stats: Stats
    var weapon: IWeapon
    var armor: IArmor
    var shield: IArmor
    var abilities: List<IAbility>

    fun updateStats()
}
