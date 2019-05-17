package com.doing.compare.test;

import com.doing.compare.TreeNode;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class JsonTree {

    static TreeNode rootNode;
    static int selfId = 1;

    public static String beanToJSONString(Object bean) {
        return new Gson().toJson(bean);
    }

    public static Object JSONToObject(String json,Class beanClass) {
        Gson gson = new Gson();
        Object res = gson.fromJson(json, beanClass);
        return res;
    }

    static JsonArray stringToArray(String jstring){
        JsonParser parser = new JsonParser();
        JsonArray Jarray = parser.parse(jstring).getAsJsonArray();

        return Jarray;
    }

    static void initTreeNode(TreeNode node) {
        if (node == null) {
            node = new TreeNode();
            node.setNodeName("ROOT");
            node.setSelfId(0);
        }
    }

    static TreeNode jsonToTree(TreeNode parentNode, JsonObject jsonObject) {
//        initTreeNode(parentNode);
        if (parentNode == null) {
            rootNode = new TreeNode();
            rootNode.setNodeName("ROOT");
            rootNode.setSelfId(0);
            parentNode = rootNode;
        }
//        parentNode.setSelfId(selfId);
//        rootNode = parentNode;
//        TreeNode treeNode = new TreeNode();
//        treeNode.setNodeName("ROOT");
//        treeNode.setSelfId(0);
//        int selfId = 1;
        Iterator iterator = jsonObject.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry)iterator.next();
            System.out.println("key:" + entry.getKey() + "  value:" + entry.getValue());
            //新建 键 结点
            TreeNode treeKeyNode = new TreeNode();
            treeKeyNode.setNodeName(entry.getKey().toString());
            treeKeyNode.setSelfId(selfId);
            selfId ++;

            //新建 键 的子树  树可以是叶子节点 | 树
            TreeNode treeValueNode = new TreeNode();
            //下面这行 要对  value  分类讨论
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(entry.getValue().toString());

            if (jsonElement.isJsonObject()){
                jsonToTree(treeKeyNode, (JsonObject)jsonElement);

            } else if (jsonElement.isJsonArray()){
                arrayToTree(treeKeyNode,(JsonArray)jsonElement);
            } else {
                treeValueNode.setNodeName(entry.getValue().toString());
                treeValueNode.setSelfId(selfId);
                selfId ++;
                treeKeyNode.addChildNode(treeValueNode);
                treeValueNode.setParentNode(treeKeyNode);

                parentNode.addChildNode(treeKeyNode);
                treeKeyNode.setParentNode(parentNode);

                System.out.println(treeKeyNode.getNodeName() + " treeKeyNode parent id:" +treeKeyNode.getParentNode().getSelfId());
                System.out.println(treeValueNode.getNodeName() + " treeValueNode parent id:" +treeValueNode.getParentNode().getSelfId());
                System.out.println(treeValueNode.getNodeName() + " treeKeyNode current id:" +treeKeyNode.getSelfId());
                System.out.println(treeValueNode.getNodeName() + " treeValueNode current id:" +treeValueNode.getSelfId());
                System.out.println("---------");

            }

        }
//        rootNode.traverse();
        return parentNode;
    }

    static TreeNode arrayToTree(TreeNode parentNode,JsonArray jsonArray) {
        if (jsonArray.size() == 0){
            return null;
        }
        for (int i =0;i<jsonArray.size();i++){
            TreeNode arrayNode = new TreeNode();
            arrayNode.setNodeName(parentNode.getNodeName() + i);
            arrayNode.setSelfId(selfId);
            selfId ++;
            parentNode.addChildNode(arrayNode);
            arrayNode.setParentNode(parentNode);
            JsonObject element = (JsonObject) jsonArray.get(i);
            TreeNode jNode = jsonToTree(arrayNode, element);
            arrayNode.addChildNode(jNode);
            jNode.setParentNode(arrayNode);
        }
        return parentNode;
    }

    public static void main(String[] args) {
//        String actual = "{\n" +
//                "      \"id\": 50,\n" +
//                "      \"name\": \"王勇\",\n" +
//                "      \"sex\": 1,\n" +
//                "      \"birth\": \"1918-01-31T16:00:00.000+0000\",\n" +
//                "      \"address\": \"北京市辖区东城区\",\n" +
//                "      \"phone\": \"BDF0D3076621C3B6EB932F2057F741F2\",\n" +
//                "      \"create_time\": \"2018-05-28T21:30:22\",\n" +
//                "      \"userid\": 1,\n" +
//                "      \"relation\": null\n" +
//                "    }";

        String actual = "{\n" +
                "\tid:1,\n" +
                "\tname:\"李四\",\n" +
                "\tsex:\"男\",\n" +
                "\tcar:{\n" +
                "\t\tname: \"SUV\",\n" +
                "\t\tcap:5,\n" +
                "\t\tprice:200099\n" +
                "\t}\n" +
                "}";
        String array = "{\n" +
                "\tid:2,\n" +
                "\tname:\"Lukus\",\n" +
                "\tfamilies:[\n" +
                "\t\t{\n" +
                "\t\t\tname:\"Smith\",\n" +
                "\t\t\tsex:\"boy\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\tname:\"Tom\",\n" +
                "\t\t\tsex:\"boy\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\tname:\"Jenny\",\n" +
                "\t\t\tsex:\"girl\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
        String complex = "{\n" +
                "\tid:2,\n" +
                "\tname:\"Lukus\",\n" +
                "\tcar:{\n" +
                "\t\tname:\"SUV\",\n" +
                "\t\tcap:5,\n" +
                "\t\tprice:20596\n" +
                "\t},\n" +
                "\tfamilies:[\n" +
                "\t\t{\n" +
                "\t\t\tname:\"Smith\",\n" +
                "\t\t\tsex:\"boy\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\tname:\"Tom\",\n" +
                "\t\t\tsex:\"boy\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\tname:\"Jenny\",\n" +
                "\t\t\tsex:\"girl\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
        JsonObject jsonObject = new JsonParser().parse(complex).getAsJsonObject();
        Iterator iterator = jsonObject.entrySet().iterator();
        jsonToTree(rootNode,jsonObject);
        System.out.println("--------------***********------------");
        rootNode.traverse();
        System.out.println(jsonObject);

    }
}
