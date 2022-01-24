package Frame;
import java.io.*;
import java.util.*;
import java.time.*;
public class Interpreter {

    //print doesnt take in anything
//Load takes in a string
//Blank constructor
    //if reg's aren't initialized its just 0 or false depending on int or bool
public Interpreter(){};
    ArrayList<String> vars = new ArrayList<String>();
    //Array List to store commands
    ArrayList<String> commands = new ArrayList<String>();

    HashMap<String, Integer> zemap = new HashMap<String, Integer>();

    Panel panel;
    IImage image;
    //panel to use image.getImage()

    //returns image which is input into image

    //that image is used for get and set color

public void load(String filename)
{
    //Array Lists to store variables

    //Bools will be represented in hashmap with a 1 or 0 for true or false respectively.
int labelcounter=0;
    try {
        Scanner scan = new Scanner(new File(filename));
        String operation = "t";
        System.out.println("The current time is "+System.currentTimeMillis());
//hashmap store an array
        while (scan.hasNext()) {
            operation=scan.next();
            labelcounter++;
            System.out.println(operation);
            //Variable commands
            switch(operation) {
                case "int":
               // if (operation.equals("int"))
                {
                    String numvar = scan.next();
                    vars.add("int " + numvar);
                    zemap.put("int "+numvar,0);
                    commands.add("int " + numvar);
                    break;
                }
                case "bool":{
                    //if (operation.equals("bool")) {
                    String boolvar = scan.next();
                    //Sets initial bool val to 0
                    zemap.put("bool "+boolvar, 0);
                    commands.add("bool " + boolvar);
                    break;
                }
                case "label":
                {
                    String labeltype=scan.next();

                    zemap.put("label "+labeltype,labelcounter);
                    commands.add(operation +" "+labeltype);
                    System.out.println(zemap);
                    break;
                }
                //Arithemtic Ops
                case "+": {
                    //if (operation.equals("+"))
                    {
                        String pluscommand = "+";
                        for (int i = 0; i < 3; i++) {
                            pluscommand += " " + scan.next();
                        }
                        commands.add(pluscommand);
                        break;
                    }
                }

                case "-": {
                //if (operation.equals("-"))

                    String subcommand = "-";
                    for (int i = 0; i < 3; i++) {
                        subcommand += " " + scan.next();
                    }
                    commands.add(subcommand);
                    break;
                }
                //if (operation.equals("*"))
                case "*":
                {
                    String multcommand = "*";
                    for (int i = 0; i < 3; i++) {
                        multcommand += " " + scan.next();
                    }
                    commands.add(multcommand);
                    break;
                }
                //if (operation.equals("/"))
                case "/":
                {
                    String divcommand = "/";
                    for (int i = 0; i < 3; i++) {
                        divcommand += " " + scan.next();
                    }
                    commands.add(divcommand);
                    break;
                }
                case "=":
                {
                    String eqcommand = "=";
                    for (int i = 0; i < 2; i++) {
                        eqcommand += " " + scan.next();
                    }
                    commands.add(eqcommand);
                    break;
                }

                //if (operation.equals("%"))
                case "%":
                {
                    String modcommand = "%";
                    for (int i = 0; i < 3; i++) {
                        modcommand += " " + scan.next();
                    }
                    commands.add(modcommand);
                    break;
                }
                //Bool Commands
                //if (operation.equals("=="))
                case "==":
                {
                    String dubequalscommand = "==";
                    for (int i = 0; i < 3; i++) {
                        dubequalscommand += " " + scan.next();
                    }
                    commands.add(dubequalscommand);
                    break;
                }
                case ">":
                //if (operation.equals(">"))
                 {
                    String greatercommand = ">";
                    for (int i = 0; i < 3; i++) {
                        greatercommand += " " + scan.next();
                    }
                    commands.add(greatercommand);
                     break;
                }
                //if (operation.equals(">="))
                case ">=":
                {
                    String greatequalcommand = ">=";
                    for (int i = 0; i < 3; i++) {
                        greatequalcommand += " " + scan.next();
                    }
                    commands.add(greatequalcommand);
                    break;
                }
                //if (operation.equals("&"))
                case "&":
                {
                    String ampersandcommand = "&";
                    for (int i = 0; i < 3; i++) {
                        ampersandcommand += " " + scan.next();
                    }
                    commands.add(ampersandcommand);
                    break;
                }
                case "|":
                //if (operation.equals("|"))
                    {
                    String orcommand = "|";
                    for (int i = 0; i < 3; i++) {
                        orcommand += " " + scan.next();
                    }
                    commands.add(orcommand);
                    break;
                }
                case "jumpif":
                {
                    String jumpifcommand= "jumpif";
                    for(int i=0; i<2; i++)
                    {
                        jumpifcommand += " "+scan.next();
                    }
                    commands.add(jumpifcommand);
                    break;
                }
                case "setpixel":
                {
                    String setpixelcmd= "setpixel";
                    for(int i=0; i<3; i++)
                    {
                        setpixelcmd += " "+scan.next();
                    }
                    commands.add(setpixelcmd);
                    break;
                }
                case "getpixel":
                {
                    String getpixelcmd= "getpixel";
                    for(int i=0; i<3; i++)
                    {
                        getpixelcmd += " "+scan.next();
                    }
                    commands.add(getpixelcmd);
                }
                default:
                {
                    if(scan.hasNext()) {
                        commands.add(operation +" "+ scan.next());
                    }
                }

            }
            System.out.println(zemap);

        }
    }catch(FileNotFoundException fnfe)
    {
        System.out.println("Error with ze file");
    }
    }


public void printProgram()
{
    //Print out data structure
    System.out.println(commands);
}
public void run()
{


    //loop through arraylist to access commands

    int counter=-1;
    //Keeps track of lines in bytecode
    int secondcounter=0;
            do{
                counter++;
                secondcounter++;
                Scanner stringscan= new Scanner(commands.get(counter));
                String operator= stringscan.next();
                switch(operator) {
                    case "+": {
                        int x;
                        int y;
                        int z;
                        //Add 2 numbers and store them back to i
                        String keycheck= stringscan.next();
                        int numbertobeadded= stringscan.nextInt();

                        //Look at what you need to do with the extra var
                        String extravar=stringscan.next();
                        if(zemap.containsKey("int "+keycheck) && zemap.containsKey("int "+extravar))
                        {
                            x=zemap.get("int "+keycheck);
                            System.out.println("x is "+x);
                            y=numbertobeadded;
                            System.out.println("y is "+y);
                            z=zemap.get("int "+extravar);
                            System.out.println("z is "+z);
                            x=z+y;
                            System.out.println("sum is "+x);
                            zemap.put("int "+keycheck,x);
                            System.out.println("sum is stored in "+keycheck);


                        }
                        else if(zemap.containsKey("int "+keycheck)) {
                            x = zemap.get("int "+keycheck);
                            y = numbertobeadded;
                            z = x + y;
                            zemap.put("int "+keycheck, z);
                        }

                       System.out.println("Addition: "+zemap.get("int "+keycheck));
                        break;
                    }
                    case "-": {
                        int x;
                        int y;
                        int z;
                        //Add 2 numbers and store them back to i
                        String keycheck= stringscan.next();
                        String extravar=stringscan.next();
                        int numbertobesubtracted= stringscan.nextInt();

                        //Look at what you need to do with the extra var

                        if(zemap.containsKey("int "+keycheck) && zemap.containsKey("int "+extravar))
                        {
                            x=zemap.get("int "+keycheck);
                            y=numbertobesubtracted;
                            z=zemap.get("int "+extravar);
                            x=z-y;
                            zemap.put("int "+keycheck,x);


                        }
                        else if(zemap.containsKey("int "+keycheck)) {
                            x = zemap.get("int "+keycheck);
                            y = numbertobesubtracted;
                            z = x - y;
                            zemap.put("int "+keycheck, z);
                        }

                        System.out.println("Subtraction: "+zemap.get(keycheck));
                        break;
                    }
                    case "*": {
                        int x;
                        int y;
                        int z;
                        //Add 2 numbers and store them back to i
                        String keycheck= stringscan.next();
                        String extravar=stringscan.next();
                        int numbertobemultiplied= stringscan.nextInt();

                        //Look at what you need to do with the extra var

                        if(zemap.containsKey("int "+keycheck) && zemap.containsKey("int "+extravar))
                        {
                            x=zemap.get("int "+keycheck);
                            y=numbertobemultiplied;
                            z=zemap.get("int "+extravar);
                            x=y*z;
                            zemap.put("int "+keycheck,x);


                        }
                        else if(zemap.containsKey("int "+keycheck)) {
                            x = zemap.get("int "+keycheck);
                            y = numbertobemultiplied;
                            z = x * y;
                            zemap.put("int "+keycheck, z);
                        }

                        System.out.println("Multiplication: "+zemap.get("int "+keycheck));
                        break;
                    }
                    case "/": {
                        //Divide t by 25
                        int x;
                        int y;
                        int z;
                        //Add 2 numbers and store them back to i
                        String keycheck= stringscan.next();
                        String extravar=stringscan.next();
                        int numbertobedivided= stringscan.nextInt();

                        //Look at what you need to do with the extra var

                        if(zemap.containsKey("int "+keycheck) && zemap.containsKey("int "+extravar))
                        {
                            x=zemap.get("int "+keycheck);
                            y=numbertobedivided;
                            z=zemap.get("int "+extravar);
                            x=(z/y);
                            zemap.put("int "+keycheck,x);


                        }
                        else if(zemap.containsKey("int "+keycheck)) {
                            x = zemap.get("int "+keycheck);
                            y = numbertobedivided;
                            z = x / y;
                            zemap.put("int "+keycheck, z);
                        }

                        System.out.println("Division: "+zemap.get("int "+keycheck));
                        break;
                    }
                    case "=":
                    {
                        String keyval= stringscan.next();
                        int value= stringscan.nextInt();
                        if(zemap.containsKey("int "+keyval))
                        {
                            zemap.put("int "+keyval,value);
                        }

                        break;
                    }
                    case "%": {
                        int x;
                        int y;
                        int z;
                        //Add 2 numbers and store them back to i
                        String keycheck= stringscan.next();
                        String extravar=stringscan.next();
                        int numbertobemodulus= stringscan.nextInt();

                        //Look at what you need to do with the extra var

                        if(zemap.containsKey("int "+keycheck) && zemap.containsKey("int "+extravar))
                        {
                            x=zemap.get("int "+keycheck);
                            System.out.println("x is "+x);
                            y=numbertobemodulus;
                            System.out.println("y is "+y);
                            z=zemap.get("int "+extravar);
                            System.out.println("z is "+z);
                            if(z==0)
                            {
                                // do nothing
                            }
                            else {
                                x = z % y;
                            }
                            zemap.put("int "+keycheck,x);


                        }
                        else if(zemap.containsKey("int "+keycheck)) {
                            x = zemap.get("int "+keycheck);
                            y = numbertobemodulus;
                            z = x % y;
                            zemap.put("int "+keycheck, z);
                        }

                        System.out.println("Modulus: "+zemap.get("int "+keycheck));
                        break;
                    }
                    //boolean functions
                        //Possibly add a bool case to ==?
                    case "==": {
                        //Takes in values to compare
                        String keycheck= stringscan.next();
                        System.out.println(keycheck);
                        String keycheck2=stringscan.next();
                        System.out.println(keycheck2);
                        int numbertobecompared= stringscan.nextInt();
                        System.out.println(numbertobecompared);

                        //Look at what you need to do with the extra var

                        if(zemap.containsKey("bool "+keycheck) && zemap.containsKey("int "+keycheck2))
                        {
                            //True
                            System.out.println(zemap.get("int "+keycheck2));
                            if(zemap.get("int "+keycheck2)==numbertobecompared)
                            {
                                zemap.put("bool "+keycheck,1);
                                System.out.println("Result is true");
                            }
                            //False
                            else
                                zemap.put("bool "+keycheck,0);
                                System.out.println("Result is false");
                        }
                        break;
                    }
                    case ">": {
                        //Takes in values to compare
                        String keycheck= stringscan.next();
                        String keycheck2=stringscan.next();
                        String numbertobecompared= stringscan.next();


                        if(zemap.containsKey("int "+keycheck) && zemap.containsKey("int "+keycheck2) && zemap.containsKey("int "+numbertobecompared))
                        {
                            //True
                            if(zemap.get("int "+keycheck2)>zemap.get("int "+numbertobecompared))
                            {
                                zemap.put("int "+keycheck,1);
                            }
                            //False
                            else
                                zemap.put("int "+keycheck,0);
                        }
                        else if(zemap.containsKey("int "+keycheck) && zemap.containsKey("int "+keycheck2))
                        {
                            int number= Integer.parseInt(numbertobecompared);
                            if(zemap.get("int "+keycheck2)>number)
                            {
                                zemap.put("int "+keycheck,1);
                            }
                            //False
                            else
                                zemap.put("int "+keycheck,0);
                        }

                    }
                    /*case ">=": {
                        String keycheck= stringscan.next();
                        String keycheck2= stringscan.next();
                        int numbertobecompared= stringscan.nextInt();

                        //Look at what you need to do with the extra var

                        if(zemap.containsKey("int "+keycheck) && zemap.containsKey("int "+keycheck2))
                        {
                            //True
                            if(zemap.get("int "+keycheck2)>=numbertobecompared)
                            {
                                zemap.put("int "+keycheck,1);
                            }
                            //False
                            else
                                zemap.put("int "+keycheck,0);
                        }
                        break;
                    }*/
                    case "&": {

                        break;
                    }
                    case "|": {

                        break;
                    }
                    //other functions
                    case "jump":
                    {
                        //resets the loop
                        String loopjump=stringscan.next();
                        //Sets counter back
                        secondcounter=zemap.get("label "+loopjump);
                        counter=secondcounter-1;
                        System.out.println("Counter is "+secondcounter);
                        System.out.println("Went back to "+loopjump);

                        break;
                    }
                    case "jumpif":
                    {
                        //If result is True, jump to exiti
                        String boolvarcondition= stringscan.next();
                        String labelname= stringscan.next();
                        //System.out.println("Bool val is "+zemap.get("bool "+boolvarcondition));
                        if(zemap.get("bool "+boolvarcondition) == 1)
                        {
                            counter=zemap.get("label "+labelname);

                        }
                        else {
                            //Else
                            //Continue with program
                            //let counter increment naturally
                        }

                        break;
                    }

                    case "time":
                    {
                        int timehold= (int)(System.currentTimeMillis() % Integer.MAX_VALUE);

                        String keyval=stringscan.next();
                        zemap.put("int "+keyval,timehold);

                        System.out.println("t is "+zemap.get("int "+keyval));
                        break;
                    }
                    case "setpixel":
                    {


                        IImage image= panel.getImage();

                        //Scans in the x, y and color
                        String xhold= stringscan.next();
                        String yhold= stringscan.next();
                        String colorhold= stringscan.next();

                        int colorval= Integer.parseInt(colorhold);
                        int x= zemap.get("int "+xhold);
                        int y= zemap.get("int "+yhold);

                        image.setColor(x,y,colorval);
                        break;
                    }
                    case "getpixel":
                    {

                        IImage image=panel.getImage();
                        String xhold= stringscan.next();
                        String yhold= stringscan.next();
                        int x= zemap.get("int "+xhold);
                        int y= zemap.get("int "+yhold);

                        int colorrecieve=image.getColor(x,y);
                    }

                }
            }while(counter<commands.size()-1);

    //for(int i=0; i<commands.size(); i++)
    //{

    //}
    //first part of index is command, other things in string are what is being used

    //switch case that will execute based on the command

    //Arithmetic takes in variable, number, variable

    //if loopi, it means for(int j=0; j<map.get(i); i++) I believe


}



}
