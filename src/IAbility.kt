
interface IAbility {
    val name: String
    val required: Array<
            HashMap<
                    Int, // Param
                    Pair<Int, Int> // Mode (>, <, >=, <=, ==) and number to compare with
                    >
            >
    val effect: Array<
            HashMap<
                    Int, // Param
                    HashMap<Int, Int> // EffectID and Effect's power
                    >
            >
    val stacks: Boolean
    val affectsSelf: Boolean
    val affectsOther: Boolean

    fun conditionFulfiled(self: IPlayer, other: IPlayer): Boolean
    fun takeEffect(self: IPlayer, other: IPlayer)
}
