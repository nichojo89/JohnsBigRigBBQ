package com.nicholssoftware.johnsbigrigbbq.ui.repository

import com.nicholssoftware.core.data.Dish
import com.nicholssoftware.johnsbigrigbbq.R

class DishRepository {
    fun getAllDishes(): Array<Dish>{
        return arrayOf(
            Dish(
                "Smoked Ribs",
                "Yummy ribs desc",
                "Yummy ribs desc",
                R.drawable.ic_ribs,
                18.00,
            1),
            Dish(
                "Mac & Bacon",
                "Sharp cheddar cheese, homemade macaroni, & apple wood bacon",
                "Sharp cheddar cheese, homemade macaroni, & apple wood bacon",
                R.drawable.ic_mac_bacon,
                14.00,
                2),
            Dish(
                "Brisket",
                "Slow cooked tender brisket",
                "Slow cooked tender brisket",
                R.drawable.ic_brisket,
                16.00,
                3),
            Dish(
                "Smoked Chicken",
                "Fresh juicy smoked chicken with Big Jons BBQ sauce",
                "Fresh juicy smoked chicken with Big Jons BBQ sauce",
                R.drawable.ic_smoked_chicken,
                13.00,
                4),
            Dish(
                "Pulled Pork",
                "Pulled pork in a koren style medley of vegetables",
                "Pulled pork in a koren style medley of vegetables",
                R.drawable.ic_pulled_pork,
                17.00,
                5),
            Dish(
                "Steak Bites",
                "Tender steak bites served with a side of asparagus",
                "Tender steak bites served with a side of asparagus",
                R.drawable.ic_steak_bites,
                12.00,
                6),
        )
    }
}