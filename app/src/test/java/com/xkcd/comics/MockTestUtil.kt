package com.xkcd.comics

import com.xkcd.comics.model.Result

class MockTestUtil {
    companion object {

        fun createComics(): Result {
            return Result(
                    img = "https://imgs.xkcd.com/comics/woodpecker.png",
                    num = 614,
                    safe_title = "Woodpecker",
                    title = "Woodpecker",
                    transcript = "[[A man with a beret and a woman are standing on a boardwalk, leaning on a handrail.]] Man: A woodpecker! <<Pop pop pop>> Woman: Yup." +
                            " [[The woodpecker is banging its head against a tree.]] Woman: He hatched about this time last year. <<Pop pop pop pop>> " +
                            "[[The woman walks away. The man is still standing at the handrail.]] Man: ... woodpecker? Man: It's your birthday! Man: Did " +
                            "you know? Man: Did... did nobody tell you? [[The man stands, looking.]] [[The man walks away.]] " +
                            "[[There is a tree.]] [[The man approaches the tree with a present in a box, tied up with ribbon.]] " +
                            "[[The man sets the present down at the base of the tree and looks up.]] [[The man walks away.]] " +
                            "[[The present is sitting at the bottom of the tree.]] [[The woodpecker looks down at the present.]] " +
                            "[[The woodpecker sits on the present.]] [[The woodpecker pulls on the ribbon tying the present closed.]] " +
                            "((full width panel)) [[The woodpecker is flying, with an electric drill dangling from its feet, held by the cord.]]" +
                            " {{Title text: If you don't have an extension cord I can get that too. Because we're friends! Right?}}" +
                    "If you don't have an extension cord I can get that too. Because we're friends! Right?",
                    year = "2009"

                )

        }

    }
}