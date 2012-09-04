/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * Lillian Michalek - lmichale
 *
 * This file contains a series of functions that utilize bit patterns to
 * manipulate integers. Each function is limited to using only certain types of 
 * bit operations and a certain number of them.
 *
 * There are three main categories of functions:
 *   (1) Bit manipulation - functions that manipulate & test sets of bits
 *   (2) Two's complement arithmetic - involve two's complement representations
 *   (3) Floating point arithmetic - operations on bit-level representations of
 *		 floating point numbers.
 *
 * Some selected examples:
 *   - bang(x) - Compute !x without using !
 *   - float_f2i(f) - Given a float f, compute (int) f
 *   - howManyBits(x) - Return the minimum number of bits needed to represent x
 *						in two's complement
 *
 * All functions have a header containing a description & examples. 
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * tmin - return minimum two's complement integer
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 4
 *   Rating: 1
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
int tmin(void) 
{
   return  1 << 31; /*shift the '1' bit to get 0x80000000*/
}


/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * isAsciiDigit - return 1 if 0x30 <= x <= 0x39 (ASCII codes for characters '0' 
 * to '9')
 *   Example: isAsciiDigit(0x35) = 1.
 *            isAsciiDigit(0x3a) = 0.
 *            isAsciiDigit(0x05) = 0.
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 15
 *   Rating: 3
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
int isAsciiDigit(int x) 
{
   int sign = 1 << 31;
   int upperBound = ~(sign | 0x39); /*if > 0x39 is added, result goes negative*/
   int lowerBound = ~0x30;/*when < 0x30 is added, result is negative*/

   /*now add x and check the sign bit for each*/
   upperBound = sign & (upperBound+x) >> 31;
   lowerBound = sign & (lowerBound+1+x) >> 31; 
   /*if either result is negative, it is not in desired range*/
   return !(upperBound | lowerBound); 
}


/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * conditional - same as x ? y : z 
 *   Example: conditional(2,4,5) = 4
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 16
 *   Rating: 3
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
int conditional(int x, int y, int z) 
{
  x = !!x; /*puts x in 0 or 1 form*/
  x = ~x+1; /*x is now either all 1's or all 0's*/

  return (x & y) | (~x & z); 
}


/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * allEvenBits - return 1 if all even-numbered bits in word set to 1
 *   Examples allEvenBits(0xFFFFFFFE) = 0, allEvenBits(0x55555555) = 1
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 12
 *   Rating: 2
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
int allEvenBits(int x) 
{
   int everyBit;
   int allOdd = (0xAA << 8) | 0xAA; 
   allOdd = allOdd | (allOdd << 16); /*int with all odd bits set to 1*/
   everyBit = allOdd | x; 
   everyBit = ~everyBit; /*all 0's if x had each even bit at 1*/
   return !everyBit;
}


/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * implication - return x -> y in propositional logic - 0 for false, 1
 * for true
 *   Example: implication(1,1) = 1
 *            implication(1,0) = 0
 *   Legal ops: ! ~ ^ |
 *   Max ops: 5
 *   Rating: 2
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
int implication(int x, int y) 
{
  return y | !(x^y); /*true if y is true OR if x and y are same value*/
}


/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * isLessOrEqual - if x <= y  then return 1, else return 0 
 *   Example: isLessOrEqual(4,5) = 1.
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 24
 *   Rating: 3
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
int isLessOrEqual(int x, int y) 
{
   int negX = ~x+1;
   int addY = negX + y; /*negative if x > y*/
   int checkSign = addY >> 31 & 1; /*shifts sign bit to the right*/

   /*the above will not work for values that push the bounds of ints
     the following code checks very large/small values*/
   int leftBit = 1 << 31;
   int xLeft = leftBit & x;
   int yLeft = leftBit & y;
   int xOrd = xLeft ^ yLeft;
   xOrd = (xOrd >> 31) & 1;
 
   return (xOrd & (xLeft>>31)) | (!checkSign & !xOrd);
}


