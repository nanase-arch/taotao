package com.taotao.test;

import org.junit.jupiter.api.Test;

public class MyTest {
    @Test
    public void show(){
      String str = "http://192.168.0.175/2020-08-13/4KJFHF8KB62GEZMADFO62XG4PEXVE0LS.png,\n" +
              "http://192.168.0.175/2020-08-13/20SFDTJUF43TMWDF11Z750ZFD00OVY24.png,\n" +
              "http://192.168.0.175/2020-08-13/31WGOVY59PYPMNEBK7FDOIZD6NHTUZY0.png,\n" +
              "http://192.168.0.175/2020-08-13/M8D9DIFQC3F46ODXCLDH879U8507N0H1.png";
      String[] split = str.split(",");
      System.out.println(split);
    }
}
