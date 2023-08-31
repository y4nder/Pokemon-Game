package gameFiles;
import java.util.Random;
import java.util.Scanner;
public class PokemonList{
    private Pokemon[] pokemonList;
    private Pokemon[] evolvedPokemons;
    private int MAX = 99;
    private int counter;
    private int counterEv;
    private Random ran;
    private Scanner scan;
    
    public PokemonList(){
        this.pokemonList = new Pokemon[MAX];
        this.pokemonList[0] = new Pokemon("Bulbasaur", "Grass", 1, "Gommon", "Fire");
        this.pokemonList[1] = new Pokemon("Snorlax", "Colorless", 1, "Uncommon" ,"Fighting");
        this.pokemonList[2] = new Pokemon("Squirtle", "Water", 1, "Rare", "Lightning");
        this.pokemonList[3] = new Pokemon("Pikachu", "Lightning", 3, "SR", "Fighting");
        this.counter = 4;

        this.evolvedPokemons = new Pokemon[MAX];
        this.evolvedPokemons[0] = new Pokemon("Wartortle", "Water", 1, "SR", "Lightning", "Squirtle");
        this.evolvedPokemons[1] = new Pokemon("Ivysaur", "Grass", 1, "SR", "Fire", "Bulbasaur");
        this.counterEv = 2;
        ran = new Random();
        scan = new Scanner(System.in);
    }

    public Pokemon getPokemon(){
        int index = ran.nextInt(0, counter);
        return pokemonList[index];
    }

    public Pokemon getEvolvedPokemon(String name){
        for(int i = 0; i < counterEv; i++){
            if(name.equalsIgnoreCase(evolvedPokemons[i].getFormerName())){
                return evolvedPokemons[i];
            }
        }
        return null;
    }

    public void displayPokemonList(){
        System.out.println();
        System.out.println("list of available pokemons out there: ");
        System.out.printf("   %15s %15s %15s %15s %15s\n", "-------", "----", "-----", "------", "--------");
        System.out.printf("   %15s %15s %15s %15s %15s\n", "Pokemon", "Type", "Level", "Rarity", "Weakness");
        System.out.printf("   %15s %15s %15s %15s %15s\n", "-------", "----", "-----", "------", "--------");
        
        for(int i = 0; i < counter; i ++){
            System.out.print("[" + (i + 1) + "]");
            pokemonList[i].showPokemonDetails();
        }
        
        System.out.println("\n------------------------------- EVOLVED ------------------------------------------");

        for(int i = 0; i < counterEv; i ++){
            System.out.print("[" + (counter + i + 1) + "]");
            evolvedPokemons[i].showPokemonDetails();
        }

        System.out.println();
    }
    
    public Pokemon addPokemon(int x){
        if(x == 1){
            if(counter < MAX) return new Pokemon();
            else return null;
        }
        else{
            if(counterEv < MAX) return new Pokemon("", "", 0, "", "", "" );
        }
        return null; 
    }

    public boolean insertSorted(Pokemon pokemon){
        if(pokemon == null){
            return false;
        }
        else{
            counter++;
            this.pokemonList[counter-1] = pokemon;
            System.out.println("Adding a new Pokemon..");
            System.out.print("Name: ");
            pokemonList[counter-1].setName(scan.next());
        
            System.out.print("Type: ");
            pokemonList[counter-1].setType(scan.next());
                    
            System.out.print("Level: ");
            pokemonList[counter-1].setLevel(scan.nextInt());

            System.out.print("Rarity: ");
            pokemonList[counter-1].setRarity(scan.next());
                    
            if(counter > 2){
                sortByLevel();
            }

            return true;
        }
    }

    private void sortByLevel(){
        for(int i = 1; i < counter; i++){
            int j = i;
            while(j > 0 && pokemonList[j-1].getPokemonLevel() > pokemonList[j].getPokemonLevel()){
                swap(pokemonList, j-1, j);
                j--;
            }
        }
    }

    private void swap(Pokemon[] pokemon, int i, int j ){
        Pokemon temp = pokemon[i];
        pokemon[i] = pokemon[j];
        pokemon[j] = temp;
    }

    public int findPokemon(String name){
        for(int i = 0; i < counter; i++ ){
            if(name.equalsIgnoreCase(pokemonList[i].getPokemonName())){
                return i;
            }
        }
        for(int i = 0; i < counterEv; i++){
            if(name.equalsIgnoreCase(evolvedPokemons[i].getPokemonName())){
                return counter + i;
            }            
        }
        return -1;
    }

    public Pokemon getIndex(int i){
        if(i != -1){
            if(i < counter)
                return pokemonList[i];
            else 
                return evolvedPokemons[ i - counter];
        }
        else return null; 
    }

    public boolean delete(Pokemon pokemon){
        if(pokemon != null){
            int i = findPokemon(pokemon.getPokemonName());
            if(i < counter){
                for( ; i < counter-1; i++){
                    pokemonList[i] = pokemonList[i+1];
                }
                counter--;
                return true;
            }
            else{
                i = i - counter;
                for( ; i < counterEv - 1; i++){
                    evolvedPokemons[i] = evolvedPokemons[i+1];
                }
                counterEv--;
                return true;
            }
        }
        else return false;
    }

    public Pokemon getAt(int i){
        if(i <= (counterEv + counter) ){
            if (i >= 0 && i <= counter) 
                return pokemonList[i-1];
            else 
                return evolvedPokemons[i - (counter + 1)];
        }
        else{
            return null;
        }
    }

    public void showPokemonDetails(int i){
        System.out.println("----------- POKEMON INFO ------------------");
        System.out.println("Pokemon name: " + pokemonList[i-1].getPokemonName());
        System.out.println("Type: " + pokemonList[i-1].getPokemonType());
        System.out.println("Level: " + pokemonList[i-1].getPokemonLevel());
        System.out.println("Rarity: " + pokemonList[i-1].getPokemonRarity());
    }
    public void showEvolvedPokemonDetails(Pokemon pokemon){
        System.out.println("----------- POKEMON INFO ------------------");
        System.out.println("Pokemon name: " + pokemon.getPokemonName());
        System.out.println("Type: " + pokemon.getPokemonType());
        System.out.println("Level: " + pokemon.getPokemonLevel());
        System.out.println("Rarity: " + pokemon.getPokemonRarity());
    }

    public int getCount(){
        return counter;
    }

}