/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * bang - Compute !x without using !
 *   Examples: bang(3) = 0, bang(0) = 1
 *   Legal ops: ~ & ^ | + << >>
 *   Max ops: 12
 *   Rating: 4 
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
int bang(int x) 
{
  x = x & (~x+1); /*gives mask of least sig bit*/
  x = ~x + 1; /*sign bit is only zero if x was zero*/

  return (~(x >> 31)) & 1; /*return opposite of the sign bit*/
}


/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 *
 * howManyBits - return the minimum number of bits required to represent x in
 *             two's complement
 *  Examples: howManyBits(12) = 5
 *            howManyBits(298) = 10
 *            howManyBits(-5) = 4
 *            howManyBits(0)  = 1
 *            howManyBits(-1) = 1
 *            howManyBits(0x80000000) = 32
 *  Legal ops: ! ~ & ^ | + << >>
 *  Max ops: 90
 *  Rating: 4
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
int howManyBits(int x) 
{
   int sign = (x>>31) & 1;
   int signChain =~sign+1;
   int placeHolder = 0; /*throwaway variable for various operations*/
   int c = 2; /*counter to increment to count the bits*/
   int copy = x; /*to be used for checking if power of 2*/
   int copy2 = x; /*to be used for checking zero*/
   int tminCheck =  x ^ (1 << 31);
   tminCheck = !tminCheck;
   tminCheck = ~tminCheck+1; /*all ones if x was tmin*/

   x = (x & ~signChain) | ((~x +1) & signChain);/*now a positive value*/
   x = x + ~0;
   x = (x | x >> 1);
   x = (x | x >> 2);
   x = (x | x >> 4);
   x = (x | x >> 8);
   x = (x | x >> 16);
   x = (x + 1); /*x is rounded up to the next power of 2*/

   /*the following chunks of code follow an algorithm that
     does five different checks, incrementing the counter
     for each check so that the result is the bit position
     of the rounded up value of x*/
   placeHolder = !(x & (0xFF | 0xFF << 8));
   placeHolder = ~placeHolder+1; 
   c += (placeHolder & 16);

   placeHolder = !(x & (0xFF | 0xFF << 16));
   placeHolder = ~placeHolder + 1;
   c += (placeHolder & 8);

   placeHolder = 0x0F | 0x0F <<8;
   placeHolder = placeHolder | placeHolder <<16;
   placeHolder = !(x & placeHolder);
   placeHolder = ~placeHolder+1;  
   c += (placeHolder & 4);

   placeHolder = 0x33 | 0x33 << 8;
   placeHolder = placeHolder | placeHolder << 16;
   placeHolder = !(x & placeHolder);
   placeHolder = ~placeHolder+1;
   c += (placeHolder & 2);

   placeHolder = 0x55 | 0x55 << 8;
   placeHolder = placeHolder | placeHolder << 16;
   placeHolder = !(x & placeHolder);
   placeHolder = ~placeHolder+1;
   c += (placeHolder & 1);

   /*determines if x is 0. if so, you want to return 1.*/
   copy2 = !copy2;
   copy2 = ~copy2+1;   

   c += ~((tminCheck)&1);

   /*add one to the count if x is a power of 2*/
   copy = copy & (copy+~0);
   c += !copy;
   return (c & ~copy2) | (copy2 & 1);
}


