package com.example.playergenerator

class Constants {
    companion object {
        val improveAreas = listOf<String>("TechkickArea","TechdribArea", "BalanceArea",
            "PowerArea" , "SpeedArea" , "StaminaArea")
        val attributesToImproveAreasMap = mapOf(
            "Attack" to mapOf(
                improveAreas[0] to 'S',
                improveAreas[1] to 'S'
            ),
            "Defence" to mapOf(
                improveAreas[2] to 'P'
            ),
            "Balance" to mapOf(
                improveAreas[2] to 'P'
            ),
            "Stamina" to mapOf(
                improveAreas[5] to 'P'
            ),
            "TopSpeed" to mapOf(
                improveAreas[4] to 'P'
            ),
            "Acceleration" to mapOf(
                improveAreas[4] to 'P'
            ),
            "Response" to mapOf(
                improveAreas[4] to 'S',
                improveAreas[5] to 'S'
            ),
            "Agility" to mapOf(
            ),
            "DribbleAccuracy" to mapOf(
                improveAreas[1] to 'P'
            ),
            "DribbleSpeed" to mapOf(
                improveAreas[1] to 'S',
                improveAreas[4] to 'S'
            ),
            "ShortPassAccuracy" to mapOf(
                improveAreas[0] to 'P'
            ),
            "ShortPassSpeed" to mapOf(
                improveAreas[0] to 'S',
                improveAreas[3] to 'S'
            ),
            "LongPassAccuracy" to mapOf(
                improveAreas[0] to 'P'
            ),
            "LongPassSpeed" to mapOf(
                improveAreas[3] to 'P'
            ),
            "ShotAccuracy" to mapOf(
                improveAreas[0] to 'P'
            ),
            "ShotPower" to mapOf(
                improveAreas[3] to 'P'
            ),
            "ShotTechnique" to mapOf(
                improveAreas[0] to 'P'
            ),
            "FreeKickAccuracy" to mapOf(
                improveAreas[0] to 'P'
            ),
            "Curving" to mapOf(
                improveAreas[0] to 'P'
            ),
            "Heading" to mapOf(
                improveAreas[2] to 'S',
                improveAreas[3] to 'S'
            ),
            "Jump" to mapOf(
                improveAreas[2] to 'S',
                improveAreas[3] to 'S'
            ),
            "Technique" to mapOf(
                improveAreas[1] to 'S',
                improveAreas[0] to 'S'
            ),
            "Aggression" to mapOf(
            ),
            "Mentality" to mapOf(
            ),
            "KeeperSkills" to mapOf(
            ),
            "Teamwork" to mapOf(
                improveAreas[2] to 'P'
            )
        )

        val seasonToImprovePointsMap = mapOf(
            1 to mapOf(
                6 to mapOf('P' to 12, 'S' to 6),
                5 to mapOf('P' to 10, 'S' to 5),
                4 to mapOf('P' to 8, 'S' to 4),
                3 to mapOf('P' to 6, 'S' to 3),
                2 to mapOf('P' to 4, 'S' to 2),
                1 to mapOf('P' to 2, 'S' to 1),
                0 to mapOf('P' to 0, 'S' to 0)
            ),
            3 to mapOf(
                6 to mapOf('P' to 9, 'S' to 5),
                5 to mapOf('P' to 8, 'S' to 4),
                4 to mapOf('P' to 6, 'S' to 3),
                3 to mapOf('P' to 5, 'S' to 2),
                2 to mapOf('P' to 3, 'S' to 2),
                1 to mapOf('P' to 2, 'S' to 1),
                0 to mapOf('P' to 0, 'S' to 0)
            ),
            6 to mapOf(
                6 to mapOf('P' to 6, 'S' to 3),
                5 to mapOf('P' to 5, 'S' to 3),
                4 to mapOf('P' to 4, 'S' to 2),
                3 to mapOf('P' to 3, 'S' to 2),
                2 to mapOf('P' to 2, 'S' to 1),
                1 to mapOf('P' to 1, 'S' to 1),
                0 to mapOf('P' to 0, 'S' to 0)
            ),
            10 to mapOf(
                6 to mapOf('P' to 3, 'S' to 2),
                5 to mapOf('P' to 3, 'S' to 1),
                4 to mapOf('P' to 2, 'S' to 1),
                3 to mapOf('P' to 2, 'S' to 1),
                2 to mapOf('P' to 1, 'S' to 1),
                1 to mapOf('P' to 1, 'S' to 0),
                0 to mapOf('P' to 0, 'S' to 0)
            )
        )

        val positionToAbbrevation = mapOf(
            "Centre Forward" to "CF",
            "Second Striker" to "SS",
            "Wing Forward" to "WF",
            "Attacking Midfielder" to "AMF",
            "Side Midfielder" to "SMF",
            "Central Midfielder" to "CMF",
            "Wing Back" to "WB",
            "Defensive Midfielder" to "DMF",
            "Side Back" to "SB",
            "Centre Back" to "CB"
        )


        val positionToMinValueMap = mapOf(
            "Centre Forward" to listOf(Pair(67,3),Pair(44,7),Pair(68,4),Pair(74,5),Pair(75,7),Pair(69,8),
                Pair(61,4),Pair(-1,1),
                Pair(63,3),Pair(70,3),Pair(60,2),Pair(63,2),Pair(57,6),Pair(65,3),
                Pair(64,6),Pair(67,3),Pair(64,3),
                Pair(53,3),Pair(51,2),Pair(68,3),Pair(67,7),Pair(59,2),Pair(-1,1),
                Pair(-1,1),Pair(48,4),Pair(58,2)),

            "Second Striker" to listOf(Pair(67,3),Pair(46,3),Pair(69,4),Pair(77,3),Pair(75,3),
                Pair(70,8),
                Pair(64,3),Pair(-1,1),Pair(60,2),Pair(69,3),Pair(61,2),Pair(60,2),Pair(61,2),
                Pair(64,3),
                Pair(65,3),Pair(68,2),Pair(67,2),Pair(53,3),Pair(52,2),Pair(68,3),
                Pair(67,3),Pair(60,2),Pair(-1,1),Pair(-1,1),Pair(48,2),Pair(58,2)),

            "Wing Forward" to listOf(Pair(67,2),Pair(44,3),Pair(65,3),Pair(74,8),Pair(74,8),Pair(70,7),Pair(60,3),
                Pair(-1,1),Pair(60,2),Pair(74,4),Pair(56,3),Pair(63,3),Pair(59,3),
                Pair(66,3),Pair(60,2),Pair(63,2),Pair(55,2),Pair(55,3),
                Pair(57,2),Pair(65,4),Pair(64,2),Pair(58,5),Pair(-1,1),Pair(-1,1),
                Pair(48,4),Pair(60,2)),

            "Attacking Midfielder" to listOf(Pair(65,3),Pair(47,3),Pair(63,2),Pair(74,6),Pair(73,5),Pair(75,4),Pair(60,3),Pair(-1,1),
                Pair(60,5),Pair(75,8),Pair(60,4),Pair(66,2),Pair(60,4),Pair(67,2),
                Pair(59,2),Pair(67,3),Pair(57,2),
                Pair(55,2),Pair(51,2),Pair(64,2),Pair(65,4),
                    Pair(58,2),Pair(-1,1),Pair(-1,1),Pair(48,4),Pair(60,3)),

            "Side Midfielder" to listOf(Pair(62,3),Pair(50,5),Pair(68,2),Pair(81,6),Pair(75,10),Pair(74,5),Pair(63,3),Pair(-1,1),
                Pair(63,3),Pair(75,2),Pair(56,5),Pair(63,3),Pair(58,4),Pair(67,3),Pair(56,3),Pair(63,2),Pair(54,3),Pair(54,2),
                Pair(57,2),Pair(63,4),
                    Pair(64,8),Pair(59,4),Pair(-1,1),Pair(-1,1),Pair(50,3),Pair(61,3)),

            "Central Midfielder" to listOf(Pair(58,2),Pair(56,2),Pair(61,3),Pair(81,6),
                Pair(73,2),Pair(70,2),Pair(59,3),Pair(-1,1),
                Pair(57,4),Pair(72,2),Pair(61,2),Pair(70,3),Pair(62,4),Pair(69,4),
                Pair(57,2),Pair(61,6),
                    Pair(55,2),Pair(58,2),Pair(58,2),Pair(65,4),Pair(67,4),Pair(62,2),
                Pair(-1,1),Pair(-1,1),Pair(51,2),Pair(61,3)),

            "Wing Back" to listOf(Pair(58,3),Pair(51,3),Pair(66,2),Pair(81,6),
                Pair(75,9),Pair(71,7),Pair(58,2),Pair(-1,1),
                Pair(60,3),Pair(67,3),Pair(58,5),Pair(65,2),Pair(58,5),Pair(71,3),
                Pair(54,4),Pair(63,3),Pair(56,2),Pair(55,2),Pair(57,5),
                    Pair(64,5),Pair(65,5),Pair(60,5),Pair(-1,1),Pair(-1,1),Pair(48,2),Pair(61,2)),

            "Defensive Midfielder" to listOf(Pair(55,2),Pair(54,7),Pair(69,6),Pair(81,9),Pair(70,2),Pair(71,2),Pair(69,3),Pair(-1,1),
                Pair(58,2),Pair(73,3),Pair(58,5),Pair(62,2),Pair(63,3),Pair(64,2),Pair(53,2),Pair(63,2),Pair(52,3),Pair(52,5),Pair(57,4),
                    Pair(65,2),Pair(71,10),Pair(60,2),Pair(-1,1),Pair(-1,1),Pair(49,3),Pair(63,3)),

            "Side Back" to listOf(Pair(54,4),Pair(58,4),Pair(68,4),Pair(81,7),Pair(75,2),Pair(71,7),Pair(58,5),Pair(-1,1),
                Pair(60,3),Pair(64,3),Pair(58,3),
                Pair(63,2),Pair(58,3),Pair(66,3),Pair(54,2),Pair(63,2),Pair(56,2),Pair(57,2),Pair(66,3),
                    Pair(67,3),Pair(59,2),Pair(58,5),Pair(-1,1),Pair(-1,1),Pair(48,3),Pair(61,3)),

            "Centre Back" to listOf(Pair(50,3),Pair(66,4),Pair(71,3),Pair(74,3),Pair(70,3),Pair(71,2),Pair(69,3),Pair(-1,1),
                Pair(55,3),Pair(62,2),Pair(58,3),Pair(60,2),Pair(59,2),Pair(65,2),
                Pair(51,2),Pair(61,2),Pair(52,2),Pair(52,2),Pair(57,2),
                    Pair(68,3),Pair(71,3),Pair(58,3),Pair(-1,1),Pair(-1,1),Pair(50,2),Pair(62,3))
        )

        val specialAbilitiesList = listOf(
            "Dribbling","TacticalDribbling","Positioning","Reaction",
            "Playmaking","Passing","Scoring","OneOnOneScoring","PostPlayer",
            "Lines","MiddleShooting","Side","Centre",
            "Penalties","OneTouchPass","Outside","Marking",
            "Sliding","Covering","D-LineControl","PenaltyStopper",
            "OneOnOneStopper","LongThrow"
        )

        val motionSettingsList = listOf(
            "DribbleStyle","FreeKickStyle","PenaltyStyle","DropKickStyle"
        )

    }
}