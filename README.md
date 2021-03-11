# pokedex

## Abstract
Pokedex is a very small demo app. It based on the MVVM architecture pattern. I used several libraries, e.g. Retrofit, Koin, Coil, Moshi and JetPack (Navigation, Room, Lifecycle, LiveData, ViewModel).

Ultimately, the application will have 5 main screens (Home, Pokemons, Moves, Locations and More). 

Currently, the Pokemons screen is implemented (now the first 20 pokemon are being downloaded and in the future I am going to add a pagination library and enable page downloads). Pokemons can be added to favorites or caught.
![Image of pokemon list](https://github.com/kwiatekM/pokedex/blob/main/images/pokemon_list.png)
The screen with information about a specific pokemon is currently being expanded with further details.

Other screens are in the design phase.

## API
I decided to use PokeApi (www.pokeapi.co). It is provides a RESTful API interface.
