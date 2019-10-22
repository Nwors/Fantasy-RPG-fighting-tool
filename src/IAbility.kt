
interface IAbility {
    val name: String
    val required: Array<
            HashMap<
                    Int, // Stat
                    Pair<Int, Int> // Mode (>, <, >=, <=, ==) and number to compare with
                    >
            >
    val effect: Array<
            HashMap<
                    Int, // Stat
                    HashMap<Int, Int> // EffectID and Effect's power
                    >
            >
    val stacks: Boolean

    fun conditionFulfiled(self: IPlayer, other: IPlayer): Boolean
    val affectsSelf: Boolean
    val affectsOther: Boolean
    fun takeEffect(self: IPlayer, other: IPlayer)
}
