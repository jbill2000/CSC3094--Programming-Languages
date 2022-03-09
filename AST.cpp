#include "AST.h"
#include <iostream>
#include <string>
using namespace std;


//these function inialize / store data in the nodes:
ASTProgram::ASTProgram()
{

}

void ASTProgram::addFunction(ASTFunction* theFunction)
{
	functions.push_back(theFunction);
}


ASTFunction::ASTFunction(string namein, ASTNode * returnTypein)
{
	name = namein;
	returnType = returnTypein;
	params = NULL;
}

void ASTFunction::addParams(ASTParam* params)
{
	this->params = params;
}


ASTParam::ASTParam()
{

}
void ASTParam::addParam(string namein, string typein)
{
	varNames.push_back(namein);
	types.push_back(typein);
}

//creates the ASTTypeList. The parameter is the set of IDENS that are the types in the list
ASTTypeList::ASTTypeList(vector<string> theStrings)
{
	theTypes = theStrings;
}
//Statements
ASTStatements::ASTStatements() {


}
//AddStatements
void ASTStatements::addStatements(ASTStatement* statementin)
{
    theStatements.push_back(statementin);
}
//Statements print
void ASTStatements::print(int depth)
{

}
//Statement
ASTStatement::ASTStatement() {


}
void ASTStatement::addIf(ASTif* ifin)
{
    this->ifNode=ifin;
}
void ASTStatement::addFor(ASTfor* forin)
{
    this->forNode=forin;
}
void ASTStatement::addDeclaration(ASTdeclaration* decin)
{
    this->decNode=decin;
}
void ASTStatement::addBlockStatement(ASTblockstatement* blockin)
{
    this->blockNode=blockin;
}
void ASTStatement::addReturn(ASTreturn* returnin)
{
    this->returnNode=returnin;
}
void ASTStatement::addFunctionCall(ASTfunctioncall* funccallin)
{
    this->functioncallNode=funccallin;
}
void ASTStatement::addFunctionCallStatementMultipleReturn(ASTfunctioncallstatementmultiplereturn* multin)
{
    this->multNode=multin;
}
void ASTStatement::addAssign(ASTassign* assignin) {

    this->assignNode=assignin;
}
void ASTStatement::print(int depth)
{

}
//Args
ASTArgs::ASTArgs()
{

}
void ASTArgs::addBoolA(ASTbooleanexprA* boolAin)
{
    theArgs.push_back(boolAin);
}
//Args Print
void ASTArgs::print(int depth)
{

}
//For
ASTfor::ASTfor(){

}
void ASTfor::addDeclaration(ASTdeclaration* decin)
{
    this->decNode=decin;
}
void ASTfor::addAssign(ASTassign* assignin)
{
    this->assignNode=assignin;
}
void ASTfor::addBoolA(ASTbooleanexprA* boolAin)
{
    this->boolExprNode=boolAin;
}
void ASTfor::addStatement(ASTStatement* statementin)
{
    this->statementNode=statementin;
}
//for print
void ASTfor::print(int depth)
{

}
//IF
ASTif::ASTif() {

}
void ASTif::addBoolA(ASTbooleanexprA* boolAin)
{
    this->booleanExprANode=boolAin;
}
void ASTif::addStatement(ASTStatement* statementin)
{
    this->statement=statementin;
}
//if print
void ASTif::print(int depth)
{

}
//Assign
ASTassign::ASTassign() {

}
void ASTassign::addBoolA(ASTbooleanexprA* boolAin)
{
    this->booleanExprANode=boolAin;
}
//Assign print
void ASTassign::print(int depth)
{

}
//return
ASTreturn::ASTreturn() {

}
void ASTreturn::addBoolA(ASTbooleanexprA* boolAin)
{
    this->booleanExprANode=boolAin;
}
//return print
void ASTreturn::print(int depth)
{

}

//Function Call
ASTfunctioncall::ASTfunctioncall() {

}
void ASTfunctioncall::addArgs(ASTArgs* argsin)
{
    this->argsNode=argsin;
}
//function call print
void ASTfunctioncall::print(int depth)
{

}

