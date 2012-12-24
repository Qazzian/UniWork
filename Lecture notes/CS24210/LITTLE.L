
letter	[A-Za-z]
digit	[0-9]

%%


":="		{return ASSIGN;}
"+="		{return ADDTO;}

"<"		{return LT;}

[;]		{return SEM;}

"if" 		{return IF;}
"then"		{return THEN;}
"else" 		{return ELSE;}
"endif" 	{return ENDIF;} 

"while" 	{return WHILE;}
"do" 		{return DO;}
"endwhile"	{return ENDWHILE;}

[$]		{return END;}

[+-]?{digit}+	{sscanf(yytext,"%d",&(yylval.value)); 
		return NUMBER;}

{letter}({letter}|{digit})* {yylval.value = installId(); 
		return ID;}

%%

#include "installId.c"

/* installId checks the symbol table to see if the identifier
   just scanned is already there.  If not, it puts it into
   the symbol table.  It returns the index of the identifier
   it has found or entered in the symbol table.
*/
int installId(void) {
	body_of_installId
}

