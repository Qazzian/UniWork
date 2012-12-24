#!/usr/local/bin/perl

# $Header: /cys/people/brenner/http/docs/cgi-lib/ex/RCS/combined-form.cgi,v 1.5 1996/07/31 16:35:52 brenner Exp $
# Copyright (C) 1994 Steven E. Brenner
# This is a small demonstration script to demonstrate the use of 
# the cgi-lib.pl library, where the script both creates the form and
# processes its input

# Adapted by Edel Sherratt 1999 to form eliza-converse.pl

require "cgi-lib.pl";


 MAIN: 
{
# Read in all the variables set by the form
  
  if (&ReadParse(*input)) {
    &ProcessForm;
  } else {
    &PrintForm;
  }
}

sub ProcessForm {

  # Print the header
  print &PrintHeader;
  print &HtmlTop ("Eliza");

  # Do some processing, and print some output
  ($text = $input{'text'}) =~ s/\n/\n<BR>/g; 
                                   # add <BR>'s after carriage returns
                                   # to multline input, since HTML does not
                                   # preserve line breaks

  &converse($text);

print <<'ENDOFTEXT';

<HR>
<form method="get" action="eliza-converse.pl">
<textarea name="text" rows=5 cols=60></textarea>
<P>

Press <input type="submit" value="here"> to continue the conversation.
<P>
Or <a href="/~dcswww">here</a> to go to the Computer Science home page.
</form>
<hr>
ENDOFTEXT

  # Close the document cleanly.
  print &HtmlBot;
}

sub converse {

  my ($in) = @_;

  if ($in =~ /mother|father|sister|brother/) {
    print "Tell me more about your family.\n";

  } elsif ($in =~ /want[a-z]* to (\w+)/) {
    print "Do you know anyone else who wants to $+?\n";

  } elsif ($in =~ /i (\w+) you/) {
    print "Perhaps in your fantasy we $+ each other.\n";

  } elsif ($in =~ /head|foot|hand|back|tummy/) {
    print "Does your $& hurt?\n";

  } elsif ($in =~ /no|not|don\'t|won't/) {
    print "Do you often have negative feelings?\n"

  } elsif ($in =~ /happy|sad|hate|love/) {
    print "Why do you feel like that?\n"

  } else {
    print "How interesting - do go on.\n";
  }
}


sub PrintForm {

  print &PrintHeader;
  print &HtmlTop ("Eliza");


  # Print out the body of the form from a single quoted here-document
  # Note that if ENDOFTEXT weren't surrounded by single quotes,
  # it would be necessary to be more careful with the other text
  # For example, the @ sign (in the address) would need to be escaped as \@
  print <<'ENDOFFORM';

<HR>
<form method="get" action="eliza-converse.pl">

What do you want to talk about?
<textarea name="text" rows=5 cols=60></textarea>
<P>

Press <input type="submit" value="here"> to continue the conversation.
<P>
Or <a href="/~dcswww">here</a> to go to the Computer Science home page.

</form>
<hr>
ENDOFFORM

  print &HtmlBot;

}
