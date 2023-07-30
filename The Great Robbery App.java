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
    String name;
    private String nickname;
    private int yearOfBorn;
    private String expertIn;
    private Item[] items;
    Person(String name,String nickname, int yearOfBorn, String expertIn)
    {
        this.name = name;
        this.nickname = nickname;
        this.yearOfBorn = yearOfBorn;
        this.expertIn = expertIn;
        
    }

    void printBioData()
    {
        System.out.println("Name :"+name);
        System.out.println("Nickname: " + nickname);
        System.out.println("Year of Birth: " + yearOfBorn);
        System.out.println("Expert in: " + expertIn);
        System.out.println("Items:");

        for (Item item : items) 
        {
            System.out.println("- " + item.getName());
        }
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

class Item
{
    private String name;
    private double value;

    void Item(String name, double value) 
    {
        this.name = name;
        this.value = value;
    }

    String getName() 
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

    Criminals(String name, String nickname, int yearOfBorn, String expertIn) 
    {
        super(name, nickname, yearOfBorn, expertIn);
    }

    void printBioData() 
    {
        System.out.println("Criminal person:");
        super.printBioData();
    }

}

class Detective extends Person
{
    static final int SUCCESS_PERCENTAGE = 75;

    Detective(String name, String nickname, int yearOfBorn, String expertIn)
    {
        super(name, nickname, yearOfBorn, expertIn);
    }
    void printBioData() 
    {
        System.out.println("Detective:");
        super.printBioData();
    }
}
    

//criminals to steal
class Building
{
    private String name;
    private Item[] items; 

    Building(String name, Item[] items) 
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
    City() 
    {
        Building[] buildings = new Building[4];
        
        Item[] bank = {new Item("Letter opener", 1.5),new Item("Stamp", 2.5) };
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
    private int randomNumber;
    private double sumRobbedValue;
    private Criminals[] criminals;
    private Item[] byAgentE,byAgentA;

    Gang() 
    {
        Criminals[] criminals = new Criminals[2];

        Item[] byAgentE= {new Item("Emarald Necklace",250),new Item("Cash Bag",100)};
        Item[] byAgentA={ new Item("Diamond Ring",150),new Item("Gold Bangles",175)};

        criminals[0] = new Rob("Eshita", "AgentE", 2004, "Searching Valuables", byAgentE);
        criminals[1] = new Bobby("Anwesha", "AgentA", 2003, "Unlocking Safes", byAgentA);
    }

    double getsumRobbedValue()
    {
        for(Item item : byAgentE)
        {
            sumRobbedValue= byAgentE.value;
        }
        for(Item item: byAgentA)
        {
            sumRobbedValue= byAgentA.value;
        }
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
            System.out.println("The gang managed to rob the following items from the"+ selectedBuilding.getName() +":");

        for (Item item : selectedBuilding.getItems()) 
        {
                sumRobbedValue += item.getValue();
                System.out.println(item.getName());
        }
        }

        else
        {
            System.out.println("The gang tried to rob the"+ selectedBuilding.getName() + "but they" + "failed");
        }
    }

}

class Police
{
    private Detective adamPalmer;
    Police()
    {
        Detective adamPalmer =new Detective("Adam Palmer","DctvAP",1990,"Suspecting");
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
            System.out.println(adamPalmer.name+" managed to catch the gang.");
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
            return false;
        }
    }
}
