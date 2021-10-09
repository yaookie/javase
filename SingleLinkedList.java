//����������
public class SingleLinkedList {
    public static void main(String[] args) {
        //�����ֻ��ڵ�
        PhoneNode p1 = new PhoneNode(01, "С��");
        PhoneNode p2 = new PhoneNode(02, "��Ϊ");
        PhoneNode p3 = new PhoneNode(03, "ƻ��");
        PhoneNode p4 = new PhoneNode(04, "����");

        //��˳�����������ڵ�
        PhoneList  phoneList = new PhoneList();
        phoneList.addByOrder((p1));
        phoneList.addByOrder((p4));
        phoneList.addByOrder((p3));
        phoneList.addByOrder((p2));
        phoneList.list();

        //�޸Ľڵ�
        phoneList.update(new PhoneNode(04, "С����"));
        System.out.println("�޸�֮�������Ϊ��");
        phoneList.list();

        //ɾ���ڵ�
        phoneList.delete(01);
        System.out.println("ɾ���������Ϊ��");
        phoneList.list();
    }
}

class PhoneList {
    PhoneNode headNode = new PhoneNode(0, "");
    //����������ӽڵ�
    //������˳�����
    public void add(PhoneNode phoneNode) {
        PhoneNode tempNode = headNode; //������ʱ�ڵ�
        while (true) {
            if (tempNode.next == null) { //�ڵ��nextָ��nullʱ����ʾ������������˳�ѭ��
                break;
            }
            tempNode = tempNode.next; //
        }
        tempNode.next = phoneNode;
    }

    //��˳�����������ڵ�
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
            System.out.println("����ı��" + phoneNode.no + "�Ѵ��ڣ�");
        } else {
            phoneNode.next = tempNode.next;
            tempNode.next = phoneNode;
        }
    }

    public void update(PhoneNode updatePhoneNode) {
        if (headNode.next == null) {
            System.out.println("����Ϊ�գ�");
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
            System.out.println("û���ҵ����" + updatePhoneNode.no + "�Ľڵ㡣");
        }
    }

    public void delete(int no) {
        //����һ�������ڵ�
        PhoneNode tempNode = headNode;
        boolean flag = false;
        while (true) {
            if (tempNode.next == null) { //���������
                break;
            }
            if (tempNode.next.no == no) { //�ҵ�Ҫɾ���Ľڵ�
                flag = true;
                break;
            }
            tempNode = tempNode.next;
        }
        if (flag) {
            //
            tempNode.next = tempNode.next.next;
        } else {
            //��û�ҵ�ʱ���
            System.out.println("δ�ҵ����Ϊ" + no + "�Ľڵ㣬����ȷ��һ�¡�");
        }
    }

    //��ʾ����
    public void list() {
        //�ж������Ƿ�Ϊ��
        if (headNode.next == null) {
            System.out.println("����Ϊ�գ�");
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