/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * byteSwap - swaps the nth byte and the mth byte
 *  Examples: byteSwap(0x12345678, 1, 3) = 0x56341278
 *            byteSwap(0xDEADBEEF, 0, 2) = 0xDEEFBEAD
 *  You may assume that 0 <= n <= 3, 0 <= m <= 3
 *  Legal ops: ! ~ & ^ | + << >>
 *  Max ops: 25
 *  Rating: 2
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
int byteSwap(int x, int n, int m) 
{ 
   int a = 0xFF; /*a byte of ones, will be used to copy single bytes*/
 
   /*<<3 is the same as multiplying by 8
     nShift and mShift contain the number of bits you need to shift*/
   int nShift = n << 3; 
   int mShift = m << 3;

   /*shift a to the desired byte in x, & with x*/
   int nByte = (a << nShift)&x;
   int mByte = (a << mShift)&x;
   int combo = (a << nShift) | (a << mShift); /*1's in the swapping bytes*/

   /*shift swapping bytes back to the left*/
   nByte = (nByte >> nShift)&a;
   mByte = (mByte >> mShift)&a;
   /*shift swapping bytes to their new position*/
   nByte = nByte << mShift;
   mByte = mByte << nShift;
   /*store the nonchanging part of the int in "combo"*/
   combo = ~combo & x;
   
   return combo | mByte | nByte; /*combine the three parts*/
}


/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * leastBitPos - return a mask that marks the position of the
 *               least significant 1 bit. If x == 0, return 0
 *   Example: leastBitPos(96) = 0x20
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 6
 *   Rating: 2 
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
int leastBitPos(int x) 
{
   /*~x+1 will only share a bit with the rightmost 1 in x*/
   return x & (~x+1);
}


/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * logicalShift - shift x to the right by n, using a logical shift
 *   Can assume that 0 <= n <= 31
 *   Examples: logicalShift(0x87654321,4) = 0x08765432
 *   Legal ops: ~ & ^ | + << >>
 *   Max ops: 20
 *   Rating: 3 
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
int logicalShift(int x, int n) 
{  
   int arithShift = x >> n;
   int removeLeftEnd = ~0 << (32 + ~n) << 1; /*shift 1's left by 32-n*/
   removeLeftEnd = ~removeLeftEnd; /*flip bits, wanted 1s are at the right end*/

   return removeLeftEnd & arithShift; /*return right end of the arithShift value*/
}


/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * float_neg - Return bit-level equivalent of expression -f for
 *   floating point argument f.
 *   Both the argument and result are passed as unsigned int's, but
 *   they are to be interpreted as the bit-level representations of
 *   single-precision floating point values.
 *   When argument is NaN, return argument.
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 10
 *   Rating: 2
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
unsigned float_neg(unsigned uf) 
{ 
   int nanCheck = 0x000000FF << 23; /*1's in the 8 exponent bits*/
   int frac = 0x7FFFFF & uf; /*contains just the fraction value*/

   /*return argument if exp bits are all 1's and frac is not zero*/
   if((nanCheck & uf) == nanCheck && frac)
      return uf;

   /*otherwise, just flip the sign bit*/
   return uf ^ (1 << 31);
}


/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * float_f2i - Return bit-level equivalent of expression (int) f
 *   for floating point argument f.
 *   Argument is passed as unsigned int, but
 *   it is to be interpreted as the bit-level representation of a
 *   single-precision floating point value.
 *   Anything out of range (including NaN and infinity) should return
 *   0x80000000u.
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 30
 *   Rating: 4
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
int float_f2i(unsigned uf) 
{
   int exp = (uf >> 23) & 0xFF; /*8 exponent bits*/
   int frac = uf & 0x7FFFFF; /*23 fraction bits*/
   int e = exp - 127; /*amount to shift normalized values by (bias of 127)*/

   /*returns if NaN*/
   if(exp == 0x7F800000)
      return 0x80000000u;
   /*rounds down to zero if exponent is zero*/
   if(!exp)
      return 0;
   /*rounds down to zero if there are no left shifts*/
   if(e < 0)
      return 0;
   /*return if out of range for ints*/
   if(e > 30)
      return 0x80000000u;

    frac = frac | 0x800000; /*normalized, append a 1 to the left of the frac*/
    if (e >= 23)
      frac = frac << (e-23); /*shift left if shift > 23*/
    else
      frac = frac >> (23 -e); /*else we need to shift right*/

   if(( uf >> 31 ) & 1) 
      return ~frac + 1; /*return negated value if sign bit is 1*/

   return frac;
}


