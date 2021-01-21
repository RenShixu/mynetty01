package com.rsx.netty.pt;

public class ProtoTest {
    public static void main(String[] args) throws Exception{
        Test.Person person = Test.Person.newBuilder().setName("张三").setAge(30).setEmail("123124@.xsf").build();
        byte[] bytes = person.toByteArray();


        Test.Person p = Test.Person.parseFrom(bytes);
        System.out.println(p.getName());
        System.out.println(p.getAge());
        System.out.println(p.getEmail());
    }
}
