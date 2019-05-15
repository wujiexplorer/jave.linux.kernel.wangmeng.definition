package com.wangmeng.bank;

/**
 * User:wangmeng
 * Date:2019/5/15
 * Time:16:54
 * Verision:2.x
 * Description:TODO
 **/
import java.util.Arrays;

public class BanksTest {
    // 用于存储预操作后的资源变化
    static int[] new_Avaliable = null;
    // 用于存储预操作的完成度
    static boolean[] new_finish = null;
    // 用于保存最终的进程执行顺序,初始化为非法进程-1
    static int right[] = { -1, -1, -1, -1, -1 };

    public static void main(String[] args) {
        // 最大需求量
        int[][] max = { { 7, 5, 3 }, { 3, 2, 2 }, { 9, 0, 2 }, { 2, 2, 2 }, { 4, 3, 3 } };
        // 当前系统可用资源量
        int[] avaliable = { 3, 3, 2 };
        // 每个进程运行还需资源量
        int[][] need = new int[5][3];
        // 每个进程已分配的资源量
        int[][] allocation = { { 0, 1, 0 }, { 2, 0, 0 }, { 3, 0, 2 }, { 2, 1, 1 }, { 0, 0, 2 } };
        // 用于第一深度预判的初始化
        boolean finish[] = { false, false, false, false, false };
        // 获取每个进程运行时还需的资源量
        for (int i = 0; i < max.length; i++) {
            for (int j = 0; j < max[i].length; j++) {
                need[i][j] = max[i][j] - allocation [i][j];
            }
        }
        // 创建递归深度
        int deep = 0;
        // 调用回溯递归算法
        deepCheck(avaliable, allocation, need, finish, deep, right);
        int i = 0;
        // 查看最终的安全序列的值，看是否存在初始的非法进程，如果存在，则说明该案例不存在安全的进程执行顺序
        for (; i < right.length; i++) {
            if (right[i] == -1) {
                break;
            }
        }
        if (i < right.length) {
            System.out.println("该案例不存在安全的进程执行顺序");
            return;
        }
        // 打印安全的执行顺序
        for (int j = 0; j < right.length; j++) {
            System.out.println(right[j]);

        }

    }

    // 完全递归回溯查找安全顺序
    public static boolean deepCheck(int[] avaliable, int[][] allocation, int[][] need, boolean finish[], int deep,
                                    int right[]) {
        int j = 0;
        boolean flog = false;
        // 如果深度为进程的个数数说明已经查找到头了,说明上一深度的进程是安全节点。因为上一深度的进程满足了当前资源数大于或等于该进程运行所需的资源数，且为安全序列中最后一个节点。
        if (deep == need.length) {
            return true;
        }
        // 遍历所有节点进程开始查找,直到找到安全校验成功的的节点进程
        for (int i = 0; i < need.length; i++) {
            // 对于未被标记的进行校验，已被标记的为已被列为安全节点所以无需再进行校验
            if (!finish[i]) {
                // 判断当前的节点进程的剩余的资源量,是否满足运行所需的资源量
                for (j = 0; j < avaliable.length; j++) {
                    // 不满足
                    if (need[i][j] > avaliable[j]) {
                        break;
                    }
                }
                // 不满足则处理下一个节点进程
                if (j < avaliable.length) {
                    continue;
                } else {
                    // 满足情况
                    // 复制会被修改的前提条件，已便于当前进程校验不成功时，可以恢复前提条件，开始下一个节点进程的校验
                    new_Avaliable = Arrays.copyOf(avaliable, avaliable.length);
                    new_finish = Arrays.copyOf(finish, finish.length);
                    // 假设当前节点进程是可以校验成功的节点进程，修改该进程运行完毕后释放之前分配的进程。
                    for (j = 0; j < new_Avaliable.length; j++) {
                        new_Avaliable[j] += allocation[i][j];
                    }
                    // 假设标记当前为校验成功的安全节点进程，下一深度查找时会忽略此进程。
                    new_finish[i] = true;
                    // 增加深度
                    deep++;
                    // 以上假设为前提，进行下一深度的安全校验判断其他所剩余进程是否可以继续运行，而不造成死锁。
                    flog = deepCheck(new_Avaliable, allocation, need, new_finish, deep, right);
                    // 如果进行安全校验后为真，说明当前进程是我们要找的进程
                    if (flog) {
                        // 保存到最终进程执行序列的数组中
                        right[--deep] = i;
                        break;
                    }

                }

            }

        }
        // 安全校验成功
        if (flog) {
            return true;
        } else {
            // 安全校验失败
            // 清楚之前的假设标记
            new_finish[right[--deep]] = false;
            return false;
        }

    }
}
