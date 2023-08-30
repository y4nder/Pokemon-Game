import java.util.Scanner;

public class PokemonDriver {
    //static objects
    static Scanner scan = new Scanner(System.in);
    static PokemonList list = new PokemonList();
    static Trainer p1 = new Trainer();
    
    //main method
    public static void main(String []args){
        System.out.println("Welcome to the trainer World~~~");
        System.out.println();

        System.out.print("What should I call you? ");
        p1.setTrainerName(scan.nextLine());

        System.out.println();
        System.out.println("Welcome Trainer " + p1.getTrainerName());
        
        showMainMenu();        
        System.out.println("Goodbye " + p1.getTrainerName());
    }

    //static methods
    public static void showMainMenu(){
        char option;
        do{
            System.out.println("------------ Menu ------------");
            System.out.println("    [1] Show Pokedex");
            System.out.println("    [2] Catch Pokemon");
            System.out.println("    [3] Level Up Pokemon");
            System.out.println("    [4] Edit Pokemon List");
            System.out.println("    [X] Exit");
            System.out.println("------------------------------");
            option = scan.next().toUpperCase().charAt(0);
            switch(option){
                case '1': 
                    char op;
                    do{
                        System.out.println("\n--> show pokedex\n");
                        p1.viewPokedex();
                        System.out.println("    ------");
                        System.out.println("--  [D] Discard a Pokemon");
                        System.out.println("--  [x] back <-");
                        op = scan.next().toUpperCase().charAt(0);
                        switch(op){
                            case 'D': 
                                if(p1.getPokemonCount() > 0){
                                    System.out.print("name of pokemon to DISCARD: ");
                                    String name = scan.next();
                                    if (p1.discardMyPokemon(p1.findPokemon(name)) == true)
                                        System.out.println(name.toUpperCase() + " was discarded from your pokedex");
                                    else System.out.println(name + " is not in your pokedex");
                                }
                                else System.out.println("YOU DO NOT OWN ANY POKEMONS YET!");

                                break;
                            case 'X': break;
                        }
                        
                    }
                    while(op != 'X');
                    break;

                case '2':
                    char opt;
                    do{
                        System.out.println("\n--> Catch Pokemon");
                        System.out.println("Pokeballs: " + p1.getTrainerPokeballs() + "\n");
                        System.out.println("    [1] Catch 1x");
                        System.out.println("    [2] Catch 10x");
                        System.out.println("    [3] Pokedex");
                        System.out.println("    [X] cancel <-");
                        opt = scan.next().toUpperCase().charAt(0);
                        Pokemon x;
                        switch(opt){
                            case '1':
                                x = list.getPokemon();
                                if(p1.catchPokemon(x) == true);
                                else {
                                    if(p1.getTrainerPokeballs() < 0)
                                        System.out.println(p1.getTrainerName() + " ran out of pokeballs");
                                    else{
                                        if (p1.evolveMyPokemon(list.getEvolvedPokemon(x.getPokemonName())))
                                            System.out.println("Your Pokemon Evolved!");
                                    }
                                }
                                break;
                            case '2':
                                if( (p1.getTrainerPokeballs() >= 10 ) && (p1.availableSlots() >=10 ) ){
                                    for(int i = 0; i < 10; i++){
                                        x = list.getPokemon();
                                        System.out.print("[" + (i + 1) + "] ");
                                        if(p1.catchPokemon(x) == true);
                                        else{
                                            if(p1.evolveMyPokemon(list.getEvolvedPokemon(x.getPokemonName())) == true);
                                        }
                                        System.out.println();
                                    }
                                    break;
                                }
                                else{
                                    if(p1.getTrainerPokeballs() < 10)
                                        System.out.println("Insufficient number of pokeballs :<");
                                    else System.out.println("Insufficient number of slots :<");
                                    break;
                                }

                            case '3': 
                                System.out.println("Show Pokedex\n");
                                p1.viewPokedex();
                                break;

                            case 'X': break;
    
                        }

                    }
                    while(opt != 'X');
                    break;

                case '3':
                    System.out.println("Level Up Pokemon\n");
                    p1.myPokemonList();
                    System.out.print("Pokemon Name to level up: ");
                    String pokemon = scan.next();
                    if(p1.levelUpPokemon(pokemon)== true) System.out.println("Leveled up");
                    else {
                        if (p1.evolveMyPokemon(list.getEvolvedPokemon(pokemon)) == true)
                            System.out.println("Your Pokemon Evolved!");
                        else System.out.println("no evolution happened :<");
                    }
                    break;

                case '4':
                    showPokemonMenu();
                    break;

                case 'X':
                    break;
            }
        }
        while(option != 'X');
    }

    public static void showPokemonMenu(){
        char option;        
        do{
            System.out.println("------------ PokemonList ------------");
            System.out.println("    [1] Display all Pokemons");
            System.out.println("    [2] Create a Pokemon");
            System.out.println("    [3] Pokemon info");
            System.out.println("    [4] Remove a Pokemon");
            System.out.println("    [X] <- back");
            System.out.println("-------------------------------------");
            option = scan.next().toUpperCase().charAt(0);
            switch(option){
                case '1':
                    list.displayPokemonList();
                    break;

                case '2':
                    System.out.println("    --> Evolved or Normal?");
                    System.out.println("    [1] Normal");
                    System.out.println("    [2] Evolved");
                    int x = scan.nextInt();
                    if(list.insertSorted(list.addPokemon(x)) == true)
                        System.out.println("Pokemon Created!");
                    else System.out.println("List of pokemons is already full!");
                    break;

                /*case '3':
                    list.displayPokemonList();
                    System.out.print("enter pokemon number: ");
                    int x = scan.nextInt();
                    if( list.getAt(x) == null) 
                        System.out.println("No Pokemon at that index");
                    else {
                        if( x > list.getCount() ) list.showEvolvedPokemonDetails(list.getAt(x));
                        else list.showPokemonDetails(x);
                    }
                    break;*/

                case '4':
                    list.displayPokemonList();
                    System.out.print("Enter pokemon name to remove: ");
                    String name = scan.next();

                    if(list.delete(list.getIndex(list.findPokemon(name)))==true){
                        System.out.println("\nREMOVED " + name.toUpperCase());
                    }
                    else System.out.println(name + " does not exist in the list");

                    list.displayPokemonList();
                    
                    break;

                case 'X':
                    break;

            }
        }
        while(option != 'X');
    }
}
