package reflect.foundation;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Member;

public class MemberTest {

    @Test
    void memberTest() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("reflect.foundation.Target");

        Member[] members = clazz.getFields();
        for (Member member : members) {
            System.out.println("==========================");
            System.out.println("getName:           " + member.getName());
            System.out.println("getModifiers:      " + member.getModifiers());
            System.out.println("getDeclaringClass: " + member.getDeclaringClass());
            System.out.println("isSynthetic:       " + member.isSynthetic());
            System.out.println("==========================");
        }
    }

}