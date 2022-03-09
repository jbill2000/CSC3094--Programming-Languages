#include "Parser.h"

//prints the error message and sets that there was an error. We use errorDetected elsewhere in the program
void Parser::printError(string code, string message, int line)
{
	cout << code << " on line : " << line << " " << message << ". Recieved " <<  scan->toString(scan->currentTokenType()) <<" \"" << scan->currentTokenString()<<"\"."  << endl;
	errorDetected = true;
}

Parser::Parser(string fname)
{
	scan = new Scanner(fname);
}

bool Parser::getErrorDetected()
{
	return errorDetected;
}

ASTProgram* Parser::parse()
{
	rdpProgram();
	return program;
}

//read in the program.
//rdp rule for program: <function> { <function> } 
void Parser::rdpProgram()
{
	//create the program AST node
	program = new ASTProgram();

	//verify is IDEN since it must start with an IDEN. Then call the RDP rule.
	if (scan->currentTokenType() == IDEN)
	{
		program->addFunction(rdpFunction());
	}
	else
	{
		printError("E1", "Expecting IDEN at program beginning", scan->getLine());
	}

	//while we are not at the end of the file, there must be another function. Read it in & save it!!
	while (scan->currentTokenType() != NONE && !errorDetected)
	{
		program->addFunction(rdpFunction());
	}
}

//this function reads in the functions in our language.
//RDP rule: <TypeList> IDEN LPAREN [<param>] RPAREN LBRACE RBRACE
ASTFunction* Parser::rdpFunction()
{
	ASTFunction* theFunc = NULL;
	string rtype;

	//read in the typelist first since that is the first thing.
	ASTNode * typeList = rdpTypeList();
	
	//verify the name is an IDEN. Read it in and create the function
	if (scan->currentTokenType() == IDEN && !errorDetected)
	{
		theFunc = new ASTFunction(scan->currentTokenString(),typeList);
		scan->advance();
	}
	else if (!errorDetected)
	{
		printError("E2b", "Expecting IDEN after return type", scan->getLine());
	}

	//verify Lparent for start of params
	if (scan->currentTokenType() == LPAREN && !errorDetected)
	{
		scan->advance();
	}
	else if (!errorDetected)
	{
		printError("E3", "Expecting ( after IDEN", scan->getLine());
	}

	//optional list. Only if starts with IDEN. Otherwise, we assume there is no param list
	if (scan->currentTokenType() == IDEN && !errorDetected)
	{
		theFunc->addParams(rdpParam());
	}

	//at the end of the params should be a RPAREN. Read.
	if (scan->currentTokenType() == RPAREN && !errorDetected)
	{
		scan->advance();
	}
	else if (!errorDetected)
	{
		printError("E4", "Expecting ) to close parameters", scan->getLine());
	}

	//should be an LBRACE after the parenthesis.
	if (scan->currentTokenType() == LBRACE && !errorDetected)
	{

		scan->advance();
	}
	else if (!errorDetected)
	{
		printError("E5", "Expecting { ", scan->getLine());
	}

	//statements goes here!
      rdpStatements();


	//Comment this out if you want to get back to printing normally.
    /*if (scan->currentTokenType() == IDEN && !errorDetected)
    {
        rdpStatements();
    }
    else if (errorDetected)
    {
        printError("E5a", "Statement not found", scan->getLine());
    }*/
	//functions end with an RBRACE
	if (scan->currentTokenType() == RBRACE && !errorDetected)
	{
		scan->advance();
	}
	else if (!errorDetected)
	{
		printError("E6", "Expecting } ", scan->getLine());
	}

	//sends the function node back to the RDP program call.
	return theFunc;
}

