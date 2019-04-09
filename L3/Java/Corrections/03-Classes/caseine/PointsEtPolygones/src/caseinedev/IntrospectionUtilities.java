/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package caseinedev;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author yvan
 */
public class IntrospectionUtilities {

    private static Random R = new Random();

    /**
     * Retourne par introspection le résultat de l'invocation d'une méthode avec
     * ses arguments.
     *
     * @param c la classe à laquelle la méthode appartient
     * @param o l'objet sur lequel s'application la méthode
     * @param methodName le nom de la méthode
     * @param pv une liste de couples (type et sa valeur)
     * @return le résultat de l'invocation
     */
    public static Object getFromMethodTA(Class<?> c, Object o, String methodName, Object... pv) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        StringBuilder methodNameAndArgs = new StringBuilder(methodName);
        methodNameAndArgs.append("(");

        Class<?>[] params = new Class<?>[pv.length / 2];
        Object[] values = new Object[pv.length / 2];
        int k = 0;
        for (int i = 0; i < pv.length; i += 2) {
            params[k] = (Class<?>) pv[i];
            methodNameAndArgs.append(params[k].getSimpleName()).append(", ");
            values[k++] = pv[i + 1];
        }
        methodNameAndArgs.append(")");
        Method method = c.getDeclaredMethod(methodName, params);
        return method.invoke(o, values);

    }

    /**
     * Retourne par introspection le résultat de l'invocation d'une méthode avec
     * ses arguments.
     *
     * @param c la classe à laquelle la méthode appartient
     * @param o l'objet sur lequel s'application la méthode
     * @param methodName le nom de la méthode
     * @param pv une liste de valeurs (dont les types sont déduits)
     * @return le résultat de l'invocation
     */
    public static Object getFromMethod(Class<?> c, Object o, String methodName, Object... pv) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object[] coupletv = new Object[2*pv.length];
        int k = 0;
        for (int i = 0; i < pv.length; i++) {
            coupletv[k++] = pv[i].getClass();
            coupletv[k++] = pv[i];
        }
        return getFromMethodTA(c, o, methodName, coupletv);
    }

    public static Object getAttribut(Class<?> c, Object o, String nom) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        Field fnom = c.getDeclaredField(nom);
        fnom.setAccessible(true);
        return fnom.get(o);

    }

    public static boolean checkIfMutable(Object o1, Method m) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, FileNotFoundException {
        Object o2 = clone(o1);
        m.setAccessible(true);

        for (int i = 0; i < 100; i++) {
            PrintStream out = System.out;
            PrintStream err = System.err;
            System.setOut(new PrintStream("tmpout"));
            System.setErr(new PrintStream("tmperr"));
            m.invoke(o2, fillParametersArray(m));
            System.setOut(out);
            System.setErr(err);
            
            if (!equals(o1, o2)) {
                return true;
            }
        }
        return false;
    }
    
    
    
    public static boolean isASetter(Method m, Class<?> C) {
        try {
            Constructor<?> c = C.getConstructor();
            c.setAccessible(true);
            Object o = c.newInstance();
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
        }
        return true;
    }

    public static boolean thereIsSetter(Class<?> C) {
        return Arrays.stream(C.getMethods())
                .anyMatch(m -> m.getName().startsWith("set"))
                || Arrays.stream(C.getDeclaredMethods())
                        .filter(m -> !Modifier.isPrivate(m.getModifiers()))
                        .anyMatch(m -> m.getName().startsWith("set"));
    }
    
    
    public static boolean isMutable(Class<?> C) {
        /*return Arrays.stream(C.getDeclaredFields())
                .anyMatch(p -> 
                        !Modifier.isPrivate(p.getModifiers()) &&
                        (
                                !(
                                        Modifier.isFinal(p.getModifiers()) &&                                
                                        p.getClass().isPrimitive() &&
                                        new Checker(p.getClass()).isMutable()
                                )
                        )
                );
        return Arrays.stream(C.getDeclaredFields())
                .anyMatch(p -> 
                        !Modifier.isPrivate(p.getModifiers()) &&
                        (
                                !Modifier.isFinal(p.getModifiers()) ||
                                (
                                        !p.getClass().isPrimitive() &&
                                        new Checker(p.getClass()).isMutable()
                                )
                        )
                );*/
 /*return !Arrays.stream(C.getDeclaredFields())
                .allMatch(
                        p -> Modifier.isPrivate(p.getModifiers()) || 
                                (
                                    Modifier.isFinal(p.getModifiers()) && 
                                            (
                                                p.getType().isPrimitive() ||
                                                !new Checker(p.getType()).isMutable()
                                            )                                    
                                )
                );*/
        return thereIsSetter(C)
                || Arrays.stream(C.getDeclaredFields())
                        .anyMatch(
                                p -> !Modifier.isPrivate(p.getModifiers())
                                && (!Modifier.isFinal(p.getModifiers())
                                || (!p.getType().isPrimitive()                                        
                                &&  isMutable(p.getType())))
                        )
                || Arrays.stream(C.getFields())
                        .anyMatch(
                                p -> !Modifier.isPrivate(p.getModifiers())
                                && (!Modifier.isFinal(p.getModifiers())
                                || (!p.getType().isPrimitive()
                                && isMutable(p.getType())))
                        );

    }
    
    public static boolean checkAttributsForMutability(Class<?> c) {
        return Arrays.stream(c.getDeclaredFields())
                        .anyMatch(
                                p -> !Modifier.isPrivate(p.getModifiers())
                                && (!Modifier.isFinal(p.getModifiers())
                                || (!p.getType().isPrimitive()
                                && isMutable(p.getType())))
                        )
                || Arrays.stream(c.getFields())
                        .anyMatch(
                                p -> !Modifier.isPrivate(p.getModifiers())
                                && (!Modifier.isFinal(p.getModifiers())
                                || (!p.getType().isPrimitive()
                                && isMutable(p.getType())))
                        );
    }

    public static boolean checkIfMutable(Class<?> c) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, FileNotFoundException {
    
        Object o1 = randomValue(c);
        for (Method m : c.getDeclaredMethods()) {
            if (!Modifier.isStatic(m.getModifiers())) {
                if (checkIfMutable(o1, m)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Object clone(Object o) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        Object co = randomValue(o.getClass());
        for (Field f : o.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            f.set(co, f.get(o));
        }
        return co;
    }

    public static boolean equals(Object o1, Object o2) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        return equals(o1, o2, false);
    }

    public static boolean equals(Object o1, Object o2, boolean isPrimitive) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        if (o1.getClass() != o2.getClass()) {
            return false;
        } else if (o1.getClass().isPrimitive() || isPrimitive) {
            return o1.equals(o2);
        } else {
            for (Field f : o1.getClass().getDeclaredFields()) {
                if (!Modifier.isStatic(f.getModifiers())) {
                    f.setAccessible(true);

                    if (!equals(f.get(o1), f.get(o2), f.getType().isPrimitive())) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public static boolean allParametersArePrimitive(Constructor<?> c) {
        return Arrays.stream(c.getParameterTypes())
                .allMatch(p -> p.isPrimitive());
    }

    public static Constructor<?> getOneSimpleConstructor(Class<?> c) {
        return Arrays.stream(c.getConstructors())
                .filter(p -> allParametersArePrimitive(p))
                .findFirst().get();
    }

    public static Object[] fillParametersArray(Executable k) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        Object[] values = (Object[]) Array.newInstance(Object.class,
                k.getParameterCount());
        Class<?>[] paramtypes = k.getParameterTypes();
        for (int i = 0; i < values.length; i++) {
            if (paramtypes[i] == char.class) {
                values[i] = (char) R.nextInt();

            } else {
                Array.set(values, i, randomValue(paramtypes[i]));
            }
        }
        return values;
    }

    public static Object
            randomValue(Class<?> c) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        if (c == void.class) {
            return null;

        } else if (c == boolean.class) {
            return R.nextBoolean();

        } else if (c == int.class
                || c == byte.class) {
            return R.nextInt();

        } else if (c == char.class) {
            return (char) R.nextInt();

        } else if (c == long.class) {
            return R.nextLong();

        } else if (c == double.class) {
            return R.nextDouble();

        } else if (c == float.class) {
            return R.nextFloat();
        } else {
            Constructor<?> k = null;
            try {
                k = c.getDeclaredConstructor();
            } catch (NoSuchMethodException | SecurityException ex1) {
                try {
                    k = c.getConstructor();
                } catch (NoSuchMethodException | SecurityException ex2) {
                    k = getOneSimpleConstructor(c);
                }
            }
            if (k != null) {
                k.setAccessible(true);
                Object o = k.newInstance(fillParametersArray(k));
                for (Field f : c.getDeclaredFields()) {
                    if (!Modifier.isStatic(f.getModifiers())) {
                        f.setAccessible(true);
                        f.set(o, randomValue(f.getType()));
                    }
                }
                return o;

            }
            return null;
        }
    }
}
