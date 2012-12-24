#!/usr/local/bin/perl

print "Filename? ";
$file = <STDIN>;        # filename is on standard input
open(FILE,$file);       # open the file - FILE is a filehandle
@file_content = <FILE>; # read into an array
close(FILE);
print @file_content;    # print the array


