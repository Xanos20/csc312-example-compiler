%{ /* -*- C++ -*- */
# include <cerrno>
# include <climits>
# include <cstdlib>
# include <string>
# include "parser_driver.h"
# include "parser.yy.hpp"

// Work around an incompatibility in flex (at least versions
// 2.5.31 through 2.5.33): it generates code that does
// not conform to C89.  See Debian bug 333231
// <http://bugs.debian.org/cgi-bin/bugreport.cgi?bug=333231>.
# undef yywrap
# define yywrap() 1
%}

%option noyywrap nounput batch debug noinput

id    [a-zA-Z][a-zA-Z_0-9]*
int   [0-9]+
blank [ \t]

%%

{blank}+    { }
[\n]+       { }

"+"         { return yy::parser::make_PLUS(); }
"("         { return yy::parser::make_LPAREN(); }
")"         { return yy::parser::make_RPAREN(); }

{int} {
    errno = 0;
    long n = strtol (yytext, NULL, 10);
    if (! (INT_MIN <= n && n <= INT_MAX && errno != ERANGE)) {
        driver.error ("integer is out of range");
    }
    return yy::parser::make_INT(n);
}
.          { driver.error ("invalid character"); }
<<EOF>>    { return yy::parser::make_END(); }
%%

void parser_driver::scan_begin () {
    yy_flex_debug = 0;
    if (file.empty () || file == "-") {
        yyin = stdin;
    } else if (!(yyin = fopen (file.c_str (), "r"))) {
        error ("cannot open " + file + ": " + strerror(errno));
        exit (EXIT_FAILURE);
    }
}
void parser_driver::scan_end () {
    fclose (yyin);
}
