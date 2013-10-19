#!/bin/bash
lyx --export pdf2 $1.lyx
pdflatex $1
git commit -m"edits" -a
git push
