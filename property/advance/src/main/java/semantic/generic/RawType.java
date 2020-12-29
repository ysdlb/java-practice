package semantic.generic;

import java.util.HashSet;
import java.util.Set;

/**
 * raw type definition:
 * The reference type that is formed by taking the name of a generic type declaration without an accompanying type argument list. <br>
 * An array type whose element type is a raw type.<br>
 * A non-static member type of a raw type R that is not inherited from a superclass or superinterface of R.
 */
public class RawType<T> {
    class Inner {
    }

    static class Nested {
    }

    Set<Integer> set1 = new HashSet<>();

    static Set<Integer> set2 = new HashSet<>();

    public static void main(String[] args) {
        RawType rawType;                // warning: RawType is a raw type
        RawType.Inner inn;              // warning: RawType.Inner is a raw type

        RawType.Nested nest;            // no warning: not parameterized type
        RawType<Object> rawType1;       // no warning: type parameter given
        RawType<?> rawType2;            // no warning: type parameter given (wildcard OK!)
    }

    public void test(RawType rawType) {
        Set<Integer> integerSet = rawType.set1;         // warning: RawType.Inner is a raw type

        Set<Integer> integerSet2 = rawType.set2;        // warning: Static member 'semantic.generic.RawType.set2' accessed via instance reference
        Set<Integer> integerSet3 = RawType.set2;        // no warning
    }

    public void test1(RawType<?> rawType) {
        Set<Integer> integerSet = rawType.set1;         // no warning: type parameter given (wildcard OK!)

        Set<Integer> integerSet2 = rawType.set2;        // warning: Static member 'semantic.generic.RawType.set2' accessed via instance reference
        Set<Integer> integerSet3 = RawType.set2;        // no warning
    }
}