//The paramters are essentially the parameters for a function
//RDP rule for reading <param> => IDEN IDEN { COMMA IDEN IDEN}
ASTParam* Parser::rdpParam()
{
	//create the node.
	ASTParam* param = new ASTParam();

	string type;
	string name;


	//verify is IDEN & read in (type)
	if (scan->currentTokenType() == IDEN && !errorDetected)
	{
		type = scan->currentTokenString();
		scan->advance();
	}
	else if(!errorDetected)
	{
		printError("E7", "Expecting IDEN for function parameter type ", scan->getLine());
	}

	//verify is Iden and read in (name)
	if (scan->currentTokenType() == IDEN && !errorDetected)
	{
		name = scan->currentTokenString();
		scan->advance();
	}
	else if (!errorDetected)
	{
		printError("E8", "Expecting IDEN for function parameter name ", scan->getLine());
	}
	
	//add this combination to the param list
	param->addParam(name, type);

	//we use the comma to indicate we have another param
	while (scan->currentTokenType() == COMMA && !errorDetected)
	{
		scan->advance(); //read the comma but don't do anything with it

		//verify is IDEN & read in (type)
		if (scan->currentTokenType() == IDEN && !errorDetected)
		{
			type = scan->currentTokenString();
			scan->advance();
		}
		else if (!errorDetected)
		{
			printError("E7", "Expecting IDEN for function parameter type ", scan->getLine());
		}

		//verify is IDEN & read in (name)
		if (scan->currentTokenType() == IDEN && !errorDetected)
		{
			name = scan->currentTokenString();
			scan->advance();
		}
		else if (!errorDetected)
		{
			printError("E8", "Expecting IDEN for function parameter name ", scan->getLine());
		}
		
		//add this pair of parameters to the list
		param->addParam(name, type);
	}
	
	return param;
}

