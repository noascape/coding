package Customer;

import Database.*;
import java.util.Arrays;
import java.util.List;

public class TestData {

    public static List<Item> getTestData1() {
        return Arrays.asList(
                new Item("DE Bottle | 1L", "xvjix0xaue", RecyclingType.MULTI_USE, MaterialType.GLASS, 0.75),
                new Item("Z Bottle | 0.33L", "j03eiqtm2t", RecyclingType.SINGLE_USE, MaterialType.PLASTIC, null),
                new Item("ABC Can | 0.5L", "tmvkrw69le", RecyclingType.SINGLE_USE, MaterialType.METAL, 0.25),
                new Item("FG Bottle | 1L", "8hgij9rqv5", RecyclingType.MULTI_USE, MaterialType.PLASTIC, 2.00),
                new Item("X Can | 0.5L", "3a6mr6o7sl", RecyclingType.SINGLE_USE, MaterialType.METAL, null),
                new Item("DE Bottle | 0.75L", "4xpokcvb7c", RecyclingType.MULTI_USE, MaterialType.GLASS, 0.50)
        );
    }

    public static List<Item> getTestData2() {
        return Arrays.asList(
                new Item("DE Bottle | 0.33L", "bzfi339nsy", RecyclingType.MULTI_USE, MaterialType.GLASS, 0.25),
                new Item("Z Bottle | 0.33L", "j03eiqtm2t", RecyclingType.SINGLE_USE, MaterialType.PLASTIC, null),
                new Item("DE Bottle | 0.5L", "3jdwml7w52", RecyclingType.MULTI_USE, MaterialType.GLASS, 0.30),
                new Item("Y Bottle | 1L", "5hqoa0ean4", RecyclingType.SINGLE_USE, MaterialType.GLASS, null),
                new Item("FG Bottle | 0.5L", "js92hp13rp", RecyclingType.MULTI_USE, MaterialType.PLASTIC, 1.00),
                new Item("ABC Can | 0.33L", "r8yz7clkz4", RecyclingType.SINGLE_USE, MaterialType.METAL, 0.25),
                new Item("Y Bottle | 0.5L", "447ds7u9j4", RecyclingType.SINGLE_USE, MaterialType.GLASS, null)
        );
    }

    public static List<Item> getTestData3() {
        return Arrays.asList(
                new Item("ABC Can | 0.5L", "tmvkrw69le", RecyclingType.SINGLE_USE, MaterialType.METAL, 0.25),
                new Item("DE Bottle | 1L", "xvjix0xaue", RecyclingType.MULTI_USE, MaterialType.GLASS, 0.75),
                new Item("Z Bottle | 1L", "k29msl0zrq", RecyclingType.SINGLE_USE, MaterialType.PLASTIC, null),
                new Item("DE Bottle | 0.75L", "4xpokcvb7c", RecyclingType.MULTI_USE, MaterialType.GLASS, 0.50),
                new Item("Y Bottle | 0.33L", "m2wepvb8rq", RecyclingType.SINGLE_USE, MaterialType.GLASS, null),
                new Item("FG Bottle | 0.5L", "3a6mr6o7sl", RecyclingType.MULTI_USE, MaterialType.PLASTIC, 1.00),
                new Item("Z Bottle | 0.33L", "j03eiqtm2t", RecyclingType.SINGLE_USE, MaterialType.PLASTIC, null)
        );
    }

    public static List<Item> getTestData4() {
        return Arrays.asList(
                new Item("DE Bottle | 0.5L", "3jdwml7w52", RecyclingType.MULTI_USE, MaterialType.GLASS, 0.30),
                new Item("ABC Can | 0.33L", "r8yz7clkz4", RecyclingType.SINGLE_USE, MaterialType.METAL, 0.25),
                new Item("X Can | 0.5L", "8hnq9sovmq", RecyclingType.SINGLE_USE, MaterialType.METAL, null),
                new Item("FG Bottle | 1L", "8hgij9rqv5", RecyclingType.MULTI_USE, MaterialType.PLASTIC, 2.00),
                new Item("Z Bottle | 0.5L", "9ofn9sovmp", RecyclingType.SINGLE_USE, MaterialType.PLASTIC, null),
                new Item("DE Bottle | 1L", "xvjix0xaue", RecyclingType.MULTI_USE, MaterialType.GLASS, 0.75),
                new Item("Y Bottle | 1L", "5hqoa0ean4", RecyclingType.SINGLE_USE, MaterialType.GLASS, null),
                new Item("ABC Can | 0.5L", "tmvkrw69le", RecyclingType.SINGLE_USE, MaterialType.METAL, 0.25)
        );
    }

