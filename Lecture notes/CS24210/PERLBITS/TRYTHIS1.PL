#!/usr/local/bin/perl

$scalar_string_id = "this is a string";  
print $scalar_string_id . "\n";

$scalar_number_id = 7;
print "scalar_number_id = " . $scalar_number_id . "\n";

@array_id = ("peach","fig","apricot");
print $array_id[0] .  $array_id[2] . $array_id[1] . "\n\n";

%hash_id = ("peach", 2, "fig", 8, "apricot", 3);
print "We have " . $hash_id{"peach"} . " peaches\n";
print "We have " . $hash_id{"fig"} . " figs\n";
print "We have " . $hash_id{"apricot"} . " apricots\n";
