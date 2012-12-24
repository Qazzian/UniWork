#!perl

# a little scanner

# read from stdin into $_
# finish at eof

while (<>) {
  # $_ is where the input goes by default
  # split splits the string $_ at whitespace

  @words = split;
  foreach $w (@words) {
    $count{$w}++;
  }
}

# $ - strings or numbers
# @ array indexed by numbers
# % string

print "Word counts (unsorted)\n"; # added by Edel Sherratt - Oct. 99
print "---------------------\n";
foreach $w (keys %count) {
  print "$w: $count{$w}\n";
}
print "\n";

# sorting added by Lynda Thomas

@sortedkeys=sort keys(%count);

print "Word counts (sorted)\n"; # added by Edel Sherratt - Oct. 99;
print "-------------------\n";
foreach $w (@sortedkeys) {
  print "$w $count{$w}\n";
}
