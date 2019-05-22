/* 
 * File:   main.cpp
 * Author: yvan
 *
 * Created on 3 avril 2014, 16:46
 */

#include <cstdlib>

#include "map.h"
#include<iostream>

using namespace std;

/*
 * 
 */
int main(int argc, char** argv) {
  map tel;
  cout << tel << endl;
  tel.mettre("0300000055", personne("A", "A"));
  cout << tel << endl;
  tel.mettre("0300000055", personne("A", "B"));
  cout << tel << endl;
  tel.mettre("0300000056", personne("A", "B"));
  cout << tel << endl;
  tel.mettre("0300000065", personne("A", "C"));
  cout << tel.recuperer("0300000055") << endl;
  tel.mettre("0310000065", personne("A", "D"));
  cout << tel.recuperer("0310000055") << endl;
  cout << tel << endl;
  return 0;
}

