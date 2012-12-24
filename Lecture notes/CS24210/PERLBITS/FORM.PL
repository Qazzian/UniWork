#!/usr/local/bin/perl

use CGI qw/:standard :html3/;

print header, title("form.pl");

sub processForm { 
# this is done if the 'submit' button has been pressed

  print start_html,

    "You, ".param('name').", whose favorite colors are ";

    foreach (param('colour')) {print "$_, " }

    print " are on a quest which is '", param('quest'),
    "', and are looking for the weight of an ",
    param('swallow'),
    ".  And this is what you have to say for yourself:",
    p, param('text'),

    hr,
    "And here is a list of the parameters you entered...",
    dump();

  end_html;
}

sub printForm {
# this is done if 'submit' has not been pressed

  local @colour = qw/chartreuse azure puce cornflower olive opal mustard/;

  local @swallow = ("African Swallow", "Continental Swallow"); 

  print start_html, 
    h2 ("Pop Quiz"),
    start_form,
      "What is thy name:",
         textfield(-name=>'name',-size=>20),

      p, "What is thy quest:",
         textfield(-name=>'quest',-size=>20),

      p, "What is thy favorite colour:",
      checkbox_group (-name=>'colour', -values=>\@colour),

      p, "What is the weight of a swallow:",
      radio_group (-name=>"swallow", -values=>\@swallow),

      p, "What do you have to say for yourself",
      textarea(-name=>'text',-rows=>5,-cols=>60),

      submit(-name=>'submit',
        -value=>"Press here to submit your query."),
    end_form,
    hr,

    "CGI.pm version of Steven E. Brenner's combined form",
  end_html;
}

if (param('submit')) {
  processForm;
} else {
  printForm;
}
