#pragma once

#include <vector>
#include <string>
using namespace std;

class ASTFunction;
class ASTNode;
class ASTProgram;
class ASTParam;
class ASTStatements;
class ASTStatement;
class ASTArgs;
class ASTfor;
class ASTif;
class ASTassign;
class ASTreturn;
class ASTfunctioncall;
class ASTblockstatement;
class ASTdeclaration;
class ASTfunctioncallstatementmultiplereturn;
class ASTbooleanexprA;
class ASTbooleanexprB;
class ASTterm;
class ASTexpr;
class ASTfactor;
class ASTpow;
class ASTelement;

// these classes are for the AST. All the classes are decendents of AST node so they can be children of each other so long as they are saved in a general ASTNode variable.
class ASTNode
{
public:
	virtual void print(int depth) = 0;

private:

};

//the ASTProgram class. For the program AST Node. Contains a list of functions
class ASTProgram : public ASTNode
{
public:
	ASTProgram();
	void addFunction(ASTFunction* theFunction);
	void print(int depth);
private:
	vector<ASTFunction*> functions;
};

//the ASTFunction class. Contains the typelist, params, name, and (eventually but not implemented here) the body of the function. You get to implement the body.
class ASTFunction : public ASTNode
{
public:
	ASTFunction(string namein, ASTNode * returnType);
	void addParams(ASTParam* theParams);
	void print(int depth);
private:
	string name;
	ASTNode * returnType;
	ASTParam* params;
};

