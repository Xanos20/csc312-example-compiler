#include <memory>
#include "lang.h"

/***** ELit *******************************************************************/

ELit::ELit(int _value) : value(_value) { }

int ELit::eval() { return value; }

/***** EPlus ******************************************************************/

EPlus::EPlus(shared_ptr<Exp> _e1, shared_ptr<Exp> _e2) : e1(_e1), e2(_e2) { }

int EPlus::eval() { return e1->eval() + e2->eval(); }