//Blockstatement
ASTblockstatement::ASTblockstatement() {

}
void ASTblockstatement::addStatements(ASTStatements* statementsin)
{
    this->statementsNode=statementsin;
}
//AST blockstatment print
void ASTblockstatement::print(int depth)
{

}

//Declaration
ASTdeclaration::ASTdeclaration() {

}
void ASTdeclaration::addBoolA(ASTbooleanexprA* boolAin)
{
    this->boolExprANode=boolAin;
}
//declaration print
void ASTdeclaration::print(int depth)
{

}
//ASTfunctioncallstatementmultreturn

ASTfunctioncallstatementmultiplereturn::ASTfunctioncallstatementmultiplereturn() {

}
void ASTfunctioncallstatementmultiplereturn::addFunctionCall(ASTfunctioncall* functioncallin) {
    multreturn.push_back(functioncallin);
}
//functioncall print
void ASTfunctioncallstatementmultiplereturn::print(int depth)
{

}
//Bool A
ASTbooleanexprA::ASTbooleanexprA() {

}
void ASTbooleanexprA::addBoolB(ASTbooleanexprB* boolbIn) {
    theBoolAvec.push_back(boolbIn);
}
//Bool A print
void ASTbooleanexprA::print(int depth)
{

}
//Bool B
ASTbooleanexprB::ASTbooleanexprB() {

}
void ASTbooleanexprB::addTerm(ASTterm* termin)
{
    theBoolBvec.push_back(termin);
}
//bool B print
void ASTbooleanexprB::print(int depth)
{

}

//Term
ASTterm::ASTterm() {

}
void ASTterm::addExpr(ASTexpr* exprin)
{
    termVec.push_back(exprin);
}
//term print
void ASTterm::print(int depth)
{

}
//Expr
ASTexpr::ASTexpr() {

}
void ASTexpr::addPow(ASTpow* powin)
{
    exprVec.push_back(powin);
}
//expr print
void ASTexpr::print(int depth)
{

}
//Factor
ASTfactor::ASTfactor() {

}
void ASTfactor::addElement(ASTelement* elementin)
{
    this->elementNode=elementin;
}
//factor print
void ASTfactor::print(int depth)
{

}
//Pow
ASTpow::ASTpow() {

}
void ASTpow::addFactor(ASTfactor* factorin)
{
    powVec.push_back(factorin);
}
//pow print
void ASTpow::print(int depth)
{

}
//Element
ASTelement::ASTelement(vector<string>elementsin)
{
    theElements=elementsin;
}
void ASTelement::addFunctionCall(ASTfunctioncall* functioncallin) {
    this->functioncallNode=functioncallin;
}
void ASTelement::print(int depth)
{
    for(int i=0;i<theElements.size();i++)
    {
        //print the type & the , if not the last thing
        cout << theElements[i]<<"";
        if(theElements.size()-1 != i)
        {
            cout << ", ";
        }
    }
}
//prints the AST Program. Simply calls print on each function with depth 0. I.e., no indents
void ASTProgram::print(int depth)
{
	for (int i = 0; i < functions.size(); i++)
	{
		functions[i]->print(0);
	}
}

//each function prints out the types, params, and the necessary () and {}. Eventually must print out the body of the function
void ASTFunction::print(int depth)
{
	returnType->print(0);
	cout <<" "<< name << "(";
	if (params != NULL)
	{
		params->print(0);
	}
	cout << ")\n{\n";

	cout << "}\n";
}

//AST Param prints out the param of the function. Notice there are no new lines since the param does not have any new lines. Only prints out the params & the commas
void ASTParam::print(int depth)
{
	for (int i = 0; i < varNames.size(); i++)
	{
		cout << types[i] << " " << varNames[i];
		if (i != varNames.size() - 1)
		{
			cout << ", ";
		}
	}
}

//This is the tree print function that is called by the function's print.
void ASTTypeList::print(int depth)
{
	//for each type in the list
	for(int i=0;i<theTypes.size();i++)
	{
		//print the type & the , if not the last thing
		cout << theTypes[i]<<"";
		if(theTypes.size()-1 != i)
		{
			cout << ", ";
		}
	}
}











