package gameFiles;
public class Trainer {
    private String trainerName;
    private Pokemon[] myPokemons;
    private int food;
    private int pokeballs;
    private int counter;
    private int MAX;

    //constructors
    public Trainer(){
        this.trainerName = "";
        this.pokeballs = 50;
        this.MAX = 50;
        this.myPokemons = new Pokemon[MAX];
        this.food = 50;
        this.counter = 0;
    }

    //setter
    public void setTrainerName(String name){this.trainerName = name;}

    //getter
    public String getTrainerName(){return trainerName;}
    public int getTrainerPokeballs(){return pokeballs;}
    public int getPokemonCount(){return counter;}
    public int availableSlots(){return MAX - counter;}
    public int getFoodCount(){return food;}

    //public methods
    public boolean catchPokemon(Pokemon pokemon){
        if(counter < MAX && pokeballs > 0){
            counter++;
            pokeballs--;
            System.out.println(pokemon.getPokemonName() + " was caught!");
            if(counter > 1){
                for(int i = 0; i < counter; i++){
                    if( myPokemons[i] != null && pokemon.getPokemonName().equalsIgnoreCase( myPokemons[i].getPokemonName() ) ){
                        if(myPokemons[i].getPokemonLevel() < myPokemons[i].getMaxLevel()){
                            myPokemons[i].levelUp();
                            System.out.println("YOU GOT A DUPLICATE! of " + pokemon.getPokemonName());
                            System.out.println(myPokemons[i].getPokemonName() +  " is now level " + (myPokemons[i].getPokemonLevel() - 1) + " --> " + myPokemons[i].getPokemonLevel() );
                            counter--;
                            return true;
                        }
                        else {
                            System.out.println( myPokemons[i].getPokemonName() + " is at maximum level!");
                            counter--;
                            return false;
                        }
                    }
                }
            }
            myPokemons[counter - 1] = pokemon;
            arrange();
            return true;
        }
        else {
            return false;
        }
    }

    public boolean levelUpPokemon(String pokemon){
        boolean flag = false;

        if(food > 0){
            for(int i = 0; i < counter; i++){
                if(pokemon.equalsIgnoreCase(myPokemons[i].getPokemonName()) ){
                    if(myPokemons[i].getPokemonLevel() < myPokemons[i].getMaxLevel()){
                        myPokemons[i].levelUp();
                        food -= 10;
                        flag = true;
                        return flag;
                    }
                    else{
                        System.out.println( myPokemons[i].getPokemonName() + " is at maximum level!");
                        return flag;
                    } 
                }
            }
            System.out.println("You do not own that pokemon");
            return flag;
        }
        System.out.println("You ran out of food");
        return flag;
    }

    public boolean evolveMyPokemon(Pokemon pokemon){
        boolean flag = false;
        if(pokemon == null) return flag;
        else{
            for(int i = 0; i < counter; i++){
                if(myPokemons[i].getPokemonName().equalsIgnoreCase(pokemon.getFormerName())){
                    System.out.println(myPokemons[i].getPokemonName() + " evolved into " + pokemon.getPokemonName());
                    myPokemons[i] = pokemon;
                    flag = true;
                    return flag;
                }
            }
            return flag;
        }
    }

    public void viewPokedex(){
        System.out.println("------------------ YOUR POKEDEX -------------------------------");
        System.out.println("Trainer: " + trainerName);
        System.out.println("Number of Food: " + food);
        System.out.println("Number of pokeballs: " + pokeballs);
        
        myPokemonList();
        System.out.println("---------------------------------------------------------------");
    }
    
    public void myPokemonList(){
        if(counter > 0){
            
            System.out.println("\nYour Pokemons: ");
            System.out.printf("   %15s %15s %15s %15s %15s\n", "-------", "----", "-----", "------", "--------");
            System.out.printf("   %15s %15s %15s %15s %15s\n", "Pokemon", "Type", "Level", "Rarity", "Weakness");
            System.out.printf("   %15s %15s %15s %15s %15s\n", "-------", "----", "-----", "------", "--------");
            System.out.println();
            
            for(int i = 0; i < counter; i++){
                System.out.print("[" + (i + 1) + "]");
                myPokemons[i].showPokemonDetails();
            }
        }
        else{
            System.out.println("No pokemons");
        }
    }

    public int findPokemon(String name){
        for( int i = 0; i < counter; i++ ){
            if( name.equalsIgnoreCase( myPokemons[i].getPokemonName() ) ){
                return i;
            }
        }
        return -1;
    }

    public boolean discardMyPokemon(int index){
        boolean flag = false;
        for(int i = index; i < counter; i++){
            myPokemons[i] = myPokemons[i+1];
            flag = true;
        }
        counter--;
        return flag;
    }

    //private methods
    private void arrange(){
        for(int i = 1; i < counter; i++){
            int j = i;
            while(j > 0 && alphaSort(myPokemons[j-1].getPokemonName(), myPokemons[j].getPokemonName())){
                swap(myPokemons, j-1, j);
                j--;
            }
        }
    }

    private boolean alphaSort(String name1, String name2){
        if(name1.equalsIgnoreCase(name2)) return false;

        for(int i = 0; i < name1.length() && i < name2.length();){
            if(name1.charAt(i) > name2.charAt(i)) return true;
            return false;
        }

        if(name1.length() > name2.length()) return true;
        else return false;
    }

    private void swap(Pokemon[] pokemon, int i, int j){
        Pokemon temp = pokemon[i];
        pokemon[i] = pokemon[j];
        pokemon[j] = temp;    
    }
}
