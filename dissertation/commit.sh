#!/bin/bash
pdflatex proposal
git commit -m"edits" -a
git push
