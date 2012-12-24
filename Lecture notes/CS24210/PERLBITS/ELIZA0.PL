#!/usr/local/bin/perl

sub converse {
  print "Sorry - I am too stupid to talk\n"
}

  printf "? ";
  $input = <STDIN>;
  &converse($input);
