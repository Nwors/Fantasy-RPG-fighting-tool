import kotlin.reflect.full.functions
import kotlin.reflect.full.memberProperties

fun main() {
    var krionel =  Player(
        "Krionel",
        PlayerStats(3,3,3,3,3),
        equipment = listOf(Armor("Chain-mail","Heavy",5,true),
            Weapon("Club", "Blunt", 3, true, fencing = false),
            Armor("Chain-leggings", "Heavy", 5, true),
            Shield ("TheBarrier", "Shield", 3, false))
    )

    var krionel2 =  Player(
        "Krionel",
        PlayerStats(3,3,3,3,3),
        equipment = listOf(Armor("Chain-mail","Heavy",5,true),
            Weapon("Club", "Blunt", 3, true, fencing = false),
            Armor("Chain-leggings", "Heavy", 5, true),
            Shield ("TheBarrier", "Shield", 3, false))
    )

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
    println(ability.conditionFulfiled(krionel, krionel2))
}
