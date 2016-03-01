all: proof types
	
types: types/enttypes.tex

proof: proof.tex
	pdflatex proof 
	pdflatex proof 

types/enttypes.tex: types/*.tex
	pdflatex types/enttypes.tex

clean:
	rm *.log *.out *.dvi *.pdf 

