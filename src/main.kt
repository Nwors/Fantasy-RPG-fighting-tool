import kotlin.reflect.full.functions
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties

fun main() {
    Player::class.memberFunctions.forEach {
        println(it)
    }

    println("---------------")

    IPlayer::class.memberProperties.forEach {
        println(it)
    }

    var loras =  Player(
        "Loras",
        PlayerStats(6,4,5,6,4),
        equipment = listOf(Weapon("Hands", "Blade", 0, true, false),
            Armor("Chain-mail","Heavy",5,true))
        )

    var lanadel =  Player(
        "Lanadel",
        PlayerStats(4,3,6,5,6),
        equipment = listOf(Weapon("Hands", "Piercing", 6, true,false ), Armor("Chain-Mail", "Heavy",5, equipped = true))
    )

    FightingTool.fight(loras, lanadel)

    val required = arrayOf(
            hashMapOf(
                    PLAYER_STAT_COORDINATION to Comparison(MORE_THAN, 2),
                    PLAYER_STAT_PERSISTENCE to Comparison(EQUAL_TO, 3)
            ), hashMapOf()
    )
    val effect = arrayOf(
            hashMapOf(
                    7 to ParamEffect(listOf(hashMapOf(1 to 3)), 5),
                    8 to ParamEffect(listOf(hashMapOf(2 to 5), hashMapOf(7 to 8)), 5)
            ), hashMapOf()
    )
    val ability = Ability("magic", true, false, true, required, effect)
    println(ability.conditionFulfiled(loras, lanadel))
}
