package com.google.firebase.example.fireeats.util

import android.content.Context
import com.google.firebase.example.fireeats.R
import com.google.firebase.example.fireeats.model.Restaurant
import java.util.Arrays
import java.util.Locale
import java.util.Random

/**
 * Utilities for Restaurants.
 */
object RestaurantUtil {

    private const val RESTAURANT_URL_FMT = "https://storage.googleapis.com/firestorequickstarts.appspot.com/food_%d.png"
    private const val MAX_IMAGE_NUM = 22

    private val NAME_FIRST_WORDS = arrayOf(
        "Afganistán", "Albania", "Alemania", "Algeria", "Andorra", "Angola", "Anguilla", "Antigua y Barbuda",
        "Antillas Holandesas", "Arabia Saudita", "Argentina", "Armenia", "Aruba", "Australia", "Austria",
        "Azerbaiyán", "Bahamas", "Bahrein", "Bangladesh", "Barbados", "Bélgica", "Belice", "Benín", "Bermudas",
        "Bielorrusia", "Bolivia", "Bosnia y Herzegovina", "Botsuana", "Brasil", "Brunéi", "Bulgaria",
        "Burkina Faso", "Burundi", "Bután", "Cabo Verde", "Camboya", "Camerún", "Canadá", "Chad", "Chile",
        "China", "Chipre", "Colombia", "Comores", "Congo (Brazzaville)", "Congo (Kinshasa)", "Cook, Islas",
        "Corea del Norte", "Corea del Sur", "Costa de Marfil", "Costa Rica", "Croacia", "Cuba", "Dinamarca",
        "Djibouti, Yibuti", "Ecuador", "Egipto", "El Salvador", "Emiratos Árabes Unidos", "Eritrea", "Eslovaquia",
        "Eslovenia", "España", "Estados Unidos", "Estonia", "Etiopía", "Feroe, Islas", "Filipinas", "Finlandia",
        "Fiyi", "Francia", "Gabón", "Gambia", "Georgia", "Ghana", "Gibraltar", "Granada", "Grecia", "Groenlandia",
        "Guadalupe", "Guatemala", "Guernsey", "Guinea", "Guinea Ecuatorial", "Guinea-Bissau", "Guyana", "Haiti",
        "Honduras", "Hong Kong", "Hungría", "India", "Indonesia", "Irak", "Irán", "Irlanda", "Isla Pitcairn",
        "Islandia", "Islas Salomón", "Islas Turcas y Caicos", "Islas Virgenes Británicas", "Israel", "Italia",
        "Jamaica", "Japón", "Jersey", "Jordania", "Kazajstán", "Kenia", "Kirguistán", "Kiribati", "Kuwait",
        "Laos", "Lesotho", "Letonia", "Líbano", "Liberia", "Libia", "Liechtenstein", "Lituania", "Luxemburgo",
        "Macedonia", "Madagascar", "Malasia", "Malawi", "Maldivas", "Malí", "Malta", "Man, Isla de", "Marruecos",
        "Martinica", "Mauricio", "Mauritania", "México", "Moldavia", "Mónaco", "Mongolia", "Mozambique", "Myanmar",
        "Namibia", "Nauru", "Nepal", "Nicaragua", "Níger", "Nigeria", "Norfolk Island", "Noruega", "Nueva Caledonia",
        "Nueva Zelanda", "Omán", "Países Bajos, Holanda", "Pakistán", "Panamá", "Papúa-Nueva Guinea", "Paraguay",
        "Perú", "Polinesia Francesa", "Polonia", "Portugal", "Puerto Rico", "Qatar", "Reino Unido", "República Checa",
        "República Dominicana", "Reunión", "Ruanda", "Rumanía", "Rusia", "Sáhara Occidental", "Samoa", "San Cristobal y Nevis",
        "San Marino", "San Pedro y Miquelón", "San Tomé y Príncipe", "San Vincente y Granadinas", "Santa Elena",
        "Santa Lucía", "Senegal", "Serbia y Montenegro", "Seychelles", "Sierra Leona", "Singapur", "Siria",
        "Somalia", "Sri Lanka", "Sudáfrica", "Sudán", "Suecia", "Suiza", "Surinam", "Swazilandia", "Tadjikistan",
        "Tailandia", "Taiwan", "Tanzania", "Timor Oriental", "Togo", "Tokelau", "Tonga", "Trinidad y Tobago", "Túnez",
        "Turkmenistan", "Turquía", "Tuvalu", "Ucrania", "Uganda", "Uruguay", "Uzbekistán", "Vanuatu", "Venezuela",
        "Vietnam", "Wallis y Futuna", "Yemen", "Zambia")

    /**
     * Create a random Restaurant POJO.
     */
    fun getRandom(context: Context): Restaurant {
        val restaurant = Restaurant()
        val random = Random()

        // Cities (first elemnt is 'Any')
        var cities = context.resources.getStringArray(R.array.cities)
        cities = Arrays.copyOfRange(cities, 1, cities.size)

        // Categories (first element is 'Any')
        var categories = context.resources.getStringArray(R.array.categories)
        categories = Arrays.copyOfRange(categories, 1, categories.size)

        val prices = intArrayOf(1, 2, 3)

        restaurant.name = getRandomName(random)
        restaurant.continent = getRandomString(cities, random)
        restaurant.typeState = getRandomString(categories, random)
        restaurant.photo = getRandomImageUrl(random)
        restaurant.size = getRandomInt(prices, random)
        restaurant.numRatings = random.nextInt(20)

        // Note: average rating intentionally not set

        return restaurant
    }

    /**
     * Get a random image.
     */
    private fun getRandomImageUrl(random: Random): String {
        // Integer between 1 and MAX_IMAGE_NUM (inclusive)
        val id = random.nextInt(MAX_IMAGE_NUM) + 1

        return String.format(Locale.getDefault(), RESTAURANT_URL_FMT, id)
    }

    /**
     * Get price represented as dollar signs.
     */
    fun getPriceString(restaurant: Restaurant): String {
        return getPriceString(restaurant.size)
    }

    /**
     * Get price represented as dollar signs.
     */
    fun getPriceString(priceInt: Int): String {
        when (priceInt) {
            1 -> return "Grande"
            2 -> return "Mediano"
            3 -> return "Pequeño"
            else -> return "Pequeño"
        }
    }

    private fun getRandomName(random: Random): String {
        return (getRandomString(NAME_FIRST_WORDS, random))
    }

    private fun getRandomString(array: Array<String>, random: Random): String {
        val ind = random.nextInt(array.size)
        return array[ind]
    }

    private fun getRandomInt(array: IntArray, random: Random): Int {
        val ind = random.nextInt(array.size)
        return array[ind]
    }
}
