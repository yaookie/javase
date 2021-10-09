//创建单链表
public class SingleLinkedList {
    public static void main(String[] args) {
        //创建手机节点
        PhoneNode p1 = new PhoneNode(01, "小米");
        PhoneNode p2 = new PhoneNode(02, "华为");
        PhoneNode p3 = new PhoneNode(03, "苹果");
        PhoneNode p4 = new PhoneNode(04, "魅族");

        //按顺序向单链表插入节点
        PhoneList  phoneList = new PhoneList();
        phoneList.addByOrder((p1));
        phoneList.addByOrder((p4));
        phoneList.addByOrder((p3));
        phoneList.addByOrder((p2));
        phoneList.list();

        //修改节点
        phoneList.update(new PhoneNode(04, "小辣椒"));
        System.out.println("修改之后的链表为：");
        phoneList.list();

        //删除节点
        phoneList.delete(01);
        System.out.println("删除后的链表为：");
        phoneList.list();
    }
}

class PhoneList {
    PhoneNode headNode = new PhoneNode(0, "");
    //向链表中添加节点
    //不考虑顺序添加
    public void add(PhoneNode phoneNode) {
        PhoneNode tempNode = headNode; //定义临时节点
        while (true) {
            if (tempNode.next == null) { //节点的next指向null时，表示到了链表最后，退出循环
                break;
            }
            tempNode = tempNode.next; //
        }
        tempNode.next = phoneNode;
    }

    //按顺序向单链表插入节点
    public void addByOrder(PhoneNode phoneNode) {
        PhoneNode tempNode = headNode;
        boolean flag = false;
        while (true) {
            if (tempNode.next == null) {
                break;
            }

            if (tempNode.next.no > phoneNode.no) {
                break;
            } else if (tempNode.next.no == phoneNode.no) {
                flag = true;
                break;
            }
            tempNode = tempNode.next;
        }
        if (flag) {
            System.out.println("插入的编号" + phoneNode.no + "已存在！");
        } else {
            phoneNode.next = tempNode.next;
            tempNode.next = phoneNode;
        }
    }

    public void update(PhoneNode updatePhoneNode) {
        if (headNode.next == null) {
            System.out.println("链表为空！");
            return;
        }

        PhoneNode tempNode = headNode.next;
        boolean flag = false;
        while (true) {
            if (tempNode == null) {
                break;
            }

            if (tempNode.no == updatePhoneNode.no) {
                flag = true;
                break;
            }
            tempNode = tempNode.next;
        }

        if (flag) {
            tempNode.brand = updatePhoneNode.brand;
        } else {
            System.out.println("没有找到编号" + updatePhoneNode.no + "的节点。");
        }
    }

    public void delete(int no) {
        //创建一个辅助节点
        PhoneNode tempNode = headNode;
        boolean flag = false;
        while (true) {
            if (tempNode.next == null) { //遍历到最后
                break;
            }
            if (tempNode.next.no == no) { //找到要删除的节点
                flag = true;
                break;
            }
            tempNode = tempNode.next;
        }
        if (flag) {
            //
            tempNode.next = tempNode.next.next;
        } else {
            //当没找到时输出
            System.out.println("未找到编号为" + no + "的节点，请再确认一下。");
        }
    }

    //显示链表
    public void list() {
        //判断链表是否为空
        if (headNode.next == null) {
            System.out.println("链表为空！");
            return;
        }
        PhoneNode tempNode = headNode.next;
        while (true) {
            if (tempNode == null) {
                break;
            }
            System.out.println(tempNode);
            tempNode = tempNode.next;
        }
    }
}

class PhoneNode {
    public int no;
    public String brand;
    public PhoneNode next;

    public PhoneNode(int no, String brand) {
        this.no = no;
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "PhoneNode{" +
                "no=" + no +
                ", brand='" + brand + '\'' +
                '}';
    }
}