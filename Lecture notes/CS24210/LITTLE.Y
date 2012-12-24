%{
	#include <ctype.h>
	#include <stdio.h>
	#include <stdlib.h>
	#include "little_decls.h"
%}

%token NUMBER
%token ID
%nonassoc LT

%token ASSIGN
%token ADDTO
%token SUBFROM
%token SEM

%token IF
%token THEN
%token ELSE
%token ENDIF

%token WHILE
%token DO
%token ENDWHILE

%token END

%%

program : sequ END		{ preorder($$.treeptr,visitor); }
	;

sequ    : command
	| command SEM sequ	{ $$.treeptr = mknode(
					makeLabel(seq_label,0),
					$1.treeptr,$3.treeptr); }
	;

command : ID ASSIGN expr 	{ $$.treeptr = mknode(
				    makeLabel(assign_label,$1.value),
				      mkleaf(
					makeLabel(id_label,$1.value)),
				      $3.treeptr); }

	| ID ADDTO expr 	{ $$.treeptr = mknode(
				    makeLabel(addto_label,$1.value),
				      mkleaf(
					makeLabel(id_label,$1.value)),
				      $3.treeptr); }

	| ID SUBFROM expr 	{ $$.treeptr = mknode(
				    makeLabel(addto_label,$1.value),
				      mkleaf(
					makeLabel(id_label,$1.value)),
				      $3.treeptr); }

	| IF expr THEN sequ ELSE sequ ENDIF
				{ $$.treeptr = mknode(
				    makeLabel(if_label,0),
				    $2.treeptr, 
					mknode(
					  makeLabel(branch_label,0),
					  $4.treeptr,$6.treeptr)); }

	| WHILE expr DO sequ ENDWHILE
				{ $$.treeptr = mknode(
					makeLabel(while_label,0),
					$2.treeptr,$4.treeptr); }
	;

expr    : NUMBER 		{ $$.treeptr = mkleaf(
					makeLabel(number_label,$1.value)); }
	| ID 			{ $$.treeptr = mkleaf(
					makeLabel(id_label,$1.value)); }
	| expr LT expr		{ $$.treeptr = mknode(
					makeLabel(lt_label,0),
					$1.treeptr, $3.treeptr); }
	;

%%

#include "lex.yy.c"

/* displays the symbol table - useful for debugging */

void outputSymbolTable(void) {
	int i;
        for (i=0;i<top_symbol;i++) printf("%s\n",symbolTable[i]);
}

/* generates a label for a node in the abstract syntax tree */

label_t *makeLabel(char *lab, int val) {
	label_t *the_label = malloc(sizeof(label_t));
	the_label -> label = strdup(lab);
	the_label -> value = val;
	return the_label;
}
