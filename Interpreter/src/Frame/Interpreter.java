//Interpreter made by Jeremy Bill.
package Frame;
import java.io.*;
import java.util.*;
import java.time.*;
public class Interpreter {

    //print doesnt take in anything
    //Load takes in a string
    //Blank constructor
    public Interpreter() {
    }
    //Array List to store commands
    ArrayList<String> commands = new ArrayList<String>();
    //Hashmap, bools are stored as 0 or 1 ie false or true. ints are ints, labels have line numbers.
    HashMap<String, Integer> zemap = new HashMap<String, Integer>();
    //panel
    Panel panel;
    //image
    IImage image;
    public void load(String filename) {
        //Array Lists to store variables
        //Bools will be represented in hashmap with a 1 or 0 for true or false respectively.
        //Label counter to keep track of lines
        int labelcounter = 0;
        //try catch
        try {
            //Scanner with the filename as run arg
            Scanner scan = new Scanner(new File(filename));
            String operation = "t";
//hashmap store an array
            //while scan has stuff to scan
            while (scan.hasNext()) {
                operation = scan.next();
                //Labelcounter
                labelcounter++;

                //Variable commands
                switch (operation) {
                    case "int":
                    {
                        ////puts anything with int in the prefix in AL with int in front and default val as 0.
                        String numvar = scan.next();
                        zemap.put("int " + numvar, 0);
                        commands.add("int " + numvar);
                        break;
                    }
                    case "bool": {
                        //puts anything with bool in the prefix in AL with bool in front, auto declared as false
                        String boolvar = scan.next();
                        //Sets initial bool val to 0
                        zemap.put("bool " + boolvar, 0);
                        commands.add("bool " + boolvar);
                        break;
                    }
                    case "label": {
                        //puts anything with label in the prefix in AL with label in front, with the line number
                        String labeltype = scan.next();
                        zemap.put("label " + labeltype, labelcounter);
                        commands.add(operation + " " + labeltype);
                        break;
                    }
                    //Arithmetic Ops
                    case "+": {
                        //Addition
                        {
                            String pluscommand = "+";
                            //Takes in 3 things after + and stores them into AL
                            for (int i = 0; i < 3; i++) {
                                pluscommand += " " + scan.next();
                            }
                            commands.add(pluscommand);
                            break;
                        }
                    }

                    case "-": {
                        //subtraction
                        String subcommand = "-";
                        //Takes in 3 things after - and puts them in AL
                        for (int i = 0; i < 3; i++) {
                            subcommand += " " + scan.next();
                        }
                        commands.add(subcommand);
                        break;
                    }

                    case "*": {
                        //mult op
                        String multcommand = "*";
                        //takes in 3 things after * and puts it in AL
                        for (int i = 0; i < 3; i++) {
                            multcommand += " " + scan.next();
                        }
                        commands.add(multcommand);
                        break;
                    }
                    case "/": {
                        //div op
                        String divcommand = "/";
                        //takes in 3 things after / op and put them in AL
                        for (int i = 0; i < 3; i++) {
                            divcommand += " " + scan.next();
                        }
                        commands.add(divcommand);
                        break;
                    }
                    case "=": {
                        //= op
                        String eqcommand = "=";
                        //Takes in 2 things after = and puts them in AL
                        for (int i = 0; i < 2; i++) {
                            eqcommand += " " + scan.next();
                        }
                        commands.add(eqcommand);
                        break;
                    }
                    //Mod
                    case "%": {
                        String modcommand = "%";
                        //Takes in 3 things after Mod and stores it into AL
                        for (int i = 0; i < 3; i++) {
                            modcommand += " " + scan.next();
                        }
                        commands.add(modcommand);
                        break;
                    }
                    //Bool Commands
                    //if (operation.equals("=="))
                    case "==": {
                        String dubequalscommand = "==";
                        //== command that takes in 3 vals after == and adds it to AL.
                        for (int i = 0; i < 3; i++) {
                            dubequalscommand += " " + scan.next();
                        }
                        commands.add(dubequalscommand);
                        break;
                    }
                    //greater than
                    case ">":
                    {
                        String greatercommand = ">";
                        //takes in 3 things after > and puts them in AL
                        for (int i = 0; i < 3; i++) {
                            greatercommand += " " + scan.next();
                        }
                        commands.add(greatercommand);
                        break;
                    }
                    //>= op
                    case ">=": {
                        String greatequalcommand = ">=";
                        //for loop to take in 3 things after >= and put them in AL
                        for (int i = 0; i < 3; i++) {
                            greatequalcommand += " " + scan.next();
                        }
                        commands.add(greatequalcommand);
                        break;
                    }
                    //& op
                    case "&": {
                        String ampersandcommand = "&";
                        //Takes in 3 things after & and puts them in AL
                        for (int i = 0; i < 3; i++) {
                            ampersandcommand += " " + scan.next();
                        }
                        commands.add(ampersandcommand);
                        break;
                    }
                    //Or command
                    case "|":

                    {
                        String orcommand = "|";
                        //puts 3 things after or in AL
                        for (int i = 0; i < 3; i++) {
                            orcommand += " " + scan.next();
                        }
                        commands.add(orcommand);
                        break;
                    }
                    //Jumpif
                    case "jumpif": {
                        String jumpifcommand = "jumpif";
                        //Takes in 2 things after jumpif and puts them in AL
                        for (int i = 0; i < 2; i++) {
                            jumpifcommand += " " + scan.next();
                        }
                        commands.add(jumpifcommand);
                        break;
                    }
                    //set pixel
                    case "setpixel": {
                        String setpixelcmd = "setpixel";
                        //Takes in 3 things after setpixel and puts them in AL
                        for (int i = 0; i < 3; i++) {
                            setpixelcmd += " " + scan.next();
                        }
                        commands.add(setpixelcmd);
                        break;
                    }
                    //Getpixel
                    case "getpixel": {
                        String getpixelcmd = "getpixel";
                        //Takes in 3 things after getpixel and adds it to AL.
                        for (int i = 0; i < 3; i++) {
                            getpixelcmd += " " + scan.next();
                        }
                        commands.add(getpixelcmd);
                        break;
                    }
                    //Default if operation is neither of the above
                    default: {
                        if (scan.hasNext()) {
                            commands.add(operation + " " + scan.next());
                        }
                    }
                }
            }
            //Catches a file not found exception
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error with ze file");
        }
    }
    public void printProgram() {
        //Print out data structure
        for(int i=0; i<commands.size(); i++)
        {
            System.out.println(commands.get(i));
        }
    }
    public void run() {

        //Image creation
        IImage image = panel.getImage();
        //For loop to loop through my Arraylist for scanner and jumping
        for(int i=0; i<commands.size();i++ ) {
            //Scanner that gets set to a string
            Scanner stringscan = new Scanner(commands.get(i));
            //operator for switch
            String operator = stringscan.next();
            switch (operator) {
                case "+": {
                    //Addition OP
                    int sum;
                    //Takes in 3 vars
                    String keycheck = stringscan.next();
                    String extravar = stringscan.next();
                    String numbertobeadded = stringscan.next();
                    //Performs addition op and puts result in Map
                    sum = add(keycheck, extravar, numbertobeadded);
                    zemap.put("int " + keycheck, sum);
                    break;
                }
                case "-":{
                    //Subtraction
                    int difference;
                    //takes in 3 strings
                    String keycheck = stringscan.next();
                    String extravar = stringscan.next();
                    String numbertobesubtracted = stringscan.next();
                    //subtracts
                    difference = subtract(keycheck, extravar, numbertobesubtracted);
                    //puts result in map
                    zemap.put("int " + keycheck, difference);
                    break;
                }
                case "*": {
                    //Multiplication
                    int product;
                    //brings in 3 strings
                    String keycheck = stringscan.next();
                    String extravar = stringscan.next();
                    String numbertobemult = stringscan.next();
                    //performs the operation
                    product = multiply(keycheck, extravar, numbertobemult);
                    //puts result in map
                    zemap.put("int " + keycheck, product);
                    break;
                }
                case "/": {
                    //Division
                    int quotient;
                    //brings in 3 strings
                    String keycheck = stringscan.next();
                    String extravar = stringscan.next();
                    String numbertobediv = stringscan.next();
                    //performs division op
                   quotient = division(keycheck, extravar, numbertobediv);
                   //puts result in map
                    zemap.put("int " + keycheck, quotient);
                    break;
                }
                case "=": {
                    //Does the = op
                    String keyval = stringscan.next();
                    String value = stringscan.next();
                    equalsop(keyval,value);
                    break;
                }
                case "%": {
                    //Creates mod variable
                    int modulus;
                    //takes in 3 strings
                    String keycheck = stringscan.next();
                    String extravar = stringscan.next();
                    String numbertobemod = stringscan.next();
                    //performs mod op and puts result in zemap
                    modulus = modulusop(keycheck, extravar, numbertobemod);
                    zemap.put("int " + keycheck, modulus);
                    break;
                }
                //comparison functions
                case "==": {
                    //Takes in values to compare with == and performs the op
                    String keycheck = stringscan.next();
                    String keycheck2 = stringscan.next();
                    String numbertobecompared = stringscan.next();
                    doubleeq(keycheck,keycheck2,numbertobecompared);
                    break;
                }
                case ">": {
                    //Takes in values to compare with > and performs the function
                    String keycheck = stringscan.next();
                    String keycheck2 = stringscan.next();
                    String numbertobecompared = stringscan.next();
                    greaterthan(keycheck,keycheck2,numbertobecompared);
                    break;
                }
                case ">=": {
                    //Performs the >= op with the 3 strings brought in
                    String keycheck = stringscan.next();
                    String keycheck2 = stringscan.next();
                    String numbertobecompared = stringscan.next();
                    greaterthanoreq(keycheck,keycheck2,numbertobecompared);
                    break;
                }
                case "&": {

                    //Calls the & operation command and takes in 3 strings
                    String keycheck = stringscan.next();
                    String extravar = stringscan.next();
                    String numbertobemodulus = stringscan.next();
                    andcommand(keycheck,extravar,numbertobemodulus);
                    break;
                }
                case "|": {
                    //Calls the | operation command after taking in 3 strings to use.
                    String keycheck = stringscan.next();
                    String extravar = stringscan.next();
                    String numbertobemodulus = stringscan.next();

                    orcommand(keycheck,extravar,numbertobemodulus);
                    break;
                }
                //other functions
                case "jump": {
                    //calls jump function which will set i to desired line
                    String loopjump = stringscan.next();
                    i=jump(loopjump);
                    break;
                }
                case "jumpif": {
                    //Sets i to the desired line based upon the jumpif, if ocndition isnt true it will use the current i val.
                    String boolvarcondition = stringscan.next();
                    String labelname = stringscan.next();
                    i=jumpif(boolvarcondition,labelname,i);
                    break;
                }
                case "time": {
                    //Gets time and puts it into map
                    int timehold = gettime();
                    String keyval = stringscan.next();
                    zemap.put("int " + keyval, timehold);
                    break;
                }
                case "setpixel": {
                    //NOTE SET AND GET PIXEL DID NOT LIKE BEING PUT INTO A FUNCTION

                    //Takes in 3 strings
                        String xpos = stringscan.next();
                        String ypos = stringscan.next();
                        String colorhold = stringscan.next();

                        //Vars for later
                    int x=0;
                    int y=0;
                    int colorval=0;
                    //If everything is in map
                    if(zemap.containsKey("int "+xpos) && zemap.containsKey("int "+ypos) && zemap.containsKey("int "+colorhold))
                    {
                        //X y color are set accordingly
                        x=zemap.get("int "+xpos);
                        y=zemap.get("int "+ypos);
                        colorval= zemap.get("int "+colorhold);
                    }
                    //Else if all 3 are in map but the prefix color is infront of colorhold and not int
                    else if(zemap.containsKey("int "+xpos) && zemap.containsKey("int "+ypos) && zemap.containsKey("color "+colorhold) )
                    {
                        //sets x y and color
                        x = zemap.get("int "+xpos);
                        y=zemap.get("int "+ypos);
                        colorval= zemap.get("color "+colorhold);
                    }
                    //else if x and y are in map and color isnt
                    else if(zemap.containsKey("int "+xpos) && zemap.containsKey("int "+ypos))
                    {
                        //sets x and y and uses value of to set val of color
                        x = zemap.get("int "+xpos);
                        y=zemap.get("int "+ypos);
                        colorval=Integer.valueOf(colorhold);
                    }
                    //Same as above but with x and color instead of x and y
                    else if(zemap.containsKey("int "+xpos) && zemap.containsKey("int "+colorhold))
                    {
                        //X and color are got from map, y is parsed
                        x = zemap.get("int "+xpos);
                        y=Integer.valueOf(ypos);
                        colorval= zemap.get("int "+colorhold);
                    }
                    //same as above but with color instead of int for colorhold
                    else if(zemap.containsKey("int "+xpos)&& zemap.containsKey("color "+colorhold))
                    {
                        //same as above but with color instead of int for colorhold
                        x = zemap.get("int "+xpos);
                        y=Integer.valueOf(ypos);
                        colorval= zemap.get("color "+colorhold);
                    }
                    //else if x is the only var in the map
                    else if(zemap.containsKey("int "+xpos))
                    {
                        //pulls x and parses y and color
                        x = zemap.get("int "+xpos);
                        y=Integer.valueOf(ypos);
                        colorval= Integer.valueOf(colorhold);
                    }
                    //Calls set color with the vals
                    image.setColor(x,y,colorval);
                    break;
                }
                case "getpixel": {
                    //3 strings
                    String colorstore = stringscan.next();
                    String xhold = stringscan.next();
                    String yhold = stringscan.next();
                    //vars
                    int x=0;
                    int y=0;
                    //if all are in map except for color
                    if(zemap.containsKey("int "+xhold) && zemap.containsKey("int "+yhold))
                    {
                        //pulls x and y
                        x=zemap.get("int "+xhold);
                        y=zemap.get("int "+yhold);

                    }
                    //if x is in map and y isnt
                    else if(zemap.containsKey("int "+xhold))
                    {
                        //pulls x and parses y
                        x = zemap.get("int "+xhold);
                        y= Integer.valueOf(yhold);
                    }
                    //Int color recieve is set to get color
                    int colorreceive= image.getColor(x,y);
                    //if color is in map with int in front put result in map
                    if(zemap.containsKey("int "+colorstore))
                    {
                        zemap.put("int "+colorstore,colorreceive);
                    }
                    //if color is in map with color in front put result in map
                    else if(zemap.containsKey("color "+colorstore))
                    {
                        zemap.put("int "+colorstore,colorreceive);
                    }
                    break;
                }
            }
        }
            //Mr Resetti
            for (int j = 0; j < zemap.size()-1; j++) {
                //Clear scanner
                Scanner clear = new Scanner(commands.get(j));
                //Extravar holder
                String nom = clear.next();
                //gets the thing needing to be reset
                String nom2electricboogaloo=clear.next();
                //Clears ints
                if (zemap.containsKey("int " + nom2electricboogaloo)) {
                    zemap.put("int " + nom2electricboogaloo, 0);

                }
                //Clears bools
                if (zemap.containsKey("bool " + nom2electricboogaloo)) {
                    zemap.put("bool " + nom2electricboogaloo, 0);

                }
                //Clearls labels
                if(zemap.containsKey("label  "+nom2electricboogaloo))
                {
                    zemap.put("label "+nom2electricboogaloo,0);
                }
            }
    }
    //Adds nums
    public int add(String storage, String num1, String num2)
    {
        //vars
        int sum=0;
        int num=0;
        int numdos=0;
        //if map contains all 3
        if (zemap.containsKey("int " + storage) && zemap.containsKey("int " + num1) && zemap.containsKey("int " + num2)) {
                        //Pull from map and store result in sum
                        num = zemap.get("int " + num1);
                        numdos = zemap.get("int " + num2);
                        sum = num + numdos;
                        return sum;
                    }
                    //Else if first and second vars in map
                    else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + num1))) {
                         //Pull from map and store result in sum and parse accordingly
                        num = zemap.get("int " + num1);
                         numdos =  Integer.valueOf(num2);
                         sum = num + numdos;
                         return sum;
                    } //Else if first and third vars in map
                    else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + num2))) {
                        //Pull from map and store result in sum and parse the missing num
                        num = Integer.valueOf(num1);
                        numdos = zemap.get("int " + num2);
                        sum=num+numdos;
                        return sum;
                    } //Else if map contains first var and only first var
                    else if (zemap.containsKey("int " + storage)) {
                        //parse other nums, store result
                        num= Integer.valueOf(num1);
                        numdos = Integer.valueOf(num2);
                        sum=num+numdos;
                        return sum;
                    }
                    //return sum
                return sum;
    }
    //Subtract
    public int subtract(String storage, String num1, String num2)
    {
        int difference=0;
        int num=0;
        int numdos=0;
        //If all vars are ints and in map
        if (zemap.containsKey("int " + storage) && zemap.containsKey("int " + num1) && zemap.containsKey("int " + num2)) {
            //vars
            num = zemap.get("int " + num1);
            numdos = zemap.get("int " + num2);
            //differnece operation
            difference = num - numdos;
            return difference;
        } //If int storage and int num 1 are in the map
        else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + num1))) {
            //vars
            num = zemap.get("int " + num1);
            numdos =  Integer.valueOf(num2);
            //difference
            difference = num - numdos;
            return difference;
        } //if map contains int storage and int num2
        else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + num2))) {
           //vars
            num = Integer.valueOf(num1);
            numdos = zemap.get("int " + num2);
            //performs the operation
            difference=num-numdos;
            return difference;
        } //if int storage is in the map
        else if (zemap.containsKey("int " + storage)) {
            //values of num
            num= Integer.valueOf(num1);
            numdos = Integer.valueOf(num2);
            //Difference
            difference=num-numdos;
            return difference;
        }
        //returns difference
        return difference;
    }
    //Multiply
    public int multiply(String storage, String num1, String num2)
    {
        int product=0;
        int num=0;
        int numdos=0;
        //If all 3 vars are ints and in map
        if (zemap.containsKey("int " + storage) && zemap.containsKey("int " + num1) && zemap.containsKey("int " + num2)) {
            //get values to mult and do mult
            num = zemap.get("int " + num1);
            numdos = zemap.get("int " + num2);
            product = num * numdos;
            return product;
        } //map has storage and first var in it
        else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + num1))) {
           //gets the product
            num = zemap.get("int " + num1);
            numdos =  Integer.valueOf(num2);
            product = num * numdos;
            return product;
        } //Else if int storage and int num 2 are in map
        else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + num2))) {
            //performs the operation
            num = Integer.valueOf(num1);
            numdos = zemap.get("int " + num2);
            //gets the product
            product=num*numdos;
            return product;
        } //Else if int storage is in map and the other vars arent
        else if (zemap.containsKey("int " + storage)) {
            //gets vars
            num= Integer.valueOf(num1);
            numdos = Integer.valueOf(num2);
            //product
            product=num*numdos;
            return product;
        }
        return product;
    }
    //Division
    public int division(String storage, String num1, String num2)
    {
        int quotient=0;
        int num=0;
        int numdos=0;
        //If 3 ints are in map
        if (zemap.containsKey("int " + storage) && zemap.containsKey("int " + num1) && zemap.containsKey("int " + num2)) {
            //pulls vars from map and does division
            num = zemap.get("int " + num1);
            numdos = zemap.get("int " + num2);
            quotient = num / numdos;
            return quotient;
        } //if first and second var in map
        else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + num1))) {
            //variables
            num = zemap.get("int " + num1);
            numdos =  Integer.valueOf(num2);
            //division
            quotient = num / numdos;
            return quotient;
        } //if first var and third var are in storage
        else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + num2))) {
            //parsees ints and gets vals from map, performs division
            num = Integer.valueOf(num1);
            numdos = zemap.get("int " + num2);
            quotient=num/numdos;
            return quotient;
        } else if (zemap.containsKey("int " + storage)) {
            //Parses ints and does division
            num= Integer.valueOf(num1);
            numdos = Integer.valueOf(num2);
            quotient=num/numdos;
            return quotient;
        }
        //return quotient
        return quotient;
    }
    //Modulus op
    public int modulusop(String storage, String num1, String num2)
    {
        int modulus=0;
        int num=0;
        int numdos=0;
        //If map contains 3 ints
        if (zemap.containsKey("int " + storage) && zemap.containsKey("int " + num1) && zemap.containsKey("int " + num2)) {
            //Variables
            num = zemap.get("int " + num1);
            numdos = zemap.get("int " + num2);
            //Modulus
            modulus = num % numdos;
            return modulus;
        } //Else is int int
        else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + num1))) {
            //calculates Modulus
            num = zemap.get("int " + num1);
            numdos =  Integer.valueOf(num2);
            modulus = num % numdos;
            return modulus;
        } //else if int int
        else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + num2))) {
            //get vars/parse vars and return mod
            num = Integer.valueOf(num1);
            numdos = zemap.get("int " + num2);
            modulus=num%numdos;
            return modulus;

        } //if storage is only one in map
        else if (zemap.containsKey("int " + storage)) {
            //parse and perform mod
            num= Integer.valueOf(num1);
            numdos = Integer.valueOf(num2);
            modulus=num%numdos;
            return modulus;
        }
        //default return
        return modulus;
    }
    //equals operation
    public void equalsop(String storage, String valuespot)
    {
        int boolval= 0;
        //if int int exist
        if (zemap.containsKey("int " + storage) && zemap.containsKey("int " + valuespot)) {
            //stores
            zemap.put("int " + storage,zemap.get("int " + valuespot));
        } //if bool storage and bool value exist
        else if (zemap.containsKey("bool " + storage) && zemap.containsKey("bool " + valuespot)) {
            //stores
            zemap.put("bool " + storage, zemap.get("bool " + valuespot));
        } //if int storage is only one that exists
        else if (zemap.containsKey("int " + storage)) {
            //puts val in map
            zemap.put("int " + storage, Integer.valueOf(valuespot));
        }
        //if map has bool storage
        else if(zemap.containsKey("bool "+storage))
        {
            //if true
            if(valuespot.equals("true"))
            {
                //bool is 1 and stores true
                boolval=1;
                zemap.put("bool "+storage,boolval);
            }
            else {
                //else bool is 0 and stores false
                boolval = 0;
                zemap.put("bool " + storage, boolval);
            }
        }
    }
    //Double eq operation
    public void doubleeq(String storage, String var1, String var2)
    {

           int num1=0;
           int num2=0;
        //if map has int int int
           if (zemap.containsKey("int " + storage) && zemap.containsKey("int " + var1) && zemap.containsKey("int " + var2)) {
           //vars
            num1=zemap.get("int "+var1);
            num2=zemap.get("int "+var2);
            //compares
            if(num1==num2)
            {
                //stores
                zemap.put("int "+storage,num1);
            }

        } //if int storage and int var 1 exist
        else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + var1))) {
            //gets and parses vars
            num1 = zemap.get("int " + var1);
            num2 =  Integer.valueOf(var2);
            //compares
            if(num1==num2)
            {
                //stores
                zemap.put("int "+storage,num1);
            }
        } //else if map has int storage and int var 2
        else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + var2))) {
            //vars
            num1 = Integer.valueOf(var1);
            num2 = zemap.get("int " + var2);
            //compares
            if(num1==num2)
            {
                //stores var
                zemap.put("int "+storage,num1);
            }

        } //if storage is int
        else if (zemap.containsKey("int " + storage)) {
            //parses vars
            num1= Integer.valueOf(var1);
            num2 = Integer.valueOf(var2);
            //compare
            if(num1==num2)
            {
                //stores num1
                zemap.put("int "+storage,num1);
            }

        }
        //if map has bool bool bool
        if (zemap.containsKey("bool " + storage) && zemap.containsKey("bool " + var1) && zemap.containsKey("bool " + var2)) {
            //vars
            num1=zemap.get("bool "+var1);
            num2=zemap.get("bool "+var2);
            if(num1==num2)
            {
                //true
                zemap.put("bool "+storage,1);
            }
            else
            {
                //false
                zemap.put("bool "+storage,0);
            }

        } //if map has bool int int
        else if(zemap.containsKey("bool "+storage) && zemap.containsKey("int "+var1)&& zemap.containsKey("int "+var2))
        {
            //vars
            num1=zemap.get("int "+var1);
            num2=zemap.get("int "+var2);
            //compares
            if(num1==num2)
            {
                //true
                zemap.put("bool "+storage,1);
            }
            else
            {
                //false
                zemap.put("bool "+storage,0);
            }
        }
        //if map has bool storage and int var 1
        else if(zemap.containsKey("bool "+storage) && zemap.containsKey("int "+var1))
        {
            //vars
            num1=zemap.get("int "+var1);
            num2=Integer.parseInt(var2);
            //compares
            if(num1==num2)
            {
                //true
                zemap.put("bool "+storage,1);
            }
            else
            {
                //false
                zemap.put("bool "+storage,0);
            }
        }
        //if storage is bool and var 2 is int
        else if(zemap.containsKey("bool "+storage) && zemap.containsKey("int "+var2))
        {
            //parses and gets var
            num1=Integer.parseInt(var1);
            num2=zemap.get("int "+var2);
            //comparison
            if(num1==num2)
            {
                //true
                zemap.put("bool "+storage,1);
            }
            else
            {
                //false
                zemap.put("bool "+storage,0);
            }
        }//else if first and second exist and are bools
        else if ((zemap.containsKey("bool " + storage) && zemap.containsKey("bool " + var1))) {
           //vars
            num1 = zemap.get("bool " + var1);
            num2 =  Integer.valueOf(var2);
            //comparison
            if(num1==num2)
            {
                //true
                zemap.put("bool "+storage,1);
            }
            else
            {
                //false
                zemap.put("bool "+storage,0);
            }
        } //if storage and var 2 are bools
        else if ((zemap.containsKey("bool " + storage) && zemap.containsKey("bool " + var2))) {
            //gets and parses vars
            num1 = Integer.valueOf(var1);
            num2 = zemap.get("bool " + var2);
            //compares
            if(num1==num2)
            {
                //true
                zemap.put("bool "+storage,1);
            }
            else
            {
                //false
                zemap.put("bool "+storage,0);
            }

        }//if only storage is in map and bool
        else if (zemap.containsKey("bool "+storage)) {
            //gets vars
            num1= Integer.valueOf(var1);
            num2 = Integer.valueOf(var2);
            //compares
            if(num1==num2)
            {
                //true
                zemap.put("bool "+storage,1);
            }
            else
            {
                //false
                zemap.put("bool "+storage,0);
            }

        }

    }
    //greater than operation
    public void greaterthan(String storage, String var1, String var2)
    {

        int num1=0;
        int num2=0;
        //if int int int
        if (zemap.containsKey("int " + storage) && zemap.containsKey("int " + var1) && zemap.containsKey("int " + var2)) {
            //gets vars
            num1=zemap.get("int "+var1);
            num2=zemap.get("int "+var2);
            //comparison
            if(num1>num2)
            {
                //stores result
                zemap.put("int "+storage,num1);
            }

        } //if storage and var 1 are ints
        else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + var1))) {
            //gets and parses vars
            num1 = zemap.get("int " + var1);
            num2 =  Integer.valueOf(var2);
            //comparison
            if(num1>num2)
            {
                //puts var in map
                zemap.put("int "+storage,num1);
            }
        } //if storage and var 2 are ints
        else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + var2))) {
           //parses and gets val
            num1 = Integer.valueOf(var1);
            num2 = zemap.get("int " + var2);
            //greater than
            if(num1>num2)
            {
                //stores var
                zemap.put("int "+storage,num1);
            }

        } //if map contains storage
        else if (zemap.containsKey("int " + storage)) {

            //parses vals
            num1= Integer.valueOf(var1);
            num2 = Integer.valueOf(var2);
            //comparison
            if(num1>num2)
            {
                //stores num1
                zemap.put("int "+storage,num1);
            }

        }

        //Bool
        //if bool bool bool
        if (zemap.containsKey("bool " + storage) && zemap.containsKey("bool " + var1) && zemap.containsKey("bool " + var2)) {
            //pull vars from map
            num1=zemap.get("bool "+var1);
            num2=zemap.get("bool "+var2);
            //comparison
            if(num1>num2)
            {
                //true
                zemap.put("bool "+storage,1);
            }
            else
            {
                //false
                zemap.put("bool "+storage,0);
            }

        } //bool int int
        else if(zemap.containsKey("bool "+storage) && zemap.containsKey("int "+var1)&& zemap.containsKey("int "+var2))
        {
            //vars
            num1=zemap.get("int "+var1);
            num2=zemap.get("int "+var2);
            //comparison
            if(num1>num2)
            {
                //true
                zemap.put("bool "+storage,1);
            }
            else
            {
                //false
                zemap.put("bool "+storage,0);
            }
        }
        //else if bool int
        else if(zemap.containsKey("bool "+storage) && zemap.containsKey("int "+var1))
        {
            //gets and parses cars
            num1=zemap.get("int "+var1);
            num2=Integer.parseInt(var2);
            //comparison
            if(num1>num2)
            {
                //true
                zemap.put("bool "+storage,1);
            }
            else
            {
                //false
                zemap.put("bool "+storage,0);
            }
        }
        //if bool and int
        else if(zemap.containsKey("bool "+storage) && zemap.containsKey("int "+var2))
        {
            //parses and gets vars
            num1=Integer.parseInt(var1);
            num2=zemap.get("int "+var2);
            //comparison
            if(num1>num2)
            {
                //true
                zemap.put("bool "+storage,1);
            }
            else
            {
                //false
                zemap.put("bool "+storage,0);
            }
        }
        //if first and second var are bools
        else if ((zemap.containsKey("bool " + storage) && zemap.containsKey("bool " + var1))) {
            //vars
            num1 = zemap.get("bool " + var1);
            num2 =  Integer.valueOf(var2);
            //comparison
            if(num1>num2)
            {
                //true
                zemap.put("bool "+storage,1);
            }
            else
            {
                //false
                zemap.put("bool "+storage,0);
            }
        } //if bool and third val is bool
        else if ((zemap.containsKey("bool " + storage) && zemap.containsKey("bool " + var2))) {
            //parses and gets vals accordingly
            num1 = Integer.valueOf(var1);
            num2 = zemap.get("bool " + var2);
            //comparison
            if(num1>num2)
            {
                //true
                zemap.put("bool "+storage,1);
            }
            else
            {
                //false
                zemap.put("bool "+storage,0);
            }

        }//if only bool
        else if (zemap.containsKey("bool "+storage)) {
            //variables
            num1= Integer.valueOf(var1);
            num2 = Integer.valueOf(var2);
            //comparison
            if(num1>num2)
            {
                //true
                zemap.put("bool "+storage,1);
            }
            else
            {
                //false
                zemap.put("bool "+storage,0);
            }

        }

    }
    //greaterthanoreq operation
    public void greaterthanoreq(String storage, String var1, String var2)
    {
        //intialized vars
        int num1=0;
        int num2=0;
        //if int int int
        if (zemap.containsKey("int " + storage) && zemap.containsKey("int " + var1) && zemap.containsKey("int " + var2)) {
            //vars
            num1=zemap.get("int "+var1);
            num2=zemap.get("int "+var2);
            //compares
            if(num1>=num2)
            {
                //stores
                zemap.put("int "+storage,num1);
            }

        } //else if int int
        else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + var1))) {
            //gets and parses vars
            num1 = zemap.get("int " + var1);
            num2 =  Integer.valueOf(var2);
            //compares
            if(num1>=num2)
            {
                //stores val
                zemap.put("int "+storage,num1);
            }
        }// if int int in map
        else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + var2))) {
            //parses and gets vals
            num1 = Integer.valueOf(var1);
            num2 = zemap.get("int " + var2);
            //compares
            if(num1>=num2)
            {
                //puts val in map
                zemap.put("int "+storage,num1);
            }

        } //if int in map
        else if (zemap.containsKey("int " + storage)) {

            //parses vals
            num1= Integer.valueOf(var1);
            num2 = Integer.valueOf(var2);
            //compares
            if(num1>=num2)
            {
                //stores val if true
                zemap.put("int "+storage,num1);
            }

        }
        //Bools
        //if bool bool bool
        if (zemap.containsKey("bool " + storage) && zemap.containsKey("bool " + var1) && zemap.containsKey("bool " + var2)) {
            //gets vars
            num1=zemap.get("bool "+var1);
            num2=zemap.get("bool "+var2);
            //compares
            if(num1>=num2)
            {
                //true
                zemap.put("bool "+storage,1);
            }
            else
            {
                //false
                zemap.put("bool "+storage,0);
            }

        } //if Bool int int
        else if(zemap.containsKey("bool "+storage) && zemap.containsKey("int "+var1)&& zemap.containsKey("int "+var2))
        {
            //gets vals
            num1=zemap.get("int "+var1);
            num2=zemap.get("int "+var2);
            //compares
            if(num1>=num2)
            {
                //true
                zemap.put("bool "+storage,1);
            }
            else
            {
                //false
                zemap.put("bool "+storage,0);
            }
        }
        //if bool int
        else if(zemap.containsKey("bool "+storage) && zemap.containsKey("int "+var1))
        {
            //gets and parses val
            num1=zemap.get("int "+var1);
            num2=Integer.parseInt(var2);
            //compares
            if(num1>=num2)
            {
                //true
                zemap.put("bool "+storage,1);
            }
            else
            {
                //false
                zemap.put("bool "+storage,0);
            }
        }
        //if Bool int
        else if(zemap.containsKey("bool "+storage) && zemap.containsKey("int "+var2))
        {
            //parses and gets val
            num1=Integer.parseInt(var1);
            num2=zemap.get("int "+var2);
            //compares
            if(num1>=num2)
            {
                //true
                zemap.put("bool "+storage,1);
            }
            else
            {
                //false
                zemap.put("bool "+storage,0);
            }
        }
        //if storage and var 1 are bools
        else if ((zemap.containsKey("bool " + storage) && zemap.containsKey("bool " + var1))) {
            //get and parse vars
            num1 = zemap.get("bool " + var1);
            num2 =  Integer.valueOf(var2);
            //compares
            if(num1>=num2)
            {
                //true
                zemap.put("bool "+storage,1);
            }
            else
            {
                //false
                zemap.put("bool "+storage,0);
            }
        } //if storage and var 2 are bools
        else if ((zemap.containsKey("bool " + storage) && zemap.containsKey("bool " + var2))) {
            //parse and get vals
            num1 = Integer.valueOf(var1);
            num2 = zemap.get("bool " + var2);
            //compare
            if(num1>=num2)
            {
                //true
                zemap.put("bool "+storage,1);
            }
            else
            {
                //false
                zemap.put("bool "+storage,0);
            }

        }//if its a single bool for storage
        else if (zemap.containsKey("bool "+storage)) {
            //parse nums
            num1= Integer.valueOf(var1);
            num2 = Integer.valueOf(var2);
            //compare
            if(num1>=num2)
            {
                //true
                zemap.put("bool "+storage,1);
            }
            else
            {
                //false
                zemap.put("bool "+storage,0);
            }

        }


    }
    //andcommand &
    public void andcommand(String storage, String var1, String var2)
    {

        //Int checks
        //If all 3 are in map and all ints
        if (zemap.containsKey("int " + storage) && zemap.containsKey("int " + var1) && zemap.containsKey("int " + var2)) {
            //put the result of & with the second and third vars in map
            zemap.put("int " + storage, zemap.get("int " + var1) & zemap.get("int " + var2));
        } //If all 3 are in map but last one is prefixed with color
        else if (zemap.containsKey("int " + storage) && zemap.containsKey("int " + var1) && zemap.containsKey("color " + var2))
        {   //put the result of & with the second and third vars in map
            zemap.put("int " + storage, zemap.get("int " + var1) & zemap.get("color " + var2));
        } //else if map has first and second var in map
        else if (zemap.containsKey("int " + storage) && zemap.containsKey("int " + var1)) {
            //put the result of & with the second and third parsed var in map
            zemap.put("int " + storage, zemap.get("int " + var1) & Integer.valueOf(var2));
        } //else if map contains first and third var
        else if (zemap.containsKey("int " + storage) && zemap.containsKey("int " + var2)) {
            //put the result of & with the second parsed var  and third vars in map
            zemap.put("int " + storage, Integer.valueOf(var1) & zemap.get("int " + var2));
        } //if only storage is in map
        else if (zemap.containsKey("int " + storage)) {
            zemap.put("int " + storage, Integer.valueOf(var1) & Integer.valueOf(var2));
        }

        //bool checks
        if (zemap.containsKey("bool " + storage) && zemap.containsKey("bool " + var1) && zemap.containsKey("bool " + var2)) {
            zemap.put("bool " + storage, zemap.get("bool " + var1) & zemap.get("bool " + var2));
        }
        else if (zemap.containsKey("bool " + storage) && zemap.containsKey("bool " + var1)) {
            zemap.put("bool " + storage, zemap.get("bool " + var1) & Integer.parseInt(var2));
        } else if (zemap.containsKey("bool " + storage) && zemap.containsKey("bool " + var2)) {
            zemap.put("bool " + storage, Integer.parseInt(var1) & zemap.get("bool " + var2));
        } else if (zemap.containsKey("bool " + storage)) {
            zemap.put("bool " + storage, Integer.valueOf(var1) & Integer.valueOf(var2));
        }
    }
    //or command |
    public void orcommand(String storage, String var1, String var2)
    {
        //int checks
        //if all 3 are in map
        if (zemap.containsKey("int " + storage) && zemap.containsKey("int " + var1) && zemap.containsKey("int " + var2)) {
            //put the result of | with the second and third vars in map
            zemap.put("int " + storage, zemap.get("int " + var1) | zemap.get("int " + var2));
        } //if all 3 are in map but last is color
        else if (zemap.containsKey("int " + storage) && zemap.containsKey("int " + var1) && zemap.containsKey("color " + var2)) {
            //put the result of | with the second and third vars in map
            zemap.put("int " + storage, zemap.get("int " + var1) | zemap.get("color " + var2));
        } //else if first and second in map
        else if (zemap.containsKey("int " + storage) && zemap.containsKey("int " + var1)) {
            //put the result of | with the second and third parsed var in map
            zemap.put("int " + storage, zemap.get("int " + var1) | Integer.parseInt(var2));
        } //else if first and third in map
        else if (zemap.containsKey("int " + storage) && zemap.containsKey("int " + var2)) {
            //put the result of | with the second parsed var and third var in map
            zemap.put("int " + storage, Integer.parseInt(var1) | zemap.get("int " + var2));
        } //else if its only the first var in map
        else if (zemap.containsKey("int " + storage)) {
            //put the result of | with the parsed vars in map
            zemap.put("int " + storage, Integer.valueOf(var1) | Integer.valueOf(var2));
        }

        //Bool checks
        //if all 3 are in map
        if (zemap.containsKey("bool " + storage) && zemap.containsKey("bool " + var1) && zemap.containsKey("bool " + var2)) {
            //put the result of | with the second and third vars in map
            zemap.put("bool " + storage, zemap.get("bool " + var1) | zemap.get("bool " + var2));
        }
        //If first and second in map
        else if (zemap.containsKey("bool " + storage) && zemap.containsKey("bool " + var1)) {
            //put the result of | with the second and parsed var in map
            zemap.put("bool " + storage, zemap.get("bool " + var1) | Integer.parseInt(var2));
        } //if first and third in map
        else if (zemap.containsKey("bool " + storage) && zemap.containsKey("bool " + var2)) {
            //put the result of | with the parsed var and third var in map
            zemap.put("bool " + storage, Integer.parseInt(var1) | zemap.get("bool " + var2));
        } //if only first is in map
        else if (zemap.containsKey("bool " + storage)) {
            //put the result of | with the parsed vars in map
            zemap.put("bool " + storage, Integer.valueOf(var1) | Integer.valueOf(var2));
        }
    }
    //jump
    public int jump(String jumper)
    {
        //will jump to the desired line num
        int hold=zemap.get("label "+jumper)-1;
        return hold;
    }
    //Jump if
    public int jumpif(String var1, String var2,int curval)
    {
        //If true
        if (zemap.get("bool " + var1) == 1) {
            //set returnval to the line of the desired jump point
            int returnval = zemap.get("label " +var2)-1;
            return returnval;
        }
        //else return curval
        return curval;
    }
    //gets time
    public int gettime()
    {
        //Returns time
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }

}
