
interface IAbility {
    val name: String
    val required: Array<
            HashMap<
                    Int, // Param
                    Comparison
                    >
            >
    val effect: Array<
            HashMap<
                    Int, // Param
                    ParamEffect
                    >
            >
    val stacks: Boolean
    val affectsSelf: Boolean
    val affectsOther: Boolean

    fun conditionFulfiled(self: IPlayer, other: IPlayer): Boolean
    fun takeEffect(self: IPlayer, other: IPlayer)
}
