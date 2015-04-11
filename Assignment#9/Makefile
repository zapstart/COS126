COMPILE := javac-introcs 

all: BeadTracker.class BlobFinder.class Luminance.class

BeadTracker.class: BeadTracker.java BlobFinder.class Luminance.class
	@echo "#####################################" 
	@echo "start compile BeadTracker.java..." 
	@echo "#####################################" 
	$(COMPILE) BeadTracker.java
	@echo "#####################################" 
	@echo "compile BeadTracker.java completed!!!" 
	@echo "#####################################" 

BlobFinder.class: BlobFinder.java Luminance.class
	@echo "#####################################" 
	@echo "start compile BlobFinder.java..." 
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

