cmake_minimum_required(VERSION 3.0)
project(native)
find_package(JNI REQUIRED)

add_library(native_headers INTERFACE)
target_include_directories(native_headers INTERFACE ${CMAKE_CURRENT_SOURCE_DIR}/include)

add_library(native SHARED App.cpp)
target_link_libraries(native PRIVATE native_headers)
target_include_directories(native PRIVATE ${JNI_INCLUDE_DIRS})