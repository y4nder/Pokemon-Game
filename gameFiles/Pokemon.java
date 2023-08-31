package gameFiles;
public class Pokemon {
    private String pokemonName;
    private String pokemonType;
    private int pokemonLevel;
    private String pokemonRarity;
    private String pokemonWeakness;
    private String formerName;
    private int maxLevel;
    private int CAP = 10;

    //constructors
    public Pokemon(){
        this.pokemonName = "";
        this.pokemonType = "";
        this.pokemonLevel = 0;
        this.pokemonRarity = "";
        this.pokemonWeakness = "";
        this.maxLevel = CAP;
    }

    public Pokemon(String name, String type, int lvl, String rarity, String weakness){
        this.pokemonName = name;
        this.pokemonType = type;
        this.pokemonLevel = lvl;
        this.pokemonRarity = rarity;
        this.pokemonWeakness = weakness;
        this.maxLevel = CAP;
    }

    public Pokemon(String name, String type, int lvl, String rarity, String weakness, String formerName){
        this.pokemonName = name;
        this.pokemonType = type;
        this.pokemonLevel = lvl;
        this.pokemonRarity = rarity;
        this.pokemonWeakness = weakness;
        this.formerName = formerName;
        this.maxLevel = CAP/2;
    }

    //public methods
    public void levelUp(){
        pokemonLevel++;  
    }   

    public void showPokemonDetails(){
        System.out.printf("%15s %15s %15s %15s %15s\n", pokemonName, pokemonType, pokemonLevel, pokemonRarity, pokemonWeakness);
    }

    //getters
    public String getPokemonName(){
        return pokemonName;
    }
    public int getPokemonLevel(){
        return pokemonLevel;
    }
    public String getPokemonType(){
        return pokemonType;
    }
    public String getPokemonRarity(){
        return pokemonRarity;
    }
    public String getPokemonWeakness(){
        return pokemonWeakness;
    }
    public String getFormerName(){
        return formerName;
    }
    public int getMaxLevel(){
        return maxLevel;
    }

    //setters
    public void setName(String name){
        this.pokemonName = name;
    }
    public void setType(String type){
        this.pokemonType = type;
    }
    public void setLevel(int lvl){
        this.pokemonLevel = lvl;
    }
    public void setRarity(String rarity){
        this.pokemonRarity = rarity;
    }
}
