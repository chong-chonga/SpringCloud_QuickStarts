package com.huanglexin.hutool;

import cn.hutool.crypto.SecureUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author LeXin Huang
 * @date 2021年05月07日 9:14
 */
@DisplayName(value = "Hutool的加密模块测试")
public class EncryptionModuleTest {

    @Test
    @DisplayName(value = "测试md5算法加密")
    public void testMD5() {
        String md5Name = SecureUtil.md5("悠一木碧1");
        System.out.println(md5Name);
    }

    @Test
    public void testIterator() {
        List<Integer> integers = new ArrayList<>();
        integers.add(12);
        integers.add(13);
        ListIterator<Integer> integerListIterator = integers.listIterator();
        while(integerListIterator.hasNext()) {
            System.out.println("integerListIterator.next() = " + integerListIterator.next());
        }
    }
}
