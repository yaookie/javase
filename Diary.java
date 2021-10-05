package test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Diary {
    static String message = "";
    static String filePath;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("1.新建日记 2.打开日记 3.修改日记 4.保存日记 5.退出");//提示操作

        while (true) {
            System.out.print("请输入指令：");//提示用户
            int command = sc.nextInt();
            switch (command) {
                case 1:
                    createDiary();//创建日记
                    break;

                case 2:
                    try {
                        openDiary();//打开日记
                        break;
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                case 3:
                    try {
                        modifyDiary();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }//修改日记
                    break;

                case 4:
                    try {
                        saveDiary();//保存日记
                        break;
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                case 5:
                    exit();//退出程序
                    break;

                default:
                    System.out.println("请输入1-5之间的数字！");
            }
        }
    }

    //创建日记
    public static void createDiary() {
        message = "";//清空暂存日记
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入内容，停止请输入stop：");//提示用户
        StringBuffer sb = new StringBuffer();
        String inputMessage = "";
        while (!inputMessage.equals("stop")) {
            if (sb.length() > 0) {
                sb.append("\r\n");
            }
            sb.append(inputMessage);//拼接输入日记
            inputMessage = sc.nextLine();//接收输入日记
        }
        message = sb.toString();//暂存已输入的日记
        System.out.println("新建日记还未保存，请输入4进行保存！");
    }

    //打开日记
    public static void openDiary() throws IOException {
        message = "";//清空暂存日记
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入需打开文件的位置：");
        filePath = sc.nextLine();//获取要打开文件的路径
        if (filePath != null && !filePath.endsWith(".txt")) {
            System.out.println("错误的文件类型");//只能打开文本文件
            return;
        }
        FileReader in = new FileReader(filePath);//创建FileReader对象
        char[] chars = new char[1024];
        int readCount;
        StringBuffer sb = new StringBuffer();
        //循环读取，一次读取一个字符数组
        while ((readCount = in.read(chars)) != -1) {
            sb.append(chars);
        }
        message = sb.toString();//暂存已输入的日记
        System.out.println(message);
        in.close();//关闭
    }

    //修改日记
    public static void modifyDiary() throws IOException {
        //判断是否有文件
        if (message == "" && filePath == null) {
            System.out.println("请先新建或打开文件");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改的内容：（格式：“需修改的文字-修改后的文字”,输入stop停止修改）");
        String inputMessage = "";
        while (!inputMessage.equals("stop")) {//输入stop，停止修改
            inputMessage = sc.nextLine();
            if (inputMessage != null && inputMessage.length() > 0) {
                String[] modify = inputMessage.split("-");//将输入的内容根据“-”拆分成数组
                if (modify != null && modify.length > 1) {
                    message = message.replace(modify[0], modify[1]);//替换内容
                }
            }
        }
        System.out.println("修改后的内容：" + message);
        saveDiary();//调用saveDiary()方法保存文件
    }

    //保存日记
    public static void saveDiary() throws IOException {
        Scanner sc = new Scanner(System.in);
        //使用字符输出流
        FileWriter out = null;
        if (filePath != null) {
            out = new FileWriter(filePath);//覆盖原文件
        } else {
            System.out.println("请输入要保存文件的绝对路径：");
            String path = sc.nextLine();//获取路径
            filePath =path;
            if (!filePath.toLowerCase().endsWith(".txt")) {
                filePath += ".txt";
            }
            out = new FileWriter(filePath);
        }
        out.write(message);//写入
        out.close();//关闭
        message = "";
        System.out.println("保存成功！保存路径为" + filePath);//提示保存成功
        filePath = null;
    }

    //退出
    public static void exit() {
        System.out.println("感谢使用，已退出！");//提示退出程序
        System.exit(0);//退出JVM的方法
    }
}

