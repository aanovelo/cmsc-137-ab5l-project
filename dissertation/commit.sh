#!/bin/bash
lyx --export pdflatex $1.lyx
pdflatex $1
git commit -m"edits" -a
git push
