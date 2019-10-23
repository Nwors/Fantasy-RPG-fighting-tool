
class Weapon (
    override val name: String,
    override val type: String,
    override var stat: Int,
    override var equipped: Boolean = false,
    val fencing: Boolean
): IEquipment