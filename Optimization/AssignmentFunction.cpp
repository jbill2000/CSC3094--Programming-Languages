#include "function.hpp"
int op(vector<unsigned int> * theVec)
{
        unsigned int vecsize = theVec->size();
        unsigned int* vec = theVec->data();
        unsigned int result = vecsize;
        unsigned int i;
        //Plus 1 loop merged in
        vec[1] = (vec[1]+ 1 << 1) & 0xFF;
        vec[0] = ((vec[0] + 1 << 1) & 0xFF) + vec[1];
    
        //addition loop added in
       // vec[0] += vec[1];
        //Modulus loops merged in
        if (vec[0] % 7 == 1)
            vec[0]++;
        //Result calcs merged in
        result += vec[1]+vec[0];
    for (unsigned int i = 2; i < vecsize; i += 2)
        {
            //Plus 1 loop merged in
            //vec[i + 1] = (vec[i + 1] + 1 << 1) & 0xFF;
            // vec[i] = ((vec[i] + 1 << 1) & 0xFF) + vec[i + 1];
             //addition loop added in
             //vec[i] += vec[i + 1];
             //Modulus loops merged in
             //Result calcs merged in


            result += (((vec[i + 1] + 1 << 1) & 0xFF)<<1) + ((vec[i] + 1
    << 1) & 0xFF);
        }
    
    return result;
}
