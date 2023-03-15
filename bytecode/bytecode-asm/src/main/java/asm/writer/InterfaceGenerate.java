package asm.writer;

import asm.utils.FileUtils;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


public class InterfaceGenerate {

    private static final String FQ_NAME = "asm/autogenerate/HelloWorld";
    private static final String PATH = FQ_NAME + ".class";

    public void generate() {
        String path = FileUtils.getClassFilePath(PATH);
        System.out.println(path);
        byte[] bytes = dump();
        FileUtils.writeBytes(path, bytes);
    }

    private byte[] dump() {
        /*
         * COMPUTE_MAXS: 计算max stack和max local信息。
         * COMPUTE_FRAMES: 既计算stack map frame信息，又计算max stack和max local信息。
         */
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        int access = Opcodes.ACC_PUBLIC | Opcodes.ACC_INTERFACE;
        cw.visit(Opcodes.V20, access, FQ_NAME, "I", null, null);

        {
            int filedAccess = Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL | Opcodes.ACC_STATIC;
            FieldVisitor fv = cw.visitField(filedAccess, "EQUAL", "I", null, 0);
            fv.visitEnd();
        }

        cw.visitEnd();
        return cw.toByteArray();
    }

    public static void main(String[] args) {
        new InterfaceGenerate().generate();
    }
}
