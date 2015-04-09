COMPILE := javac-introcs 

all: BlobFinder.class Luminance.class

BlobFinder.class: BlobFinder.java Luminance.class
	@echo "#####################################" 
	@echo "start compile BlobFinder.java" 
	@echo "#####################################" 
	$(COMPILE) BlobFinder.java
	@echo "#####################################" 
	@echo "compile BlobFinder.java completed!!!" 
	@echo "#####################################" 

Luminance.class: Luminance.java
	@echo "" 
	@echo "#####################################" 
	@echo "start compile Luminance.java" 
	@echo "#####################################" 
	$(COMPILE) Luminance.java 
	@echo "#####################################" 
	@echo "compile Luminance.java completed!!!" 
	@echo "#####################################" 

.PHONY:
clean:
	-rm -rf *class

