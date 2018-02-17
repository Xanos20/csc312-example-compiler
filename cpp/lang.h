#pragma once

using namespace std;

struct Exp {
    virtual int eval() = 0;
};

struct ELit : public Exp {
    int value;
    ELit(int _value);
    int eval();
};

struct EPlus : public Exp {
    shared_ptr<Exp> e1;
    shared_ptr<Exp> e2;
    EPlus(shared_ptr<Exp> _e1, shared_ptr<Exp> _e2);
    int eval();
};
