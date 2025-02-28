#include "utils.h"

bool between( double val, double lower, double upper )
{
    return val >= lower && val <= upper;
}

double sqr( double val )
{
    return val*val;
}
