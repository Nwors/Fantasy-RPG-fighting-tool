
class Armor (
    override val name: String,
    override val type: String,
    override var stat: Int,
    override var equipped: Boolean = false
): IEquipment
