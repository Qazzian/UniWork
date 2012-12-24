#!/usr/local/bin/perl

while (<>) {
  @words = split;
  foreach $w (@words) {
    $count{$w}++;
  }
}

@sortedkeys = sort keys(%count);

foreach $w (@sortedkeys) {
  print "$w\t$count{$w}\n" if ($w =~ m/l/);
}

