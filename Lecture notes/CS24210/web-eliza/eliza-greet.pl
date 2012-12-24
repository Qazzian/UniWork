#!/usr/local/bin/perl

# $Header: /cys/people/brenner/http/docs/web/RCS/simple-form.cgi,v 1.4 1996/03/29 22:07:56 brenner Exp $
# Copyright (C) 1994 Steven E. Brenner
# This is a small demonstration script to demonstrate the use of 
# the cgi-lib.pl library

# Adapted by Edel Sherratt 1999 to form eliza-greet.pl

require "cgi-lib.pl";

MAIN:
{

  # Read in all the variables set by the form
  &ReadParse(*input);

  # Print the header
  print &PrintHeader;
  print &HtmlTop ("Eliza");

  # Do some processing, and print some output
  ($text = $input{'text'}) =~ s/\n/\n<BR>/g; 
                                   # add <BR>'s after carriage returns
                                   # to multline input, since HTML does not
                                   # preserve line breaks

 &greet ($text);

}

sub greet {
  my ($name) = @_;
  print "Pleased to meet you $name.  ";
  print "<A HREF=\"eliza-converse.pl\">Do you want to talk?</A><P>";
  print "Or would you rather <A HREF=\"/~dcswww\">return to the Computer Science home page?</A>";
}

