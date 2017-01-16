package exemplesDuCours;

import java.util.ArrayList;
import java.util.Collection;

public class d11bis_invocationMethodesParam {

public static <T> void m4(Collection<T> c, T t) {
}

public static void main(String[] args) {
  Object o = new Object();
  Collection<Object> co = new ArrayList<>();
  m4(co, o);// T est remplacé par Object
  String s = "";
  Collection<String> cs = new ArrayList<>();
  m4(cs, s);// T est remplacé par String
  m4(co, o);// T est remplacé par Object
  m4(cs, o);// IMPOSSIBLE A COMPILER
  Integer i = 1;
  Float f = 1.0f;
  Number n = 1;
  Collection<Number> cn = new ArrayList<>();
  m4(cn, i);// T est remplacé par Number
  m4(cn, f);// T est remplacé par Number
  m4(cn, n);// T est remplacé par Number
  m4(co, n);// T est remplacé par Object
  m4(co, i);// T est remplacé par Object
  m4(cs, n);// IMPOSSIBLE A COMPILER
}
}
