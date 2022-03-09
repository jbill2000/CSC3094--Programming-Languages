#pragma once

#include <iostream>
#include <string>
#include "Scanner.h"
#include "AST.h"
using namespace std;



class Parser
{
public:
	//constructor that reads in the file name & gets the scanner read
	Parser(string fname); 
	ASTProgram * parse();

	//function that handles errors. Once called, the errorDetected flag is set.
	void printError(string code, string message, int line);

	//prototypes for the RDP functions
	void rdpProgram();
	ASTFunction* rdpFunction();
	ASTParam* rdpParam();
	ASTNode * rdpTypeList(); // the rdpTypeList prototype. For <TypeList> => IDEN {, IDEN}
	ASTStatements* rdpStatements(); //Statements prototype
	ASTStatement* rdpStatement();
	ASTfunctioncall* rdpFunctioncall();
	ASTpow* rdpPow();
	ASTreturn* rdpReturn();
	ASTterm* rdpTerm();
	ASTfactor* rdpFactor();
	ASTelement* rdpElement();
	ASTexpr* rdpExpr();
	ASTdeclaration* rdpDeclaration();
	ASTbooleanexprA* rdpBooleanExprA();
	ASTblockstatement* rdpBlockStatement();
	ASTassign* rdpAssign();
	ASTArgs* rdpArgs();
	ASTbooleanexprB* rdpBooleanExprB();
	ASTfor* rdpFor();
	ASTfunctioncallstatementmultiplereturn* rdpFunctioncallStatementMultipleReturn();
	ASTif* rdpIf();
	//returns if an error was detected already
	bool getErrorDetected();
private:
	bool errorDetected;
	Scanner* scan;
	ASTProgram * program;
};
