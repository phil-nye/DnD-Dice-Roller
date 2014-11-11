####################################################################################################
										VIRTUAL DICE ROLLER
####################################################################################################

Created by Philip Ni


** Document included:
	READ_ME.txt
	Dice.jar (executable Java JAR file)
	Dice.java (source code)
	manifest.txt (required for JAR file compilation)

** Please read the "Instructions" tab for usage instructions 

** Feel free to contact me if you have any question about how this program works, if you have any
   suggestions on how to improve the user interface, or if you have any issues with running the
   application. My email can be found below.
   
** This application program was written and compiled using Java 8 (version 1.8.0_26) for 64-bit
   machines.

** Compiling *.jar files:
	1)	Make a manifest to show the compiler where the main class is. And example can be found in
		the attached manifest.txt file. (The manifest file must end with two newlines and be a text
		document.)
	2)	Compile *.java main file (in this case, Dice.java).
	3)	Compile the jar file with the classes (*.class) and manifest file.
			jar cmf [jar file name].jar [manifest file].txt [class files].class
			
			cmf flag breakdown:
				c ==> create JAR file
				m ==> include manifest file
				f ==> output to file rather than stdout
			
			*Visit http://docs.oracle.com/javase/tutorial/deployment/jar/build.html for more
			 information on creating a JAR file.

** Running *.jar files in the command prompt:
	javac -jar [jar file]


*	Copyright (c) 2012, Philip Cho Ni <philipni122@gmail.com>
*	All rights reserved.
*
*	Redistribution and use in source and binary forms, with or without
*	modification, are permitted provided that the following conditions are met:
*		* Redistributions of source code must retain the above copyright
*		  notice, this list of conditions and the following disclaimer.
*		* Redistributions in binary form must reproduce the above copyright
*		  notice, this list of conditions and the following disclaimer in the
*		  documentation and/or other materials provided with the distribution.
*		* Neither the name of the <organization> nor the
*		  names of its contributors may be used to endorse or promote products
*		  derived from this software without specific prior written permission.
*
*	THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
*	ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
*	WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
*	DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
*	DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
*	(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
*	LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
*	ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
*	(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
*	SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
*
*	Created by Philip C. Ni
*	Last Modified:	10/31/2014