//the ASTParam node. Contains the list of params for a specific function. the ith varName corresponds to the ith type.
class ASTParam : public ASTNode
{
public:
	ASTParam();
	void addParam(string name, string type);
	void print(int depth);
private:
	vector<string> varNames; 
	vector<string> types;
};
//the ASTTypeList Node delcaration. Inheritcs from ASTNode
class ASTTypeList : public ASTNode
{
public:
	ASTTypeList(vector<string> typesIn);
	void print(int depth);
private:
	vector<string> theTypes;
};
class ASTStatements : public ASTNode
{
public:
   ASTStatements();
   void addStatements(ASTStatement* statementin);
   void print(int depth);
private:
    ASTStatement* statementNode;
    vector<ASTStatement*> theStatements;
};
//The ASTStatement Node declaration. Inherits from ASTNode
class ASTStatement : public ASTNode
{
public:
    ASTStatement();
    void addIf(ASTif* ifin);
    void addFor(ASTfor* forin);
    void addDeclaration(ASTdeclaration* decin);
    void addBlockStatement(ASTblockstatement* blockin);
    void addReturn(ASTreturn* returnin);
    void addFunctionCall(ASTfunctioncall* funccallin);
    void addFunctionCallStatementMultipleReturn(ASTfunctioncallstatementmultiplereturn* multin);
    void addAssign(ASTassign* assignin);
    void print(int depth);
private:
    ASTif* ifNode;
    ASTfor* forNode;
    ASTdeclaration* decNode;
    ASTblockstatement* blockNode;
    ASTreturn* returnNode;
    ASTfunctioncall* functioncallNode;
    ASTassign* assignNode;
    ASTfunctioncallstatementmultiplereturn* multNode;
    vector<string> statementin;
};
//The ASTArgs Node declaration. Inherits from ASTNode
class  ASTArgs : public ASTNode
{
public:
    ASTArgs();
    void addBoolA(ASTbooleanexprA* boolAin);
    void print(int depth);
private:
    vector<ASTbooleanexprA*> theArgs;

};
//The ASTfor Node declaration. Inherits from ASTNode
class  ASTfor : public ASTNode
{
public:
    ASTfor();
    void addDeclaration(ASTdeclaration* decin);
    void addAssign(ASTassign* assignin);
    void addBoolA(ASTbooleanexprA* boolAin);
    void addStatement(ASTStatement* statementin);
    void print(int depth);
private:
    ASTdeclaration* decNode;
    ASTassign* assignNode;
    ASTbooleanexprA* boolExprNode;
    ASTStatement* statementNode;
};
//The ASTif Node declaration. Inherits from ASTNode
class ASTif : public ASTNode
{
public:
    ASTif();
    void addBoolA(ASTbooleanexprA* boolAin);
    void addStatement(ASTStatement* statementin);
    void print(int depth);
private:
    ASTbooleanexprA* booleanExprANode;
    ASTStatement* statement;
};
//The ASTassign Node declaration. Inherits from ASTNode
class ASTassign : public ASTNode
{
public:
    ASTassign();
    void addBoolA(ASTbooleanexprA* boolAin);
    void print(int depth);
private:
    ASTbooleanexprA* booleanExprANode;
};
//The ASTreturn Node declaration. Inherits from ASTNode
class ASTreturn : public ASTNode
{
public:
    ASTreturn();
    void addBoolA(ASTbooleanexprA* boolAin);
    void print(int depth);
private:
    vector<ASTbooleanexprA*> theReturn;
    ASTbooleanexprA* booleanExprANode;
};
//The ASTfunctioncall Node declaration. Inherits from ASTNode
class ASTfunctioncall : public ASTNode
{
public:
    ASTfunctioncall();
    void addArgs(ASTArgs* argsin);
    void print(int depth);
private:
    ASTArgs* argsNode;
};
//The ASTblockstatement Node declaration. Inherits from ASTNode
class ASTblockstatement : public ASTNode
{
public:
    ASTblockstatement();
    void addStatements(ASTStatements* statementsin);
    void print(int depth);
private:
    ASTStatements* statementsNode;
};
//The ASTdeclaration Node declaration. Inherits from ASTNode
class ASTdeclaration : public ASTNode
{
public:
    ASTdeclaration();
    void addBoolA(ASTbooleanexprA* boolAin);
    void print(int depth);
private:
    ASTbooleanexprA* boolExprANode;
};
//The ASTfunctioncallstatementmultiplereturn Node declaration. Inherits from ASTNode
class ASTfunctioncallstatementmultiplereturn : public ASTNode
{
public:
    ASTfunctioncallstatementmultiplereturn();
    void addFunctionCall(ASTfunctioncall* functioncallin);
    void print(int depth);
private:
vector<ASTfunctioncall*> multreturn;
};
//The ASTbooleanexprA Node declaration. Inherits from ASTNode
class ASTbooleanexprA : public ASTNode
{
public:
    ASTbooleanexprA();
    void addBoolB(ASTbooleanexprB* boolbIn);
    void print(int depth);
private:
    vector<ASTbooleanexprB*> theBoolAvec;

};
//The ASTbooleanexprB Node declaration. Inherits from ASTNode
class ASTbooleanexprB : public ASTNode
{
public:
    ASTbooleanexprB();
    void addTerm(ASTterm* termin);
    void print(int depth);
private:
    vector<ASTterm*> theBoolBvec;
};
//The ASTterm Node declaration. Inherits from ASTNode
class ASTterm : public ASTNode
{
public:
    ASTterm();
    void addExpr(ASTexpr* exprin);
    void print(int depth);
private:
    vector<ASTexpr*> termVec;
};
//The ASTexpr Node declaration. Inherits from ASTNode
class ASTexpr : public ASTNode
{
public:
    ASTexpr();
    void addPow(ASTpow* powin);
    void print(int depth);
private:
    vector<ASTpow*> exprVec;
};
//The ASTfactor Node declaration. Inherits from ASTNode
class ASTfactor : public ASTNode
{
public:
    ASTfactor();
    void addElement(ASTelement* elementin);
    void print(int depth);
private:
    ASTelement* elementNode;
};
//The ASTpow Node declaration. Inherits from ASTNode
class ASTpow : public ASTNode
{
public:
    ASTpow();
    void addFactor(ASTfactor* factorin);
    void print(int depth);
private:
    vector<ASTfactor*> powVec;
};
//The ASTelement Node declaration. Inherits from ASTNode
class ASTelement : public ASTNode
{
public:
    ASTelement(vector<string>elementin);
    void addFunctionCall(ASTfunctioncall* functioncallin);
    void print(int depth);
private:
    ASTfunctioncall* functioncallNode;
    vector<string>theElements;
};
