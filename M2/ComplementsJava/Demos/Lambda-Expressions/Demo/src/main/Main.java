
package main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author yvan
 */
public class Main {
    
    private static String plusCourte(Stream<String> ts) {
        Optional<String> os = ts.parallel().reduce((a, b)-> a.length() < b.length() ? a : b);
        if (os.isPresent())
            return os.get();
        else
            return null;
    }
    
    private static String plusLongue(Stream<String> ts) {
        return ts.parallel().reduce("",
                (a, b)-> a.length() > b.length() ? a : b
        );
    }
    private static String concatenation(Stream<String> ts) {
        return ts.parallel().reduce("",
                (a, b)-> a+" "+b
        );
    }
    
    private static String concatenationSB(Stream<String> ts) {
        return ts.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
    }
    
    private static List<String> list(Stream<String> ts) {
        return ts.collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
    }
    
    private static List<String> list2(Stream<String> ts) {
        return ts.collect(Collectors.toList());
    }
    
    public static int nbCar(Stream<String> ts) {
        return ts.reduce(0, (n, s) -> n+s.length(), (l,r) -> l+r);
    }
    
    public static char plusGrandCar(Stream<String> ts) {
        return ts.reduce('A', (n, s) -> {
            char pg = n;
            int i = 0;
            s = s.toUpperCase();
            while(i < s.length() && pg < 'Z') {
                if (s.charAt(i) > pg) {
                    pg = s.charAt(i);
                }
                ++i;
            }
            return pg;
        }, (l,r) -> l>r?l:r);
    }
    
    public static Set<Character> lesLettres(Stream<String> ts) {
        return ts.reduce(new HashSet<>(), 
                (l,r)->{
                    for(char c : r.toCharArray()) {
                        l.add(c);
                    }
                    return l;
                }, 
                (l,r)->{ 
                    l.addAll(r);
                    return l;
                });
    }
    
    
    
    public static void main(String[] args) {
        String[] ts = {"un", "deux", "trois", "quatre", "cinq"};
        System.out.println(plusLongue(Arrays.stream(ts)));
        System.out.println(nbCar(Arrays.stream(ts)));
        System.out.println(plusCourte(Arrays.stream(ts)));
        System.out.println(plusCourte(Arrays.stream(new String[] {})));
        System.out.println(plusGrandCar(Arrays.stream(ts)));
        
        lesLettres(Arrays.stream(ts)).forEach(System.out::print);
        System.out.println();
        System.out.println(concatenation(Arrays.stream(ts)));
        System.out.println(concatenationSB(Arrays.stream(ts)));
        
        list(Arrays.stream(ts)).forEach(System.out::println);
    }
   
}
