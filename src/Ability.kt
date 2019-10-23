
import java.lang.Exception
import kotlin.reflect.full.functions
import kotlin.reflect.full.memberProperties

class Ability(
        override val name: String,
        override val affectsSelf: Boolean,
        override val affectsOther: Boolean,
        override val stacks: Boolean,
        override val required: Array<HashMap<Int, Pair<Int, Int>>>,
        override val effect: Array<HashMap<Int, HashMap<Int, Int>>>) : IAbility {
    init {
        if (name.isEmpty()) throw Exception("Empty ability name")
        if (!(affectsSelf || affectsOther)) throw Exception("Affects nobody")
        if (required.size != NUMBER_OF_PARAM_TARGETS) throw Exception("Incorrect number of required stats' targets")
        if (effect.size != MAX_NUMBER_OF_AFFECTED_TARGETS) throw Exception("Incorrect number of effect stats' targets")
    }

    fun getReflectedList(player: IPlayer): List<Int> {
        val res = mutableListOf<Int>()
        var param = (player::class.memberProperties.toList()[ENDURANCE].call(player));
        if (param is Int) res.add(param) else throw Exception("Reflection exception- not Int encountered")
        for(ind in PLAYER_STATS_MEMBERS) {
            param = PlayerStats::class.memberProperties.toList()[ind].call(player.stats)
            if (param is Int) res.add(param) else throw Exception("Reflection exception- not Int encountered")
        }
        for(ind in PLAYER_FUNCTIONS) {
            param = IPlayer::class.functions.toList()[ind].call(player)
            if (param is Int) res.add(param) else throw Exception("Reflection exception- not Int encountered")
        }
        return res;
    }

    override fun conditionFulfiled(self: IPlayer, other: IPlayer): Boolean {
        var curPlayer: IPlayer
        var fulfilled: Boolean = true
        var list: List<Int>
        var expected: Int
        var actual: Int
        for(player in required) {
            curPlayer = when(required.indexOf(player)) {
                PLAYER_SELF -> self
                PLAYER_OTHER -> other
                else -> throw Exception("Player ID is not from [0; $NUMBER_OF_PARAM_TARGETS]")
            }
            list = getReflectedList(curPlayer)
            for(param in player) {
                expected = param.value.second
                actual = list[param.key]
                fulfilled = fulfilled && when (param.value.first) {
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