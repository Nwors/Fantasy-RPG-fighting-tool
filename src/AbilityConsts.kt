
const val PLAYER_SELF = 0
const val PLAYER_OTHER = 1
const val MAX_NUMBER_OF_AFFECTED_TARGETS = 2
const val NUMBER_OF_PARAM_TARGETS = 2

const val MORE_THAN = 0
const val MORE_OR_EQUAL_TO = 1
const val EQUAL_TO = 2
const val LESS_OR_EQUAL_TO = 3
const val LESS_THAN = 4

// member properties
const val ENDURANCE = 1 // 0
const val STATS = 4

// member properties of PlayerStats
const val COORDINATION = 0
const val PERSISTENCE = 1
const val REACTION = 2
const val STRENGTH = 3
const val WILL = 4

val PLAYER_STATS_MEMBERS = listOf(COORDINATION, PERSISTENCE, REACTION, STRENGTH, WILL) // 1-5

// functions
const val ARMOUR = 0
const val EVASION = 1
const val FORTITUDE = 2
const val PARRY = 3
const val SPEED = 4
const val STAMINA = 5

val PLAYER_FUNCTIONS = listOf(ARMOUR, EVASION, FORTITUDE, PARRY, SPEED, STAMINA) // 6-11