//RDP TypeList function. 
//Rule is     <TypeList> => IDEN {, IDEN}
ASTNode * Parser::rdpTypeList()
{
	vector<string> types;

	//reads the first IDEN if no error has been detected.
	if (scan->currentTokenType() == IDEN && !errorDetected)
	{
		types.push_back(scan->currentTokenString()); //keeps track of the lexme.
		scan->advance(); // advances the scanner to the next thing
	}
	else if(!errorDetected) //only print out an error if the error is not detected previously. The error detected is set in printError.
	{
		printError("E9", "Expecting IDEN for function parameter type ", scan->getLine()); //the code is really for you to know which error is throwing the message.
	}


	//Keep looping while we find a COMMA. This is because the COMMA is the first part of the {} rule and indicates if we have another.
	while(scan->currentTokenType() == COMMA && !errorDetected)
	{
		scan->advance(); //advancing if we get a comma since we don't need to store it.

		//read and save the IDEN if no error is detected.
		if(scan->currentTokenType() == IDEN && !errorDetected)
		{
			types.push_back(scan->currentTokenString());
			scan->advance();
		}
		else if(! errorDetected)
		{
			printError("E10", "Expecting IDEN after COMMA in return type ",scan->getLine());
		}
	}

	//return the new tree node (which is a ASTTypeList) to the calling Function. In this case, it should be rdpFunction.
	return new ASTTypeList(types);
}
//Is AST STatements right here?
ASTStatements * Parser::rdpStatements()
{

    //Keep looping while we find a COMMA. This is because the COMMA is the first part of the {} rule and indicates if we have another.
    /*while(scan->currentTokenType() !=NONE && !errorDetected)
    {
        statements.push_back(scan->currentTokenString());
        scan->advance();
        rdpStatement();
    }*/
    ASTStatements* thestatements= new ASTStatements();
    while(scan->currentTokenType()!=RBRACE && !errorDetected)
    {
        thestatements->addStatements(rdpStatement());
    }
    //return the new tree node (which is a ASTTypeList) to the calling Function. In this case, it should be rdpFunction.
    //return new ASTStatements(statements);
    return thestatements;
}
ASTStatement * Parser::rdpStatement()
{
    ASTStatement* statement= new ASTStatement();

    if(scan->currentTokenType() == IDEN && !errorDetected && scan->currentTokenString()=="if")
    {
        scan->advance();
        statement->addIf(rdpIf());
        cout<<"Returned from if"<<"\n";
        //statement.push_back(scan->currentTokenString());
    }
    //For
    else if(scan->currentTokenType() == IDEN && !errorDetected && scan->currentTokenString()=="for")
    {
        scan->advance();
        statement->addFor(rdpFor());
    }
    //Declaration
    else if (scan->currentTokenType() == IDEN && scan->nextTokenType()==IDEN && scan->currentTokenString()!="return" &&!errorDetected)
    {
        //scan->advance();

        if(scan->nextTokenType()==IDEN && !errorDetected) {
            //Its past the double IDENS
            statement->addDeclaration(rdpDeclaration());
        }
        //statement.push_back(scan->currentTokenString());
    }
    //Assign
    else if (scan->currentTokenType() == IDEN && scan->nextTokenType()==EQL && !errorDetected)
    {
        //scan->advance();
       // cout<<"Current token STring is "<<scan->currentTokenString() << "\n";
        //cout<<"Next token string is"<< scan->nextTokenString() << "\n";
        //Advances to check if its a EQL
        //If stuff hits fan, remove next with Current
        if(scan->nextTokenType()== EQL && !errorDetected) {
            //advances again to the thing after the semi colon
            //scan->advance();
            statement->addAssign(rdpAssign());
        }
        //statement.push_back(scan->currentTokenString());
    }
        //BlockStatement
    else if(scan->currentTokenType() == LBRACE && !errorDetected)
    {
        //add that brace to something

        scan->advance();
        statement->addBlockStatement(rdpBlockStatement());
        /*if(scan->currentTokenType()==RBRACE && !errorDetected)
        {
            scan->advance();
        }*/
        //statement.push_back(scan->currentTokenString());
    }//Function Call
    else if(scan->currentTokenType()==IDEN && scan->nextTokenType()==LPAREN && !errorDetected)
    {
        cout << "Function Call" << "\n";
        scan->advance();
        if(scan->currentTokenType()==LPAREN && !errorDetected)
        {
            statement->addFunctionCall(rdpFunctioncall());
            if(scan->currentTokenType()==EOL && !errorDetected)
            {
                scan->advance();
                //Add EOL To something?
            }
        }


    }
    //Return
    else if(scan->currentTokenType()==IDEN && scan->currentTokenString()=="return" && !errorDetected)
    {
        scan->advance();
       statement->addReturn(rdpReturn());
    }
        //FunctioncallMult return
    else if(scan->currentTokenType()==COMMA && !errorDetected)
    {
        // scan->advance();
        if(scan->nextTokenType()==IDEN && !errorDetected)
        {
            scan->advance();
            statement->addFunctionCallStatementMultipleReturn(rdpFunctioncallStatementMultipleReturn());
        }
    }
    else if(!errorDetected)
    {
        printError("E11", "Expecting if, for, declaration, assign, blockstatement, functioncall, or blockstatement", scan->getLine());
    }
    cout << "Leaving Statement"<< "\n";
    return statement;
}
ASTif* Parser::rdpIf() {

    if(scan->currentTokenType()==LPAREN && !errorDetected) {
       cout << "Inside the If Parentheses\n";
        scan->advance();
        cout << "Token after advancing inside IF is " << scan->currentTokenString() << "\n";
        rdpBooleanExprA();
        cout<< "\n";
        cout<< scan->currentTokenString() << " \n";
        if(scan->currentTokenType()==RPAREN && !errorDetected)
        {
            scan->advance();
            rdpStatement();
        }
    }
    else if(!errorDetected)
    {
        printError("E19", "Expecting if  ", scan->getLine());
    }
    return nullptr;
}
ASTfor* Parser::rdpFor() {

    if(scan->currentTokenType()==LPAREN && !errorDetected)
    {
        cout<<"Inside the For Parentheses"<<"\n";
        scan->advance();
        rdpDeclaration();
        rdpBooleanExprA();

    }
    else if(!errorDetected)
    {
        printError("E21", "Expecting LPAREN for the For   ", scan->getLine());
    }
    if(scan->currentTokenType()==EOL && !errorDetected)
    {
        scan->advance();
        rdpAssign();
        if(scan->currentTokenType()==RPAREN && !errorDetected)
        {
            cout << "Left Assign in For" << "\n";
            scan->advance();
            cout << "After advancing in FOr, Current token type is "<<scan->currentTokenString() << "\n";
            rdpStatement();
        }
    }
    else if(!errorDetected)
    {
        printError("E22", "Expecting EOL  ", scan->getLine());
    }

    //Return something
    return nullptr;
}
ASTdeclaration* Parser::rdpDeclaration() {
    //Store the var TYPE

    scan->advance();
    cout<<" Token after dec type is "<<scan->currentTokenString() << "\n";
    //Store the var name

    //THis is = or ;
    scan->advance();
    //so it is past the two idens

    //scan->advance();
    //Instantiating
    if(scan->currentTokenType()==EQL && !errorDetected)
    {
        scan->advance();
        cout<<"Token after = in dec is "<<scan->currentTokenString() << "\n";
        rdpBooleanExprA();
        cout<<"semi colon at end came back" << "\n";
        //Add the semi colon to storage

        scan->advance();

    }
    //Declaring
    else if(scan->currentTokenType()==EOL && !errorDetected)
    {
        scan->advance();
        cout<<"Token after Semicolon in dec is "<<scan->currentTokenString() << "\n";
        //store the declaration
    }
    else if(!errorDetected)
    {
        printError("E24", "Expecting EQL OR EOL  ", scan->getLine());
    }

    return nullptr;
}
ASTassign* Parser::rdpAssign() {

    cout << "Made it to Assign" << "\n";
    if(scan->currentTokenType()== IDEN && !errorDetected)
    {
        scan->advance();
        if(scan->currentTokenType()==EQL && !errorDetected)
        {
            scan->advance();
        }
    }
    //advancing after storing the eql
    rdpBooleanExprA();
    //Should be checking for an EOL
    if(scan->currentTokenType()==EOL && !errorDetected)
    {
        scan->advance();
    }
    else if(!errorDetected)
    {
        printError("E25", "Expecting EOL after assign ", scan->getLine());
    }
    cout<< "Leaving assign with " << scan->currentTokenString() << "\n";
    return nullptr;
}
ASTblockstatement* Parser::rdpBlockStatement() {

    rdpStatements();
    //Return from statements, check R brace. Move on
    if(scan->currentTokenType()==RBRACE)
    {
        scan->advance();
    }
    else if(!errorDetected)
    {
        printError("E26", "Expecting RBRACE at end of block statement  ", scan->getLine());
    }
    return nullptr;
}
ASTreturn* Parser::rdpReturn() {

    cout<<"Hit Return";




    if(scan->currentTokenType()==EOL && !errorDetected)
    {
        scan->advance();
        //Return something here
    }
    else if(scan->currentTokenType()==IDEN && !errorDetected)
    {

        rdpBooleanExprA();
        //Do somehting wiht EOL?

    }
    else if(!errorDetected)
    {
        printError("E27", "Expecting EOL ", scan->getLine());
    }
    //if there is a comma
    while(scan->currentTokenType()==COMMA && !errorDetected)
    {
        scan->advance();
        //No need to store the commas

        //Checks to see if its an iden or constant after the comma
        if(scan->currentTokenType()==CONSTANT && !errorDetected)
        {
            rdpBooleanExprA();
        }
        else if(scan->currentTokenType()==IDEN && !errorDetected)
        {
            rdpBooleanExprA();
        }
    }

    //If just eol do this
    if(scan->currentTokenType()==EOL && !errorDetected)
    {
        scan->advance();
    }
    return nullptr;
}
ASTArgs* Parser::rdpArgs()
{
    rdpBooleanExprA();
    while(scan->currentTokenType()==COMMA && !errorDetected)
    {
        scan->advance(); //Do soemthing with the comma
        rdpBooleanExprA();
    }
    if(!errorDetected)
    {
        printError("E28", "Expecting Args ", scan->getLine());
    }
    return nullptr;
}
ASTfunctioncallstatementmultiplereturn* Parser::rdpFunctioncallStatementMultipleReturn() {
    while(scan->currentTokenType()==COMMA && !errorDetected)
    {
        scan->advance(); //Do soemthing with the comma
        if(scan->currentTokenType()==COMMA && !errorDetected)
        {
            //Store comma to use later

            scan->advance();
            if(scan->currentTokenType()==IDEN && !errorDetected)
            {
                scan->advance();
                //store the iden
            }
        }
        else if(!errorDetected)
        {
            printError("E29", "Expecting multfunctionreturn ", scan->getLine());
        }
    }
    if(scan->currentTokenType()==EQL && !errorDetected)
    {
        rdpFunctioncall();
        scan->advance();
        if(scan->currentTokenType()== EOL && !errorDetected)
        {
            scan->advance();

            //Store EOL
        }
        else if(!errorDetected)
        {
            printError("E30", "Expecting EQL or EOL", scan->getLine());
        }
    }
    return nullptr;
}
ASTfunctioncall* Parser::rdpFunctioncall() {

    if(scan->currentTokenType()== LPAREN && !errorDetected)
    {
        scan->advance();
        if(scan->currentTokenType()==IDEN && !errorDetected) {
            rdpArgs();
        }

        if(scan->currentTokenType()==RPAREN && !errorDetected)
        {
            scan->advance();
        }
        else if(!errorDetected)
        {
            printError("Enumberhere2", "Needs RParen in functioncal",scan->getLine());
        }

    }
    else if(!errorDetected)
    {
        printError("Enumberhere", "Needs LParen in functioncal",scan->getLine());
    }

    return nullptr;
}
ASTbooleanexprA* Parser::rdpBooleanExprA() {
    cout<< "Made it to bool A"<<"\n";
    //scan->advance();
    //cout<< "Token after advancing inside Bool A  is " << scan->currentTokenString()<<"\n";
    rdpBooleanExprB();
    //After coming back from RDP Bool B
    while((scan->currentTokenType()==AND || scan->currentTokenType()==OR) && !errorDetected)
    {
        scan->advance();
        rdpBooleanExprB();

        /*if(scan->currentTokenType()==AND && !errorDetected)
        {
            scan->advance();
            if(scan->currentTokenType()==IDEN && !errorDetected) {

                rdpBooleanExprB();
            }
        }
        else if(scan->currentTokenType()==OR && !errorDetected)
        {
            scan->advance();
            if(scan->currentTokenType()==IDEN && !errorDetected) {
                rdpBooleanExprB();
            }
        }*/
    }
    cout << "Leave Bool A" << "\n";
    return nullptr;
}
ASTbooleanexprB* Parser::rdpBooleanExprB() {
    cout<< "Made it to bool B"<<"\n";
    cout<< "Token Arriving at Bool B is " << scan->currentTokenString() <<"\n";
    //scan->advance();
    rdpTerm();
    cout<<"Returned from term with token "<<scan->currentTokenString()<<"\n";
    /*if(scan->currentTokenType()==IDEN &&!errorDetected)
    {
        //scan->advance();
        rdpTerm();

    }
    else if(scan->currentTokenType()==CONSTANT && !errorDetected)
    {
       // scan->advance();
        rdpTerm();
    }
    else if(scan->currentTokenType()==LPAREN && ! errorDetected)
    {
        //scan->advance();
        rdpTerm();
    }
    else if(!errorDetected)
    {
        printError("ENEW","Expecting IDEN CONST OR LPAREN",scan->getLine());
    }*/

    while(scan->currentTokenType()==EE || scan->currentTokenType()==GR || scan->currentTokenType()==GRE && !errorDetected)
    {
        if(scan->currentTokenType()==EE && !errorDetected)
        {
            //scan->advance();
            cout<<"Token type after advancing in EE is "<<scan->currentTokenString()<<"\n";
            //Store EE Somehow before advancing


            scan->advance();

            rdpTerm();
        }
        else if(scan->currentTokenType()==GR && !errorDetected)
        {
            //scan->advance();
            scan->advance();
            rdpTerm();
        }
        else if(scan->currentTokenType()==GRE && !errorDetected)
        {
            //scan->advance();
            scan->advance();
            rdpTerm();
        }
        else if(!errorDetected)
        {
            printError("E33","Expecting GR or GRE or EE",scan->getLine());
        }
        //scan->advance();
    }
    cout <<"leaving bool b " <<"\n";
return nullptr;
}
ASTterm* Parser::rdpTerm() {
   cout<< "In term"<< "\n";
    rdpExpr();
    cout<< "Coming back from expr, token is "<<scan->currentTokenString() << "\n";
    //Comment the following if blocks out if getting weird errors.
    /*if(scan->currentTokenType()==IDEN &&!errorDetected)
    {
        //scan->advance();
        rdpExpr();

    }
    else if(scan->currentTokenType()==CONSTANT && !errorDetected)
    {
        //scan->advance();
        rdpExpr();
    }
    else if(scan->currentTokenType()==LPAREN && ! errorDetected)
    {
        //scan->advance();
        rdpExpr();
    }
    else if(!errorDetected)
    {
        printError("ENEW2","Expecting IDEN CONST OR LPAREN",scan->getLine());
    }*/
    while(scan->currentTokenType()==PLUS|| scan->currentTokenType()==MINUS && !errorDetected)
    {

           cout<<"Token inside while at start, inside term is "<<scan->currentTokenString() << "\n";
        scan->advance();
            cout<< "After advancing in term, current token is "<<scan->currentTokenString() <<"\n";
            //scan->advance();
        //cout<< "After advancing  twice in term, current token is "<<scan->currentTokenString() <<"\n";
             //This is debug to see if it recognizes a constant which it does
             //if(scan->currentTokenType()==CONSTANT && !errorDetected) {

                 rdpExpr();
        cout<< "\n";
                 //scan->advance();
            // }
             //Errors on its return

        cout<<"Leaving term, token is "<<scan->currentTokenString();
        cout<< "\n";
        }
    cout << "Leaving Term";
    cout<< "\n";
    return nullptr;
}
ASTexpr* Parser::rdpExpr() {
    cout<< "In expr"<< "\n";
    rdpPow();
    cout << "Coming back from pow, token is " << scan->currentTokenString() << "\n";
    //If weird errors comment out the following code block
    /*if(scan->currentTokenType()==IDEN &&!errorDetected)
    {
        //scan->advance();
        rdpPow();

    }
    else if(scan->currentTokenType()==CONSTANT && !errorDetected)
    {
        //scan->advance();
        rdpPow();
    }
    else if(scan->currentTokenType()==LPAREN && ! errorDetected)
    {
        //scan->advance();
        rdpPow();
    }
    else if(!errorDetected)
    {
        printError("ENEW3","Expecting IDEN CONST OR LPAREN",scan->getLine());
    }*/
    while((scan->currentTokenType()==MULTIPLY || scan->currentTokenType()== DIVIDE) && !errorDetected)
    {
        scan->advance();
        rdpPow();

        /*if(!errorDetected)
        {
            printError("E35","Expecting Multiply or Divide",scan->getLine());
        }*/
    }
    cout << "Leaving EXPR"<< "\n";
    return nullptr;
}
ASTpow* Parser::rdpPow() {
    cout<< "In pow"<< "\n";
    rdpFactor();
    cout<<"Coming back from factor, Token is "<<scan->currentTokenString() << "\n";
    //cout<<"Test";
    //If weird errors comment out this code block
    /*if(scan->currentTokenType()==IDEN &&!errorDetected)
    {
        //scan->advance();
        rdpFactor();

    }
    else if(scan->currentTokenType()==CONSTANT && !errorDetected)
    {
        //scan->advance();
        rdpFactor();
    }
    else if(scan->currentTokenType()==LPAREN && ! errorDetected)
    {
        //scan->advance();
        rdpFactor();
    }
    else if(!errorDetected)
    {
        printError("ENEW4","Expecting IDEN CONST OR LPAREN",scan->getLine());
    }*/

    while(scan->currentTokenType()==POW && !errorDetected)
    {
       cout<<"Goes inside pow loop"<< "\n";
        scan->advance();
       rdpFactor();
        cout<< "\n";

        /*if(!errorDetected)
        {
            printError("E36","Expecting POW",scan->getLine());
        }*/
    }
    cout << "Leaving POW"<< "\n";
    return nullptr;
}
ASTfactor* Parser::rdpFactor() {
    cout<< "In factor"<< "\n";
    if(scan->currentTokenType()==IDEN && !errorDetected)
    {
        rdpElement();
    }
    else if(scan->currentTokenType()== CONSTANT && !errorDetected)
    {
        rdpElement();
    }
    else if(scan->currentTokenType()==LPAREN && !errorDetected)
    {
        //Call this
       // scan->advance();
        rdpBooleanExprA();
        //Once it comes back
        scan->advance();
        if(scan->currentTokenType()==RPAREN && !errorDetected)
        {
            scan->advance();
        }
    }
    else if(!errorDetected)
    {
        printError("E37","Expecting IDEN, CONST, L or R PAREN",scan->getLine());
    }
    //scan->advance();
    /*if(scan->currentTokenType()==IDEN && !errorDetected)
    {
        rdpElement();
        //scan->advance();
    }
    else if(scan->currentTokenType()==CONSTANT && !errorDetected)
    {
        rdpElement();
        //scan->advance();
    }
    else if(scan->currentTokenType()==LPAREN && !errorDetected)
    {
        rdpElement();
        //scan->advance();
    }*/

   /* if(scan->currentTokenType()!=IDEN|| scan->currentTokenType()!=CONSTANT || scan->currentTokenType()!=LPAREN && !errorDetected)
    {
        //scan->advance();
        rdpBooleanExprA();
        cout<<"After advancing in factor, the current token type is" << scan->currentTokenString()<<"\n";
    }
    else if(!errorDetected)
    {

    }*/
   cout << "Leaving factor"<< "\n";
    return nullptr;
}
ASTelement* Parser::rdpElement() {
    //ASTelement* elepointer= new ASTelement();
    vector<string> elements;
    cout<< "In element"<< "\n";
    cout <<"Current token type in element is "<<scan->currentTokenString() << "\n";
    if(scan->currentTokenType()==IDEN && !errorDetected)
    {
        elements.push_back(scan->currentTokenString());
        scan->advance();
        //Add element to something
        cout<<"Token String after advancing in element is "<<scan->currentTokenString()<< "\n";
        if(scan->currentTokenType()==LPAREN && !errorDetected)
        {
            elements.push_back(scan->currentTokenString());
            scan->advance();
            rdpFunctioncall();
        }
    }
    else if(scan->currentTokenType()==CONSTANT && !errorDetected)
    {
        cout << "Constant "<<scan->currentTokenString() << "\n";
        elements.push_back(scan->currentTokenString());
        scan->advance();
        //add constant to something
    }
    else if(!errorDetected)
    {
        printError("E38","Expecting Iden or const ",scan->getLine());
    }
//return something
    cout << "Leaving Element"<< "\n";
    return new ASTelement(elements);
}