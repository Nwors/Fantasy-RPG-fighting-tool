
import java.lang.Exception
import kotlin.reflect.full.functions
import kotlin.reflect.full.memberProperties

class Ability : IAbility {
    constructor(name: String,
                affectsSelf: Boolean,
                affectsOther: Boolean,
                stacks: Boolean,
                required: Array<HashMap<Int, Comparison>>,
                effect: Array<HashMap<Int, ParamEffect>>) {
        this.name = name
        this.affectsSelf = affectsSelf
        this.affectsOther = affectsOther
        this.stacks = stacks
        this.required = required
        this.effect = effect

        checkFields()
    }

    constructor(name: String, abilities: List<IAbility>) {
        this.name = name
        this.required = Array<HashMap<Int, Comparison>>(NUMBER_OF_PARAM_TARGETS) { hashMapOf() }
        this.effect = Array<HashMap<Int, ParamEffect>>(MAX_NUMBER_OF_AFFECTED_TARGETS) { hashMapOf() }
        stacks = abilities.map { ability -> ability.stacks }.reduce { acc, ability -> acc || ability }
        affectsSelf = abilities.map { ability -> ability.affectsSelf }.reduce { acc, ability -> acc || ability }
        affectsOther = abilities.map { ability -> ability.affectsOther }.reduce { acc, ability -> acc || ability }
        abilities.map { it.required }.forEach {
            playersArray -> playersArray.forEachIndexed {
                playerIndex, paramsHashMap -> paramsHashMap.forEach {
                    (paramIndex, paramModeAndValue) -> if (required[playerIndex].containsKey(paramIndex)) {
                        Comparison.merge(
                                required[playerIndex][paramIndex]!!,
                                paramModeAndValue).first()
                    }
                }
            }
        }
        // No effect implemented
        checkFields()
    }

    override val name: String
    override val affectsSelf: Boolean
    override val affectsOther: Boolean
    override val stacks: Boolean
    override val required: Array<HashMap<Int, Comparison>>
    override val effect: Array<HashMap<Int, ParamEffect>>

    fun checkFields() {
        if (name.isEmpty())
            throw Exception("Empty ability name")
        if (!(affectsSelf || affectsOther))
            throw Exception("Affects nobody")
        if (required.size != NUMBER_OF_PARAM_TARGETS)
            throw Exception("Incorrect number of required stats' targets")
        if (effect.size != MAX_NUMBER_OF_AFFECTED_TARGETS)
            throw Exception("Incorrect number of effect stats' targets")
    }

    fun getReflectedList(player: IPlayer): List<Int> {
        val res = mutableListOf<Int>()
        var param = (player::class.memberProperties.toList()[REFLECTION_ENDURANCE].call(player))
        if (param is Int) res.add(param) else throw Exception("Reflection exception- not Int encountered in properties")
        for(ind in PLAYER_STATS_MEMBERS) {
            param = PlayerStats::class.memberProperties.toList()[ind].call(player.stats)
            if (param is Int) res.add(param) else throw Exception("Reflection exception- not Int encountered in stats")
        }
        for(ind in PLAYER_FUNCTIONS) {
            param = player::class.functions.toList()[ind].call(player)
            if (param is Int) res.add(param) else throw Exception("Reflection exception- not Int encountered in functions")
        }
        return res
    }

    override fun conditionFulfiled(self: IPlayer, other: IPlayer): Boolean {
        var curPlayer: IPlayer
        var fulfilled: Boolean = true
        var list: List<Int>
        var expected: Int
        var actual: Int
        var comparisonMode: Int
        for(player in required) {
            curPlayer = when(required.indexOf(player)) {
                PLAYER_SELF -> self
                PLAYER_OTHER -> other
                else -> throw Exception("Player ID is not from 0..$NUMBER_OF_PARAM_TARGETS")
            }
            list = getReflectedList(curPlayer)
            for(param in player) {
                comparisonMode = param.value.mode
                expected = param.value.value
                actual = list[param.key]
                fulfilled = fulfilled && when (comparisonMode) {
                    EQUAL_TO -> expected == actual
                    MORE_THAN -> expected < actual
                    MORE_OR_EQUAL_TO -> expected <= actual
                    LESS_THAN -> expected > actual
                    LESS_OR_EQUAL_TO -> expected >= actual
                    else -> throw Exception("Comparison mode exception")
                }
            }
        }
        return fulfilled
    }

    override fun takeEffect(self: IPlayer, other: IPlayer) {
    }
}