#!/bin/bash
lyx --export pdf2 $1.lyx $1.tex
pdflatex $1
git commit -m"edits" -a
git push
