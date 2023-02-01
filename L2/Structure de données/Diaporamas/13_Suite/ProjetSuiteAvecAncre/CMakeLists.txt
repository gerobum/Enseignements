cmake_minimum_required(VERSION 3.24)
project(suite)

set(CMAKE_CXX_STANDARD 17)

add_executable(
        main.cpp
        suite.cpp
        suite.h main.cpp)
