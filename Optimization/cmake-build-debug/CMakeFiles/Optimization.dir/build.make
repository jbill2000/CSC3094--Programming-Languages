# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.19

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Disable VCS-based implicit rules.
% : %,v


# Disable VCS-based implicit rules.
% : RCS/%


# Disable VCS-based implicit rules.
% : RCS/%,v


# Disable VCS-based implicit rules.
% : SCCS/s.%


# Disable VCS-based implicit rules.
% : s.%


.SUFFIXES: .hpux_make_needs_suffix_list


# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

#Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake

# The command to remove a file.
RM = /Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Users/jeremybill/CLionProjects/Optimization

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/jeremybill/CLionProjects/Optimization/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/Optimization.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/Optimization.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/Optimization.dir/flags.make

CMakeFiles/Optimization.dir/main.cpp.o: CMakeFiles/Optimization.dir/flags.make
CMakeFiles/Optimization.dir/main.cpp.o: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/jeremybill/CLionProjects/Optimization/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/Optimization.dir/main.cpp.o"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/Optimization.dir/main.cpp.o -c /Users/jeremybill/CLionProjects/Optimization/main.cpp

CMakeFiles/Optimization.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/Optimization.dir/main.cpp.i"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/jeremybill/CLionProjects/Optimization/main.cpp > CMakeFiles/Optimization.dir/main.cpp.i

CMakeFiles/Optimization.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/Optimization.dir/main.cpp.s"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/jeremybill/CLionProjects/Optimization/main.cpp -o CMakeFiles/Optimization.dir/main.cpp.s

# Object files for target Optimization
Optimization_OBJECTS = \
"CMakeFiles/Optimization.dir/main.cpp.o"

# External object files for target Optimization
Optimization_EXTERNAL_OBJECTS =

Optimization: CMakeFiles/Optimization.dir/main.cpp.o
Optimization: CMakeFiles/Optimization.dir/build.make
Optimization: CMakeFiles/Optimization.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/jeremybill/CLionProjects/Optimization/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable Optimization"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/Optimization.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/Optimization.dir/build: Optimization

.PHONY : CMakeFiles/Optimization.dir/build

CMakeFiles/Optimization.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/Optimization.dir/cmake_clean.cmake
.PHONY : CMakeFiles/Optimization.dir/clean

CMakeFiles/Optimization.dir/depend:
	cd /Users/jeremybill/CLionProjects/Optimization/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/jeremybill/CLionProjects/Optimization /Users/jeremybill/CLionProjects/Optimization /Users/jeremybill/CLionProjects/Optimization/cmake-build-debug /Users/jeremybill/CLionProjects/Optimization/cmake-build-debug /Users/jeremybill/CLionProjects/Optimization/cmake-build-debug/CMakeFiles/Optimization.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/Optimization.dir/depend

