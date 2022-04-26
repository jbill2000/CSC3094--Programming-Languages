#include "function.h"

int op(vector<unsigned int> * theVec)
{
	//you may only modify the code in this fuction; you may not inline assembly instructions as they are most likely not cross compatible. 
	//what i have placed here is a copy of the unoptimzied function
	for (unsigned int i = 0; i < theVec->size(); i++)
	{
		theVec->at(i) = theVec->at(i) * 2;
	}

	for (unsigned int i = 0; i < theVec->size(); i++)
	{

		if (theVec->at(i) > 4095)
		{
			int amountToRemove = theVec->at(i) / 4096;
			amountToRemove = amountToRemove * 4096;
			theVec->at(i) = theVec->at(i) - amountToRemove;
		}
	}

	for (unsigned int i = 0; i < theVec->size(); i++)
	{
		theVec->at(i) = theVec->at(i) + 1;
	}

	for (unsigned int i = 1; i < theVec->size() - 2; i += 2)
	{
		theVec->at(i) = theVec->at(i) + theVec->at(i - 1);
	}
	for (unsigned int i = 0; i < theVec->size(); i++)
	{
		if (theVec->at(i) % 2 == 0)
			theVec->at(i) = theVec->at(i) + 1;
	}
	for (unsigned int i = 0; i < theVec->size(); i++)
	{
		theVec->at(i) = theVec->at(i) + 1;
	}

	int result = 0;
	for (unsigned int i = 0; i < theVec->size(); i++)
	{
		result += theVec->at(i);
	}
	return result;

}