    public static List<Item> getTestData5() {
        return Arrays.asList(
                new Item("ABC Can | 0.33L", "r8yz7clkz4", RecyclingType.SINGLE_USE, MaterialType.METAL, 0.25),
                new Item("Z Bottle | 0.33L", "j03eiqtm2t", RecyclingType.SINGLE_USE, MaterialType.PLASTIC, null),
                new Item("FG Bottle | 1L", "8hgij9rqv5", RecyclingType.MULTI_USE, MaterialType.PLASTIC, 2.00),
                new Item("DE Bottle | 1L", "xvjix0xaue", RecyclingType.MULTI_USE, MaterialType.GLASS, 0.75),
                new Item("Y Bottle | 0.5L", "447ds7u9j4", RecyclingType.SINGLE_USE, MaterialType.GLASS, null),
                new Item("DE Bottle | 0.5L", "3jdwml7w52", RecyclingType.MULTI_USE, MaterialType.GLASS, 0.30)
        );
    }

    public static List<Item> getTestData6() {
        return Arrays.asList(
                new Item("DE Bottle | 0.33L", "bzfi339nsy", RecyclingType.MULTI_USE, MaterialType.GLASS, 0.25),
                new Item("FG Bottle | 0.5L", "js92hp13rp", RecyclingType.MULTI_USE, MaterialType.PLASTIC, 1.00),
                new Item("X Can | 0.5L", "8hnq9sovmq", RecyclingType.SINGLE_USE, MaterialType.METAL, null),
                new Item("Y Bottle | 1L", "5hqoa0ean4", RecyclingType.SINGLE_USE, MaterialType.GLASS, null),
                new Item("DE Bottle | 0.75L", "4xpokcvb7c", RecyclingType.MULTI_USE, MaterialType.GLASS, 0.50),
                new Item("FG Bottle | 1L", "8hgij9rqv5", RecyclingType.MULTI_USE, MaterialType.PLASTIC, 2.00),
                new Item("Z Bottle | 1L", "k29msl0zrq", RecyclingType.SINGLE_USE, MaterialType.PLASTIC, null)
        );
    }

    public static List<Item> getTestData7() {
        return Arrays.asList(
                new Item("ABC Can | 0.5L", "tmvkrw69le", RecyclingType.SINGLE_USE, MaterialType.METAL, 0.25),
                new Item("Z Bottle | 0.33L", "j03eiqtm2t", RecyclingType.SINGLE_USE, MaterialType.PLASTIC, null),
                new Item("FG Bottle | 0.5L", "js92hp13rp", RecyclingType.MULTI_USE, MaterialType.PLASTIC, 1.00),
                new Item("DE Bottle | 1L", "xvjix0xaue", RecyclingType.MULTI_USE, MaterialType.GLASS, 0.75),
                new Item("X Can | 0.5L", "8hnq9sovmq", RecyclingType.SINGLE_USE, MaterialType.METAL, null),
                new Item("ABC Can | 0.33L", "r8yz7clkz4", RecyclingType.SINGLE_USE, MaterialType.METAL, 0.25),
                new Item("Y Bottle | 0.5L", "447ds7u9j4", RecyclingType.SINGLE_USE, MaterialType.GLASS, null)
        );
    }

    public static List<Item> getTestData8() {
        return Arrays.asList(
                new Item("Y Bottle | 1L", "5hqoa0ean4", RecyclingType.SINGLE_USE, MaterialType.GLASS, null),
                new Item("ABC Can | 0.5L", "tmvkrw69le", RecyclingType.SINGLE_USE, MaterialType.METAL, 0.25),
                new Item("DE Bottle | 0.5L", "3jdwml7w52", RecyclingType.MULTI_USE, MaterialType.GLASS, 0.30),
                new Item("X Can | 0.5L", "8hnq9sovmq", RecyclingType.SINGLE_USE, MaterialType.METAL, null),
                new Item("FG Bottle | 0.5L", "js92hp13rp", RecyclingType.MULTI_USE, MaterialType.PLASTIC, 1.00),
                new Item("DE Bottle | 1L", "xvjix0xaue", RecyclingType.MULTI_USE, MaterialType.GLASS, 0.75)
        );
    }
}

