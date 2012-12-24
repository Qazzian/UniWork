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
