#!/usr/local/bin/perl

sub greet {
  print "Pleased to meet you @_\n";
}

  print "Hello, I'm Eliza. What is your name? ";
  $name = <STDIN>;
  &greet($name);
