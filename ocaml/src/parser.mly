%{
open Lang
%}

%token <int> INT

%token LPAREN     (* ( *)
%token RPAREN     (* ) *)
%token PLUS       (* + *)

%token EOF

%start <Lang.exp> prog

%%

prog:
  | e=exp EOF                           { e }

exp:
  | n=INT                               { EInt n }
  | LPAREN PLUS e1=exp e2=exp RPAREN    { EAdd (e1, e2) }
