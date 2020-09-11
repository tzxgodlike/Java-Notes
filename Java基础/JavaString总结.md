https://blog.csdn.net/qq_34490018/article/details/82110578
1.使用字符串常量池，每当我们使用字面量（String s=”1”;）创建字符串常量时，JVM会首先检查字符串常量池，如果该字符串已经存在常量池中，那么就将此字符串对象的地址赋值给引用s（引用s在Java栈中）。如果字符串不存在常量池中，就会实例化该字符串并且将其放到常量池中，并将此字符串对象的地址赋值给引用s（引用s在Java栈中）。
使用字符串常量池，每当我们使用关键字new（String s=new String(”1”);）创建字符串常量时，JVM会首先检查字符串常量池，如果该字符串已经存在常量池中，那么不再在字符串常量池创建该字符串对象，而直接堆中复制该对象的副本，然后将堆中对象的地址赋值给引用s，如果字符串不存在常量池中，就会实例化该字符串并且将其放到常量池中，然后在堆中复制该对象的副本，然后将堆中对象的地址赋值给引用s。 [方法区和堆都创建]
https://blog.csdn.net/AlphaWun/article/details/92383416?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task

1.字符串常量拼接
        String str3 = "Hello"+" word";
		String str4 = "Hello word";
		System.out.println(str3 == str4); //true
        上面是字符串常量拼接的例子：在编译时，JVM编译器对字符串做了优化，str3就被优化成“Hello word”，str3和str4指向字符串常量池同一个字符串常量，所以==比较为true
2.字符串常量+字符串变量、字符串变量之间的拼接
        String str5 = "Hello";
		String str6 = " word";
		String str7 = "Hello word";
		String str8 = str5+" word";
		System.out.println(str7 == str8);  //false
        String通过+号来拼接字符串的时候，如果有字符串变量参与,实际上底层会转成通过StringBuilder的append( )方法来实现，大致过程如下

        StringBuilder sb = new StringBuilder( );
		sb.append(str5);
		sb.append(" word");
		str8 = sb.toString();


https://blog.csdn.net/u012337114/article/details/81317992 Java String + 拼接字符串原理
String字符串拼接通过StringBuilder走中间过程，通过append方法实现
null拼接会变成字符串"null"
程序有大量字符串拼接时，建议考虑直接写StringBuilder实现，就不需要底层new很多临时sb对象了。因为直接String + 会产生很多个sb对象 不如直接用一个 调用方法就行 