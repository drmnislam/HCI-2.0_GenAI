﻿# Heaps

## 什么是Heaps?
Heaps，堆又称优先队列（Priority Queues），由此可知，堆就是元素是具有优先级的队列。
应用场景：很多啦，比如操作系统调度程序，在选择下一个运行进程时，最简单的就是采用队列每个进程运行固定的时间片。但是对于一些本来能很快运行结束的进程要等前面的都跑一遍才能被处理，就不是很机智。这时就应该给队列中的短作业较高的优先级，优先被选择处理。堆就是用于处理类似这种场景的数据结构。

堆（最小堆）的基本接口

```
public interface MinHeap<T extends Comparable<? super T>> {
    void insert(T item);
    T deleteMin();
    boolean isEmpty();
}

```

## Heaps的实现
* 简单链表
    * 一种是在表头以O(1)执行插入操作，O(N)遍历链表执行删除最小元素操作
    * 另一种始终保持链表是排好序的，即插入操作为O(N)，删除最小元操作为O(1)。由于删除操作总是比插入要少一些，所以这种方法还不如第一种_(:з」∠)_

* 二叉查找树：插入和删除最小元都是O(logN)。然而，
    1. 由于每次操作都只删除最小元，左右子树会失去平衡。但其实最坏情况也就是左边为空，右边为应有数量的两倍，而且通过使用平衡树还能得到改善。
    2. 二叉查找树支持太多多余的操作

综上，链表呢不合适，而二叉查找树可以用但是又不那么精简，所以我们引入新的更好的选择——二叉堆（Binary Heap以下简称堆）。

### 堆的结构性质
* 堆是一棵完全二叉树，它的高是O(logN）的
* 完全二叉树的特别之处使得它可以采用数组保存，我们从下标1开始存放数据，那么对于位置i上的元素，它的子节点为位置2i和2i+1，它的父亲在i/2位置上。
    * 位置0
    * 堆数组的大小，需要事先估计，必要时要重新调整大小
![二叉堆及其数组表示][1]

### 堆的堆序性质
在一个堆中，对于每一个节点X，X的父亲中的关键字小于等于X中的关键字。因此，最小元总是可以再根处找到。


## 基本堆操作
## 插入操作 - 上滤(percolate up)
1. 将要插入的元素X放在下一个可用位置（即最后）
2. 若此时堆序结构未被破坏，即X的关键字大于等于其父亲的关键字，插入完成
3. 否则，将X与其父节点元素对调
4. 继续第三步直到X的插入能够满足堆序性质
![堆-上滤][2]


## 删除最小元操作 - 下滤(percolate down)
1. 将最小元移除，即在根部得到一个空穴
2. 选出空穴子节点中较小的一个移入空穴
3. 重复第二步直到堆中的最后一个元素X可以放入空穴

* 这里需要考虑的一点是，若遇到一个节点只有一个儿子的情况，要加以判断。
* 可以考虑一种取巧的方式，假设每个节点都有两个儿子。为了实施这种解法，当堆的大小为偶数时，在每个下滤开始时，可将值大于堆中任何元素的标记放在堆最后的位置上。

![堆-下滤][3]

## 堆的构建
当二叉堆的初始值由N项作为输入，通过使用N个插入操作来完成。可以证明这个操作的平均时间开销是O(N)的。

* 定理：包含2^(h+1)-1个节点，高度为h的理想二叉树的节点高度的和是2^(h+1)-1-(h+1)。
* 由以上定理可得插入N项时间复杂度为O(N)


  [1]: images/heap.jpg
  [2]: images/heap-percolateup.jpg
  [3]: images/heap-percolatedown-2.jpg