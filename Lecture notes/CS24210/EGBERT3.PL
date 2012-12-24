#!/usr/local/bin/perl

use CGI qw/:standard/;

sub getOrder {
  print start_html, 
    start_form,
      textarea(-name=>'order',-rows=>5,-cols=>60),
      p,
      submit(-name=>'submit',
        -value=>'Press here to submit your order.'),
    end_form,
  end_html;
}

sub shoppingList {

  local ($order, $shopping_list) = @_; 
    # $shopping_list is a reference to %shopping_list

  local %price = (brassica=>30, book=>100, fruit=>20,
    hot_drink=>50, newspaper=>45);

  $order =~ tr/A-Z/a-z/;

  local $category;

  if ($order =~ /cabbage|kale|green|broccoli/) {
    $category = 'brassica';
  } elsif ($order =~ /book/) {
    $category = 'book';
  } elsif ($order =~ /newspaper/) {
    $category = 'newspaper'
  } elsif ($order =~ /tea|coffee/) {
    $category = 'hot_drink';
  } elsif ($order =~ /apple|banana|pear/) {
    $category = 'fruit';
  } 

  if ($category) {
    $shopping_list{$&} += $price{$category};
    shoppingList  ($`.$',\%shopping_list); 
         #notice the reference to the hash table, %shopping_list
  } else {
    local $total, $return_string;
    foreach(keys(%$shopping_list)) {
      $return_string = $return_string.
        p("Your $_ will cost ".$shopping_list{$_}." pence");
      $total += $shopping_list{$_};
    }
    $return_string = $return_string.p("Total bill: $total pence");
    return $return_string;
  }

}

sub processForm { 
  print start_html,
    h4("You said: ", param('order')),
    shoppingList (param('order')),
    h4 ("Will that be all?"),
    startform,
       submit(-name=>'finished',-value=>'yes, thanks.'),
    endform,
    h4("Or would you like to change your order?"),
  end_html;
}

sub greet {
  print start_html,
    h4("How do you do.",
      p("I'm Egbert, your friendly sales assistant."),
      p("How may I help you?")),
  end_html;
}

print header;

if (param('submit')) { processForm; }
else { greet; }

getOrder;
