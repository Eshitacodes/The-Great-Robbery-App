import java.util.Random;
public class TheGreatRoberyApp
{
    public static void main(String[] args) 
    {
        City city= new City();
        Gang gang=new Gang();
        Police police=new Police();

        gang.getGangInfo();
        gang.letRob(city.getBuildings());
        boolean isGangCaught=police.catchCriminals(gang);

        do
        {
            gang.letRob(city.getBuildings());
            isGangCaught= police.catchCriminals(gang);
        }while(!isGangCaught);
    }
}



abstract class Person
{
    public String name;
    private String nickname;
    private int yearOfBorn;
    private String expertIn;
    Item[] items;
    
    public Person(String name,String nickname, int yearOfBorn, String expertIn)
    {
        this.name = name;
        this.nickname = nickname;
        this.yearOfBorn = yearOfBorn;
        this.expertIn = expertIn;
        
        
    }

    boolean printBioData()
    {
        System.out.println("Name :"+name);
        System.out.println("Nickname: " + nickname);
        System.out.println("Year of Birth: " + yearOfBorn);
        System.out.println("Expert in:" + expertIn);
        System.out.println("Items:\n");

        Gang gang=new Gang();
        
        for (Item item : gang.items) 
        {
            
            System.out.println("- " + item.getName());
        }
        return false;
    }


    String getName() 
    {
        return name;
    }

    String getNickname() 
    {
        return nickname;
    }
}



public class Item
{
    public String name;
    public double value;

    public Item(String name, double value) 
    {
        this.name = name;
        this.value = value;
    }

    public String getName() 
    {
        return name;
    }

    double getValue() 
    {
        return value;
    }
}



class Criminals extends Person
{
    static final int SUCCESS_PERCENTAGE = 85;

    public Criminals(String name, String nickname, int yearOfBorn, String expertIn) 
    {
        super(name, nickname, yearOfBorn, expertIn);
    }

    boolean printBioData() 
    {
        System.out.println("\nCriminal person:\n");
        super.printBioData();
        return false;
    }

}



class Detective extends Person
{
    static final int SUCCESS_PERCENTAGE = 75;

    public Detective(String name, String nickname, int yearOfBorn, String expertIn)
    {
        super(name, nickname, yearOfBorn, expertIn);
    }
    boolean printBioData() 
    {
        System.out.println("Detective:");
        super.printBioData();
        return false;
    }
}
    


//criminals to steal
class Building
{
    public String name;
    public Item[] items; 

    public Building(String name, Item[] items) 
    {
        this.name = name;
        this.items = items;
    }
    String getName()
    {
        return name;
    }
    Item[] getItems()
    {
        return items;
    }

}



class City
{
    Building[] buildings = new Building[4];
    
    public City() 
    {
        Item[] bank =new Item[2];
        bank[0] = new Item("Letter opener", 1.5);
        bank[1]= new Item("Stamp", 2.5);
        buildings[0] =new Building("Bank", bank);

        Item[] mansion = { new Item("Pair of fancy shoes", 25), new Item("Broken glass", 0.1) };
        buildings[1] = new Building("Mansion", mansion);

        Item[] postOffice = { new Item("Letter to Jenny", 1.5), new Item("Pencil", 2.0) };
        buildings[2] = new Building("Post Office", postOffice);

        Item[] supermarket = { new Item("A loaf of bread", 2.5), new Item("A bag of tea", 6.5) };
        buildings[3] = new Building("Supermarket", supermarket);
    }

    Building[] getBuildings() 
    {
        return buildings;
    }
}



class Gang
{
    Random random= new Random();
    public int randomNumber;
    public double sumRobbedValue;
    //public Criminals[] criminals;
    //public Item[] byAgentE,byAgentA;

    public Item[] items=new Item[4];

    Criminals[] criminals = new Criminals[2];

    

    public Gang() 
    {
        //Item[] byAgentE= {new Item("Emarald Necklace",250), new Item("Cash Bag",100)};
        //Item[] byAgentA={ new Item("Diamond Ring",150),new Item("Gold Bangles",175)};

        criminals[0] = new Criminals("Rob", "AgentR", 2004, "Searching Valuables");
        criminals[1] = new Criminals("Bobby", "AgentB", 2003, "Unlocking Safes");

        items[0]=new Item("Emarald Necklace",250);
        items[1]=new Item("Cash Bag",100);
        items[2]=new Item("Diamond Ring",150);
        items[3]=new Item("Gold Bangles",175);
    }

    double getsumRobbedValue()
    {

        /*for(Item items : items  )
        {
            sumRobbedValue= sumRobbedValue+items.value;
        }
        /*for(Item item: items)
        {
            sumRobbedValue= item.value;
        }*/
        return sumRobbedValue;
    }

    void getGangInfo()
    {
        for(Criminals criminals: criminals)
        {
            System.out.println(criminals.printBioData());
        }
    }

    boolean isSuccessfulRobbery()
    {
        randomNumber=random.nextInt(100);
        int SuccessRob= Criminals.SUCCESS_PERCENTAGE * criminals.length;

        if(randomNumber <SuccessRob)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    void letRob(Building[] buildings)
    {
        int randomBuilding= random.nextInt(buildings.length);
        Building selectedBuilding = buildings[randomBuilding];

        if(isSuccessfulRobbery()==true)
        {
            System.out.println("\nThe gang managed to rob the following items from the "+ selectedBuilding.getName() +":\n");

        for (Item item : selectedBuilding.getItems()) 
        {
                sumRobbedValue += item.getValue();
                System.out.println(item.getName());
                
        }
        }

        else
        {
            System.out.println("\nThe gang tried to rob the "+ selectedBuilding.getName() + "but they" + "failed\n");
        }
    }

   

}



class Police
{
    public Detective adamPalmer;
    public Police()
    {
        adamPalmer =new Detective("Adam Palmer","DctvAP",1990,"Suspecting");
    }

    boolean areCriminalsCaught()
    {
        Random random=new Random();
        int randomNumber2=random.nextInt(100);

        if(randomNumber2  >=Detective.SUCCESS_PERCENTAGE)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean catchCriminals(Gang gang)
    {
        if(areCriminalsCaught()==true)
        {
            System.out.println("\n"+adamPalmer.name+" managed to catch the gang.\n");
            if (gang.getsumRobbedValue() > 0) 
            {
            System.out.println("The stolen items are recovered. Their overall value is estimated to $" + gang.getsumRobbedValue());
            } 
            else 
            {
                System.out.println("The gang couldn't steal anything.");
            }
            return true;
        }
        else if(areCriminalsCaught()==false)
        {
            System.out.println(adamPalmer.getName()+" managed to catch the gang.");
            System.out.println("They managed to steal items valued $"+ gang.getsumRobbedValue());
            
        }
        return false;
    }
}
