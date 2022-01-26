package Frame;
import java.io.*;
import java.util.*;
import java.time.*;
public class Interpreter {

    //print doesnt take in anything
//Load takes in a string
//Blank constructor
    //if reg's aren't initialized its just 0 or false depending on int or bool
    public Interpreter() {
    }

    ;
    ArrayList<String> vars = new ArrayList<String>();
    //Array List to store commands
    ArrayList<String> commands = new ArrayList<String>();

    HashMap<String, Integer> zemap = new HashMap<String, Integer>();

    Panel panel;
    IImage image;
    //panel to use image.getImage()

    //returns image which is input into image

    //that image is used for get and set color

    public void load(String filename) {
        //Array Lists to store variables

        //Bools will be represented in hashmap with a 1 or 0 for true or false respectively.
        int labelcounter = 0;
        try {
            Scanner scan = new Scanner(new File(filename));
            String operation = "t";
            System.out.println("The current time is " + System.currentTimeMillis());
//hashmap store an array
            while (scan.hasNext()) {
                operation = scan.next();
                labelcounter++;
                System.out.println(operation);
                //Variable commands
                switch (operation) {
                    case "int":
                        // if (operation.equals("int"))
                    {
                        String numvar = scan.next();
                        vars.add("int " + numvar);
                        zemap.put("int " + numvar, 0);
                        commands.add("int " + numvar);
                        break;
                    }
                    case "bool": {
                        //if (operation.equals("bool")) {
                        String boolvar = scan.next();
                        //Sets initial bool val to 0
                        zemap.put("bool " + boolvar, 0);
                        commands.add("bool " + boolvar);
                        break;
                    }
                    case "label": {
                        String labeltype = scan.next();

                        zemap.put("label " + labeltype, labelcounter);
                        commands.add(operation + " " + labeltype);
                        break;
                    }
                    //Arithmetic Ops
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
                    case "*": {
                        String multcommand = "*";
                        for (int i = 0; i < 3; i++) {
                            multcommand += " " + scan.next();
                        }
                        commands.add(multcommand);
                        break;
                    }
                    //if (operation.equals("/"))
                    case "/": {
                        String divcommand = "/";
                        for (int i = 0; i < 3; i++) {
                            divcommand += " " + scan.next();
                        }
                        commands.add(divcommand);
                        break;
                    }
                    case "=": {
                        String eqcommand = "=";
                        for (int i = 0; i < 2; i++) {
                            eqcommand += " " + scan.next();
                        }
                        commands.add(eqcommand);
                        break;
                    }

                    //if (operation.equals("%"))
                    case "%": {
                        String modcommand = "%";
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
                    case ">=": {
                        String greatequalcommand = ">=";
                        for (int i = 0; i < 3; i++) {
                            greatequalcommand += " " + scan.next();
                        }
                        commands.add(greatequalcommand);
                        break;
                    }
                    //if (operation.equals("&"))
                    case "&": {
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
                    case "jumpif": {
                        String jumpifcommand = "jumpif";
                        for (int i = 0; i < 2; i++) {
                            jumpifcommand += " " + scan.next();
                        }
                        commands.add(jumpifcommand);
                        System.out.println(jumpifcommand);
                        break;
                    }
                    case "setpixel": {
                        String setpixelcmd = "setpixel";
                        for (int i = 0; i < 3; i++) {
                            setpixelcmd += " " + scan.next();
                        }
                        commands.add(setpixelcmd);
                        break;
                    }
                    case "getpixel": {
                        String getpixelcmd = "getpixel";
                        for (int i = 0; i < 3; i++) {
                            getpixelcmd += " " + scan.next();
                        }
                        commands.add(getpixelcmd);
                        break;
                    }
                    default: {
                        if (scan.hasNext()) {
                            commands.add(operation + " " + scan.next());
                        }
                    }

                }
                System.out.println(zemap);

            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error with ze file");
        }
    }


    public void printProgram() {
        //Print out data structure
        System.out.println(commands);
    }

    public void run() {
        //loop through arraylist to access commands
        //int counter = -1;
        //Keeps track of lines in bytecode
        //int secondcounter = 0;
        IImage image = panel.getImage();

        //do {
        for(int i=0; i<commands.size()-1;i++ ) {
            //System.out.println("counter before it is incremented is "+counter);
            //counter++;
            //secondcounter++;
            Scanner stringscan = new Scanner(commands.get(i));
            String operator = stringscan.next();
            switch (operator) {
                case "+": {
                    int sum;
                    //Add 2 numbers and store them back to i
                    String keycheck = stringscan.next();
                    String extravar = stringscan.next();
                    String numbertobeadded = stringscan.next();

                    /*if(zemap.containsKey("int "+extravar))
                    {
                        x=zemap.get("int "+extravar);
                    }
                    else
                    {
                        x=Integer.parseInt(extravar);
                    }
                    if(zemap.containsKey("int "+numbertobeadded))
                    {
                        y=zemap.get("int "+numbertobeadded);
                    }
                    else
                    {
                        y= Integer.parseInt(numbertobeadded);
                    }

                    if(zemap.containsKey("int "+keycheck))
                    {
                        sum=x+y;
                        zemap.put("int "+keycheck,sum);
                    }*/

                    sum = add(keycheck, extravar, numbertobeadded);
                    zemap.put("int " + keycheck, sum);

                    //Look at what you need to do with the extra var

                    break;
                }
                case "-": {
                    int x;
                    int y;
                    int difference;
                    //Add 2 numbers and store them back to i
                    String keycheck = stringscan.next();
                    String extravar = stringscan.next();
                    String numbertobesubtracted = stringscan.next();

                    difference = subtract(keycheck, extravar, numbertobesubtracted);
                    zemap.put("int " + keycheck, difference);
                    /*if(zemap.containsKey("int "+extravar))
                    {
                        x=zemap.get("int "+extravar);
                    }
                    else
                    {
                        x=Integer.parseInt(extravar);
                    }
                    if(zemap.containsKey("int "+numbertobesubtracted))
                    {
                        y=zemap.get("int "+numbertobesubtracted);
                        difference=x-y;
                    }
                    else
                    {
                        y= Integer.parseInt(numbertobesubtracted);
                        difference=x-y;
                    }

                        zemap.put("int "+keycheck,difference);*/

                   /* System.out.println(keycheck);
                    System.out.println(extravar);
                    System.out.println(numbertobesubtracted);

                    //Look at what you need to do with the extra var

                    if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + extravar) && zemap.containsKey("int " + numbertobesubtracted)) {
                        x = zemap.get("int " + keycheck);
                        y = zemap.get("int " + extravar);
                        z = zemap.get("int " + numbertobesubtracted);
                        x = y - z;
                        zemap.put("int " + keycheck, x);
                    } else if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + extravar)) {
                        x = zemap.get("int " + keycheck);
                        y = zemap.get("int " + extravar);
                        z = Integer.parseInt(numbertobesubtracted);

                        System.out.println("Performing " + y + " - " + z);
                        x = y - z;
                        zemap.put("int " + keycheck, x);
                    } else if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + numbertobesubtracted)) {
                        x = zemap.get("int " + keycheck);

                        y = Integer.parseInt(extravar);
                        z = zemap.get("int " + numbertobesubtracted);
                        System.out.println("Performing " + y + " - " + z);
                        x = y - z;
                        zemap.put("int " + keycheck, x);
                    } else if (zemap.containsKey("int " + keycheck)) {
                        x = zemap.get("int " + keycheck);

                        z = Integer.parseInt(numbertobesubtracted);
                        y = Integer.parseInt(extravar);
                        System.out.println("Performing " + y + " - " + z);
                        x = y - z;
                        zemap.put("int " + keycheck, x);
                    }
                    System.out.println("Subtraction: " + zemap.get("int " + keycheck));*/
                    break;
                }
                case "*": {
                    int x;
                    int y;
                    int product;
                    String keycheck = stringscan.next();
                    String extravar = stringscan.next();
                    String numbertobemult = stringscan.next();


                    product = multiply(keycheck, extravar, numbertobemult);
                    zemap.put("int " + keycheck, product);

                    /*if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + extravar) && zemap.containsKey("int " + numbertobemult)) {
                        product = zemap.get("int " + keycheck);
                        x = zemap.get("int " + extravar);
                        y = zemap.get("int " + numbertobemult);
                        product = x * y;
                        zemap.put("int "+keycheck,product);
                        //return modulus;
                    } else if ((zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + extravar))) {
                        product = zemap.get("int " + keycheck);
                        x = zemap.get("int " + extravar);
                        y =  Integer.valueOf(numbertobemult);
                        product = x * y;
                        zemap.put("int "+keycheck,product);
                        //return modulus;
                    } else if ((zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + numbertobemult))) {
                        product= zemap.get("int " + keycheck);
                        x = Integer.valueOf(extravar);
                        y = zemap.get("int " + numbertobemult);

                        product=x*y;
                        zemap.put("int "+keycheck,product);
                        //return modulus;

                    } else if (zemap.containsKey("int " + keycheck)) {
                        product = zemap.get("int " + keycheck);
                        x= Integer.valueOf(extravar);
                        y = Integer.valueOf(numbertobemult);
                        product=x * y ;
                        zemap.put("int "+keycheck,product);
                        //return modulus;
                    }*/
                    /*if(zemap.containsKey("int "+extravar))
                    {
                        x=zemap.get("int "+extravar);
                    }
                    else
                    {
                        x=Integer.parseInt(extravar);
                    }
                    if(zemap.containsKey("int "+numbertobemult))
                    {
                        y=zemap.get("int "+numbertobemult);
                        product=x*y;
                    }
                    else
                    {
                        y= Integer.parseInt(numbertobemult);
                        product=x*y;
                    }
                    zemap.put("int "+keycheck,product);*/
                    //Look at what you need to do with the extra var
                    /*if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + extravar) && zemap.containsKey("int " + numbertobemultiplied)) {
                        x = zemap.get("int " + keycheck);
                        y = zemap.get("int " + numbertobemultiplied);
                        z = zemap.get("int " + extravar);
                        x = y * z;
                        zemap.put("int " + keycheck, x);
                    } else if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + extravar) && zemap.containsKey("color " + numbertobemultiplied)) {
                        x = zemap.get("int " + keycheck);
                        y = zemap.get("color " + numbertobemultiplied);
                        z = zemap.get("int " + extravar);
                        x = y * z;
                        zemap.put("int " + keycheck, x);
                    } else if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + extravar)) {
                        x = zemap.get("int " + keycheck);
                        y = Integer.parseInt(numbertobemultiplied);
                        z = zemap.get("int " + extravar);
                        x = y * z;
                        zemap.put("int " + keycheck, x);
                    } else if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + numbertobemultiplied)) {
                        x = zemap.get("int " + keycheck);
                        y = zemap.get("int " + numbertobemultiplied);
                        z = Integer.parseInt(extravar);
                        x = y * z;
                        zemap.put("int " + keycheck, x);
                    } else if (zemap.containsKey("int " + keycheck)) {
                        x = zemap.get("int " + keycheck);
                        y = Integer.parseInt(numbertobemultiplied);
                        z = Integer.parseInt(extravar);
                        x = z * y;
                        zemap.put("int " + keycheck, x);
                    }
                    System.out.println("Multiplication: " + zemap.get("int " + keycheck));*/

                    break;
                }
                case "/": {
                    //Divide t by 25

                    int x;
                    int y;
                    int quotient;
                    String keycheck = stringscan.next();
                    String extravar = stringscan.next();
                    String numbertobediv = stringscan.next();


                   quotient = division(keycheck, extravar, numbertobediv);
                    zemap.put("int " + keycheck, quotient);



                    //int modulus=0;
                    //int num=0;
                    //int numdos=0;
                    /*if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + extravar) && zemap.containsKey("int " + numbertobediv)) {
                        quotient = zemap.get("int " + keycheck);
                        x = zemap.get("int " + extravar);
                        y = zemap.get("int " + numbertobediv);
                        quotient = x / y;
                        zemap.put("int "+keycheck,quotient);
                        //return modulus;
                    } else if ((zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + extravar))) {
                        quotient = zemap.get("int " + keycheck);
                        x = zemap.get("int " + extravar);
                        y =  Integer.valueOf(numbertobediv);
                        quotient = x / y;
                        zemap.put("int "+keycheck,quotient);
                        //return modulus;
                    } else if ((zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + numbertobediv))) {
                        quotient= zemap.get("int " + keycheck);
                        x = Integer.valueOf(extravar);
                        y = zemap.get("int " + numbertobediv);

                        quotient=x/y;
                        zemap.put("int "+keycheck,quotient);
                        //return modulus;

                    } else if (zemap.containsKey("int " + keycheck)) {
                        quotient = zemap.get("int " + keycheck);
                        x= Integer.valueOf(extravar);
                        y = Integer.valueOf(numbertobediv);
                        quotient=x / y ;
                        zemap.put("int "+keycheck,quotient);
                        //return modulus;
                    }*/

                    /*if(zemap.containsKey("int "+extravar))
                    {
                        x=zemap.get("int "+extravar);
                    }
                    else
                    {
                        x=Integer.parseInt(extravar);
                    }
                    if(zemap.containsKey("int "+numbertobediv))
                    {
                        y=zemap.get("int "+numbertobediv);
                        quotient=x/y;
                    }
                    else
                    {
                        y= Integer.parseInt(numbertobediv);
                        quotient=x/y;
                    }
                    zemap.put("int "+keycheck,quotient);*/


                    /*int x;
                    int y;
                    int z;
                    //Add 2 numbers and store them back to i
                    String keycheck = stringscan.next();
                    String extravar = stringscan.next();
                    String numbertobedivided = stringscan.next();

                    //Look at what you need to do with the extra var

                    if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + extravar) && zemap.containsKey("int " + numbertobedivided)) {
                        x = zemap.get("int " + keycheck);
                        y = zemap.get("int " + extravar);
                        z = zemap.get("int " + numbertobedivided);
                        x = (y / z);
                        zemap.put("int " + keycheck, x);
                    } else if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + extravar) && zemap.containsKey("color " + numbertobedivided)) {
                        x = zemap.get("int " + keycheck);
                        y = zemap.get("int " + extravar);
                        z = zemap.get("color " + numbertobedivided);
                        x = (y / z);
                        zemap.put("int " + keycheck, x);
                    } else if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + extravar)) {
                        x = zemap.get("int " + keycheck);
                        y = zemap.get("int " + extravar);
                        z = Integer.parseInt(numbertobedivided);
                        x = (y / z);
                        zemap.put("int " + keycheck, x);
                    } else if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + numbertobedivided)) {
                        x = zemap.get("int " + keycheck);
                        y = Integer.parseInt(extravar);
                        z = zemap.get("int " + numbertobedivided);
                        x = (y / z);
                        zemap.put("int " + keycheck, x);
                    } else if (zemap.containsKey("int " + keycheck)) {
                        x = zemap.get("int " + keycheck);
                        y = Integer.parseInt(extravar);
                        z = Integer.parseInt(numbertobedivided);
                        x = (y / z);
                        zemap.put("int " + keycheck, x);
                    }
                    System.out.println("Division: " + zemap.get("int " + keycheck));*/
                    break;
                }
                case "=": {
                    String keyval = stringscan.next();
                    String value = stringscan.next();
                    equalsop(keyval,value);
                    /*if (zemap.containsKey("int " + keyval) && zemap.containsKey("int " + value)) {
                        zemap.put("int " + keyval,zemap.get("int " + value));
                    } else if (zemap.containsKey("bool " + keyval) && zemap.containsKey("bool " + value)) {
                        zemap.put("bool " + keyval, zemap.get("bool " + value));
                    } else if (zemap.containsKey("int " + keyval)) {
                        zemap.put("int " + keyval, Integer.valueOf(value));
                    }*/
                    break;
                }
                case "%": {
                    int x;
                    int y;
                    int modulus;
                    String keycheck = stringscan.next();
                    String extravar = stringscan.next();
                    String numbertobemod = stringscan.next();


                    //int modulus=0;
                    //int num=0;
                    //int numdos=0;
                    /*if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + extravar) && zemap.containsKey("int " + numbertobemod)) {
                        modulus = zemap.get("int " + keycheck);
                        x = zemap.get("int " + extravar);
                        y = zemap.get("int " + numbertobemod);
                        modulus = x%y;
                        zemap.put("int "+keycheck,modulus);
                        //return modulus;
                    } else if ((zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + extravar))) {
                        modulus = zemap.get("int " + keycheck);
                        x = zemap.get("int " + extravar);
                        y =  Integer.valueOf(numbertobemod);
                        modulus = x%y;
                        zemap.put("int "+keycheck,modulus);
                        //return modulus;
                    } else if ((zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + numbertobemod))) {
                        modulus= zemap.get("int " + keycheck);
                        x = Integer.valueOf(extravar);
                        y = zemap.get("int " + numbertobemod);

                        modulus=x%y;
                        zemap.put("int "+keycheck,modulus);
                        //return modulus;

                    } else if (zemap.containsKey("int " + keycheck)) {
                        modulus = zemap.get("int " + keycheck);
                        x= Integer.valueOf(extravar);
                        y = Integer.valueOf(numbertobemod);
                        modulus=x%y;
                        zemap.put("int "+keycheck,modulus);
                        //return modulus;
                    }*/

                    //return modulus;
                    modulus = modulusop(keycheck, extravar, numbertobemod);
                    zemap.put("int " + keycheck, modulus);
                    /*if(zemap.containsKey("int "+extravar))
                    {
                        x=zemap.get("int "+extravar);
                    }
                    else
                    {
                        x=Integer.parseInt(extravar);
                    }
                    if(zemap.containsKey("int "+numbertobemod))
                    {
                        y=zemap.get("int "+numbertobemod);
                        modulus=x%y;
                    }
                    else
                    {
                        y= Integer.parseInt(numbertobemod);
                        modulus=x%y;
                    }
                    zemap.put("int "+keycheck,modulus);*/
                    /* int x;
                    int y;
                    int z;
                    //Add 2 numbers and store them back to i
                    String keycheck = stringscan.next();
                    String extravar = stringscan.next();
                    String numbertobemodulus = stringscan.next();

                    //Look at what you need to do with the extra var
                    if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + extravar) && zemap.containsKey("int " + numbertobemodulus)) {
                        x = zemap.get("int " + keycheck);
                        System.out.println("x is " + x);
                        y = zemap.get("int " + extravar);
                        System.out.println("y is " + y);
                        z = zemap.get("int " + numbertobemodulus);
                        System.out.println("z is " + z);
                        if (z == 0) {
                            // do nothing
                        } else {
                            x = y % z;
                        }
                        zemap.put("int " + keycheck, x);
                    } else if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + extravar) && zemap.containsKey("color " + numbertobemodulus)) {
                        x = zemap.get("int " + keycheck);
                        System.out.println("x is " + x);
                        y = zemap.get("int " + extravar);
                        System.out.println("y is " + y);
                        z = zemap.get("color " + numbertobemodulus);
                        System.out.println("z is " + z);
                        if (z == 0) {
                            // do nothing
                        } else {
                            x = y % z;
                        }
                        zemap.put("int " + keycheck, x);
                    } else if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + extravar)) {
                        x = zemap.get("int " + keycheck);
                        System.out.println("x is " + x);
                        y = zemap.get("int " + extravar);
                        System.out.println("y is " + y);
                        z = Integer.parseInt(numbertobemodulus);
                        System.out.println("z is " + z);
                        if (z == 0) {
                            // do nothing
                        } else {
                            x = y % z;
                        }
                        zemap.put("int " + keycheck, x);
                    } else if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + numbertobemodulus)) {
                        x = zemap.get("int " + keycheck);
                        System.out.println("x is " + x);
                        y = Integer.parseInt(extravar);
                        System.out.println("y is " + y);
                        z = zemap.get("int " + numbertobemodulus);
                        System.out.println("z is " + z);
                        if (z == 0) {
                            // do nothing
                        } else {
                            x = y % z;
                        }
                        zemap.put("int " + keycheck, x);
                    } else if (zemap.containsKey("int " + keycheck)) {
                        x = zemap.get("int " + keycheck);
                        y = Integer.parseInt(extravar);
                        z = Integer.parseInt(numbertobemodulus);
                        x = y % z;
                        zemap.put("int " + keycheck, x);
                    }
                    System.out.println("Modulus: " + zemap.get("int " + keycheck));*/
                    break;
                }
                //boolean functions
                //Possibly add a bool case to ==?
                case "==": {
                    //Takes in values to compare
                    String keycheck = stringscan.next();
                    String keycheck2 = stringscan.next();
                    String numbertobecompared = stringscan.next();
                    //Look at what you need to do with the extra var
                        int keycheck2val=0;
                        int numbertobecomparedvalue=0;
                    if(zemap.containsKey("int "+keycheck2))
                    {
                        keycheck2val = zemap.get("int "+keycheck2);
                    }
                    else if(!zemap.containsKey("int "+keycheck2))
                    {
                        keycheck2val = Integer.valueOf(keycheck2);
                    }
                    if(zemap.containsKey("int "+numbertobecompared))
                    {
                        numbertobecomparedvalue = zemap.get("int "+numbertobecompared);
                    }
                    else if(!zemap.containsKey(numbertobecompared))
                    {
                        numbertobecomparedvalue = Integer.valueOf(numbertobecompared);
                    }
                    if(zemap.containsKey("int "+keycheck))
                    {
                        if(keycheck2val == numbertobecomparedvalue)
                        {
                            zemap.put("int "+keycheck, keycheck2val);
                        }
                    }
                    else if(zemap.containsKey("bool "+keycheck))
                    {
                        if(keycheck2val == numbertobecomparedvalue)
                        {
                            zemap.put("bool "+keycheck, 1);
                        }
                        else if(keycheck2val != numbertobecomparedvalue)
                            zemap.put("bool "+keycheck,0);
                    }
                        //Checks if map contains keycheck2, if it does great.
                        /*if(zemap.containsKey("int "+keycheck2) && zemap.containsKey("int "+numbertobecompared))
                        {
                            keycheck2val=zemap.get("int "+keycheck2);
                        }
                        //If not in map, it will parse it to an int.
                        else if(!zemap.containsKey("int "+keycheck2))
                        {
                            keycheck2val=Integer.valueOf(keycheck2);
                        }
                        //Above logic is repeated for the second variable
                        if(zemap.containsKey("int "+numbertobecompared))
                        {
                            numbertobecomparedvalue=zemap.get("int "+numbertobecompared);
                        }
                        //If not in map, it will parse it to an int.
                        else if(!zemap.containsKey("int "+numbertobecompared))
                        {
                            numbertobecomparedvalue=Integer.valueOf(numbertobecompared);
                        }
                        //If first variable inputted ie the storage variable is an int, do the following
                        if(zemap.containsKey("int "+keycheck))
                        {
                            if(keycheck2val==numbertobecomparedvalue)
                            {
                                zemap.put("int "+keycheck,keycheck2val);
                            }
                            else if(numbertobecomparedvalue==keycheck2val)
                            {
                                zemap.put("int "+keycheck,numbertobecomparedvalue);
                            }
                        }
                        else if(zemap.containsKey("bool "+keycheck))
                        {
                            if(keycheck2val==numbertobecomparedvalue)
                            {
                                zemap.put("bool "+keycheck,1);
                            }
                            else
                                zemap.put("bool "+keycheck,0);
                        }*/

                   /* if (zemap.containsKey("bool " + keycheck) && zemap.containsKey("int " + keycheck2) && zemap.containsKey("int " + numbertobecompared)) {
                        //True
                        System.out.println(zemap.get("int " + keycheck2));
                        if (zemap.get("int " + keycheck2) == zemap.get("int " + numbertobecompared)) {
                            zemap.put("bool " + keycheck, 1);
                            System.out.println("Result is true");

                        }
                        //False
                        else {
                            zemap.put("bool " + keycheck, 0);
                        }
                        System.out.println("Result is false");
                    } else if (zemap.containsKey("bool " + keycheck) && zemap.containsKey("int " + keycheck2)) {

                        if (zemap.get("int " + keycheck2) == Integer.parseInt(numbertobecompared)) {
                            zemap.put("bool " + keycheck, 1);
                            System.out.println("Result is true");
                            System.out.println("Set " + keycheck + " to be true");

                        } else {
                            zemap.put("bool " + keycheck, 0);
                            System.out.println("Result is false");
                            System.out.println("Set " + keycheck + " to be false");
                        }
                    } else if (zemap.containsKey("bool " + keycheck) && zemap.containsKey("int " + numbertobecompared)) {
                        if (zemap.get("int " + numbertobecompared) == Integer.parseInt(keycheck2)) {
                            zemap.put("bool " + keycheck, 1);
                            System.out.println("Result is true");
                        }
                        //False
                        else {
                            zemap.put("bool " + keycheck, 0);
                        }
                        System.out.println("Result is false");
                    } else if (zemap.containsKey("bool " + keycheck)) {
                        if (Integer.parseInt(numbertobecompared) == Integer.parseInt(keycheck2)) {
                            zemap.put("bool " + keycheck, 1);
                            System.out.println("Result is true");
                        }
                        //False
                        else {
                            zemap.put("bool " + keycheck, 0);
                        }
                        System.out.println("Result is false");
                    }
                    //If all are ints
                    else if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + keycheck2) && zemap.containsKey("int " + numbertobecompared)) {
                        //True
                        if (zemap.get("int " + keycheck2) == zemap.get("int " + numbertobecompared)) {
                            zemap.put("int " + keycheck, zemap.get("int " + keycheck2));
                        } else if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + keycheck2)) {

                            if (zemap.get("int " + keycheck2) == Integer.parseInt(numbertobecompared)) {
                                zemap.put("int " + keycheck, zemap.get("int " + keycheck2));
                            }
                        } else if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + numbertobecompared)) {
                            if (zemap.get("int " + numbertobecompared) == Integer.parseInt(keycheck2)) {
                                zemap.put("int " + keycheck, zemap.get("int " + numbertobecompared));
                            }
                        } else if (zemap.containsKey("int " + keycheck)) {
                            if (Integer.parseInt(numbertobecompared) == Integer.parseInt(keycheck2)) {
                                zemap.put("int " + keycheck, Integer.parseInt(numbertobecompared));
                            }
                        }
                    }*/
                    break;
                }
                case ">": {
                    //Takes in values to compare
                    String keycheck = stringscan.next();
                    String keycheck2 = stringscan.next();
                    String numbertobecompared = stringscan.next();

                    int keycheck2val = 0;
                    int numbertobecomparedvalue = 0;
                    if(zemap.containsKey("int "+keycheck2))
                    {
                        keycheck2val = zemap.get("int "+keycheck2);
                    }
                    else
                    {
                        keycheck2val = Integer.valueOf(keycheck2);
                    }
                    if(zemap.containsKey("int "+numbertobecompared))
                    {
                        numbertobecomparedvalue = zemap.get("int "+numbertobecompared);
                    }
                    else
                    {
                        numbertobecomparedvalue = Integer.valueOf(numbertobecompared);
                    }

                    if(zemap.containsKey("int "+keycheck))
                    {
                        if(keycheck2val>numbertobecomparedvalue)
                        {
                            zemap.put("int "+keycheck,keycheck2val);
                        }
                        else
                        {
                            zemap.put("int "+keycheck,numbertobecomparedvalue);
                        }
                    }
                    else if(zemap.containsKey("bool "+keycheck))
                    {
                        if(keycheck2val>numbertobecomparedvalue)
                        {
                            zemap.put("bool "+keycheck,1);
                        }
                        else
                        {
                            zemap.put("bool "+keycheck,0);
                        }

                    }
                    //Checks if map contains keycheck2, if it does great.
                    /*if (zemap.containsKey("int " + keycheck2)) {
                        keycheck2val = zemap.get("int " + keycheck2);
                    }
                    //If not in map, it will parse it to an int.
                    else if (!zemap.containsKey("int " + keycheck2)) {
                        keycheck2val = Integer.parseInt(keycheck2);
                    }
                    //Above logic is repeated for the second variable
                    if (zemap.containsKey("int " + numbertobecompared)) {
                        numbertobecomparedvalue = zemap.get("int " + numbertobecompared);
                    }
                    //If not in map, it will parse it to an int.
                    else if (!zemap.containsKey("int " + numbertobecompared)) {
                        numbertobecomparedvalue = Integer.parseInt(numbertobecompared);
                    }

                    //If first variable inputted ie the storage variable is an int, do the following

                    if (zemap.containsKey("int " + keycheck)) {
                        if (keycheck2val > numbertobecomparedvalue) {
                            zemap.put("int " + keycheck, keycheck2val);
                        } else if (numbertobecomparedvalue > keycheck2val) {
                            zemap.put("int " + keycheck, numbertobecomparedvalue);
                        }
                    } else if (zemap.containsKey("bool " + keycheck)) {
                        if (keycheck2val > numbertobecomparedvalue) {
                            zemap.put("bool " + keycheck, 1);
                        } else if (numbertobecomparedvalue > keycheck2val) {
                            zemap.put("bool " + keycheck, 0);
                        }
                    }
                    if (zemap.containsKey("bool " + keycheck) && zemap.containsKey("int " + keycheck2) && zemap.containsKey("int " + numbertobecompared)) {
                        //True
                        if (zemap.get("bool " + keycheck2) > zemap.get("int " + numbertobecompared)) {
                            zemap.put("bool " + keycheck, 1);
                        }
                        //False
                        else {
                            zemap.put("bool " + keycheck, 0);
                        }
                    } else if (zemap.containsKey("bool " + keycheck) && zemap.containsKey("int " + keycheck2)) {
                        int number = Integer.parseInt(numbertobecompared);
                        if (zemap.get("int " + keycheck2) > number) {
                            zemap.put("bool " + keycheck, 1);
                        }
                        //False
                        else {
                            zemap.put("bool " + keycheck, 0);
                        }
                    } else if (zemap.containsKey("bool " + keycheck) && zemap.containsKey("int " + numbertobecompared)) {
                        int number = Integer.parseInt(keycheck2);
                        if (zemap.get("int " + numbertobecompared) > number) {
                            zemap.put("bool " + keycheck, 1);
                        }
                        //False
                        else {
                            zemap.put("bool " + keycheck, 0);
                        }
                    } else if (zemap.containsKey("bool " + keycheck)) {
                        int number = Integer.parseInt(numbertobecompared);
                        int num2 = Integer.parseInt(keycheck2);
                        if (num2 > number) {
                            zemap.put("bool " + keycheck, 1);
                        }
                        //False
                        else {
                            zemap.put("bool " + keycheck, 0);
                        }
                    }*/

                    break;
                }
                case ">=": {
                    String keycheck = stringscan.next();
                    String keycheck2 = stringscan.next();
                    String numbertobecompared = stringscan.next();

                    //Look at what you need to do with the extra var

                    if (zemap.containsKey("bool " + keycheck) && zemap.containsKey("int " + keycheck2) && zemap.containsKey("int " + numbertobecompared)) {
                        //True
                        if (zemap.get("int " + keycheck2) >= zemap.get("int " + numbertobecompared)) {
                            zemap.put("bool " + keycheck, 1);
                        }
                        //False
                        else {
                            zemap.put("bool " + keycheck, 0);
                        }
                    } else if (zemap.containsKey("bool " + keycheck) && zemap.containsKey("int " + keycheck2)) {
                        int number = Integer.valueOf(numbertobecompared);
                        //True
                        if (zemap.get("int " + keycheck2) >= number) {
                            zemap.put("bool " + keycheck, 1);
                        }
                        //False
                        else {
                            zemap.put("bool " + keycheck, 0);
                        }
                    } else if (zemap.containsKey("bool " + keycheck) && zemap.containsKey("int " + numbertobecompared)) {
                        int number = Integer.valueOf(keycheck2);
                        //True
                        if (zemap.get("int " + numbertobecompared) >= number) {
                            zemap.put("bool " + keycheck, 1);
                        }
                        //False
                        else {
                            zemap.put("bool " + keycheck, 0);
                        }
                    } else if (zemap.containsKey("bool " + keycheck)) {
                        int number = Integer.valueOf(numbertobecompared);
                        int num2 = Integer.valueOf(keycheck2);
                        //True
                        if (num2 >= number) {
                            zemap.put("bool " + keycheck, 1);
                        }
                        //False
                        else {
                            zemap.put("bool " + keycheck, 0);
                        }
                    }
                        /*String keycheck= stringscan.next();
                        String keycheck2=stringscan.next();
                        String numbertobecompared= stringscan.next();

                        int keycheck2val=0;
                        int numbertobecomparedvalue=0;

                        //Checks if map contains keycheck2, if it does great.
                        if(zemap.containsKey("int "+keycheck2))
                        {
                            keycheck2val=zemap.get("int "+keycheck2);
                        }
                        //If not in map, it will parse it to an int.
                        else if(!zemap.containsKey("int "+keycheck2))
                        {
                            keycheck2val=Integer.parseInt(keycheck2);
                        }
                        //Above logic is repeated for the second variable
                        if(zemap.containsKey("int "+numbertobecompared))
                        {
                            numbertobecomparedvalue=zemap.get("int "+numbertobecompared);
                        }
                        //If not in map, it will parse it to an int.
                        else if(!zemap.containsKey("int "+numbertobecompared))
                        {
                            numbertobecomparedvalue=Integer.parseInt(numbertobecompared);
                        }

                        //If first variable inputted ie the storage variable is an int, do the following

                        if(zemap.containsKey("int "+keycheck))
                        {
                            if(keycheck2val>=numbertobecomparedvalue)
                            {
                                zemap.put("int "+keycheck,keycheck2val);
                            }
                            else if(numbertobecomparedvalue>=keycheck2val)
                            {
                                zemap.put("int "+keycheck,numbertobecomparedvalue);
                            }
                        }
                        else if(zemap.containsKey("bool "+keycheck))
                        {
                            if(keycheck2val>=numbertobecomparedvalue)
                            {
                                zemap.put("bool "+keycheck,1);
                            }
                            else if(numbertobecomparedvalue>=keycheck2val)
                            {
                                zemap.put("bool "+keycheck,0);
                            }
                        }*/
                    break;
                }
                case "&": {
                    int x;
                    int y;
                    int z;
                    //Add 2 numbers and store them back to i
                    String keycheck = stringscan.next();
                    String extravar = stringscan.next();
                    String numbertobemodulus = stringscan.next();

                    //Look at what you need to do with the extra var
                    if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + extravar) && zemap.containsKey("int " + numbertobemodulus)) {
                        x = zemap.get("int " + keycheck);
                        System.out.println("x is " + x);
                        y = zemap.get("int " + extravar);
                        System.out.println("y is " + y);
                        z = zemap.get("int " + numbertobemodulus);
                        System.out.println("z is " + z);
                        if (z == 0) {
                            // do nothing
                        }
                        zemap.put("int " + keycheck, zemap.get("int " + extravar) & zemap.get("int " + numbertobemodulus));
                    } else if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + extravar) && zemap.containsKey("color " + numbertobemodulus)) {
                        x = zemap.get("int " + keycheck);
                        System.out.println("x is " + x);
                        y = zemap.get("int " + extravar);
                        System.out.println("y is " + y);
                        z = zemap.get("color " + numbertobemodulus);
                        System.out.println("z is " + z);
                        if (z == 0) {
                            // do nothing
                        }

                        zemap.put("int " + keycheck, zemap.get("int " + extravar) & zemap.get("color " + numbertobemodulus));
                    } else if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + extravar)) {
                        x = zemap.get("int " + keycheck);
                        System.out.println("x is " + x);
                        y = zemap.get("int " + extravar);
                        System.out.println("y is " + y);
                        z = Integer.valueOf(numbertobemodulus);
                        System.out.println("z is " + z);
                        if (z == 0) {
                            // do nothing
                        }
                        zemap.put("int " + keycheck, zemap.get("int " + extravar) & Integer.parseInt(numbertobemodulus));
                    } else if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + numbertobemodulus)) {
                        x = zemap.get("int " + keycheck);
                        System.out.println("x is " + x);
                        y = Integer.valueOf(extravar);
                        System.out.println("y is " + y);
                        z = zemap.get("int " + numbertobemodulus);
                        System.out.println("z is " + z);

                        zemap.put("int " + keycheck, Integer.parseInt(extravar) & zemap.get("int " + numbertobemodulus));
                    } else if (zemap.containsKey("int " + keycheck)) {
                        x = zemap.get("int " + keycheck);
                        y = Integer.valueOf(extravar);
                        z = Integer.valueOf(numbertobemodulus);
                        zemap.put("int " + keycheck, y & z);
                    }
                    break;
                }
                case "|": {
                    int x;
                    int y;
                    int z;
                    //Add 2 numbers and store them back to i
                    String keycheck = stringscan.next();
                    String extravar = stringscan.next();
                    String numbertobemodulus = stringscan.next();

                    //Look at what you need to do with the extra var
                    if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + extravar) && zemap.containsKey("int " + numbertobemodulus)) {
                        x = zemap.get("int " + keycheck);
                        System.out.println("x is " + x);
                        y = zemap.get("int " + extravar);
                        System.out.println("y is " + y);
                        z = zemap.get("int " + numbertobemodulus);
                        System.out.println("z is " + z);
                        zemap.put("int " + keycheck, zemap.get("int " + extravar) | zemap.get("int " + numbertobemodulus));
                    } else if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + extravar) && zemap.containsKey("color " + numbertobemodulus)) {
                        x = zemap.get("int " + keycheck);
                        System.out.println("x is " + x);
                        y = zemap.get("int " + extravar);
                        System.out.println("y is " + y);
                        z = zemap.get("color " + numbertobemodulus);
                        System.out.println("z is " + z);

                        zemap.put("int " + keycheck, zemap.get("int " + extravar) | zemap.get("color " + numbertobemodulus));
                    } else if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + extravar)) {
                        x = zemap.get("int " + keycheck);
                        System.out.println("x is " + x);
                        y = zemap.get("int " + extravar);
                        System.out.println("y is " + y);
                        z = Integer.valueOf(numbertobemodulus);
                        System.out.println("z is " + z);

                        zemap.put("int " + keycheck, zemap.get("int " + extravar) | Integer.parseInt(numbertobemodulus));
                    } else if (zemap.containsKey("int " + keycheck) && zemap.containsKey("int " + numbertobemodulus)) {
                        x = zemap.get("int " + keycheck);
                        System.out.println("x is " + x);
                        y = Integer.valueOf(extravar);
                        System.out.println("y is " + y);
                        z = zemap.get("int " + numbertobemodulus);
                        System.out.println("z is " + z);

                        zemap.put("int " + keycheck, Integer.parseInt(extravar) | zemap.get("int " + numbertobemodulus));
                    } else if (zemap.containsKey("int " + keycheck)) {
                        x = zemap.get("int " + keycheck);
                        y = Integer.valueOf(extravar);
                        z = Integer.valueOf(numbertobemodulus);
                        zemap.put("int " + keycheck, Integer.valueOf(extravar) | Integer.valueOf(numbertobemodulus));
                    }
                    break;
                }
                //other functions
                case "jump": {
                    //resets the loop
                    String loopjump = stringscan.next();
                    //Sets counter back
                   // secondcounter = zemap.get("label " + loopjump);
                    //counter = secondcounter - 1;
                    i=zemap.get("label "+loopjump)-1;
                    //System.out.println("Counter is " + counter);
                    break;
                }
                case "jumpif": {
                    //If result is True, jump to exiti
                    String boolvarcondition = stringscan.next();
                    String labelname = stringscan.next();
                    if (zemap.get("bool " + boolvarcondition) == 1) {
                        i = zemap.get("label " + labelname)-1;
                        //System.out.println("counter after var goes to true is " + counter);
                    }
                    break;
                }
                case "time": {
                    int timehold = (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
                    String keyval = stringscan.next();
                    zemap.put("int " + keyval, timehold);
                    System.out.println("t is " + zemap.get("int " + keyval));
                    break;
                }
                case "setpixel": {


                    /*String xCor = stringscan.next();
                    String yCor = stringscan.next();
                    String color = stringscan.next();

                    int tempC;
                    int tempA;
                    int tempB;
                    if(zemap.containsKey("int "+xCor))
                    {
                        tempA = zemap.get("int "+xCor);
                    }
                    else
                    {
                        tempA = Integer.valueOf(xCor);
                    }
                    if(zemap.containsKey("int "+yCor))
                    {
                        tempB = zemap.get("int "+yCor);
                    }
                    else
                    {
                        tempB = Integer.valueOf(yCor);
                    }
                    if(zemap.containsKey("color "+color))
                    {
                        tempC = zemap.get("color "+color);
                    }
                    else if(zemap.containsKey("int"+color))
                    {
                        tempC = zemap.get("int"+color);
                    }
                    else
                    {
                        tempC = Integer.valueOf(color);
                    }
                    image.setColor(tempA,tempB,tempC);*/
                    int x=0;
                    int y=0;
                    int colorval=0;
                        String xpos = stringscan.next();
                        String ypos = stringscan.next();
                        String color = stringscan.next();

                        if(zemap.containsKey("int "+xpos))
                        {
                            x = zemap.get("int "+xpos);
                        }
                        else
                        {
                            x = Integer.valueOf(xpos);
                        }
                        if(zemap.containsKey("int "+ypos))
                         {
                            y= zemap.get("int "+ypos);
                         }
                        else
                        {
                            y= Integer.valueOf(ypos);
                        }
                        if(zemap.containsKey("color "+color))
                        {
                            colorval = zemap.get("color "+color);
                        }
                        else if(zemap.containsKey("int "+color))
                        {
                            colorval= zemap.get("int "+color);
                        }
                        else if(!zemap.containsKey("int "+color))
                        {
                            colorval = Integer.valueOf(color);
                        }
                        image.setColor(x,y,colorval);
                    //Scans in the x, y and color
                    /*String xhold = stringscan.next();
                    String yhold = stringscan.next();
                    String colorhold = stringscan.next();
                    System.out.println("Xhold is " + xhold + " Yhold is " + yhold + " Colorhold is " + colorhold);
                    int colorval = 0;
                    int x = 0;
                    int y = 0;
                    if (zemap.containsKey("int " + xhold) && zemap.containsKey("int " + yhold) && zemap.containsKey("int " + colorhold)) {
                        x = zemap.get("int " + xhold);
                        y = zemap.get("int " + yhold);
                        colorval = zemap.get("int " + colorhold);
                        /*if (colorval < 0) {
                            colorval = 255;
                            zemap.put("int " + colorhold, colorval);
                        }
                        System.out.println("Color val0 is " + colorval);
                        System.out.println("Drawing");
                    } else if (zemap.containsKey("int " + xhold) && zemap.containsKey("int " + yhold) && zemap.containsKey("color " + colorhold)) {
                        x = zemap.get("int " + xhold);
                        y = zemap.get("int " + yhold);
                        colorval = zemap.get("color " + colorhold);
                        if (colorval < 0) {
                            colorval = 255;
                        }
                        System.out.println("Color val1 is " + colorval);
                    } else if (zemap.containsKey("int " + xhold) && zemap.containsKey("int " + yhold)) {
                        x = zemap.get("int " + xhold);
                        y = zemap.get("int " + yhold);
                        colorval = Integer.parseInt(colorhold);
                        if (colorval < 0) {
                            colorval = 255;
                        }
                        System.out.println("Color val2 is " + colorval);
                        System.out.println("Drawing");
                    } else if (zemap.containsKey("int " + xhold) && zemap.containsKey("int " + colorhold)) {
                        x = zemap.get("int " + xhold);
                        y = Integer.parseInt(yhold);
                        colorval = zemap.get("int " + colorhold);
                        if (colorval < 0) {
                            colorval = 255;
                            zemap.put("int " + colorhold, colorval);
                        }
                        System.out.println("Color val3 is " + colorval);
                        System.out.println("Drawing");
                    } else if (zemap.containsKey("int " + xhold)) {
                        x = zemap.get("int " + xhold);
                        y = Integer.parseInt(yhold);
                        colorval = Integer.parseInt(colorhold);
                        if (colorval < 0) {
                            colorval = 255;
                        }
                        System.out.println("Color val4 is " + colorval);
                        System.out.println("Drawing");
                    }
                    System.out.println("X color is " + x + " Y color is " + y + " colorval is " + colorval);
                    image.setColor(x, y, colorval);*/
                    //System.out.println(zemap);
                    break;
                }
                case "getpixel": {
                    //Image image = panel.getImage();
                    String colorstore = stringscan.next();
                    String xhold = stringscan.next();
                    String yhold = stringscan.next();
                    int x;
                    int y;
                    int color;
                    if(zemap.containsKey("int "+xhold))
                    {
                        x=zemap.get("int " + xhold);
                    }
                    else
                    {
                        x=Integer.valueOf(xhold);
                    }
                    if(zemap.containsKey("int "+yhold))
                    {
                        y=zemap.get("int "+yhold);
                    }
                    else {
                        y = Integer.valueOf(yhold);
                    }

                    int colorrecieve = image.getColor(x, y);

                    if(zemap.containsKey("int "+colorstore))
                    {
                        zemap.put("int "+colorstore,colorrecieve);
                    }
                    else if(zemap.containsKey("color "+colorstore))
                    {
                        zemap.put("color "+colorstore,colorrecieve);
                    }
                    break;
                }
            }
        }
        //} while (counter < commands.size() - 1);

        //System.out.println("left do while");

            for (int j = 0; j < zemap.size()-1; j++) {
                Scanner clear = new Scanner(commands.get(j));
                String nom = clear.next();
                String nom2electricboogaloo=clear.next();
                System.out.println(nom);
                System.out.println(nom2electricboogaloo);
                if (zemap.containsKey("int " + nom2electricboogaloo)) {
                    zemap.put("int " + nom2electricboogaloo, 0);
                    System.out.println("Cleared nom");
                }
                if (zemap.containsKey("bool " + nom2electricboogaloo)) {
                    zemap.put("bool " + nom2electricboogaloo, 0);
                    System.out.println("Cleared nom");
                }
                if(zemap.containsKey("label  "+nom2electricboogaloo))
                {
                    zemap.put("label "+nom2electricboogaloo,0);
                }
            }

        System.out.println(zemap);
    }
    public int add(String storage, String num1, String num2)
    {
        int sum=0;
        int num=0;
        int numdos=0;
        if (zemap.containsKey("int " + storage) && zemap.containsKey("int " + num1) && zemap.containsKey("int " + num2)) {
                        sum = zemap.get("int " + storage);
                        num = zemap.get("int " + num1);
                        numdos = zemap.get("int " + num2);
                        sum = num + numdos;
                        return sum;
                    } else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + num1))) {
                         sum = zemap.get("int " + storage);
                         num = zemap.get("int " + num1);
                         numdos =  Integer.valueOf(num2);
                         sum = num + numdos;

                         return sum;
                    } else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + num2))) {
                        sum = zemap.get("int " + storage);
                        num = Integer.valueOf(num1);
                        numdos = zemap.get("int " + num2);

                        sum=num+numdos;

                        return sum;

                    } else if (zemap.containsKey("int " + storage)) {
                        sum = zemap.get("int " + storage);
                        num= Integer.valueOf(num1);
                        numdos = Integer.valueOf(num2);
                        sum=num+numdos;
                        return sum;
                    }

                return sum;
    }
    public int subtract(String storage, String num1, String num2)
    {
        int difference=0;
        int num=0;
        int numdos=0;
        if (zemap.containsKey("int " + storage) && zemap.containsKey("int " + num1) && zemap.containsKey("int " + num2)) {
            difference = zemap.get("int " + storage);
            num = zemap.get("int " + num1);
            numdos = zemap.get("int " + num2);
            difference = num - numdos;
            return difference;
        } else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + num1))) {
            difference = zemap.get("int " + storage);
            num = zemap.get("int " + num1);
            numdos =  Integer.valueOf(num2);
            difference = num - numdos;

            return difference;
        } else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + num2))) {
            difference = zemap.get("int " + storage);
            num = Integer.valueOf(num1);
            numdos = zemap.get("int " + num2);

            difference=num-numdos;

            return difference;

        } else if (zemap.containsKey("int " + storage)) {
            difference = zemap.get("int " + storage);
            num= Integer.valueOf(num1);
            numdos = Integer.valueOf(num2);
            difference=num-numdos;
            return difference;
        }

        return difference;
    }
    public int multiply(String storage, String num1, String num2)
    {
        int product=0;
        int num=0;
        int numdos=0;
        if (zemap.containsKey("int " + storage) && zemap.containsKey("int " + num1) && zemap.containsKey("int " + num2)) {
            product = zemap.get("int " + storage);
            num = zemap.get("int " + num1);
            numdos = zemap.get("int " + num2);
            product = num * numdos;
            return product;
        } else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + num1))) {
            product = zemap.get("int " + storage);
            num = zemap.get("int " + num1);
            numdos =  Integer.valueOf(num2);
            product = num * numdos;

            return product;
        } else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + num2))) {
            product = zemap.get("int " + storage);
            num = Integer.valueOf(num1);
            numdos = zemap.get("int " + num2);

            product=num*numdos;

            return product;

        } else if (zemap.containsKey("int " + storage)) {
            product = zemap.get("int " + storage);
            num= Integer.valueOf(num1);
            numdos = Integer.valueOf(num2);
            product=num*numdos;
            return product;
        }

        return product;
    }
    public int division(String storage, String num1, String num2)
    {
        int quotient=0;
        int num=0;
        int numdos=0;
        if (zemap.containsKey("int " + storage) && zemap.containsKey("int " + num1) && zemap.containsKey("int " + num2)) {
            quotient = zemap.get("int " + storage);
            num = zemap.get("int " + num1);
            numdos = zemap.get("int " + num2);
            quotient = num / numdos;
            return quotient;
        } else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + num1))) {
            quotient = zemap.get("int " + storage);
            num = zemap.get("int " + num1);
            numdos =  Integer.valueOf(num2);
            quotient = num / numdos;

            return quotient;
        } else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + num2))) {
            quotient= zemap.get("int " + storage);
            num = Integer.valueOf(num1);
            numdos = zemap.get("int " + num2);

            quotient=num/numdos;

            return quotient;

        } else if (zemap.containsKey("int " + storage)) {
            quotient = zemap.get("int " + storage);
            num= Integer.valueOf(num1);
            numdos = Integer.valueOf(num2);
            quotient=num/numdos;
            return quotient;
        }

        return quotient;
    }
    public int modulusop(String storage, String num1, String num2)
    {
        int modulus=0;
        int num=0;
        int numdos=0;
        if (zemap.containsKey("int " + storage) && zemap.containsKey("int " + num1) && zemap.containsKey("int " + num2)) {
            modulus = zemap.get("int " + storage);
            num = zemap.get("int " + num1);
            numdos = zemap.get("int " + num2);
            modulus = num % numdos;
            return modulus;
        } else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + num1))) {
            modulus = zemap.get("int " + storage);
            num = zemap.get("int " + num1);
            numdos =  Integer.valueOf(num2);
            modulus = num % numdos;
            return modulus;
        } else if ((zemap.containsKey("int " + storage) && zemap.containsKey("int " + num2))) {
            modulus= zemap.get("int " + storage);
            num = Integer.valueOf(num1);
            numdos = zemap.get("int " + num2);
            modulus=num%numdos;
            return modulus;

        } else if (zemap.containsKey("int " + storage)) {
            modulus = zemap.get("int " + storage);
            num= Integer.valueOf(num1);
            numdos = Integer.valueOf(num2);
            modulus=num%numdos;
            return modulus;
        }

        return modulus;
    }
    public void equalsop(String storage, String valuespot)
    {
        int boolval= 0;
        if (zemap.containsKey("int " + storage) && zemap.containsKey("int " + valuespot)) {
            zemap.put("int " + storage,zemap.get("int " + valuespot));
        } else if (zemap.containsKey("bool " + storage) && zemap.containsKey("bool " + valuespot)) {
            zemap.put("bool " + storage, zemap.get("bool " + valuespot));
        } else if (zemap.containsKey("int " + storage)) {
            zemap.put("int " + storage, Integer.valueOf(valuespot));
        }
        /*else if(zemap.containsKey("bool "+storage))
        {
            if(valuespot.equals("true"))
            {
                boolval=1;
                zemap.put("bool "+storage,boolval);
            }
            else
                boolval=0;
            zemap.put("bool "+storage,boolval);
        }*/
    }

}
