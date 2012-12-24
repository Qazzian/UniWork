<!DOCTYPE style-sheet PUBLIC "-//James Clark//DTD DSSSL Style Sheet//EN" [
<!ENTITY docbook.dsl PUBLIC "-//Norman Walsh//DOCUMENT DocBook HTML Stylesheet//EN" CDATA DSSSL>
]>

<style-sheet>
<style-specification id="docbook-plain" use="docbook">
<style-specification-body>

;; This is a modification of the file described below to provide a standard
;;  presentation of small articles for the Dept. CS at Aberystwyth
;; Nigel Hardy (nwh@aber.ac.uk) October 1999
;;

;; $Id: html.dsl,v 1.1 2000/02/28 12:06:07 nwh Exp $
;;
;; This file is part of the Modular DocBook Stylesheet distribution.
;; See ../README or http://www.berkshire.net/~norm/dsssl/
;;
;; Example of a customization layer on top of the modular docbook style
;; sheet.  Definitions inserted in this file take precedence over 
;; definitions in the 'use'd stylesheet(s).

(define %generate-article-toc% #t)
(define %generate-article-titlepage% #t)
(define %section-autolabel% #t)
(define %admon-graphics% #t)
(define %admon-graphics-path% "http://www.aber.ac.uk/~nwh/docbook/images/")
(define %graphic-default-extension% "gif")

(define %html-ext% ".html")
(define %root-filename% "index")

(define (article-titlepage-recto-elements)
  (list (normalize "title") 
	(normalize "subtitle") 
	(normalize "corpauthor") 
	(normalize "authorgroup") 
	(normalize "author") 
	(normalize "date")
	(normalize "releaseinfo")
	(normalize "abstract")))

</style-specification-body>
</style-specification>

<external-specification id="docbook" document="docbook.dsl">

</style-sheet>

