# Farm Game (Java OOP, Console)

A small, fully OOP-structured farm simulation you run in the terminal.

## Features
- OOP principles: encapsulation, inheritance, polymorphism, abstraction
- Day system, crops (Wheat, Corn), animals (Cow, Chicken)
- Buy/sell at the market, field and barn management
- Save/Load game (JSON via Gson)
- Maven project, Java 17

## Run
```bash
cd farm-game-java-en
mvn -q clean package
mvn -q exec:java
```
Or start a new game:
```bash
mvn -q exec:java -Dexec.args="--new"
```

## Keybinds
- `1`: Next day
- `2`: Plant
- `3`: Harvest
- `4`: Market (Buy/Sell)
- `5`: Animal care (Feed/Collect)
- `6`: Farm status
- `7`: Save game
- `0`: Exit

## Code structure
- `core`: Game loop, World, TimeSystem
- `entities.crops`: `Crop` (abstract), `Wheat`, `Corn`
- `entities.animals`: `Animal` (abstract), `Cow`, `Chicken`
- `buildings`: `Field`, `Barn`
- `inventory`: `Item`, `Inventory`
- `player`: `Player`
- `market`: `Market`
- `io`: `SaveLoad` (Gson)
