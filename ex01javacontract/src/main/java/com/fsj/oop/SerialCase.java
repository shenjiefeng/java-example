package com.fsj.oop;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * 5.4.10 序列化类新增属性时,请不要修改 serialVersionUID 字段,避免反序列失败;如 果完全不兼容升级,避免反序列化混乱,那么请修改 serialVersionUID 值。
 * 反序列化的调用链如下：
 ObjectInputStream.readObject ->
 readObject0 ->
 readOrdinaryObject ->
 readClassDesc ->
 readNonProxyDesc ->
 ObjectStreamClass.initNonProxy
 * todo translate source code comments
 * @link https://mp.weixin.qq.com/s/5xcDDtsVYdgzUebF3_Mg4g
 */
public class SerialCase implements Serializable {

    private static final long serialVersionUID = -4730963108063758252L;

    public static void main(String[] args) {

//        serial();
        deSerial();
    }

    static void serial() {
        //Initializes The Object
        User1 user = new User1();
        user.setName("hollis");
        //Write Obj to File
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(oos);
        }
    }

    static void deSerial() {
        //Read Obj from File
        File file = new File("tempFile");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            User1 newUser = (User1) ois.readObject();
            System.out.println("反序列化："+newUser);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(ois);
            try {
                FileUtils.forceDelete(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
