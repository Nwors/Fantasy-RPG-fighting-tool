
class Player(
    override val name: String,
    override val stats: Stats,
    override var weapon: IWeapon,
    override var armor: IArmor,
    override var abilities: List<IAbility> = emptyList(),
    var shield: IArmor = Armor("Shield",0)
) : IPlayer {
    //General defence stats
    var evasion = 0
    var parry = 0
    var armored = 0
    var stamina = 0
    var sustainability = 0
    //Move and acting
    var powersLeft = 0
    var speed = 0

    override fun updateStats() {
        evasion = 10 + stats.reaction
        parry = 10 + stats.coordination + shield.armorStat
        armored = 10 + stats.strength + armor.armorStat
        stamina = 10 + stats.persistence
        sustainability = 10 + stats.will

        powersLeft = 50 + stats.persistence
        speed = 4 + stats.coordination / 3
    }
}
