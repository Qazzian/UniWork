#!/usr/local/bin/perl

# from a program by Mike Slattery, June 1996

# swap takes a string, and replaces any words in %table by 
# corresponding replacements

%table = ('i','you', 'you','i', 'my','your', 'your','my');

sub swap {
  local ($in) = @_;
  local ($w, $head, $tail, $new);

  if ($in =~/[a-z]+/) {
    $w = $&;	# the match
    $head = $`;	# before the match
    $tail = $'; # after the match

    # look up $new in the table; if not found, $new = $w
    $new = $table{$w} || $w;	

    # put the sentence back together
    $head.$new.&swap($tail); 
  }

  else { $in }
}

while ($input = <>) {
   chop $input;
   $input =~ tr/A-Z/a-z/;
   print &swap($input)."\n";
}
