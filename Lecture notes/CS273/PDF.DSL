<!DOCTYPE style-sheet PUBLIC "-//James Clark//DTD DSSSL Style Sheet//EN" [
<!ENTITY docbook.dsl PUBLIC "-//Norman Walsh//DOCUMENT DocBook Print Stylesheet//EN" CDATA DSSSL>
]>

<style-sheet>
<style-specification id="docbook-plain" use="docbook">
<style-specification-body>

;; This is a modification of the file described below to provide a standard
;;  presentation of small articles for the Dept. CS at Aberystwyth
;; Nigel Hardy (nwh@aber.ac.uk) October 1999
;;

;; $Id: pdf.dsl,v 1.1 2000/02/14 13:51:37 nwh Exp $
;;
;; This file is part of the Modular DocBook Stylesheet distribution.
;; See ../README or http://www.berkshire.net/~norm/dsssl/
;;
;; Example of a customization layer on top of the modular docbook style
;; sheet.  Definitions inserted in this file take precedence over 
;; definitions in the 'use'd stylesheet(s).

(define %generate-article-toc% #f)
(define %generate-article-titlepage-on-separate-page% #f)
(define %title-font-family%  %body-font-family%)
(define %section-autolabel% #t)
(define %paper-type% "A4")
(define %admon-graphics% #t)
(define %admon-graphics-path% "c:/usr/local/lib/docbook/images/")
(define %default-quadding% 'start)
(define %line-spacing-factor% 1.1)
(define %body-start-indent% 0pi)
(define %block-start-indent% 1pi)
(define %para-sep% (/ %bf-size% 1.5))

(define %bottom-margin% 
  ;; Height of bottom margin
  (if (equal? %visual-acuity% "large-type")
      7.5pi 
      6pi))

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
