#!/usr/local/bin/perl

sub greet {
  my ($name) = @_;
  chop $name;
  print "Pleased to meet you $name,  Do you want to talk?\n";
  print "(Say `bye' when you've had enough).\n\n";
  $input = <STDIN>;
}


sub converse {

  my ($in) = @_;
  chop $in;

  if ($in =~ /mother|father|sister|brother/) {
    print "Tell me more about your family.\n";

  } else {
    print "How interesting - do go on.\n";

  }
  $input = <STDIN>;
}


  print "Hello, I'm Eliza. What's your name? ";
  $input = <STDIN>;
  &greet($input);

  while (($input) !~ m/[Bb]ye/) {
    $input =~ tr/A-Z/a-z/;
    &converse($input);
  };

  print "Bye, bye - see you around.\n"
