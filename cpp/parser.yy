%skeleton "lalr1.cc" /* -*- C++ -*- */
%require "3.0.4"
%defines
%define parser_class_name {parser}
%define api.token.constructor
%define api.value.type variant
%define parse.assert

%code requires {
#ifndef YY_NULLPTR
  #define YY_NULLPTR nullptr
#endif
#include <string>
#include <memory>
#include "lang.h"
class parser_driver;
using namespace std;
}

// The parsing context
%param { parser_driver &driver }

%define parse.trace
%define parse.error verbose

%code {
#include "parser_driver.h"
#include "lang.h"

using namespace std;
}

%define api.token.prefix {TOK_}
%token
  END     0   "eof"
  PLUS        "+"
  LPAREN      "("
  RPAREN      ")"
;

%token <int> INT "int"

%type  < shared_ptr<Exp> > exp
%parse-param {shared_ptr<Exp> *ret}

%%

%start prog;

prog:
  exp "eof"             { *ret = $1; }

exp:
  "int"                 { $$ = make_shared<ELit>($1); }
| "(" "+" exp exp ")"   { $$ = make_shared<EPlus>($3, $4); }

%%

// NOTE: Bison's error reporting simply forwards to the driver
void yy::parser::error(const std::string &m) { driver.error(m); }

